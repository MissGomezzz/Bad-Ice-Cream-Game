package persistence;

import domain.game.Level;
import domain.game.LevelLoader;
import java.io.*;
import java.util.*;

public class LevelImporter {
    //Log aplicado con ayuda de la IA
    private static final Set<Character> VALID_CHARACTERS = Set.of(
            'W', 'R', 'Y', 'I', 'H', 'L', 'S', 'F', 'P', 'G', 'B', 'C', 'N', 'T', 'M', 'O', 'V', '.', ' '
    );

    public static Level importFromFile(File file) throws BadIceException, IOException {
        StringBuilder log = new StringBuilder();
        log.append("Validando nivel: ").append(file.getName()).append("\n");

        if (!file.exists()) {
            log.append("ERROR: Archivo no encontrado\n");
            System.err.println(log);
            throw new BadIceException("El archivo no existe: " + file.getName());
        }

        if (!file.getName().endsWith(".txt")) {
            log.append("ERROR: Extensión inválida (requiere .txt)\n");
            System.err.println(log);
            throw new BadIceException("El archivo debe ser .txt");
        }

        try {
            validateLevelFile(file, log);
            log.append("Validación exitosa\n");
            System.out.println(log);
            return LevelLoader.loadFromFile(file, new ArrayList<>());

        } catch (BadIceException e) {
            log.append("FALLO: ").append(e.getMessage()).append("\n");
            System.err.println(log);
            throw e;
        }
    }

    private static void validateLevelFile(File file, StringBuilder log)
            throws BadIceException, IOException {

        List<String> lines = new ArrayList<>();
        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    log.append("Linea ").append(lineNumber).append(": vacia (ignorada)\n");
                }
                lines.add(line);
            }
        }

        List<String> nonEmptyLines = lines.stream()
                .filter(l -> !l.isBlank())
                .toList();

        if (nonEmptyLines.isEmpty()) {
            throw new BadIceException("Archivo vacío");
        }

        int expectedCols = nonEmptyLines.get(0).length();
        int expectedRows = nonEmptyLines.size();

        log.append("Dimensiones: ").append(expectedRows).append("x").append(expectedCols).append("\n");

        if (expectedRows < 10 || expectedCols < 10) {
            throw new BadIceException(
                    String.format("Dimensiones muy pequeñas: %dx%d (minimo 10x10)", expectedRows, expectedCols)
            );
        }

        for (int i = 0; i < nonEmptyLines.size(); i++) {
            String line = nonEmptyLines.get(i);
            if (line.length() != expectedCols) {
                throw new BadIceException(
                        String.format("Línea %d: esperaba %d caracteres, tiene %d",
                                i + 1, expectedCols, line.length())
                );
            }
        }

        Map<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < nonEmptyLines.size(); i++) {
            String line = nonEmptyLines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);

                if (!VALID_CHARACTERS.contains(ch)) {
                    throw new BadIceException(
                            String.format("Línea %d, columna %d: caracter invalido '%c'",
                                    i + 1, j + 1, ch)
                    );
                }
            }
        }

        int playerCount = charCount.getOrDefault('P', 0);
        if (playerCount == 0) {
            throw new BadIceException("Debe haber al menos un jugador (P)");
        }

        log.append("Jugadores: ").append(playerCount).append("\n");

        int totalFruits = charCount.getOrDefault('G', 0) +
                charCount.getOrDefault('B', 0) +
                charCount.getOrDefault('C', 0) +
                charCount.getOrDefault('N', 0);
        log.append("Frutas: ").append(totalFruits).append("\n");

        int totalEnemies = charCount.getOrDefault('T', 0) +
                charCount.getOrDefault('M', 0) +
                charCount.getOrDefault('O', 0) +
                charCount.getOrDefault('V', 0);
        log.append("Enemigos: ").append(totalEnemies).append("\n");
    }
}
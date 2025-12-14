package persistence;

import domain.game.Game;
import domain.game.PlayingState;
import java.io.*;

/**
 * Carga el estado guardado del juego desde un archivo .dat
 */
public class GameLoader {

    public static PlayingState load(Game game, File file) throws BadIceException, IOException {
        if (!file.exists()) {
            throw new BadIceException("El archivo no existe: " + file.getName());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            GameSaver.SaveData data = (GameSaver.SaveData) ois.readObject();

            // Crear nuevo PlayingState con los datos cargados
            PlayingState state = new PlayingState(game, data.levelNumber);

            state.setTimerTicks(data.timerTicks);
            state.getLevel().getPlayers().getFirst().setScore(data.playerScore);

            return state;

        } catch (ClassNotFoundException e) {
            throw new BadIceException("Formato de archivo incompatible", e);
        }
    }
}
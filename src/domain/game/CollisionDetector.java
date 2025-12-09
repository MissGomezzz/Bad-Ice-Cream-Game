package domain.game;

import domain.entities.*;
import domain.model.*;

import java.util.List;

/**
 * Maneja las interacciones entre entidades cuando colisionan
 */
public class CollisionDetector {

    /**
     * Bloques que bloquean el paso
     */
    public static boolean isBlocked(Board board, Position pos) {
        // Fuera del tablero = bloqueado
        if (!board.isInside(pos)) return true;

        // Bloqueado si la celda no es atravesable
        return !board.isWalkable(pos);
    }

    /**
     * Jugador + frutas: si están en la misma celda,
     * se marca la fruta como recogida y se suma el puntaje.
     */
    public static void checkPlayerFruit(List<Player> players, List<Fruit> fruits) {
        for (Player p : players) {
            for (Fruit f : fruits) {
                if (f.isCollected() || f.isFrozen()) continue;

                if (sameCell(p.getPosition(), f.getPosition())) {
                    f.collect();
                    p.addScore(f.getPoints());
                }
            }
        }
    }

    /**
     * Jugador + enemigos: si están en la misma celda,
     * se llama a la lógica de "golpe" del jugador.
     */
    public static void checkPlayerEnemy(List<Player> players, List<Enemy> enemies) {
        for (Player p : players) {
            for (Enemy e : enemies) {
                if (sameCell(p.getPosition(), e.getPosition())) {
                    p.onHitByEnemy(e);
                }
            }
        }
    }

    /**
     * Jugador + fogatas: si el jugador está en una fogata encendida, muere
     */
    public static void checkPlayerCampfire(List<Player> players, Board board) {
        for (Player p : players) {
            Position pos = p.getPosition();
            CellType cellType = board.getCellType(pos);

            if (cellType == CellType.CAMPFIRE_ON) {
                p.onHitByEnemy(null); // Eliminar al jugador
            }
        }
    }


    private static boolean intersects(Player p, Enemy e) {
        return sameCell(p.getPosition(), e.getPosition());
    }

    protected static boolean sameCell(Position a, Position b) {
        return a.getRow() == b.getRow() && a.getCol() == b.getCol();
    }
}

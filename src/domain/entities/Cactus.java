package domain.entities;

import domain.game.Level;
import domain.model.Position;
import java.util.List;

/**
 * Cactus: Fruta estática que alterna entre estado seguro y peligroso cada 30 segundos
 * - Estado seguro: Puede ser recolectado normalmente (250 puntos)
 * - Estado peligroso: Tiene púas que eliminan al jugador si lo toca
 */
public class Cactus extends Fruit {

    public static final int CACTUS_SCORE = 250;
    public static final Sprite SPRITE = new Sprite("/cactus.png");

    private static final int TICKS_PER_CYCLE = 1800; // 30 segundos a 60 FPS (30 * 60)

    private int tickCounter = 0;
    private boolean hasSpikesDangerous = false; // false = seguro, true = peligroso

    public Cactus(Position position) {
        super(position, CACTUS_SCORE, SPRITE);
    }

    @Override
    public int getPoints() {
        return CACTUS_SCORE;
    }

    /**
     * Actualiza el estado del cactus:
     * - Alterna entre seguro y peligroso cada 30 segundos
     * - Si tiene púas, elimina jugadores que lo toquen
     */
    @Override
    public void update(Level level) {
        // No actualizar si ya fue recolectado o está congelado
        if (isCollected() || isFrozen()) {
            return;
        }

        // Incrementar contador y cambiar estado cada 30 segundos
        tickCounter++;
        if (tickCounter >= TICKS_PER_CYCLE) {
            hasSpikesDangerous = !hasSpikesDangerous;
            tickCounter = 0;
        }

        // Si tiene púas, verificar colisión con jugadores
        if (hasSpikesDangerous) {
            checkPlayerCollision(level);
        }
    }

    /**
     * Verifica si algún jugador está en contacto con el cactus peligroso
     */
    private void checkPlayerCollision(Level level) {
        List<Player> players = level.getPlayers();

        for (Player player : players) {
            if (player.getPosition().equals(this.position)) {
                player.onHitByEnemy(null); // Elimina al jugador
            }
        }
    }

    /**
     * Solo permite recolectar el cactus cuando NO tiene púas
     */
    @Override
    public void collect() {
        if (!hasSpikesDangerous && !isCollected()) {
            super.collect();
        }
    }

    /**
     * @return true si el cactus tiene púas y es peligroso
     */
    public boolean isDangerous() {
        return hasSpikesDangerous && !isCollected();
    }

    /**
     * @return true si el cactus está seguro y puede ser recolectado
     */
    public boolean isSafe() {
        return !hasSpikesDangerous && !isCollected();
    }

    /**
     * @return Ticks restantes hasta el próximo cambio de estado (útil para UI)
     */
    public int getTicksUntilStateChange() {
        return TICKS_PER_CYCLE - tickCounter;
    }

    /**
     * @return Progreso del ciclo actual de 0.0 a 1.0 (útil para animaciones)
     */
    public float getCycleProgress() {
        return (float) tickCounter / TICKS_PER_CYCLE;
    }
}
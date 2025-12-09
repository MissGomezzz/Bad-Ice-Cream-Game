package domain.entities;

import domain.behavior.CherryMovement;
import domain.game.Level;
import domain.model.Position;

/**
 * Cereza: Fruta parcialmente estática que se teletransporta cada 20 segundos
 */
public class Cherry extends Fruit {

    public static final int CHERRY_SCORE = 150;
    public static final Sprite SPRITE = new Sprite("/cherry.png");

    private final CherryMovement behavior;

    public Cherry(Position position) {
        super(position, CHERRY_SCORE, SPRITE);
        this.behavior = new CherryMovement();
    }

    @Override
    public int getPoints() {
        return CHERRY_SCORE;
    }

    @Override
    public void update(Level level) {
        // Si ya fue recolectada o está congelada, no actualiza
        if (isCollected() || isFrozen()) {
            return;
        }

        // Delegar el comportamiento a la clase de teletransporte
        behavior.update(level, this);
    }

    // Métodos de conveniencia para acceder al behavior
    public int getTicksUntilTeleport() {
        return behavior.getTicksUntilTeleport();
    }

    public float getTeleportProgress() {
        return behavior.getTeleportProgress();
    }

    public int getSecondsUntilTeleport() {
        return behavior.getSecondsUntilTeleport();
    }
}
package domain.entities;

import domain.behavior.FruitMovementBehavior;
import domain.game.Level;
import domain.model.Position;

/**
 * Una fruta puede ser dinámica
 */
public abstract class MovingFruit extends Fruit {

    protected FruitMovementBehavior movementBehavior;

    public MovingFruit(Position position, int points, Sprite sprite,  FruitMovementBehavior movementBehavior) {
        super(position, points, sprite);
        this.movementBehavior = movementBehavior;
    }

    /**
     * Las frutas móviles actualizan su estado solo si:
     *  No están congeladas ni recogidas
     */
    @Override
    public void update(Level level) {
        if (!isCollected() && !isFrozen() && movementBehavior != null) {
            movementBehavior.move(level, this);
        }
    }
}

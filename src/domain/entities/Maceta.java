package domain.entities;

import domain.behavior.MovementBehavior;
import domain.model.Position;
import domain.utils.Direction;

/**
 * Establece la relación con clase Enemy y su comportamiento
 */
public class Maceta extends Enemy{
    public Maceta(Position position, Direction initialDirection, MovementBehavior movementBehavior) {
        super(position, initialDirection, movementBehavior);
    }
}

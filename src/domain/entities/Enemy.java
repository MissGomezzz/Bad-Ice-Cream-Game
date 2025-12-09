package domain.entities;

import domain.utils.Direction;
import domain.model.Position;
import domain.behavior.MovementBehavior;

/**
 * Establece las diferencias principales de un enemigo en comparación con una entidad.
 */
public class Enemy extends Entity{
    protected Direction direction;
    protected MovementBehavior movementBehavior;

    public Enemy (Position position, Direction initialDirection, MovementBehavior movementBehavior) {
        super(position);
        this.direction = initialDirection;
        this.movementBehavior = movementBehavior;
    }

    public Direction getDirection() {return this.direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public MovementBehavior getMovementBehavior() {return this.movementBehavior;}
}

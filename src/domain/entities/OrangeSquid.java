package domain.entities;

import domain.behavior.MovementBehavior;
import domain.model.Position;
import domain.utils.Direction;

public class OrangeSquid extends Enemy{
    public OrangeSquid(Position position, Direction initialDirection, MovementBehavior movementBehavior) {
        super(position, initialDirection, movementBehavior);
    }
}

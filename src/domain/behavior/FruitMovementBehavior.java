package domain.behavior;

import domain.entities.MovingFruit;
import domain.game.Level;

/**
 * Define cómo se mueve una fruta móvil en cada tick del juego.
 */
public interface FruitMovementBehavior {
    void move(Level level, MovingFruit fruit);
}

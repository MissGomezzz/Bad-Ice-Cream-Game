package domain.entities;

import domain.behavior.NarvalMovement;
import domain.model.Position;
import domain.utils.Direction;

/**
 * Enemigo que patrulla y embiste cuando detecta al jugador alineado
 * Recorre el espacio sin perseguir directamente
 * Si detecta un jugador alineado horizontal o verticalmente, embiste y destruye hielos
 */
public class Narval extends Enemy {

    public Narval(Position position, Direction initialDirection, NarvalMovement movement) {
        super(position, initialDirection, movement);
    }

    @Override
    public NarvalMovement getMovementBehavior() {
        return (NarvalMovement) super.getMovementBehavior();
    }
}

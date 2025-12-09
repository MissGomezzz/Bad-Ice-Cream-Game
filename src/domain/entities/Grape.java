package domain.entities;
import domain.model.Position;
import domain.game.Level;

/**
 * Fruta estática.
 */
public class Grape extends Fruit{
    private static final int GRAPE_SCORE = 50;
    private static final Sprite SPRITE = new Sprite("/grape.jpg");

    public Grape(Position position) {
        super(position, GRAPE_SCORE, SPRITE);
    }

    @Override
    // Las uvas son estáticas
    public void update(Level level) { }
    @Override
    public int getPoints() {return GRAPE_SCORE;}
}

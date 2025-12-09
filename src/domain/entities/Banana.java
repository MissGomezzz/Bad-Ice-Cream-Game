package domain.entities;
import domain.model.Position;
import domain.game.Level;

public class Banana extends Fruit{
    private static final int BANANA_SCORE = 100;
    private static final Sprite SPRITE = new Sprite("/banana.jpg");
    public Banana(Position position){
        super(position, BANANA_SCORE, SPRITE);
    }

    @Override
    // Los bananos son estáticos
    public void update(Level level) { }
    @Override
    public int getPoints() {return BANANA_SCORE;}
}

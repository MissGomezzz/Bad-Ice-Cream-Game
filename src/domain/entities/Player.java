package domain.entities;

import domain.game.Flavour;
import domain.model.Position;
import domain.utils.Direction;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class Player extends Entity {
    private int score = 0;
    private Direction direction = Direction.DOWN;

    private boolean dead = false;
    private int invulnerableTicks = 0;
    private static final int INVULNERABLE_TIME = 30;

    private static final AnimatedSprite VANILLA_SPRITE;
    private static final AnimatedSprite STRAWBERRY_SPRITE;
    private static final AnimatedSprite CHOCOLATE_SPRITE;

    private Flavour flavour = Flavour.VANILLA;

    static {
        VANILLA_SPRITE = buildSprite("vanilla");
        STRAWBERRY_SPRITE = buildSprite("strawberry");
        CHOCOLATE_SPRITE = buildSprite("chocolate");
    }

    private static AnimatedSprite buildSprite(String prefix) {
        Map<Direction, String> sprites = new EnumMap<>(Direction.class);
        sprites.put(Direction.UP, "/" + prefix + "-up.gif");
        sprites.put(Direction.DOWN, "/" + prefix + "-down.gif");
        sprites.put(Direction.LEFT, "/" + prefix + "-left.gif");
        sprites.put(Direction.RIGHT, "/" + prefix + "-right.gif");
        return new AnimatedSprite(sprites);
    }

    public Player(Position position) {
        super(position);
        setFlavour(Flavour.VANILLA);
    }

    public void setFlavour(Flavour flavour) {
        if (flavour == null) return;
        this.flavour = flavour;

        if (flavour == Flavour.STRAWBERRY) setAnimatedSprite(STRAWBERRY_SPRITE);
        else if (flavour == Flavour.CHOCOLATE) setAnimatedSprite(CHOCOLATE_SPRITE);
        else setAnimatedSprite(VANILLA_SPRITE);
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public void render(Graphics2D g, int tileSize) {
        if (animatedSprite != null) {
            int x = position.getCol() * tileSize;
            int y = position.getRow() * tileSize;
            animatedSprite.draw(g, x, y, tileSize, tileSize, direction);
        }
    }

    public Direction getDirection() { return this.direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public int getScore() { return score; }
    public void addScore(int pts) { this.score += pts; }
    public void setScore(int playerScore) { this.score = playerScore; }

    public boolean isDead() { return dead; }

    public void tick() {
        if (invulnerableTicks > 0) invulnerableTicks--;
    }

    public void onHitByEnemy(Entity e) {
        if (dead) return;
        if (invulnerableTicks > 0) return;

        dead = true;
        invulnerableTicks = INVULNERABLE_TIME;
    }
}

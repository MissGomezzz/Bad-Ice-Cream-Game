package domain.game;

import domain.entities.*;
import java.util.List;

public class LevelFactory {

    public static Level createLevel(int levelNumber) {
        return createLevel(levelNumber, GameMode.PVP);
    }

    public static Level createLevel(int levelNumber, GameMode mode) {
        Level level = switch (levelNumber) {
            case 2 -> createLevel2();
            case 3 -> createLevel3();
            default -> createLevel1();
        };

        if (mode == GameMode.PLAYER) {
            List<Player> players = level.getPlayers();
            while (players.size() > 1) {
                players.remove(1);
            }
        }

        return level;
    }

    private static Level createLevel1() {
        String path = "/maps/level1.txt";
        List<Class<? extends Fruit>> phases = List.of(
                Banana.class,
                Grape.class
        );
        return LevelLoader.loadFromResource(path, phases);
    }

    private static Level createLevel2() {
        String path = "/maps/level2.txt";
        List<Class<? extends Fruit>> phases = List.of(
                Banana.class,
                Pineapple.class
        );
        return LevelLoader.loadFromResource(path, phases);
    }

    private static Level createLevel3() {
        String path = "/maps/level3.txt";
        List<Class<? extends Fruit>> phases = List.of(
                Cactus.class,
                Cherry.class
        );
        return LevelLoader.loadFromResource(path, phases);
    }
}

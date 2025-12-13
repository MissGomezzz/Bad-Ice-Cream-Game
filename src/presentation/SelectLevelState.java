package presentation;

import domain.game.Game;
import domain.game.GameState;
import domain.game.PlayingState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Pantalla para seleccionar el nivel (1, 2 o 3).
 */
public class SelectLevelState implements GameState {

    private final Game game;
    private final int gameMode; // 0=PvP, 1=PvM, 2=MvM

    // Imágenes
    private Image backgroundGif;
    private Image buttonBackBg;
    private Image backButton;
    private Image playerBg;
    private Image levelSelect;
    private Image levelOne;
    private Image levelTwo;
    private Image levelThree;

    // Caja superior
    private final int topBoxX = 32;
    private final int topBoxY = 24;
    private final int topBoxWidth = 512;
    private final int topBoxHeight = 320;

    // Caja inferior
    private final int bottomBoxX = 32;
    private final int bottomBoxY = 360;
    private final int bottomBoxWidth = 512;
    private final int bottomBoxHeight = 160;

    // Botón BACK
    private final int backBtnWidth = 168;
    private final int backBtnHeight = 64;
    private final int backBtnX = bottomBoxX + (bottomBoxWidth - backBtnWidth) / 2;
    private final int backBtnY = bottomBoxY + (bottomBoxHeight - backBtnHeight) / 2;

    // Niveles
    private final int padding = 20;
    private final int levelBoxWidth = 114;
    private final int levelBoxHeight = 112;

    // Posiciones Y para todas las cajas de nivel
    private final int levelBoxY = topBoxY + (topBoxHeight - levelBoxHeight) / 2;

    // Posiciones X de cada nivel
    private final int level1BoxX = topBoxX + padding+50;
    private final int level2BoxX = topBoxX + padding + levelBoxWidth + padding+50;
    private final int level3BoxX = topBoxX + padding + (levelBoxWidth + padding) * 2+50;

    public SelectLevelState(Game game, int gameMode) {
        this.game = game;
        this.gameMode = gameMode;

        try {
            this.backgroundGif = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/home-animation.gif"))
            ).getImage();

            this.buttonBackBg = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/back-button-bg.jpg"))
            ).getImage();

            this.backButton = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/back-button.jpg"))
            ).getImage();

            this.playerBg = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/player-bg.jpg"))
            ).getImage();
            this.levelSelect = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/level-select.png"))
            ).getImage();
            this.levelOne = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/level-1.png"))
            ).getImage();
            this.levelTwo = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/level-2.png"))
            ).getImage();
            this.levelThree = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/level-3.png"))
            ).getImage();
        } catch (Exception e) {
            System.err.println("Error cargando recursos: " + e.getMessage());
        }
    }

    @Override
    public void render(Graphics2D g) {
        int width = GamePanel.WIDTH;
        int height = GamePanel.HEIGHT;

        // Fondo
        if (backgroundGif != null) {
            g.drawImage(backgroundGif, 0, 0, width, height, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        }

        // Fondo completo
        if (backgroundGif != null) {
            g.drawImage(backgroundGif, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }

        // Caja superior
        if (playerBg != null) {
            g.drawImage(playerBg, topBoxX, topBoxY, topBoxWidth, topBoxHeight, null);
        }

        // Título "Level select"
        if (levelSelect != null) {
            int titleWidth = 330;
            int titleHeight = 40;

            int titleX = topBoxX + (topBoxWidth - titleWidth) / 2;
            int titleY = topBoxY + 20;

            g.drawImage(levelSelect, titleX, titleY, titleWidth, titleHeight, null);
        }

        // Caja inferior BACK
        if (buttonBackBg != null) {
            g.drawImage(buttonBackBg, bottomBoxX, bottomBoxY, bottomBoxWidth, bottomBoxHeight, null);
        }

        // Botón BACK
        if (backButton != null) {
            g.drawImage(backButton, backBtnX, backBtnY, backBtnWidth, backBtnHeight, null);
        }
        //Niveles
        if (levelOne != null) {
            g.drawImage(levelOne, level1BoxX, levelBoxY, levelBoxWidth, levelBoxHeight, null);
        }
        if (levelTwo != null) {
            g.drawImage(levelTwo, level2BoxX, levelBoxY, levelBoxWidth, levelBoxHeight, null);
        }
        if (levelThree != null) {
            g.drawImage(levelThree, level3BoxX, levelBoxY, levelBoxWidth, levelBoxHeight, null);
        }
    }

    @Override
    public void mouseClicked(Integer x, Integer y) {
        // Click BACK
        if (x >= backBtnX && x <= backBtnX + backBtnWidth &&
                y >= backBtnY && y <= backBtnY + backBtnHeight) {

            game.setState(new ChooseFlavourState(game, gameMode));
            return;
        }
        // Nivel 1
        if (x >= level1BoxX && x <= level1BoxX + levelBoxWidth &&
                y >= levelBoxY && y <= levelBoxY + levelBoxHeight) {

            System.out.println("Seleccionaste Nivel 1");
            int levelNumber = 1;
            game.setState(new PlayingState(game, levelNumber));
            return;
        }
        // Nivel 2
        if (x >= level2BoxX && x <= level2BoxX + levelBoxWidth &&
                y >= levelBoxY && y <= levelBoxY + levelBoxHeight) {

            System.out.println("Seleccionaste Nivel 2");
            int levelNumber = 2;
            game.setState(new PlayingState(game, levelNumber));
            return;
        }
        // Nivel 3
        if (x >= level3BoxX && x <= level3BoxX + levelBoxWidth &&
                y >= levelBoxY && y <= levelBoxY + levelBoxHeight) {

            System.out.println("Seleccionaste Nivel 3");
            int levelNumber = 3;
            game.setState(new PlayingState(game, levelNumber));
        }
    }

    @Override
    public void keyPressed(Integer key) {}

    @Override
    public void update() {}

    @Override
    public void keyReleased(Integer keyCode) {}

}
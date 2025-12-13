package presentation;

import domain.game.Game;
import domain.game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ChooseFlavourState implements GameState {
    private final Game game;
    int optionSelected;

    // Imágenes
    private Image backgroundGif;
    private Image buttonBackBg;
    private Image backButton;
    private Image playerBg;
    private Image chooseFlavour;
    private Image threeIceCreams;

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

    // Imagen helados juntos
    private final int iceCreamWidth = 230;
    private final int iceCreamHeight = 230;
    private final int iceCreamX = 170;
    private final int iceCreamY = 90;


    public ChooseFlavourState(Game game, int optionSelected) {
        this.game = game;

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
            this.chooseFlavour = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/choose-flavour.png"))
            ).getImage();
            this.threeIceCreams = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/joined-icecreams.png"))
            ).getImage();

        } catch (Exception e) {
            System.err.println("Error cargando recursos: " + e.getMessage());
        }

    }
    @Override
    public void render(Graphics2D g) {

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

        // Caja inferior BACK
        if (buttonBackBg != null) {
            g.drawImage(buttonBackBg, bottomBoxX, bottomBoxY, bottomBoxWidth, bottomBoxHeight, null);
        }

        // Botón BACK
        if (backButton != null) {
            g.drawImage(backButton, backBtnX, backBtnY, backBtnWidth, backBtnHeight, null);
        }

        if (threeIceCreams != null) { {
            g.drawImage (threeIceCreams, iceCreamX, iceCreamY, iceCreamWidth, iceCreamHeight, null);
        }
        }

        // Título "Choose your flavour!"
        if (chooseFlavour != null) {
            int titleWidth = 330;
            int titleHeight = 40;

            int titleX = topBoxX + (topBoxWidth - titleWidth) / 2;
            int titleY = topBoxY + 20;

            g.drawImage(chooseFlavour, titleX, titleY, titleWidth, titleHeight, null);
        }


    }

    @Override
    public void keyPressed(Integer key) {
        if (key == KeyEvent.VK_ENTER) {
            game.setState(new SelectLevelState(game, optionSelected));
        }
    }

    @Override
    public void mouseClicked(Integer x, Integer y) {
        // Click BACK
        if (x >= backBtnX && x <= backBtnX + backBtnWidth &&
                y >= backBtnY && y <= backBtnY + backBtnHeight) {
            game.setState(new SelectModeState(game));
        }

        // Click sobre helados
        if (x >= iceCreamX && x <= iceCreamX + iceCreamWidth &&
                y >= iceCreamY && y <= iceCreamY + iceCreamHeight) {
            game.setState(new SelectLevelState(game, optionSelected));
        }

    }

    @Override
    public void update() {}

    @Override
    public void keyReleased(Integer keyCode) {}

}
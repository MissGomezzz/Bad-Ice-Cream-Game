package presentation;

import domain.game.AIProfile;
import domain.game.Flavour;
import domain.game.Game;
import domain.game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ChooseFlavourState implements GameState {

    private final Game game;
    private final int optionSelected;

    private final AIProfile aiProfileP1;
    private final AIProfile aiProfileP2;

    private Flavour flavourP1 = Flavour.VANILLA;
    private Flavour flavourP2 = Flavour.VANILLA;

    private int selectingIndex = 1;

    private Image backgroundGif;
    private Image buttonBackBg;
    private Image backButton;
    private Image playerBg;
    private Image chooseFlavour;
    private Image threeIceCreams;

    private Image vanillaIcon;
    private Image strawberryIcon;
    private Image chocolateIcon;

    private final int topBoxX = 32;
    private final int topBoxY = 24;
    private final int topBoxWidth = 512;
    private final int topBoxHeight = 320;

    private final int bottomBoxX = 32;
    private final int bottomBoxY = 360;
    private final int bottomBoxWidth = 512;
    private final int bottomBoxHeight = 160;

    private final int backBtnWidth = 168;
    private final int backBtnHeight = 64;
    private final int backBtnX = bottomBoxX + (bottomBoxWidth - backBtnWidth) / 2;
    private final int backBtnY = bottomBoxY + (bottomBoxHeight - backBtnHeight) / 2;

    private final int iceCreamWidth = 230;
    private final int iceCreamHeight = 230;
    private final int iceCreamX = 170;
    private final int iceCreamY = 90;

    private final int cardW = 140;
    private final int cardH = 55;
    private final int cardX = topBoxX + 40;
    private final int cardY = topBoxY + 95;
    private final int cardGap = 15;

    public ChooseFlavourState(Game game, int optionSelected) {
        this(game, optionSelected, null, null);
    }

    public ChooseFlavourState(Game game, int optionSelected, AIProfile aiProfileP1, AIProfile aiProfileP2, Flavour flavourP1, Flavour flavourP2) {
        this.game = game;
        this.optionSelected = optionSelected;
        this.aiProfileP1 = aiProfileP1;
        this.aiProfileP2 = aiProfileP2;

        this.flavourP1 = (flavourP1 != null) ? flavourP1 : Flavour.VANILLA;
        this.flavourP2 = (flavourP2 != null) ? flavourP2 : Flavour.VANILLA;

        selectingIndex = needsTwoFlavours() ? 1 : 1;
        loadAssets();
    }


    public ChooseFlavourState(Game game, int optionSelected, AIProfile aiProfileP1, AIProfile aiProfileP2) {
        this.game = game;
        this.optionSelected = optionSelected;
        this.aiProfileP1 = aiProfileP1;
        this.aiProfileP2 = aiProfileP2;

        selectingIndex = needsTwoFlavours() ? 1 : 1;
        loadAssets();
    }

    private boolean needsTwoFlavours() {
        return optionSelected == 1 || optionSelected == 2 || optionSelected == 3;
    }

    private void loadAssets() {
        try {
            backgroundGif = new ImageIcon(Objects.requireNonNull(getClass().getResource("/home-animation.gif"))).getImage();
            buttonBackBg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/back-button-bg.jpg"))).getImage();
            backButton = new ImageIcon(Objects.requireNonNull(getClass().getResource("/back-button.jpg"))).getImage();
            playerBg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/player-bg.jpg"))).getImage();
            chooseFlavour = new ImageIcon(Objects.requireNonNull(getClass().getResource("/choose-flavour.png"))).getImage();
            threeIceCreams = new ImageIcon(Objects.requireNonNull(getClass().getResource("/joined-icecreams.png"))).getImage();

            vanillaIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/vanilla-down.gif"))).getImage();
            strawberryIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/strawberry-down.gif"))).getImage();
            chocolateIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/chocolate-down.gif"))).getImage();

        } catch (Exception e) {
            System.err.println("Error cargando recursos: " + e.getMessage());
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (backgroundGif != null) {
            g.drawImage(backgroundGif, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }

        if (playerBg != null) {
            g.drawImage(playerBg, topBoxX, topBoxY, topBoxWidth, topBoxHeight, null);
        }

        if (chooseFlavour != null) {
            int titleWidth = 330;
            int titleHeight = 40;
            int titleX = topBoxX + (topBoxWidth - titleWidth) / 2;
            int titleY = topBoxY + 20;
            g.drawImage(chooseFlavour, titleX, titleY, titleWidth, titleHeight, null);
        }

        if (threeIceCreams != null) {
            g.drawImage(threeIceCreams, iceCreamX, iceCreamY, iceCreamWidth, iceCreamHeight, null);
        }

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);

        String who = needsTwoFlavours() ? ("PLAYER " + selectingIndex) : "PLAYER";
        g.drawString("SELECT FOR " + who, cardX, cardY - 10);

        drawCard(g, Flavour.VANILLA, "VANILLA", vanillaIcon, cardY, isCurrentSelected(Flavour.VANILLA));
        drawCard(g, Flavour.STRAWBERRY, "STRAWBERRY", strawberryIcon, cardY + (cardH + cardGap), isCurrentSelected(Flavour.STRAWBERRY));
        drawCard(g, Flavour.CHOCOLATE, "CHOCOLATE", chocolateIcon, cardY + 2 * (cardH + cardGap), isCurrentSelected(Flavour.CHOCOLATE));

        if (buttonBackBg != null) {
            g.drawImage(buttonBackBg, bottomBoxX, bottomBoxY, bottomBoxWidth, bottomBoxHeight, null);
        }

        if (backButton != null) {
            g.drawImage(backButton, backBtnX, backBtnY, backBtnWidth, backBtnHeight, null);
        }
    }

    private boolean isCurrentSelected(Flavour f) {
        if (!needsTwoFlavours()) return flavourP1 == f;
        if (selectingIndex == 1) return flavourP1 == f;
        return flavourP2 == f;
    }

    private void drawCard(Graphics2D g, Flavour f, String label, Image icon, int y, boolean selected) {
        g.setColor(new Color(255, 255, 255, 70));
        g.fillRect(cardX, y, cardW, cardH);

        g.setColor(Color.WHITE);
        g.drawRect(cardX, y, cardW, cardH);

        if (icon != null) {
            g.drawImage(icon, cardX + 8, y + 6, 42, 42, null);
        }

        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.WHITE);
        g.drawString(label, cardX + 58, y + 32);

        if (selected) {
            g.setColor(new Color(0, 0, 0, 120));
            g.fillRect(cardX, y, cardW, cardH);
            g.setColor(Color.YELLOW);
            g.drawRect(cardX + 2, y + 2, cardW - 4, cardH - 4);
        }
    }

    private void goNext() {
        if (!needsTwoFlavours()) {
            flavourP2 = flavourP1;
        }
        game.setState(new SelectLevelState(game, optionSelected, aiProfileP1, aiProfileP2, flavourP1, flavourP2));
    }

    @Override
    public void keyPressed(Integer key) {
        if (key == KeyEvent.VK_ENTER) goNext();
    }

    @Override
    public void mouseClicked(Integer x, Integer y) {
        if (x >= backBtnX && x <= backBtnX + backBtnWidth &&
                y >= backBtnY && y <= backBtnY + backBtnHeight) {

            if (optionSelected == 2 || optionSelected == 3) {
                game.setState(new ChooseAIProfileState(game, optionSelected));
            } else {
                game.setState(new SelectModeState(game));
            }
            return;
        }

        Flavour clicked = clickedFlavour(x, y);
        if (clicked != null) {
            if (!needsTwoFlavours()) {
                flavourP1 = clicked;
            } else {
                if (selectingIndex == 1) flavourP1 = clicked;
                else flavourP2 = clicked;

                if (selectingIndex == 1) {
                    selectingIndex = 2;
                } else {
                    goNext();
                }
            }
            return;
        }

        if (x >= iceCreamX && x <= iceCreamX + iceCreamWidth &&
                y >= iceCreamY && y <= iceCreamY + iceCreamHeight) {
            if (needsTwoFlavours() && selectingIndex == 1) {
                selectingIndex = 2;
            } else {
                goNext();
            }
        }
    }

    private Flavour clickedFlavour(int x, int y) {
        if (x < cardX || x > cardX + cardW) return null;

        int y1 = cardY;
        int y2 = cardY + (cardH + cardGap);
        int y3 = cardY + 2 * (cardH + cardGap);

        if (y >= y1 && y <= y1 + cardH) return Flavour.VANILLA;
        if (y >= y2 && y <= y2 + cardH) return Flavour.STRAWBERRY;
        if (y >= y3 && y <= y3 + cardH) return Flavour.CHOCOLATE;

        return null;
    }

    @Override public void update() {}
    @Override public void keyReleased(Integer keyCode) {}
}

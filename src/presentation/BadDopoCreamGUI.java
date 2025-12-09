package presentation;

import domain.game.Game;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del juego Bad DOPO Cream.
 */
public class BadDopoCreamGUI extends JFrame {

    public BadDopoCreamGUI() {
        setTitle("Bad DOPO Cream");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el juego
        Game game = new Game();
        game.setState(new MenuState(game));

        // Crear el panel que dibuja y actualiza el juego
        GamePanel panel = new GamePanel(game);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Tamaño base
        setSize(800, 800);
        setLocationRelativeTo(null);

        // Pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);

        panel.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BadDopoCreamGUI::new);
    }
}

package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author 
 * Andres Cascante Salas
 * Jose Andres Slon Conejo
 * Giancarlo Navarro Valverde
 */

public class PanelImagen extends JPanel {

    public PanelImagen() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);

        try {
            dibujarMesa(g);
        } catch (IOException ex) {
            Logger.getLogger(PanelImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dibujarMesa(Graphics g) throws IOException {
        Image background = ImageIO.read(getClass().getResource("../Imagenes/Madera.jpeg"));
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

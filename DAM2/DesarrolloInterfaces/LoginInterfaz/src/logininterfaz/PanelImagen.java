package logininterfaz;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel {
    private Image imagen;

    public PanelImagen(String ruta) {
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}

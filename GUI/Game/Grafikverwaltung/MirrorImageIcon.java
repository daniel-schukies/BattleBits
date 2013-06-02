package GUI.Game.Grafikverwaltung;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Klasse zum Spiegeln eines Bildes
 * @author Daniel Schukies, Sebastian Junger 
 *
 */
@SuppressWarnings("serial")
class MirrorImageIcon extends ImageIcon {

	/**
	 * Setzt die zu spiegelnde Datei mithilfe des Dateinamens
	 * @param filename Name der zu spiegelnden Datei
	 */
    public MirrorImageIcon(String filename) {
    	super(filename);
    }
    
    /**
     * Setzt das zu spiegelnde Bild
     * @param image Bild, das gespiegelt werden soll
     */
    public MirrorImageIcon(Image image)
    {
    	super(image);
    }

    /**
     * Spiegelt das gestzte Bild
     */
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
    	Graphics2D g2 = (Graphics2D)g.create();
    	g2.translate(getIconWidth(), 0);
    	g2.scale(-1, 1);
    	super.paintIcon(c, g2, x, y);
    }

}

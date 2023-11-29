package Lab12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * A GUI panel for drawing a city made up of many houses.
 * 
 * This version is for lab 12. It includes a size for the Houses.
 * 
 * @author Prof. Heisler
 * @version Nov. 14, 2023
 */
public class CityPanel extends JPanel implements MouseListener, MouseMotionListener{
	private ArrayList<House> houses;
	private House preview;
	private Color color;
	private int sizeX;
	private int sizeY;
	
	/**
	 * Creates an empty CityPanel.
	 */
	public CityPanel() {
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.houses = new ArrayList<House>();
		this.color = Color.ORANGE;
		this.sizeX = 50;
		this.sizeY = 50;
		this.preview = new House(0, 0, sizeX, sizeY, color);
	}

	/**
	 * This method is called by the system when a component needs to be painted.
	 * Which can be at one of three times:
	 *    --when the component first appears
	 *    --when the size of the component changes (including resizing by the user)
	 *    --when repaint() is called
	 *    
	 * Partially overrides the paintComponent method of JPanel.
	 * 
	 * @param g -- graphics context onto which we can draw
	 */
	public void paintComponent(Graphics g) {
		// Call parent JPanel's paintComponent method to fill in panel with background color.
		super.paintComponent(g);   // "partial overriding"
		
		// Draw all the houses.
		for(int i = 0; i < this.houses.size(); i++) {
			this.houses.get(i).draw(g);
		}
		
		// Draw the preview one
		this.preview.draw(g);
	}
	
	/**
	 * Sets the size of the next house to draw.
	 * 
	 * @param newX - horizontal size
	 * @param newY - vertical size
	 */
	public void setCurrentSize(int val, boolean isX) {
		if(isX)
			this.sizeX = val;
		else
			this.sizeY = val;
		this.preview = new House(0, 0, this.sizeX, this.sizeY, this.color);
		this.repaint();
	}
	
	/**
	 * Sets the color for the next house.
	 * 
	 * @param newColor
	 */
	public void setColor(Color newColor) {
		this.color = newColor;
		this.preview.setColor(newColor);
	}
	
	/**
	 * Clears the houses from the panel.
	 */
	public void clearHouses() {
		this.houses.clear();
		this.repaint();
	}
	
	// The following methods are from the MouseListener interface.
	// For any not used, simply leave empty.

	/**
	 * The method is invoked when the user clicks the mouse on the GUI.
	 * 
	 * @param e -- object with info on the mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		this.houses.add(new House(e.getX(), e.getY(), sizeX, sizeY, color));
		this.repaint();
	}

	public void mouseEntered(MouseEvent e) {
		// unused method
	}

	public void mouseExited(MouseEvent e) {
		// unused method
	}

	public void mousePressed(MouseEvent e) {
		// unused method
	}

	public void mouseReleased(MouseEvent e) {
		// unused method
	}
	
	// The following methods are from the MouseMotionListener interface.
	// For any not used, simply leave empty.
	
	/**
	 * This is called whenever the mouse is moved inside this panel.
	 * Moves a preview of the next house.
	 */
	public void mouseMoved(MouseEvent e) {
		this.preview.move(e.getX(), e.getY());
		this.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		// unused method
	}
	
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}
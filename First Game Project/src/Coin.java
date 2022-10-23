import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Coin {

	// The name of the image representing the Coin.
	// This file must be in the same folder as the Java source files.
	private String imgName = "coin.gif";
	private Image image;

	// Movement variables
	private int x;
	private int y;
	private int dx, dy;

	// Visibility variable.
	private boolean isVisible;

	// Represents the width and height of the Coin. This will be
	// used later...
	private int width;
	private int height;
	
	//Controls coin activity
	private boolean gameStatus;

	// Constructor for the Coin.
	public Coin() {
		// Creates the image representing the Coin.
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imgName));
		this.image = ii.getImage();

		// Calculates the size of the Coin based on the image.
		this.width = this.image.getWidth(null);
		this.height = this.image.getHeight(null);

		// Sets the initial position of the Coin.
		this.x = Game.W_WIDTH;
		this.y = (int)(Math.random() * Game.W_HEIGHT) + 50;

		// Default setting to true.
		this.isVisible = true;
		
		//sets initial game status
		this.gameStatus = false;
	}

	// Returns the current visibility of the coin.
	public boolean getVisibility() {
		return isVisible;
	}

	// Sets the visibility to the incoming parameter.
	public void setVisible(boolean visOrNot) {
		this.isVisible = visOrNot;
	}

	// Moves the Coin. 
	// The width and height of the screen must be passed so that it cannot go out of bounds.
	public void move() {
		// Updates the Coin's location based on its dx and dy values.
		this.x = this.x -1;

		// Ensures that it can't move out of bounds.
		if (this.x < 1) {
			this.x = Game.W_WIDTH;
			this.y = (int)(Math.random() * Game.W_HEIGHT) + 50;
			this.isVisible = true;
		}
	}

	// Returns the x-coordinate of the Coin.
	// This is primarily so that the content pane knows where to draw it on screen.
	public int getX() {
		return this.x;
	}

	// Returns the y-coordinate of the Coin.
	public int getY() {
		return this.y;
	}

	// Returns the image of the Coin.
	public Image getImage() {
		return this.image;
	}

	// Returns the hitbox associated with the Coin.
	public Rectangle getHitbox() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	//sets coin activity based on game contents'
	public void getGameStatus(boolean activeOrNot) {
		gameStatus = activeOrNot;
				
		//sets coins position to neutral when the game is off
		if (!gameStatus) {
			this.x = Game.W_WIDTH;
		}
	}

}

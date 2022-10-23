//Johvonne Keane
//21/01/2019
//KIRBY DASH ATTACK

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class WaddleDee {

	// The name of the image representing the Coin.
	// This file must be in the same folder as the Java source files.
	private String imgName = "WaddleDee.gif";
	private Image image;

	// Movement variables
	private int x;
	private int y;
	private int dy;

	// Visibility variable.
	private boolean isVisible;

	//Controls jumping
	private boolean isJumping;

	//sets enemy speed
	private final int SPEED = -1;

	//keeps enemy maxY distance
	private final int maxY = 635  ;

	//variable to help control gravity
	private int gravity;

	// Represents the width and height of the WaddleDee. This will be
	private int width;
	private int height;
	
	//Controls enemy activity
	private boolean gameStatus;


	// Constructor for the WaddleDee.
	public WaddleDee() {


		// Creates the image representing the WaddleDee.
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imgName));
		this.image = ii.getImage();

		// Calculates the size of the WaddleDee based on the image.
		this.width = this.image.getWidth(null);
		this.height = this.image.getHeight(null);

	

		//WaddleDee spawn points
		this.x = Game.W_WIDTH;
		this.y = maxY;

		// Default settings.
		this.isVisible = true;
		gravity = 0;
		isJumping = false;



	}

	// Returns the current visibility of the WaddleDee.
	public boolean getVisibility() {
		return isVisible;
	}

	// Sets the visibility to the incoming parameter.
	public void setVisible(boolean visOrNot) {
		this.isVisible = visOrNot;
	}



	// Moves the WaddleDee. 
	// The width and height of the screen must be passed so that it cannot go out of bounds.
	public void move() {
		// Updates the Coin's location based on its dx and dy values.
		
		//helps control gravity
		gravity++;
		this.x = this.x + SPEED;
		//makes WaddleDee fall slower
		if ((gravity%5 == 0 && this.dy > 0) || this.dy < 0) {
			this.y = this.y + this.dy;
		}
		
		//times random jumps
		int jumpTimming = (int) ((Math.random()* 300)+ 1);
		int jumping = (int) ((Math.random()* 300)+ 1);
		if (jumping == jumpTimming && this.y == maxY) {
			isJumping = true;
			dy = -25;
			this.x = this.x + SPEED;

		}
		if (isJumping = true) {
			dy++;
			this.x = this.x + SPEED;
		}

		if (jumping != jumpTimming && this.y == maxY) {
			isJumping = false;
			dy = 0;
		}


		// Ensures that it can't move out of bounds.
		if (this.x < 1) {
			this.x = Game.W_WIDTH;			
			this.y = maxY;
			this.isVisible = true;
		}
		if (this.y >= maxY) {
			this.y = maxY;
		}
	}

	// Returns the x-coordinate of the WaddleDee.
	// This is primarily so that the content pane knows where to draw it on screen.
	public int getX() {
		return this.x;
	}

	// Returns the y-coordinate of the WaddleDee.
	public int getY() {
		return this.y;
	}

	// Returns the image of the WaddleDee.
	public Image getImage() {
		return this.image;
	
	}

	// Returns the hitbox associated with the WaddleDee.
	public Rectangle getHitbox() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	//sets WaddleDee activity based on game contents'
	public void getGameStatus(boolean activeOrNot) {
		gameStatus = activeOrNot;
		
		//sets WaddleDee position to neutral when the game is off
		if (!gameStatus) {
			this.x = Game.W_WIDTH;
			this.y = maxY;
			
		}
	}
	

}



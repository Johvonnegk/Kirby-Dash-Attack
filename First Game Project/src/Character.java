import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Character {

	// The name of the image representing the character.
	// This file must be in the same folder as the Java source files.
	private String imgName = "KirbyMove.gif";
	private Image image;



	// Movement variables
	private int x;
	private int y;
	private int dx, dy;
	private int gravity;

	//Sets character activity
	private boolean start = false;

	//Helps Sets character activity based on game status
	private boolean gameStatus;


	//Immunity controls
	private boolean immune;
	private int immuneTime;

	//Keeps track of character health
	private int lives;

	//Keeps track of coins collected
	private int coinCount;

	//Helps create a flickering animation
	private boolean flickering;
	private int flickerCount;

	//lets game content know if the character should be visible or not
	private boolean isVisible;


	//Jump controls
	private boolean isJumping;
	private boolean leftJump;
	private boolean rightJump;


	// Represents the width and height of the character. This will be
	private int width;
	private int height;

	// Constructor for the character.
	public Character() {
		
		
		//sets initial jump
		isJumping = false;
		// Creates the image representing the character.
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imgName));
		this.image = ii.getImage();

		// Calculates the size of the character based on the image.
		this.width = this.image.getWidth(null);
		this.height = this.image.getHeight(null);



		// Sets the initial position of the character.
		this.x = 4;
		this.y = 600;

		//sets initial flickering to neutral
		flickering = false;
		isVisible = true;

		//sets initial coins collected
		coinCount = 0;

		//sets initial health
		lives = 3;

		//sets initial immunity
		immune = false;
		immuneTime = 0;

		//sets initial activity
		start = false;
		gameStatus = false;


	}

	//controls immunity and health reduction
	public void hit() {
		if (!immune) {
			immune = true;
			lives--;
			if (lives < 0) {
				start = false;
			}

		}

	}

	//lets game content know about the characters "state"
	public boolean immunity() {
		return immune;
	}


	//Method controls Controls flickering
	public void flicker() {
		this.flickering = true;
		flickerCount = 0;
	}
	
	//lets game content know if character is flickering or not 
	public boolean flickering() {
		return this.flickering;
	}

	// Moves the character. 
	// The width and height of the screen must be passed so that it cannot go out of bounds.
	public void move() {
		
		//helps with gravity control
		gravity++;

		//controls how long immunity lasts for
		if (immune) {
			immuneTime++;
			if (immuneTime >= 200) {
				immune = false;
				immuneTime = 0;
			}

		}



		// Updates the character's location based on its dx and dy values.
		this.x = this.x + this.dx;
		//helps character fall slower
		if ((gravity%5 == 0 && this.dy > 0) || this.dy < 0) {
			this.y = this.y + this.dy;
		}


		//Controls jump movement
		if (isJumping && rightJump)  {
			dy++;
			this.x = this.x + 1;
		}
		if (isJumping && leftJump) {
			dy++;
			this.x = this.x + -1;
		}
		if (isJumping) {
			this.dy++;
		}


		// Ensures that it can't move out of bounds.
		if (this.x < 1) {
			this.x = 1;
		}

		if (this.y < 1) {
			this.y = 1;
		}

		//also resets jump and gravity to neutral
		if (this.y > 600) {
			this.y = 600;
			isJumping = false;
			gravity = 0;
		}

		if (this.x > Game.W_WIDTH) {
			this.x = Game.W_WIDTH;
		}

		if (this.y > Game.W_HEIGHT) {
			this.y = Game.W_HEIGHT;
		}
		if (this.x > 1163) {
			this.x =1163;
		}

		//controls how long and fast the character flickers for
		if (flickering) {
			flickerCount++;


			if (30 % flickerCount == 0) {
				this.isVisible = !this.isVisible;

			}

			if (flickerCount > 30) {
				this.isVisible = true;
				flickering = false;



			}
		}
	}






	//lets game content know if character is visible or not
	public boolean getVisibility() {
		return isVisible;
	}

	// Returns the x-coordinate of the character.
	// This is primarily so that the content pane knows where to draw it on screen.
	public int getX() {
		return this.x;
	}

	// Returns the y-coordinate of the character.
	public int getY() {
		return this.y;
	}

	// Returns the image of the character.
	public Image getImage() {
		return this.image;
	}


	// How to react to keys for movement purposes
	public void keyPressed(KeyEvent e) {
		// Grabs a code that represents the key that was pressed.
		int key = e.getKeyCode();

		//controls jump inputs
		if (key == KeyEvent.VK_SPACE  && !isJumping) {
			this.dy = -35;
			isJumping = true;

		}
		
		//controls left and right movements 
		if (key == KeyEvent.VK_LEFT) {
			this.dx = -5;
			leftJump = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.dx = 5;
			rightJump = true;
		}
		//starts the game
		if (key == KeyEvent.VK_ENTER && !gameStatus) {
			startGame();
		}

	}
	//Method that increases coin count when coins are collected
	public void coinCollected() {
		coinCount++;
	}
	//returns characters coinCount to game content
	public int getCoinCount() {
		return coinCount;
	}



	// The following code runs when a key is released.
	// It resets the dx and dy variables.
	public void keyReleased(KeyEvent e) {
		// Grabs a code that represents the key that was pressed.
		int key = e.getKeyCode();


		//********************
		if (key == KeyEvent.VK_LEFT) {
			this.dx = 0;
			leftJump = false;
		}

		if (key == KeyEvent.VK_RIGHT) {
			this.dx = 0;
			rightJump = false;
		}


	}
	//starts game and resets character to a neutral state
	public void startGame() {
		start = true;
		lives = 3;
		x = 4;
		y = 600;
		coinCount = 0;
	}
	
	//lets gameContent know when to start
	public boolean getStart() {
		return start;
	}
	
	//returns health to game content
	public int getHealth() {
		return lives;

	}
	
	//sets character activity based on game contents'
	public void getGameStatus(boolean activeOrNot) {
		gameStatus = activeOrNot;
	}



	// Returns the hitbox associated with the Character.
	public Rectangle getHitbox() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	
}



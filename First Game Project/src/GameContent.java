import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.ImageIcon;
public class GameContent extends JPanel implements ActionListener{

	// Timer for animation purposes
	private Timer timer;


	// Our main character
	private Character player;

	// The coin object.
	private Coin coin;

	// The Enemy object
	public ArrayList<WaddleDee> enemies;

	//***************************************
	private ScrolingBackground[] bg;

	//Controls game start up screen
	private boolean gameStart;

	//controls gameOver screen
	private boolean gameOver;

	//************************
	private boolean gameStatus;

	// Constructor for the game content
	public GameContent(int width, int height) {

		// Common settings for JPanel
		this.setFocusable(true);
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.setSize(width, height);

		// Creates an instance of the player's character.
		this.player = new Character();

		// Creates the Coin object.
		this.coin = new Coin();

		// Creates the Enemy object
		this.enemies = new ArrayList<WaddleDee>();
		this.enemies.add(new WaddleDee());

		// Background
		this.bg = new ScrolingBackground[2];
		this.bg[0] = new ScrolingBackground(0,0, "KirbyBackground.png");
		this.bg[1] = new ScrolingBackground(Game.W_WIDTH,0, "KirbyBackgroundFlipped.png");

		// Adds a KeyListener to this JPanel object. 
		// A key listener is an object that intercepts keystrokes allowing
		// you to process them. The code for this key listener is at the 
		// bottom of this file.
		this.addKeyListener(new KeyListener());		

		// Creates a new Timer. The timer "goes off" every 5ms and calls
		// the actionPerformed() method. This allows us to update game content
		// rapidly.


		this.timer = new Timer(5, this);
		this.timer.start();

		//sets game status' to neutral
		this.gameStart = false;

		this.gameOver = false;

		gameStatus = false;
	}


	// The following method must be included so that the board is displayed on screen.
	// It's a required method that ensures the window is actively redrawn.
	public void addNotify() {
		super.addNotify(); 
	}

	// This method instructs Java how to "paint" the game content.
	// It uses the Graphics parameter g which is automatically sent to the method  
	// (don't worry about where it comes from) to draw on the screen.
	// The Graphics object is converted to a Graphics2d object which 
	// allows us to draw images.
	public void paint(Graphics g) {
		// Tells the JPanel class to paint itself. DO NOT MODIFY!
		super.paint(g);

		// Grabs the Graphics object to draw images. DO NOT MODIFY!
		Graphics2D g2d = (Graphics2D)g;

		//prints when the game is over
		if  (gameOver && !gameStart) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource("KirbyGameOver.gif"));
			g2d.drawImage(ii.getImage(), 0,  0, this);

			//prints coinCount as a score
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 36));
			g.drawString("Score: " + player.getCoinCount(), 630, 50);;
		}


		//prints when game is starting for the first time
		if (!gameStart && !gameOver) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource("KirbyStart.gif"));
			g2d.drawImage(ii.getImage(), 0,  0, this);
		}

		//prints when game is active
		if (gameStart && !gameOver) {
			// Draw backgrounds
			g2d.drawImage(this.bg[0].getImage(), this.bg[0].getX(),  this.bg[0].getY(), this);
			g2d.drawImage(this.bg[1].getImage(), this.bg[1].getX(),  this.bg[1].getY(), this);


			//prints character health
			if (player.getHealth() == 3) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource("fullHealth.png"));
				g2d.drawImage(ii.getImage(), 0,  0, this);
			}
			if (player.getHealth() == 2) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource("2heart.png"));
				g2d.drawImage(ii.getImage(), 0,  0, this);
			}
			if (player.getHealth() == 1) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource("1heart.png"));
				g2d.drawImage(ii.getImage(), 0,  0, this);
			}
			if (player.getHealth() == 0) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource("noHeart.png"));
				g2d.drawImage(ii.getImage(), 0,  0, this);
			}


			//Draws every enemy object
			for (int i = 0; i < this.enemies.size(); i++) {
				WaddleDee wd = this.enemies.get(i);

				if (wd.getVisibility() == true) {

					g2d.drawImage(wd.getImage(),  wd.getX(),  wd.getY(), this);
				}

			}

			// Draws the character if its visible	
			if (player.getVisibility() == true) {
				g2d.drawImage(this.player.getImage(),  this.player.getX(),  this.player.getY(), this);
			}




			// Draws the coin.
			if (this.coin.getVisibility()) {
				g2d.drawImage(this.coin.getImage(),  this.coin.getX(),  this.coin.getY(), this);
			}
			//prints coinCount as a score
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			g.drawString("Score: " + player.getCoinCount(), 1250, 50);
		}












		// Synchronizes the graphics state. DO NOT MODIFY!
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}


	//Method that allows game start-up
	public void startGame() {
		if (player.getStart()) {
			gameStart = true;
			gameOver = false;
			gameStatus = true;
		}
	}

	public void actionPerformed(ActionEvent e) {
		// This method is called every 5ms. We will use it to tell
		// classes that they should update their images, locations, and
		// other similar content. For now, it is empty.

		//checks if game is started only when game is not started
		if (!gameStart) {
			startGame();
		}

		//only runs when game is on
		if (gameStart && !gameOver) {

			//randomizes enemy spawning
			int spawn = (int)(Math.random() * 100) + 1;
			//spawns enemies
			if (spawn == 1 && this.enemies.size() <= 1) {
				this.enemies.add(new WaddleDee());
			}



			// Move background.
			this.bg[0].move();
			this.bg[1].move();


			// Updates the player's position.
			player.move();

			//  Update the coin's location.
			this.coin.move();

			//moves enemies
			for (int i = 0; i < this.enemies.size(); i++) {
				WaddleDee wd = this.enemies.get(i);

				if (wd.getVisibility() == true) {
					wd.move();
				}
			}

			//Checks if enemies need to be removed
			waddleDeeRemove();

			// Check for collisions
			checkForCollisions();
		}

		// Tells the JPanel to repaint itself since object positions
		// may have changed.
		repaint();

		//sets gameOver to true
		if (!gameOver) {
			if (player.getHealth() < 0){
				gameOver = true;
				gameStart = false;
				gameStatus = false;
			}
		}
		//interacts with characters game status to let it know to be active or not
		player.getGameStatus(gameStatus);

		//interacts with coins game status and sets its activities
		coin.getGameStatus(gameStatus);
		
		//Interacts with all enemies and sets their activities
		if (this.enemies.size()> 0) {
			for (int i = 0; i <this.enemies.size();i++) {
				WaddleDee wd = this.enemies.get(i);
				wd.getGameStatus(gameStatus);
			}
		}
		
		
		


	}











	// Method that checks for collisions and reacts accordingly.
	private void checkForCollisions() {
		// Get the hitbox for the Character.
		Rectangle charHitbox = this.player.getHitbox();

		// Get the hitbox for the coin.
		Rectangle coinHitbox = this.coin.getHitbox();


		//Checks for collisions between character and coin
		if (charHitbox.intersects(coinHitbox) && coin.getVisibility()) {
			coin.setVisible(false);
			player.coinCollected();
		}


		//checks for collisions between character and enemies
		for (int i = 0; i < this.enemies.size(); i++) {
			WaddleDee wd = this.enemies.get(i);

			Rectangle waddleDeeHitbox = wd.getHitbox();

			if ((((charHitbox.intersects(waddleDeeHitbox) && !player.flickering()))) && ((charHitbox.intersects(waddleDeeHitbox) && !player.immunity()))){
				player.hit();
				player.flicker();
			}
		}
	}
	//Method that removes enemies when needed
	public void waddleDeeRemove() {
		if (this.enemies.size() != 0) {
			for (int i = 0; i < 1; i++) {
				WaddleDee wd = this.enemies.get(0);

				if (wd.getX() <= 4) {
					this.enemies.remove(0);

				}
			}
		}

	}






	// Private class -- this is a class that outside Java files
	// cannot see. It's sole purpose is to intercept keystrokes
	// and send them to the player class for processing.
	// DO NOT MODIFY!
	private class KeyListener extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}
}



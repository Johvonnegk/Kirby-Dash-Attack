import javax.swing.*;
import java.awt.*;
/* Game.java
 * 
 * This class extends JFrame. It opens the main window of our game.
 * This means that it IS considered a JFrame and also has additional member variables
 * as shown below. Additionally, it allows us to set various attributes in the constructor of the class.
 *
 */
public class Game extends JFrame {

	// Static final variables are constant values that never change.
	static final int W_WIDTH = 1358;
	static final int W_HEIGHT = 768;

	
	public Game() {
		// Creates an instance of our game content and adds 
		// it to the JFrame's content pane.
		this.getContentPane().add(new GameContent(W_WIDTH, W_HEIGHT));
		
		// Sets various attributes of the JFrame.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(W_WIDTH, W_HEIGHT);
		
		this.setTitle("Game Template");
		this.setResizable(false);
		this.setVisible(true);
		

	}
	

	
	// The game's main method. This simply creates an instance
	// of the JFrame and from there, the game begins running!
	public static void main(String[] args) {
		Game gameWindow = new Game();
		
		
		
	}
}

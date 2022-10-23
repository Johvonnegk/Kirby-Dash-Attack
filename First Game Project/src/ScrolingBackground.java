import java.awt.Image;

import javax.swing.ImageIcon;

public class ScrolingBackground {
	private int x;
	private int y;
	
	private Image image;
	
	private int width, height;

	public ScrolingBackground(int startX, int startY, String imgName) {
		this.x = startX;
		this.y = startY;
		
		// Image stuff.
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imgName));
		image = ii.getImage();
		
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	//creates scrolling effect
	public void move() {
		this.x = this.x-1;

		if (this.x < (-1 * Game.W_WIDTH)) {
			this.x = Game.W_WIDTH;
		}
	}
	
	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.*;

public class Tile {
	Color tileColor;
	int xMin;
	int yMin;
	int xMax;
	int yMax;
	int sideLength;
	boolean white;
	Graphics gg;

	/*
	 * constructs each individual tile with coordinates of location and color
	 */
	public Tile(int x, int y, int n, Color c) {
		white = true;
		tileColor = c;
		xMin = x;
		yMin = y;
		xMax = x + n - 1;
		yMax = y + n - 1;
		sideLength = n;
	}

	// x and y coordinates for the tiles
	public int getXMin() {
		return xMin;
	}

	public int getYMin() {
		return yMin;
	}

	public int getXMax() {
		return xMax;
	}

	public int getYMax() {
		return yMax;
	}

	public int getSideLength() {
		return sideLength;
	}

	public Color getColor() {
		return tileColor;
	}

	public boolean isWhite() {
		return white;
	}

	public void changeWhite() {
		white = (!white);
	}

	/*
	 * tile properties are changed to black
	 */
	public void makeTileBlack() {
		tileColor = Color.BLACK;
		white = false;
	}

	/*
	 * when the tile is clicked, the tile's color is changed from either black
	 * to white or vice versa (based on tile's current properties)
	 */
	public void tileIsClicked() {
		if (white) {
			tileColor = Color.BLACK; // if tile is white, it changes to black
			white = false;
		} else {
			tileColor = Color.WHITE; // if tile is black, it changes to white
			white = true;
		}
	}

}

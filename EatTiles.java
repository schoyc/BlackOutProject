import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;
import javax.swing.*;

/*
 * changes color of tiles upon mouse click 
 */
public class EatTiles extends TimerTask {
	private ArrayList<Integer> indices; // carries all white tiles
	private ArrayList<Tile> tilez; // carries the properties of each tile, such
									// as color
	int number;
	Random generator;
	boolean wasFirstCreated;

	/*
	 * constructor for EatTiles
	 */
	public EatTiles(ArrayList<Tile> ts, ArrayList<Integer> indexes, int n) {
		super();
		indices = indexes;

		tilez = ts;
		number = n;

		generator = new Random(3341321); // random number generator to pick
											// random tiles to change color
		wasFirstCreated = false;

	}

	/*
	 * adds the twenty-five tiles to the ArrayList
	 */
	public void fillIndexes() {
		if (!wasFirstCreated) {
			for (int i = 0; i < 25; i++) {

				indices.add(new Integer(i));
			}
			wasFirstCreated = true;
		}
	}

	/*
	 * chooses random tile in the ArrayList and checks its color if white, the
	 * tile is turned black, then removed from the ArrayList
	 */
	public void run() {
		if (indices.size() > 0) {
			int r = generator.nextInt(indices.size()); // chooses random tile in
														// ArrayList
			int index = indices.get(r).intValue();

			if (tilez.get(index).isWhite()) {

				tilez.get(index).makeTileBlack();
				indices.remove(indices.get(r)); /*
												 * once turned black, it is
												 * taken out of the ArrayList to
												 * ensure that the compiler does
												 * not attempt to turn black
												 * tiles black
												 */
			}
		}

	}
}
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;

public class GameTimer extends Timer {
	Timer timer; // creates timer
	int delay; // sets rate of task
	int period; // sets total time timer runs
	EatTiles task; // TimerTask by which tile colors are changed
	ArrayList<Tile> tilez;
	Random generator;

	/*
	 * constructs the game timer
	 */
	public GameTimer(int d, int p, int n, ArrayList<Tile> ts,
			ArrayList<Integer> indexes) {
		timer = new Timer();
		delay = d;
		period = p;
		ArrayList<Tile> tilez = ts; // holds properties of tiles
		task = new EatTiles(tilez, indexes, n);
		task.fillIndexes(); // adds the twenty-five tiles to the ArrayList

	}

	/*
	 * each method returns the stated variable
	 */
	public int getDelay() {
		return delay;
	}

	public void setPeriod(int newPeriod) {
		period = newPeriod;
	}

	public int getPeriod() {
		return period;
	}

	public EatTiles getTask() {
		return task;
	}

	public void setTask(EatTiles eat) {
		task = eat;
	}

}
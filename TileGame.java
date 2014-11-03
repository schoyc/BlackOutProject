import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author mikewang Steven Chen  Anthony Kim
 * TileGame is the top level container   
 */
public class TileGame extends JPanel implements MouseListener {

	private static Container contentPane;
	private static JFrame frame;
	ArrayList<Tile> tiles;
	private int i;
	private int a;
	private int blue;
	ArrayList<Integer> indexes;
	Tile t;
	private GameTimer timer;
	private Timer timerTime;
	boolean gameOver;
	static JLabel scoreBoard;
	static int points = 0;
	static String score = "POINTS";
	static Font font;
	static Font sizedFont;
	int topBorderY = 140;
	int bottomBorderY = 20;
	int leftBorderX = 20;
	int rightBorderX = 20;
	boolean firstTime;
	static Font fontAdj;
	static boolean appOpened = true;

	static TileGame newGame;
	static boolean b = true;
	static boolean howToPlayClicked = false;
	static boolean askToStartAgain = false;

	public TileGame(int n) { // adds mouse click to tile game
		BlankArea blankArea = new BlankArea();
		add(blankArea);
		blankArea.addMouseListener(this);
		addMouseListener(this);
		tiles = new ArrayList<Tile>();
		indexes = new ArrayList<Integer>();
		blue = 0;

		int x = 0;
		int y = 0;
		Color c = Color.WHITE;
		gameOver = false;
		firstTime = true;
		// places tiles onto the screen
		for (int i = leftBorderX; i < 500 + rightBorderX; i += 100) {
			for (int j = topBorderY; j < 500 + topBorderY; j += 100) {

				Tile t = new Tile(i, j, 100, c);
				tiles.add(t);
				y++;
				x++;
			}
		}
		timer = new GameTimer(5000, 1000, n, tiles, indexes); // runs TileGame
																// at a
																// consistent
																// rate

	}

	/*
	 * creates GUI for the application
	 */
	public void paint(Graphics g) {

		super.paint(g);
		if (appOpened) { // set up homescreen
			g.setColor(Color.WHITE);

			// four rectangles with Start, How to Play, Credits, and Dedication
			// Start and How to Play are clickable

			g.drawRect(0, 150, 250, 250); // creates rectangle of given size
			g.fillRect(0, 150, 250, 250); // colors in the rectangle
			g.drawRect(250, 400, 250, 250);
			g.fillRect(250, 400, 250, 250);
			g.setColor(Color.BLACK);
			g.drawRect(250, 150, 250, 250);
			g.fillRect(250, 150, 250, 250);
			g.drawRect(0, 400, 250, 250);
			g.fillRect(0, 400, 250, 250);
			g.setFont(fontAdj);
			g.setFont(new Font("Impact", Font.BOLD, 80)); // Changes the font
															// style and font
															// size of String
			g.setColor(Color.BLACK);
			g.drawString("BLACKOUT!", 70, 90);
			g.setFont(new Font("Neuropol", Font.BOLD, 40));

			String[] names = { "START", "GAME" };
			for (int i = 0; i < names.length; i++) // prints "START GAME" in an
													// organized fashion

			{
				g.drawString(names[i], 30, 190 + 63 + 63 * i);
			}

			g.drawString("Dedicated", 275, 250 + 170 + 63); // prints string in
															// new line
			g.drawString("to Annabel", 270, 250 + 170 + 63 + 50);
			g.setFont(new Font("Neuropol", Font.BOLD, 30));

			g.setColor(Color.WHITE); // background color of rectangle is white

			String[] names2 = { "HOW", "TO", "PLAY" };
			for (int j = 0; j < names2.length; j++)

			{
				g.drawString(names2[j], 340, 170 + 63 + 63 * j);
			}
			String[] names3 = { "Steven Chen", "Anthony Kim", "Mike Wang" };
			for (int k = 0; k < names3.length; k++)
				g.drawString(names3[k], 20, 250 + 170 + 63 + 63 * k);

		}

		else if (howToPlayClicked) { // opens instructions
			g.setColor(new Color(108, 123, 139));
			g.drawRect(0, 0, 500, 500);
			g.fillRect(0, 0, 500, 500);
			g.setColor(Color.WHITE); // creates a white square
			g.drawRect(50, 50, 100, 100);
			g.fillRect(50, 50, 100, 100);
			g.setFont(new Font("Neuropol", Font.BOLD, 30)); // changes font size
															// and style
			// prints instructions of game: clicking black tiles turn them
			// white; clicking white tiles turn them black
			g.drawString("= click to turn ", 165, 50 + 35);
			g.setColor(Color.BLACK); // creates a black square
			g.drawString("black", 195, 80 + 35);
			g.drawRect(50, 170, 100, 100);
			g.fillRect(50, 170, 100, 100);
			g.drawString("= click to turn ", 165, 150 + 50);
			g.setColor(Color.WHITE);
			g.drawString("white", 195, 180 + 50);
			// prints more instructions: prevent entire board form turning black
			// for as long as possible
			g.drawString("KEEP THE ENTIRE BOARD", 30, 250 + 100);
			g.drawString("FROM TURNING", 30, 290 + 100);
			g.setColor(Color.BLACK);
			g.drawString("BLACK", 300, 290 + 100);
			g.drawRect(200, 400, 270, 65);
			g.setColor(Color.WHITE);
			g.drawString("BACK TO MENU", 210, 385 + 50);

		}

		else { // prints score at the top of the screen during gameplay
			g.setColor(new Color(108, 123, 139));
			g.drawRect(0, 0, 500 + leftBorderX + rightBorderX, 500 + topBorderY
					+ bottomBorderY);
			g.fillRect(0, 0, 500 + leftBorderX + rightBorderX, 500 + topBorderY
					+ bottomBorderY); // creates rectangle box for score
			firstTime = false;
			g.setColor(new Color(162, 181, 205));
			g.drawRect(120, 10, 300, 110);
			g.fillRect(120, 10, 300, 110);
			g.setColor(Color.GRAY);
			g.setFont(fontAdj);
			g.setFont(new Font("Neuropol", Font.BOLD, 50));
			g.drawString(score, 190, 60);
			String str;

			str = "" + points;
			g.drawString(str, 245, 110);

			for (Tile t : tiles) { // loops through array and returns properties
									// of each element

				g.setColor(t.getColor());
				g.drawRect(t.getXMin(), t.getYMin(), t.getSideLength(),
						t.getSideLength());
				g.fillRect(t.getXMin(), t.getYMin(), t.getSideLength(),
						t.getSideLength());

			}

			if (askToStartAgain) { // prints game over message once the whole
									// board turns black

				if (points > 3200) // highest score by Anthony; very impressive
									// if beaten
				{
					g.setColor(Color.BLUE);
					g.drawString("CONGRATS", 50, 200);
					g.drawString("YOU ROCK!", 50, 250); // if able to attain
														// score over 3200,
														// participant receives
														// compliment
				} else {
					g.setColor(Color.RED);
					g.drawString("GAME OVER", 50, 200);
					g.drawString("YOU SUCK", 50, 250);
					g.drawString("AS MUCH AS MIKE", 50, 300); // if unable to
																// attain score
																// over 3200,
																// participant
																// receives
																// insult
				}
			}
		}
	}

	public void startGame() { 
		// begins the game by turning random tiles black at given intervals
		timer.scheduleAtFixedRate(timer.getTask(), timer.getDelay(),
				timer.getPeriod());
		System.out.println("The intial period is " + timer.getPeriod());
		timerTime = new Timer();
		final TimerTask task = new TimerTask() {
			public void run() {
				repaint();
			}
		};
		timerTime
				.scheduleAtFixedRate(task, timer.getDelay(), timer.getPeriod()); // .getDelay()
																					// and
																					// .getPeriod()
																					// allow
																					// for
																					// acceleration

		final Timer accelerate = new Timer();
		final TimerTask acc = new TimerTask() {
			public void run() {
				if (!gameOver) {
					goFaster(timer, timerTime);
				}

			}
		};

		accelerate.schedule(acc, 3000, 5000); // accelerates game speed at the
												// rate shown

	}

	// stops the speed of the game from accelerating
	public void stopFaster(TimerTask task) {
		task.cancel();
	}

	// gradually increase speed in which tiles turn black
	public void goFaster(GameTimer timer, Timer timerTime) {
		if (indexes.size() > 0) {
			timer.getTask().cancel();
			int newTime = timer.getPeriod() * 90 / 100;
			points += 100;
			System.out.println("New period is " + newTime);
			System.out.println("White tiles lfeft: " + indexes.size());
			timer.setPeriod(newTime);
			EatTiles eat = new EatTiles(tiles, indexes, 5);
			timer.setTask(eat);
			timer.scheduleAtFixedRate(timer.getTask(), 0, newTime);
			TimerTask task1 = new TimerTask() {
				public void run() {

					repaint();
				}
			};
			timerTime.scheduleAtFixedRate(task1, 0, newTime / 2);
		} else {
			askToStartAgain = true;
			gameOver = true;
		}
	}

	/*
	 * return collection of tiles
	 */
	public ArrayList<Tile> getTilesGrid() {
		return tiles;
	}

	/*
	 * this is the callback method for mouse press event
	 * upon mouse pressed, removes the tile that is selected 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {

		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		int f = (x - leftBorderX) / 100;
		int g = (y - topBorderY) / 100;
		a = 5 * f + g;

		if (x < 500 + rightBorderX && y < 500 + topBorderY && x >= leftBorderX
				&& y >= topBorderY && !gameOver) {
			tiles.get(a).tileIsClicked();

			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					takeOutTiles(a);
				}
			};
			timer.schedule(task, 5);

		}
		this.repaint();

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	/*
	 * actual logic to remove the tile
	 */
	public void takeOutTiles(int v) {

		if (tiles.get(v).isWhite()) {
			points += 10;
			indexes.add(new Integer(v));
		}

		else {
			points -= 5;
			indexes.remove(new Integer(v));
		}
	}

	public void foo() {
		appOpened = false;
	}

	public void foo2() {
		howToPlayClicked = true;

	}

	public void foo3() {
		howToPlayClicked = false;
	}

	public void foo4() {
		appOpened = true;
	}

	/*
	 * callback method for mouse click event
	 * it will either shop help or start a new game depending on which action is clicked.  
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		if (b) {
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			if (howToPlayClicked) {
				if (x < 1000 && y < 1000) {
					foo3();
					foo4();
					repaint();
				}
			} else {

				if (x < 250 && y < 400) {
					foo();
					appOpened = false;
					repaint();
					newGame.startGame();
					b = false;

				} else if (x >= 250 && x < 500) {
					foo();
					foo2();
					repaint();

				}
			}
		}
	}

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setTitle("Tiles");
		frame.setSize(700, 700);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		contentPane = frame.getContentPane();
		newGame = new TileGame(5);
		contentPane.add(newGame);

		scoreBoard = new JLabel("Points: " + 10 * points, SwingConstants.CENTER);

		scoreBoard.setVerticalAlignment(SwingConstants.TOP);

		scoreBoard.setBorder(BorderFactory.createLineBorder(Color.black));

		System.out.println("The game is starting");
		System.out.println(newGame.indexes.size());
		frame.show();
	}
}
import java.awt.*;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Aquarium extends Frame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6690475893594141255L;
	Image aquariumImage, memoryImage;
	Thread thread;
	MediaTracker tracker;
	Graphics memoryGraphics;
	Graphics2D g2;

	Environment environment = new Environment();
	int numberFish = 250;
	int sleepTime = 1000 / 30;

	boolean runOK = true;
	Rectangle edges;
	private BufferedImage memoryImage2;
	private Graphics memoryGraphics2;
	private Graphics2D g22;

	public Aquarium() {
		setTitle("The Aquarium");
		tracker = new MediaTracker(this);

		FishFactory fishFactory = new FishFactory();

		try {

			aquariumImage = Toolkit.getDefaultToolkit().getImage("aquarium.jpg");
			tracker.addImage(aquariumImage, 0);

			InputStream stream = getClass().getClassLoader().getResourceAsStream("aquarium.jpg");
			BufferedImage image = ImageIO.read(stream);
			tracker.addImage(aquariumImage, 0);
		} catch (IOException ex) {

			Toolkit tk = Toolkit.getDefaultToolkit();

			URL url = getClass().getResource("aquarium.jpg");
			aquariumImage = tk.createImage(url);
			tracker.addImage(aquariumImage, 0);

		}

		try {
			tracker.waitForID(0);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		setSize(aquariumImage.getWidth(this), aquariumImage.getHeight(this));

		setResizable(false);
		setVisible(true);

		memoryImage = createImage(getSize().width, getSize().height);
		memoryGraphics = memoryImage.getGraphics();
		g2 = (Graphics2D) memoryGraphics;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);

//		memoryImage2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//		memoryGraphics2 = memoryImage2.getGraphics();
//		g22 = (Graphics2D) memoryGraphics2;
//		g22.setRenderingHints(rh);
//		g22.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
//		g22.setColor(Color.red);
//		
		
		environment.setEdges(new Rectangle(0 + getInsets().left, 0 + getInsets().top, getSize().width - (getInsets().left + getInsets().right),
				getSize().height - (getInsets().top + getInsets().bottom)));
		// System.out.println(Environment.edges.width);

		for (int loopIndex = 0; loopIndex < numberFish; loopIndex++) {
			Environment.addFish(fishFactory.createFish());
		}

		System.out.println(Environment.fishes.size());

		thread = new Thread(this);
		thread.start();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				runOK = false;
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {

		// java.io.File currentDir = new java.io.File("");
		// System.out.println(currentDir.getAbsolutePath());

		new Aquarium();
	}

	public void run() {

		Fish fish;

		while (runOK) {
			// System.out.println(fishes.size());
			for (int loopIndex = 0; loopIndex < Environment.fishes.size(); loopIndex++) {
				fish = Environment.fishes.elementAt(loopIndex);
				fish.act();
			}

			try {
				Thread.sleep(sleepTime);
			} catch (Exception exp) {
				System.out.println(exp.getMessage());
			}
			// fishes.addElement(new Fish(edges, this));
			repaint();
		}
	}

	public void update(Graphics g) {
		// g2.rotate(.1*(Math.PI/180),aquariumImage.getWidth(this)/2,
		// aquariumImage.getHeight(this)/2);

		g2.drawImage(aquariumImage, 0, 0, this);

		for (int loopIndex = 0; loopIndex < Environment.fishes.size(); loopIndex++) {
			g2.drawImage(Environment.fishes.elementAt(loopIndex).drawFishImage(), (int)Environment.fishes.elementAt(loopIndex).getLocation().getX(), (int)Environment.fishes.elementAt(loopIndex).getLocation().getY(), this);
			
		}

//		g22.rotate(.5*(Math.PI /180),50,50);
//		g22.drawRect(20, 20, 60, 60);
		
		g.drawImage(memoryImage, 0, 0, this);
		g.drawImage(memoryImage2, 100, 100, this);

	}
}

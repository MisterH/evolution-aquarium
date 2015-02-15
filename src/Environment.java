import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;
import java.util.Vector;

public class Environment {
	static Random random = new Random(System.currentTimeMillis());
	static Rectangle edges = new Rectangle(0, 0);
	static final RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	static Vector<Fish> fishes = new Vector<Fish>();

	public static void setEdges(Rectangle rectangle) {
		edges = rectangle;

	}

	public static void addFish(Fish newFish) {
		fishes.add(newFish);
	}

	public static void removeFish(Fish fish) {
		fishes.remove(fish);
		System.out.println(fish + " removed from environment");
	}

}

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Renderer implements Rendering {
	private Point location;
	private Point lastLocation;
	private Point lastLocation2;
	private BufferedImage memoryImage;
	private Graphics memoryGraphics;
	private Graphics2D g2;

	public Image Render(Fish fish, RenderingHints rh) {

		// location = fish.getLocation();
		lastLocation = fish.getLastLocation();
		lastLocation2 = fish.getLastLocation2();

		location = new Point(20, 20);

		memoryImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB); // TODO:
																				// dynamic
																				// image
																				// size
		memoryGraphics = memoryImage.getGraphics();
		g2 = (Graphics2D) memoryGraphics;
		g2.setBackground(new Color(0, 0, 0, 0));
		g2.clearRect(0, 0, 400, 400); // TODO: dynamic image size
		g2.setRenderingHints(rh);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		if (Environment.random.nextDouble() > 0) {
			//g2.rotate(Environment.random.nextDouble(), 30, 30);
		//	AffineTransform transformer = new AffineTransform();
//			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	//		AffineTransform tx = AffineTransform.getScaleInstance(.06, .06);
		//		tx.translate(0, 0);
		//	AffineTransform op = new AffineTransform(tx, AffineTransform.TYPE_NEAREST_NEIGHBOR);
	//		image = op.filter(image, null);
			
		//	Graphics2D g2d = (Graphics2D)g;
	//		g2.setTransform(tx);

		}
		g2.setColor(fish.getColor());

		// fish.getNose().getX() + location.x,fish.getNose().getY()+ location.y
		Polygon p = new Polygon();
		Polygon p2 = new Polygon();
		Polygon p3 = new Polygon();
		Polygon p4 = new Polygon();
		Polygon p5 = new Polygon();

		p.addPoint((int) fish.getNose().getX() + location.x, (int) fish.getNose().getY() + location.y);
		p.addPoint((int) fish.getBodyTop().getX() + location.x, (int) fish.getBodyTop().getY() + location.y);
		p.addPoint((int) fish.getRearTop().getX() + location.x, (int) fish.getRearTop().getY() + location.y);
		p.addPoint((int) fish.getTailTop().getX() + location.x, (int) fish.getTailTop().getY() + location.y);
		p.addPoint((int) fish.getTailMiddle().getX() + location.x, (int) fish.getTailMiddle().getY() + location.y);
		p.addPoint((int) fish.getTailBottom().getX() + location.x, (int) fish.getTailBottom().getY() + location.y);
		p.addPoint((int) fish.getRearBottom().getX() + location.x, (int) fish.getRearBottom().getY() + location.y);
		p.addPoint((int) fish.getBodyBottom().getX() + location.x, (int) fish.getBodyBottom().getY() + location.y);

		p2.addPoint((int) fish.getBodyTop().getX() + location.x, (int) fish.getBodyTop().getY() + location.y);
		p2.addPoint((int) fish.getFinTop().getX() + location.x, (int) fish.getFinTop().getY() + location.y);
		p2.addPoint((int) fish.getRearTop().getX() + location.x, (int) fish.getRearTop().getY() + location.y);

		p3.addPoint((int) fish.getBodyBottom().getX() + location.x, (int) fish.getBodyBottom().getY() + location.y);
		p3.addPoint((int) fish.getFinBottom().getX() + location.x, (int) fish.getFinBottom().getY() + location.y);
		p3.addPoint((int) fish.getRearBottom().getX() + location.x, (int) fish.getRearBottom().getY() + location.y);

		p4.addPoint((int) fish.getNose().getX() + location.x, (int) fish.getNose().getY() + location.y);
		p4.addPoint((int) fish.getBodyTop().getX() + location.x, (int) fish.getBodyTop().getY() + location.y);
		p4.addPoint((int) fish.getRearTop().getX() + location.x, (int) fish.getRearTop().getY() + location.y);

		p5.addPoint((int) fish.getNose().getX() + location.x, (int) fish.getNose().getY() + location.y);
		p5.addPoint((int) fish.getRearBottom().getX() + location.x, (int) fish.getRearBottom().getY() + location.y);
		p5.addPoint((int) fish.getBodyBottom().getX() + location.x, (int) fish.getBodyBottom().getY() + location.y);

		g2.fillPolygon(p);
		g2.setColor(new Color(fish.getColor().getRed(), fish.getColor().getGreen(), fish.getColor().getBlue(), 150));
		g2.fillPolygon(p2);
		g2.fillPolygon(p3);
		g2.setColor(fish.getColor().brighter());
		g2.fillPolygon(p4);
		g2.setColor(fish.getColor().darker());
		g2.fillPolygon(p5);
		// g.drawLine(location.x, location.y, lastLocation.x, lastLocation.y);
		// g.drawLine(lastLocation.x, lastLocation.y, lastLocation2.x,
		// lastLocation2.y);
		return memoryImage;
	}
}

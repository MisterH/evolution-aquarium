import java.awt.Point;

public interface Swimming {

	public Point swim(Point currentLocation, Point target);
	public Point getVelocity();
	public void setVelocity(Point newVelocity);

}

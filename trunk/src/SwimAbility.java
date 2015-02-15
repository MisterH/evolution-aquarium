import java.awt.Point;

public class SwimAbility implements Swimming {
	Point velocity = new Point(0, 0);
	
	public SwimAbility() {

	}

	public Point swim(Point currentLocation, Point target) {
		
		//Default behaviour is to swim around randomly
		
		if (Environment.random.nextInt() % 17 <= 1) {

			velocity.x += Environment.random.nextInt() % 4;

			velocity.x = Math.min(velocity.x, 5);
			velocity.x = Math.max(velocity.x, -5);

			velocity.y += Environment.random.nextInt() % 4;

			velocity.y = Math.min(velocity.y, 3);
			velocity.y = Math.max(velocity.y, -3);

		}

		return new Point(currentLocation.x + velocity.x, currentLocation.y + velocity.y);
	}

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {

		this.velocity = velocity;
	}


}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;

class Fish extends Observable {
	// static Component tank;
	private SwimAbility swimAbility = new SwimAbility();
	private Renderer fishRenderer = new Renderer();
	private Point location;
	private Point target;
	private Point lastLocation;
	private Point lastLocation2;

	// general Attributes
	private boolean isAlive;
	private long dob;
	private int weight;
	private int maxWeight;
	private int minWeight;

	// movement attributes
	private double speed;
	private double heading;
	private double maxTurnRate;
	private double maxSpeed;
	private double maxAcceleration;

	// rendering attributes
	private double noseOffset; // the vertical offset of the nose
	private int bodyHeight;
	private int bodyLength;
	private double upperMidriff; // the horizontal position of the upper
									// mid-body point as a percentage of length
	private double lowerMidriff; // the horizontal position of the lower
									// mid-body point as a percentage of length
	private int tailHeight1; // the height of the tail where it joins the body
	private int TailHeight2; // the tip to tip height of the tail
	private int upperTailLength; // the horizontal length of the tail to the top
									// tip from where the tail joins the body
	private int lowerTailLength;
	private double TailDelta; // the horizontal position

	private Point nose;
	private Point bodyTop;
	private Point bodyBottom;
	private Point finTop;
	private Point finBottom;
	private Point rearTop;
	private Point rearBottom;
	private Point tailTop;
	private Point tailMiddle;
	private Point tailBottom;

	private Color color;
	private Color blurColor;
	private int shimmerDuration = 0;

	private int shimmerAmount =10;
	int colorMin = 128 - shimmerAmount;
	int colorRange = 255 - shimmerAmount - colorMin;

	public Fish(Rectangle edges) {
		dob = System.currentTimeMillis();
		concieve();
		isAlive = true;
		setColor(Color.GREEN);
		target = setTarget();
		this.location = new Point(400, 400);
		lastLocation = new Point(400, 400);
		lastLocation2 = new Point(400, 400);
		this.setColor(new Color((int) (Environment.random.nextInt() % colorMin) + colorRange, (int) (Environment.random.nextInt() % colorMin) + colorRange,
				(int) (Environment.random.nextInt() % colorMin) + colorRange));
	}

	private void concieve() {

		// nose = new Point(0, 0);
		// bodyTop = new Point(10, -5);
		// bodyBottom = new Point(9, 5);
		// finTop = new Point(15, -8);
		// finBottom = new Point(14, 7);
		// rearTop = new Point(20, -2);
		// rearBottom = new Point(20, 2);
		// tailTop = new Point(30, -9);
		// tailMiddle = new Point(23, 0);
		// tailBottom = new Point(27, 7);

		int delta1 = 2;
		int delta2 = 1;
		nose = new Point(0, 0);
		bodyTop = new Point(10 + (Environment.random.nextInt(delta1) - delta2), -5 + (Environment.random.nextInt(delta1) - delta2));
		bodyBottom = new Point(9 + (Environment.random.nextInt(delta1) - delta2), 5 + (Environment.random.nextInt(delta1) - delta2));
		finTop = new Point(15 + (Environment.random.nextInt(2*delta1) - 2*delta2), -8 + (Environment.random.nextInt(delta1) - delta2));
		finBottom = new Point(14 + (Environment.random.nextInt(delta1) - delta2), 7 + (Environment.random.nextInt(delta1) - delta2));
		rearTop = new Point(20 + (Environment.random.nextInt(delta1) - delta2), -2 + (Environment.random.nextInt(delta1) - delta2));
		rearBottom = new Point(20 + (Environment.random.nextInt(delta1) - delta2), 2 + (Environment.random.nextInt(delta1) - delta2));
		tailTop = new Point(30 + (Environment.random.nextInt(delta1) - delta2), -9 + (Environment.random.nextInt(delta1) - delta2));
		tailMiddle = new Point(23 + (Environment.random.nextInt(delta1) - delta2), 0 + (Environment.random.nextInt(delta1) - delta2));
		tailBottom = new Point(27 + (Environment.random.nextInt(delta1) - delta2), 7 + (Environment.random.nextInt(delta1) - delta2));
	}

	public void act() {

		if (shimmerDuration > 0) {

			if (shimmerDuration % 2 == 1) {
				setColor(new Color(color.getRed() - shimmerAmount, color.getGreen() - shimmerAmount, color.getBlue() - shimmerAmount));
			} else {
				setColor(new Color(color.getRed() + shimmerAmount, color.getGreen() + shimmerAmount, color.getBlue() + shimmerAmount));

			}
			shimmerDuration--;

		} else if (Environment.random.nextDouble() > .999) {
			shimmerDuration = 20;
		}

		if (location.equals(target)) {
			System.out.println(this + " reached target");
			target = setTarget();
		} else {
			lastLocation2.setLocation(lastLocation.x, lastLocation.y);
			lastLocation.setLocation(location.x, location.y);
			location = move(location);
		}

		if (location.x < Environment.edges.x) {
			location.x = Environment.edges.x;

		}

		if ((location.x) > (Environment.edges.x + Environment.edges.width)) {
			location.x = Environment.edges.x + Environment.edges.width;

		}

		if (location.y < Environment.edges.y) {
			location.y = Environment.edges.y;

		}

		if ((location.y) > (Environment.edges.y + Environment.edges.height)) {
			location.y = Environment.edges.y + Environment.edges.height;

		}

	}

	private Point setTarget() {

		return new Point(Environment.random.nextInt(Environment.edges.width), Environment.random.nextInt(Environment.edges.height));
	}

	private Point move(Point currentLocation) {
		return swimAbility.swim(currentLocation, target);
	}

	public Image drawFishImage() {
		return fishRenderer.Render(this, Environment.rh);
	}

	public Color getColor() {
		return color;
	}

	public Color getBlurColor() {
		return blurColor;
	}

	public void setColor(Color color) {
		this.color = color;
		this.blurColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 35);
	}

	public void setSwim(SwimAbility swim) {
		this.swimAbility = swim;
	}

	private void remove() {
		Environment.removeFish(this);
	}

	private void die() {
		System.out.println(this + " died.");
		isAlive = false;
	}

	public SwimAbility getSwimAbility() {
		return swimAbility;
	}

	public void setSwimAbility(SwimAbility swimAbility) {
		this.swimAbility = swimAbility;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public long age() {

		return System.currentTimeMillis() - dob;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public long getDob() {
		return dob;
	}

	public void setDob(long dob) {
		this.dob = dob;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
		if (weight < minWeight)
			die();
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(int minWeight) {
		this.minWeight = minWeight;
	}

	public Point getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Point lastLocation) {
		this.lastLocation = lastLocation;
	}

	public Point getLastLocation2() {
		return lastLocation2;
	}

	public void setLastLocation2(Point lastLocation2) {
		this.lastLocation2 = lastLocation2;
	}

	public Point getNose() {
		return nose;
	}

	public void setNose(Point nose) {
		this.nose = nose;
	}

	public Point getBodyTop() {
		return bodyTop;
	}

	public void setBodyTop(Point bodyTop) {
		this.bodyTop = bodyTop;
	}

	public Point getBodyBottom() {
		return bodyBottom;
	}

	public void setBodyBottom(Point bodyBottom) {
		this.bodyBottom = bodyBottom;
	}

	public Point getFinTop() {
		return finTop;
	}

	public void setFinTop(Point finTop) {
		this.finTop = finTop;
	}

	public Point getFinBottom() {
		return finBottom;
	}

	public void setFinBottom(Point finBottom) {
		this.finBottom = finBottom;
	}

	public Point getRearTop() {
		return rearTop;
	}

	public void setRearTop(Point rearTop) {
		this.rearTop = rearTop;
	}

	public Point getRearBottom() {
		return rearBottom;
	}

	public void setRearBottom(Point rearBottom) {
		this.rearBottom = rearBottom;
	}

	public Point getTailTop() {
		return tailTop;
	}

	public void setTailTop(Point tailTop) {
		this.tailTop = tailTop;
	}

	public Point getTailMiddle() {
		return tailMiddle;
	}

	public void setTailMiddle(Point tailMiddle) {
		this.tailMiddle = tailMiddle;
	}

	public Point getTailBottom() {
		return tailBottom;
	}

	public void setTailBottom(Point tailBottom) {
		this.tailBottom = tailBottom;
	}
}
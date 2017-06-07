package model;
/**
 * Model a location in a city.
 * 
 * @author EMAKO TIENTCHEU
 * @version 
 */
public class Location
{
	private int x;
	private int y;

	public Location(int x, int y) {
		if (x < 0) {
			throw new IllegalArgumentException("Negative x-coordinate: " + x);
		}
		if (y < 0) {
			throw new IllegalArgumentException("Negative y-coordinate: " + y);
		}
		this.x = x;
		this.y = y;
	}

	public Location nextLocation(Location destination) {
		int destX = destination.getX();
		int destY = destination.getY();
		int offsetX = x > destX ? -1 : x < destX ? 1 : 0;
		int offsetY = y > destY ? -1 : y < destY ? 1 : 0;
		if ((offsetX != 0) || (offsetY != 0)) {
			return new Location(x + offsetX, y + offsetY);
		}
		return destination;
	}

	public int distance(Location destination) {
		int xDist = Math.abs(destination.getX() - x);
		int yDist = Math.abs(destination.getY() - y);
		return Math.max(xDist, yDist);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Location) {
			Location otherLocation = (Location) other;
			return x == otherLocation.getX() && y == otherLocation.getY();
		} else
			return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 8));
		result = prime * result + (int) (y ^ (y >>> 16));
		return result;
	}

	@Override
	public String toString() {
		return "location " + x + "," + y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

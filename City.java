package model;
import java.util.LinkedList;
import java.util.List;
    
/**
 * A collection of items in the city.
 * 
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class City
{
	private List<Item> items;
	private int width;
	private int height;

	private static final int DEFAULT_WIDTH = 35;
	private static final int DEFAULT_HEIGHT = 35;

	public City(int width, int height) {
		if (width < 1) {
			throw new IllegalArgumentException("Width must be positive: " + width);
		}
		if (height < 1) {
			throw new IllegalArgumentException("Height must be positive: " + height);
		}
		this.width = width;
		this.height = height;
		items = new LinkedList<Item>();
	}

	public City() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public void addItem(Item item) {
		if (items.contains(item)) {
			throw new IllegalArgumentException(item + " already recorded in the city.");
		}
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void removeItem(Item item) {
		if (!items.remove(item)) {
			throw new IllegalArgumentException(item + " is not in the city.");
		}
	}

	@Override
	public String toString() {
		return "City size " + width + " by " + height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

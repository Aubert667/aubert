package model;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Model a passenger wishing to get from one
 * location to another.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class Passenger implements DrawableItem
{
	private Location pickup;
	private Location destination;
	private Image image;

	public Passenger(Location pickup, Location destination) {
		if (pickup == null || destination == null)
			throw new NullPointerException("cannot be null");
		this.pickup = pickup;
		this.destination = destination;
		image = new ImageIcon(getClass().getResource("/images/person.jpg")).getImage();
	}

	public int getNbPersons() {
		return 1;
	}

	@Override
	public String toString() {
		return "Passenger travelling from " + pickup + " to " + destination;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Location getLocation() {
		return pickup;
	}

	public Location getPickupLocation() {
		return pickup;
	}

	public Location getDestination() {
		return destination;
	}
}

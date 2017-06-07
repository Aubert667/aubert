package model;
import javax.swing.ImageIcon;

import java.awt.Image;

/**
 * A shuttle is able to carry multiple passengers.
 * This implementation is non-functional.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class Shuttle extends Vehicle implements DrawableItem
{
	private final int capacity;

	private Passenger passenger;
	private Image emptyImage;
	private Image passengerImage;

	public Shuttle(TaxiCompany company, Location location, String id) {
		super(company, location, id);
		capacity = 10;
		emptyImage = new ImageIcon(getClass().getResource("/images/bus.jpg")).getImage();
		passengerImage = new ImageIcon(getClass().getResource("/images/bus+persons.jpg")).getImage();

	}

	public void act() {
		Location target = getTargetLocation();
		if (target != null) {
			// Find where to move to next.
			Location next = getLocation().nextLocation(target);
			setLocation(next);
			if (next.equals(target)) {
				if (passenger != null) {
					notifyPassengerArrival(passenger);
					offloadPassenger();
				} else
					notifyPickupArrival();
			}
		} else
			incrementIdleCount();
	}

	@Override
	public boolean canPickupPassenger(Passenger passenger) {
		if (isFree())
			return true;
		return false;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public boolean isFree() {
		return getTargetLocation() == null && passenger == null;
	}

	public void setPickupLocation(Location location) {
		setTargetLocation(location);
	}

	public void pickup(Passenger passenger) {
		this.passenger = passenger;
		setTargetLocation(passenger.getDestination());
	}

	public void offloadPassenger() {
		passenger = null;
		clearTargetLocation();
	}

	@Override
	public Image getImage() {
		if (passenger != null) {
			return passengerImage;
		} else {
			return emptyImage;
		}
	}

	@Override
	public String toString() {
		return "Taxi at (" + getLocation() + ")";
	}

}

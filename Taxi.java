package model;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A taxi is able to carry a single passenger.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class Taxi extends Vehicle implements DrawableItem
{
	
	private Passenger passenger;
	private Image emptyImage;
	private Image passengerImage;

	public Taxi(TaxiCompany company, Location location, String id) {
		super(company, location, id);
		emptyImage = new ImageIcon(getClass().getResource("/images/taxi.jpg")).getImage();
		passengerImage = new ImageIcon(getClass().getResource("/images/taxi+person.jpg")).getImage();

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
		if (isFree() && !(passenger instanceof PassengerGroup))
			return true;
		return false;
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

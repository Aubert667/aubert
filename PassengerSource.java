package model;
import java.util.Random;

/**
 * Periodically generate passengers.
 * Keep track of the number of passengers for whom
 * a vehicle cannot be found.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class PassengerSource implements Actor
{

	private static final double CREATION_PROBABILITY = 0.2;

	private City city;
	private TaxiCompany company;
	private Random rand;
	private int missedPickups;

	public PassengerSource(City city, TaxiCompany company) {
		if (city == null) {
			throw new NullPointerException("city");
		}
		if (company == null) {
			throw new NullPointerException("company");
		}
		this.city = city;
		this.company = company;
		rand = new Random();
		missedPickups = 0;
	}

	public void act() {
		if (rand.nextDouble() <= CREATION_PROBABILITY) {
			Passenger passenger = createPassenger();
			if (company.requestPickup(passenger))
				city.addItem(passenger);
			else
				missedPickups += passenger.getNbPersons();
		}
	}

	public int getMissedPickups() {
		return missedPickups;
	}

	private Passenger createPassenger() {
		int cityWidth = city.getWidth();
		int cityHeight = city.getHeight();

		Location pickupLocation = new Location(rand.nextInt(cityWidth), rand.nextInt(cityHeight));
		Location destination;
		do {
			destination = new Location(rand.nextInt(cityWidth), rand.nextInt(cityHeight));
		} while (pickupLocation.equals(destination));
		if (Math.random() <= 0.5)
			return new Passenger(pickupLocation, destination);
		else
			return new PassengerGroup(pickupLocation, destination);
	}
}

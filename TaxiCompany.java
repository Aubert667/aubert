package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle. This version operates a only taxis.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class TaxiCompany
{
	private static int NUMBER_OF_TAXIS;
	private static int NUMBER_OF_SHUTTLES;

	private List<Vehicle> vehicles;

	private City city;

	private Map<Vehicle, Passenger> assignments;

	public TaxiCompany(City city, int nb_taxis, int nb_shuttles) {
		this.city = city;
		NUMBER_OF_TAXIS = nb_taxis;
		NUMBER_OF_SHUTTLES = nb_shuttles;
		vehicles = new LinkedList<>();
		assignments = new HashMap<>();
		setupVehicles();
	}

	public City getCity() {
		return city;
	}

	public boolean requestPickup(Passenger passenger) {
		Vehicle vehicle = scheduleVehicle(passenger);
		if (vehicle != null) {
			assignments.put(vehicle, passenger);
			vehicle.setPickupLocation(passenger.getPickupLocation());
			return true;
		} else
			return false;
	}

	public void arrivedAtPickup(Vehicle vehicle) {
		Passenger passenger = (Passenger) assignments.remove(vehicle);
		city.removeItem(passenger);
		vehicle.pickup(passenger);
	}

	public void arrivedAtDestination(Vehicle vehicle, Passenger passenger) {
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	private Vehicle scheduleVehicle(Passenger p) {
		List<Vehicle> freeVehicles = new ArrayList<>();
		for (Vehicle v : vehicles)
			if (v.canPickupPassenger(p))
				freeVehicles.add(v);
		if (freeVehicles.isEmpty())
			return null;
		if (freeVehicles.size() == 1)
			return freeVehicles.get(0);

		Vehicle result = null;
		int min = new Location(0, 0).distance(new Location(city.getWidth(), city.getHeight())) + 1;
		int distanceTemp = 0;
		for (Vehicle v : freeVehicles) {
			if ((distanceTemp = p.getLocation().distance(v.getLocation())) < min) {
				min = distanceTemp;
				result = v;
			}
		}
		return result;
	}

	private void setupVehicles() {
		int cityWidth = city.getWidth();
		int cityHeight = city.getHeight();

		Random rand = new Random(12345);

		for (int i = 0; i < NUMBER_OF_TAXIS; i++) {
			Taxi taxi = new Taxi(this, 
					new Location(rand.nextInt(cityWidth), rand.nextInt(cityHeight)),
					"T" + i);
			vehicles.add(taxi);
			city.addItem(taxi);
		}
		
		for (int i = 0; i < NUMBER_OF_SHUTTLES; i++) {
			Shuttle shuttle = new Shuttle(this, 
					new Location(rand.nextInt(cityWidth), rand.nextInt(cityHeight)),
					"T" + i);
			vehicles.add(shuttle);
			city.addItem(shuttle);
		}
	}
	
}

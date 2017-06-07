package model;

import java.awt.Image;

import javax.swing.ImageIcon;



public class PassengerGroup extends Passenger {
	
	
	private static final int NB_PERSONS = 3;

	private Location pickup;
	private Location destination;

	public PassengerGroup(Location pickup, Location destination) {
		super(pickup, destination);
		Image newImage = new ImageIcon(getClass().getResource("/images/persons.jpg")).getImage();
		setImage(newImage);
	}

	@Override
	public int getNbPersons() {
		return NB_PERSONS;
	}

	@Override
	public String toString() {
		return "Passenger group travelling from " + pickup + " to " + destination;
	}

}

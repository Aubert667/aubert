package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import model.Actor;
import model.City;
import model.PassengerSource;
import model.TaxiCompany;



import view.CityGUI;

/**
 * Run the simulation by asking a collection of actors to act.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class Simulation
{
	private List<Actor> actors;

	public Simulation() {

		CountDownLatch latch = new CountDownLatch(1);
		ConfigReader config = new ConfigReader(latch);
		config.run();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		actors = new LinkedList<>();
		City city = new City(config.getWidth(), config.getHeight());

		TaxiCompany company = new TaxiCompany(city, config.getNumber_of_taxis(), config.getNumber_of_shuttles());
		PassengerSource source = new PassengerSource(city, company);

		actors.addAll(company.getVehicles());
		actors.add(source);
		actors.add(new CityGUI(city));

	}

	public void run() {
		for (int i = 0; i < 300; i++) {
			step();
			wait(400);
		}
		System.out.println("End simulation");
	}

	public void step() {
		for (Actor actor : actors) {
			actor.act();
		}
	}

	private void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
}

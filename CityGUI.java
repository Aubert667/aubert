package view;

import java.awt.*;
import javax.swing.*;
import model.Actor;
import model.City;
import model.DrawableItem;
import model.Item;
import model.Taxi;
import model.Location;
import model.Passenger;

/**
 * Provide a view of the vehicles and passengers in the city.
 *
 * @author EMAKO TIENTCHEU
 * @version 2017
 */
public class CityGUI extends JFrame implements Actor
{
	
	private static final long serialVersionUID = 7688;
	private City city;
	private CityView cityView;
	private JPanel panel_A, panel_B;
	private JLabel taxisLabel;
	private JLabel freeTaxisLabel;
	private JLabel waitingPeople;

	public CityGUI(City city) {
		super("Simulation of taxis operating on a city grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.city = city;
		cityView = new CityView(city.getWidth(), city.getHeight());

		panel_A = new JPanel(new GridBagLayout());


		panel_B = new JPanel(new GridLayout(0, 3));

		panel_B.add(
				new JLabel("dimesion : width = " + city.getWidth() + " , height = " + city.getHeight()));
		panel_B.add(taxisLabel = new JLabel("taxis number : "));
		panel_B.add(freeTaxisLabel = new JLabel("free taxis number : "));
		panel_B.add(waitingPeople = new JLabel("waiting people : "));

		createContentPane();
		displayGUI();
	}

	private void createContentPane() {
		panel_A.add(cityView);
		add(panel_A, BorderLayout.CENTER);
		add(panel_B, BorderLayout.SOUTH);
	}

	private void displayGUI() {
		setPreferredSize(new Dimension(600, 600));
		setMinimumSize(new Dimension(400, 400));
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		pack();

		setLocationRelativeTo(null);
		// Display
		setVisible(true);
	}

	public void act() {
		cityView.preparePaint();

		int nb_taxis = 0, nb_taxis_libres = 0, nb_psg = 0;

		for (Item item : city.getItems()) {
			if (item instanceof DrawableItem) {
				if (item instanceof Taxi) {
					nb_taxis++;
					if (((Taxi) item).isFree())
						nb_taxis_libres++;
				}
				if (item instanceof Passenger)
					nb_psg++;
				DrawableItem it = (DrawableItem) item;
				Location location = it.getLocation();
				cityView.drawImage(location.getX(), location.getY(), it.getImage());
			}

		}
		
		taxisLabel.setText("taxis number : " + nb_taxis);
		freeTaxisLabel.setText("free taxis number : " + nb_taxis_libres);
		waitingPeople.setText("waiting people : " + nb_psg);


		repaint();
	}

	private class CityView extends JPanel {
		static final long serialVersionUID = 20131230;

		private final int VIEW_SCALING_FACTOR = 10;

		private int cityWidth, cityHeight;
		private int xScale, yScale; // panel size / city size
		private Dimension size; // size of this panel
		private Graphics g;
		private Image cityImage;

		/**
		 * Create a new CityView component.
		 */
		public CityView(int cityWidth, int cityHeight) {
			this.cityWidth = cityWidth;
			this.cityHeight = cityHeight;
			setBackground(Color.white);
			size = new Dimension(0, 0);
		}

		public void preparePaint() {

			// Draw the grid
			g.setColor(Color.white);
			g.fillRect(0, 0, size.width, size.height);

			g.setColor(Color.gray);
			for (int i = 0, x = 0; x < size.width; i++, x = i * xScale) {
				g.drawLine(x, 0, x, size.height - 1);
			}
			for (int i = 0, y = 0; y < size.height; i++, y = i * yScale) {
				g.drawLine(0, y, size.width - 1, y);
			}
		}

		/**
		 * Draw the image for a particular item.
		 */
		public void drawImage(int x, int y, Image image) {
			g.drawImage(image, x * xScale + 1, y * yScale + 1, xScale - 1, yScale - 1, this);
		}

		/**
		 * The city view component needs to be redisplayed. Copy the internal
		 * image to screen.
		 */
		@Override
		public void paintComponent(Graphics g) {
			if (cityImage != null) {
				g.drawImage(cityImage, 0, 0, null);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			Dimension size_of_parent = getParent().getSize();
			double width_city_view;
			double height_city_view;
			double ratio_1 = (size_of_parent.width) / ((double) size_of_parent.height);
			double ratio_2 = (cityWidth) / ((double) cityHeight);
			height_city_view = (width_city_view = ratio_2 >= ratio_1 ? 
						size_of_parent.width : 
							size_of_parent.height * ratio_2) / ratio_2;
			int real_widht;
			int real_height;

			xScale = (real_widht = ((int) width_city_view) - ((int) width_city_view) % cityWidth) / cityWidth;
			yScale = (real_height = ((int) height_city_view) - ((int) height_city_view) % cityHeight) / cityHeight;
			cityImage = cityView.createImage(real_widht, real_height);
			g = cityImage.getGraphics();
			
			
			xScale = xScale < 1 ? VIEW_SCALING_FACTOR : xScale;
			yScale = yScale < 1 ? VIEW_SCALING_FACTOR : yScale;
			return (size = new Dimension(real_widht, real_height));
		}

	} // End internal class CityView


} 

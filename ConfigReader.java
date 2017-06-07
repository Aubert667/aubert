package controller;

import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ConfigReader {
	
	
	private JFrame frame;
	private CountDownLatch latch;

	private int number_of_taxis;
	private int number_of_shuttles;
	private int width;
	private int height;

	// Create the components we will put in the form
	JButton confirm;
	JLabel taxisNumberLabel, shuttlesNumberLabel, widthLabel, heightLabel;
	JTextField taxisField, shuttlesField, widthField, heightField;

	public ConfigReader(CountDownLatch latch) {
		this.latch = latch;
	}

	public void build() {

		// Create the components we will put in the form
		taxisNumberLabel = new JLabel("Nombre de taxis : ");
		taxisField = new JTextField(5);
		shuttlesNumberLabel = new JLabel("Nombre de navettes : ");
		shuttlesField = new JTextField(5);
		widthLabel = new JLabel("Largeur de la ville : ");
		widthField = new JTextField(5);
		heightLabel = new JLabel("Hauteur de la ville : ");
		heightField = new JTextField(5);
		confirm = new JButton("Confirm");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildContentPane();
		frame.pack();

		// put the window int the midlle of the screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void buildContentPane() {
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;

		gc.weightx = 2;
		gc.weighty = 2;

		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 2;
		panel.add(taxisNumberLabel, gc);

		gc.gridy = 1;
		panel.add(shuttlesNumberLabel, gc);

		gc.gridy = 2;
		panel.add(widthLabel, gc);

		gc.gridy = 3;
		panel.add(heightLabel, gc);

		gc.gridx = 2;
		gc.gridy = 0;
		gc.gridwidth = 1;
		panel.add(taxisField, gc);

		gc.gridy = 1;
		panel.add(shuttlesField, gc);

		gc.gridy = 2;
		panel.add(widthField, gc);

		gc.gridy = 3;
		panel.add(heightField, gc);

		gc.gridx = 0;
		gc.gridy = 4;
		panel.add(confirm, gc);

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					number_of_taxis = Integer.parseInt(taxisField.getText());
					number_of_shuttles = Integer.parseInt(shuttlesField.getText());
					width = Integer.parseInt(widthField.getText());
					height = Integer.parseInt(heightField.getText());

					latch.countDown();
					// close the window if all inputs are correct
					frame.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Input numbers please!", "Inane warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		frame.getRootPane().setDefaultButton(confirm);
	}


	
	public int getNumber_of_taxis() {
		return number_of_taxis;
	}

	public int getNumber_of_shuttles() {
		return number_of_shuttles;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void run() {
		frame = new JFrame("Configuration");
		build();
	}

	
}

package software_Engineering_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SecondFrame extends JFrame {

	private JTable table;
	DefaultTableModel model;
	protected JTextField txtId;
	private JScrollPane scrollPane;
	protected SecondFrame secondFrame = this;
	// private boolean isLinked;
	static Bike bikeOfuser;
	private int bikeID;
	private enterID enterid;
	private ArrayList<Bike> blist = new ArrayList<Bike>();
	static ArrayList<Bike> availBikes = new ArrayList<Bike>();
	private ArrayList<Bike> occupiedBikes = new ArrayList<Bike>();
	private ArrayList<Bike> bikesToshow = new ArrayList<Bike>();
	private String[] dockingStation = { "SciTech", "Humanities", "Law", "ScoSci", "Backgate", "StudentUnion" };

	SecondFrame() {
		this.setTitle(BicycleRental.SecondFrameName + "-Available Bikes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // mainframe.setUndecorated(true);
		this.setLocation(500, 90);
		this.setPreferredSize(new Dimension(400, 680));

		String[] columnNames = { "ID Num", "Ratings", "Condition", "Price", "LateFee" };
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setBackground(Color.gray);
		table.setOpaque(true);
		scrollPane = new JScrollPane(table);

		JButton backButton = new JButton("Back");
		JButton linkButton = new JButton("Link bike");
		JButton unlinkButton = new JButton("Unlink bike");

		JPanel display = new JPanel(new GridLayout());
		JPanel buttonPanel = new JPanel(new GridLayout());

		display.add(scrollPane);
		buttonPanel.add(backButton);
		buttonPanel.add(linkButton);
		buttonPanel.add(unlinkButton);
		backButton.setBackground(Color.GREEN);
		linkButton.setBackground(Color.GREEN);
		unlinkButton.setBackground(Color.GREEN);
		backButton.addActionListener(new backButtonaction());
		linkButton.addActionListener(new linkaction());
		unlinkButton.addActionListener(new unlinkaction());

		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(display);
		this.pack();
		this.setVisible(true);

		this.getAvaiablebikesfromList();
		this.loadBikesbyLocation(BicycleRental.location, availBikes);
		this.showTableData(bikesToshow);

		try (Scanner bscan = new Scanner(new File("userbike.txt"))) {
			while (bscan.hasNext()) {
				String[] nextLine = bscan.nextLine().split("_");

				// Extract bike attributes from the line
				int bicycleID = Integer.parseInt(nextLine[0]);
				String location = nextLine[1];
				String userRating = nextLine[2];
				String condition = nextLine[3];
				boolean isAvailable = Boolean.parseBoolean(nextLine[4]);
				double price = Double.parseDouble(nextLine[5]);
				double latefee = Double.parseDouble(nextLine[6]);

				// Create a Bike object
				bikeOfuser = new Bike(bicycleID, location, condition, userRating, isAvailable, price, latefee);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addToTable(Bike bike)
	// Adds an animal to the table
	{
		String[] item = { "" + bike.getBicycleID(), "" + bike.getCondition(), "" + bike.getUserRating(),
				bike.getPrice() + "0", bike.getlatefee() + "0" };
		model.addRow(item);
	}

	public void showTableData(ArrayList<Bike> bikesToshow) {
		if (bikesToshow.size() > 0) {
			for (Bike b : bikesToshow) {
				addToTable(b);
			}
		}

	}

	// searches for the bike to see if it exists
	public boolean bikeExist(int bicycleID) {
		for (Bike bike : availBikes) {
			if (bike.getBicycleID() == (bicycleID)) {
				return true;
			}
		}
		return false;
	}

	// return a bike from its id
	public Bike findBike(int bicycleID) {
		for (Bike bike : availBikes) {
			if (bike.getBicycleID() == (bicycleID)) {
				return bike;
			}
		}
		return null;
	}

	public void getAvaiablebikesfromList() {
		availBikes.clear();
		blist = Bike.addbikes("file.txt");
		if (blist.size() != 0) {
			for (Bike bike : blist) {
				if (bike.getAvailable()) {
					availBikes.add(bike);
				}
			}
		}

	}

	public void loadBikesbyLocation(String location, ArrayList<Bike> availBikes) {
		bikesToshow.clear();
		if (availBikes.size() != 0) {
			for (Bike b : availBikes) {
				if (b.getLocation().equals(location)) {
					bikesToshow.add(b);
				}
			}
		}
	}

	public void rentBike(int ID) {
		if (availBikes.size() != 0) {
			for (int b = 0; b < availBikes.size(); b++) {
				if (availBikes.get(b).getBicycleID() == ID) {
					bikeOfuser = availBikes.get(b);
					availBikes.remove(b);
					bikeOfuser.setAvailable(false);

				}
			}
		}
	}

	public void returnBike() {

		bikeOfuser.setLocation(BicycleRental.location);
		bikeOfuser.setAvailable(true);
		availBikes.add(bikeOfuser);
		bikeOfuser = null;

	}

	private class backButtonaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			secondFrame.dispose();
			new BicycleRental();

		}

	}

	private class unlinkaction extends JFrame implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (bikeOfuser != null) {
				returnBike();
				model.setRowCount(0);
				loadBikesbyLocation(BicycleRental.location, availBikes);
				showTableData(bikesToshow);
				new SuccessScreen("Unlinked with bike sucesfully");
				new UpdateFile();
			} else {
				new ErrorFrame("No bike currently likned to this account");
			}

		}

	}

	private class linkaction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (bikeOfuser == null) {
				new enterID();
			} else {
				new ErrorFrame("Unlink current bike before getting another");
			}

		}

	}

	private class enterID extends JFrame {

		private JPanel pDisplay = new JPanel();
		private JPanel bPanel = new JPanel();
		private JButton getButton = new JButton("Get");
		private JButton bkButton = new JButton("Back");

		enterID() {
			enterid = this;
			this.setTitle("ID Entry");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setLocation(550, 290);
			this.setPreferredSize(new Dimension(300, 200));

			pDisplay.add(new JLabel("Enter ID of Bike:"));
			txtId = new JTextField(20);
			pDisplay.add(txtId);
			bkButton.setBackground(Color.GREEN);
			getButton.setBackground(Color.GREEN);
			getButton.addActionListener(new getAction());
			bkButton.addActionListener(new bkAction());
			bPanel.add(bkButton);
			bPanel.add(getButton);

			this.add(pDisplay);
			this.add(bPanel, BorderLayout.SOUTH);
			this.pack();
			this.setVisible(true);
		}

	}

	private class getAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (txtId.getText().isEmpty()) {

				new ErrorFrame("Enter a Valid ID");

			} else {
				try {

					bikeID = Integer.parseInt(txtId.getText());
					if (bikeExist(bikeID) == true && findBike(bikeID).getLocation().equals(BicycleRental.location)) {
						new Time(findBike(bikeID).getPrice());
						rentBike(bikeID);
						model.setRowCount(0);
						loadBikesbyLocation(BicycleRental.location, availBikes);
						showTableData(bikesToshow);
						new UpdateFile();
						enterid.dispose();
						new SuccessScreen("Linked with bike" + bikeID + " sucesfully");
					}

					else {
						new ErrorFrame("Bike is not available");
					}

				} catch (NumberFormatException n) {
					new ErrorFrame("Enter Numbers only");
					// JOptionPane.showMessageDialog(null, "Enter Numbers Only", "Error",
					// JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	private class bkAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			enterid.dispose();

		}

	}

}

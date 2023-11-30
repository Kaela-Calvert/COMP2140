package software_Engineering_Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BicycleRental {
	static String SecondFrameName;
	static String location;
	private String[] dockingStation = { "SciTech", "Humanities", "MED", "ScoSci", "Backgate", "StudentUnion" };
	JFrame mainframe = new JFrame("UWI On-Wheels");

	/*
	 * public static void main(String[] args) {
	 * 
	 * javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run() {
	 * // new BicycleRental(); new ();
	 * 
	 * } });
	 * 
	 * }
	 */

	BicycleRental() {

		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setResizable(false); // mainframe.setUndecorated(true);
		mainframe.setLocation(500, 90);
		mainframe.setPreferredSize(new Dimension(390, 730));
		mainframe.setLayout(null);

		mainframe.getContentPane().setBackground(Color.GRAY);

		JButton SciTechButton = new JButton("SciTech Station");
		JButton HumanitiesButton = new JButton("HumanitIes Station");
		JButton MedButton = new JButton("MedSci Station");
		JButton SUButton = new JButton("Student Union Station");
		JButton ScoSciButton = new JButton("Social Sci Station");
		JButton BGButton = new JButton("Back Gate Station");
		JButton Mainmenu = new JButton("Back");

		SciTechButton.setBackground(Color.GREEN);
		HumanitiesButton.setBackground(Color.GREEN);
		MedButton.setBackground(Color.GREEN);
		SUButton.setBackground(Color.GREEN);
		ScoSciButton.setBackground(Color.GREEN);
		BGButton.setBackground(Color.GREEN);
		Mainmenu.setBackground(Color.GREEN);

		SUButton.setBounds(88, 30, 200, 50);
		SciTechButton.setBounds(88, 130, 200, 50);
		MedButton.setBounds(88, 230, 200, 50);
		HumanitiesButton.setBounds(88, 330, 200, 50);
		ScoSciButton.setBounds(88, 430, 200, 50);
		BGButton.setBounds(88, 530, 200, 50);
		Mainmenu.setBounds(88, 630, 200, 50);

		SUButton.addActionListener(new SUaction());
		SciTechButton.addActionListener(new SciTechaction());
		MedButton.addActionListener(new Medaction());
		HumanitiesButton.addActionListener(new Humanitiesaction());
		ScoSciButton.addActionListener(new ScoSciaction());
		BGButton.addActionListener(new BGaction());
		Mainmenu.addActionListener(new Menu());

		mainframe.add(SUButton);
		mainframe.add(SciTechButton);
		mainframe.add(MedButton);
		mainframe.add(HumanitiesButton);
		mainframe.add(ScoSciButton);
		mainframe.add(BGButton);
		mainframe.add(Mainmenu);
		mainframe.pack();
		mainframe.setVisible(true);

	}

	private class SUaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			SecondFrameName = "Studnet Union Station";
			location = dockingStation[5];
			mainframe.dispose();
			new SecondFrame();
		}

	}

	private class SciTechaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			SecondFrameName = "Science & Tech. Station";
			location = dockingStation[0];
			mainframe.dispose();
			new SecondFrame();
		}

	}

	private class Medaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			SecondFrameName = "Medical Sci. Station";
			location = dockingStation[2];
			mainframe.dispose();
			new SecondFrame();
		}

	}

	private class Humanitiesaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			SecondFrameName = "Humanities Station";
			location = dockingStation[1];
			mainframe.dispose();
			new SecondFrame();
		}

	}

	private class ScoSciaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			SecondFrameName = "Social Sci. Station";
			location = dockingStation[3];
			mainframe.dispose();
			new SecondFrame();
		}

	}

	private class BGaction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			SecondFrameName = "Back Gate Station";
			location = dockingStation[4];
			mainframe.dispose();
			new SecondFrame();
		}

	}

	private class Menu implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			mainframe.dispose();
			// UWIonWheelsApp.usermenu();
		}

	}

}

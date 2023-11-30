package software_Engineering_Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Time {

	JRadioButton radioButton1;
	JRadioButton radioButton2;
	JRadioButton radioButton3;
	JButton ok;
	double bikePrice;
	static double payPrice;

	Time(double bikePrice) {
		this.bikePrice = bikePrice;
		JFrame frame = new JFrame("Time Seclection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());

		JPanel panel = new JPanel();

		// Create buttons
		radioButton1 = new JRadioButton("5 hours");
		radioButton2 = new JRadioButton("1 hour");
		radioButton3 = new JRadioButton("10 minutes");
		ok = new JButton("done");
		ok.setBackground(Color.GREEN);

		// Create a button group to ensure only one radio button is selected at a time
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);

		ok.addActionListener(new oklistener());

		// Add radio buttons to the frame
		frame.add(radioButton1);
		frame.add(radioButton2);
		frame.add(radioButton3);

		panel.add(ok);

		frame.add(panel);
		frame.setSize(300, 150);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);

	}

	private class oklistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (radioButton1.isSelected()) {
				payPrice = 3 * bikePrice;
				System.out.println(payPrice);

			}
			if (radioButton2.isSelected()) {
				payPrice = 2 * bikePrice;
				System.out.println(payPrice);

			}
			if (radioButton3.isSelected()) {
				payPrice = bikePrice;
				System.out.println(payPrice);

			}
		}

	}

}

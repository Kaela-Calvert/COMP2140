package software_Engineering_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorFrame extends JFrame {

	private ErrorFrame ef = this;

	ErrorFrame(String Error) {
		JPanel panel = new JPanel();
		JPanel bpanel = new JPanel();
		JLabel label = new JLabel(Error);
		JButton ok = new JButton("Ok");
		ok.setBackground(Color.GREEN);
		this.setTitle("Error");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // mainframe.setUndecorated(true);
		this.setLocation(520, 290);
		this.setPreferredSize(new Dimension(350, 100));
		panel.add(label);
		bpanel.add(ok);
		ok.addActionListener(new okAction());
		this.add(panel, BorderLayout.CENTER);
		this.add(bpanel, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}

	private class okAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ef.dispose();
		}
	}

}

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

public class SuccessScreen extends JFrame {

	private SuccessScreen ss = this;

	SuccessScreen(String successType) {
		JPanel panel = new JPanel();
		JPanel bpanel = new JPanel();
		JLabel label = new JLabel(successType);
		JButton ok = new JButton("Ok");
		ok.setBackground(Color.GREEN);
		this.setTitle("Success!!");
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
			ss.dispose();
		}
	}

}

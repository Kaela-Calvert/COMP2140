
package software_Engineering_Project;

import java.awt.BorderLayout;
//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class IncidentReportViewer extends JFrame {
	private JTextArea textArea;

	public IncidentReportViewer() {
		setTitle("Incident Report Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);

		textArea = new JTextArea();
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane, BorderLayout.CENTER);

		loadIncidentReports("Incidents.txt");
	}

	private void loadIncidentReports(String filePath) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(filePath)));
			textArea.setText(content);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to load incident reports.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			IncidentReportViewer viewer = new IncidentReportViewer();
			viewer.setVisible(true);
		});
	}
}

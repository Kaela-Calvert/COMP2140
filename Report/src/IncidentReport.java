import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncidentReport extends JFrame {

    // User Information Fields
    private JTextField usernameText = new JTextField();
    private JTextField userIDText = new JTextField();
    private JTextField userPhone = new JTextField();
    private JTextField useremail = new JTextField();

    // Incident Details Fields
    private JTextField userdate = new JTextField();
    private JTextField usertime = new JTextField();
    private JTextField userlocation = new JTextField();
    private JTextArea userincident = new JTextArea();

    // Injury Information Fields
    private JRadioButton injuredyes = new JRadioButton("Yes");
    private JRadioButton injuredno = new JRadioButton("No");
    private JTextArea userinjuries = new JTextArea();

    // Witness Information Fields
    private JRadioButton witnessyes = new JRadioButton("Yes");
    private JRadioButton witnessno = new JRadioButton("No");
    private JTextField witnessname = new JTextField();
    private JTextField witnessnumber = new JTextField();

    // GUI Components
    private JPanel pnlDisplay;
    private JPanel pnlCommand;
    private JPanel pictureDisplay;
    private JButton cmdSubmit;
    private JButton cmdMenu;
    private IncidentReport thisframe;

    public IncidentReport() {
        setTitle("Incident Report");
        thisframe = this;

        // Panels and Layout
        pictureDisplay = new JPanel();
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel(new GridLayout(14, 1));

        // User Information Section
        addLabelAndField("Please enter Full name*:", usernameText);
        addLabelAndField("User IDnumber eg 620000000*:", userIDText);
        addLabelAndField("Phone Number xxx-xxx-xxxx*:", userPhone);
        addLabelAndField("Email Address*:", useremail);

        // Incident Details Section
        addLabelAndField("Date of incident yy-mm-dd*:", userdate);
        addLabelAndField("Time of incident*:", usertime);
        addLabelAndField("Location of incident eg. Student Union*:", userlocation);
        addLabelAndTextArea("Incident Details*:", userincident);

        // Injury Information Section
        addRadioButton("Person Injured:", injuredyes, injuredno);
        addLabelAndTextArea("Injuries Details:", userinjuries);

        // Witness Information Section
        addRadioButton("Witness:", witnessyes, witnessno);
        addLabelAndField("Witness Name:", witnessname);
        addLabelAndField("Witness Number:", witnessnumber);

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("Report/report.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension size = new Dimension(700, 200);
        picLabel.setPreferredSize(size);
        pictureDisplay.add(picLabel);

        // Buttons
        cmdSubmit = new JButton("Submit");
        cmdMenu = new JButton("Retur to Main Menu");

        pnlCommand.add(cmdMenu);
        pnlCommand.add(cmdSubmit);

        // Add action listeners for buttons if needed
        //cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdMenu.addActionListener(new CloseButtonListener());
        cmdSubmit.addActionListener(new SubmitButtonListener());

        // Add Components to Frame
        add(pnlDisplay, BorderLayout.CENTER);
        add(pictureDisplay, BorderLayout.NORTH);
        add(pnlCommand, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addLabelAndField(String labelText, JTextField textField) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2)); // Adjust the gaps here
        rowPanel.add(new JLabel(labelText));
        textField.setColumns(20);
        rowPanel.add(textField);
        pnlDisplay.add(rowPanel);
    }

    // Helper method to add a label and a text area
    private void addLabelAndTextArea(String labelText, JTextArea textArea) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        rowPanel.add(new JLabel(labelText));
        textArea.setColumns(20);
        textArea.setRows(3);
        rowPanel.add(new JScrollPane(textArea));
        pnlDisplay.add(rowPanel);
    }

    // Helper method to add a label and two checkboxes
    private void addRadioButton(String labelText, JRadioButton radioButton1, JRadioButton radioButton2) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2)); // Adjust the gaps here
        rowPanel.add(new JLabel(labelText));
        rowPanel.add(radioButton1);
        rowPanel.add(radioButton2);
        pnlDisplay.add(rowPanel);
    }

    private String checkemail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
        while (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(thisframe, "Invalid email format. Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            email = JOptionPane.showInputDialog(thisframe, "Enter email");
        }
    
        return email;
    }
    private String checkphone(String phone) {
        // Regular expression for the xxx-xxx-xxxx format
        String regex = "\\d{3}-\\d{3}-\\d{4}";

        while (!phone.matches(regex)) {
            JOptionPane.showMessageDialog(thisframe, "Invalid phone number format. Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            phone = JOptionPane.showInputDialog(thisframe, "Enter phone number:");
        }

        return phone;
    }   

    private String checkdate(String date) {
        String regex = "\\d{2}-\\d{2}-\\d{2}";

        while (!date.matches(regex)) {
            JOptionPane.showMessageDialog(thisframe, "Invalid date format. Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
            date = JOptionPane.showInputDialog(thisframe, "Enter date:");
        }

        return date;
    }  

    private String checkIdnum(String ID){
        String regex = "^62\\d{7}$";

        while (!ID.matches(regex)) {
            JOptionPane.showMessageDialog(thisframe, "Invalid ID number format. Please enter a valid ID number.", "Error", JOptionPane.ERROR_MESSAGE);
            ID = JOptionPane.showInputDialog(thisframe, "Enter ID number:");
        }

        return ID;
    }

    private boolean checkcomplete(String Id, String name, String phone, String email, String date, String time, String location, String incidentDetails){
        if (Id.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty() || date.isEmpty() || time.isEmpty() || location.isEmpty() || incidentDetails.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            thisframe.setVisible(false);
        }
    }

 public class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cmdSubmit.setEnabled(false);

            try{
                String ID = userIDText.getText();
                String fullName = usernameText.getText();
                String phone = userPhone.getText();
                String email = useremail.getText();
                String date = userdate.getText();
                String time = usertime.getText();
                String location = userlocation.getText();
                String incidentDetails = userincident.getText();
                boolean injured = injuredyes.isSelected();
                String injuriesDetails = userinjuries.getText();
                boolean hasWitness = witnessyes.isSelected();
                String witnessName = witnessname.getText();
                String witnessNumber = witnessnumber.getText();
                String injury;
                String wit;   

            if (injured) {
                injury = "Yes";
                injuredno.setSelected(false);
            } else {
                injury = "No";
                injuredyes.setSelected(false);
            }

            if (hasWitness) {
                wit = "Yes";
                witnessno.setSelected(false);
            } else {
                wit = "No";
                witnessyes.setSelected(false);
            }

            //System.out.println("File open for writing");
            if (checkcomplete(ID, fullName, phone, email, date, time, location, incidentDetails)==false){
                phone=checkphone(phone);
                email=checkemail(email);
                date=checkdate(date);
                ID=checkIdnum(ID);

                if(witnessNumber.isEmpty()){
                    witnessName="";
                }else{
                    witnessName=checkphone(witnessNumber);
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Incidents.txt", true))) {
                    // Write each field to the file'
                    writer.write("ID: " + ID);
                    writer.newLine();
                    writer.write("Full Name: " + fullName);
                    writer.newLine();
                    writer.write("Phone: " + phone);
                    writer.newLine();
                    writer.write("Email: " + email);
                    writer.newLine();
                    writer.write("Date: " + date);
                    writer.newLine();
                    writer.write("Time: " + time);
                    writer.newLine();
                    writer.write("Location: " + location);
                    writer.newLine();
                    writer.write("Incident Details: " + incidentDetails);
                    writer.newLine();
                    writer.write("Injured: " + injury);
                    writer.newLine();
                    writer.write("Injuries Details: " + injuriesDetails);
                    writer.newLine();
                    writer.write("Witness: " + wit);
                    writer.newLine();
                    writer.write("Witness Name: " + witnessName);
                    writer.newLine();
                    writer.write("Witness Number: " + witnessNumber);
                    writer.newLine();
                    writer.newLine();

                    JOptionPane.showMessageDialog(thisframe, "Your report has been made");
                    thisframe.setVisible(false);
                } 
                catch (IOException error) {
                    JOptionPane.showMessageDialog(thisframe, "Error saving to file: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    thisframe.setVisible(false);
                }
                catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(thisframe, "Invalid ID number");
                    thisframe.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(thisframe, "Please fill in all required (*) fields. Return to main menue to Try again", "Error", JOptionPane.ERROR_MESSAGE);
                cmdSubmit.setEnabled(true);
                //thisframe.setVisible(false);
            }
                
        }finally {
            System.out.println("");
        }
    }
} 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IncidentReport());
    }
}

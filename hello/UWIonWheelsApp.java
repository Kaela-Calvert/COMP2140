
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class UWIonWheelsApp extends JFrame{
   JPanel Logo=new JPanel();
   JPanel Button=new JPanel();


   public UWIonWheelsApp(){
    super("UWI on Wheels");
    setLayout(new BorderLayout());

       //add Logo image to Logo panel
       ImageIcon icon = new ImageIcon("Logo.png");
       JLabel label = new JLabel(icon);
       Logo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
       Logo.add(label);
       //Logo.setSize(1000,800);
       Logo.setBackground(Color.LIGHT_GRAY);

       //add buttons to Button panel
       JButton loginButton = new JButton("Login");
       JButton signupButton = new JButton("Sign up");
       Dimension buttonSize = new Dimension(200, 50);
       loginButton.setPreferredSize(buttonSize);
       signupButton.setPreferredSize(buttonSize);
       Button.setBackground(Color.LIGHT_GRAY);
       Button.add(loginButton);
       Button.add(signupButton);

       //add ActionListener to buttons
       loginButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               //action for login button
               login();
           }
       });

       signupButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               //action for register button
               showSignupPage();
           }
       });

       //add panels to JFrame
       add(Logo, BorderLayout.NORTH);
       add(Button, BorderLayout.CENTER);

       setSize(600, 400);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
   }

   //Sign up Page
   private void showSignupPage() {
    JFrame signupFrame = new JFrame("Sign Up");
    signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    signupFrame.setSize(600, 700);
    signupFrame.setLayout(new BorderLayout());

    JPanel topPanel = new JPanel();
    ImageIcon icon = new ImageIcon("Logo.png");
    JLabel label = new JLabel(icon);
    topPanel.add(label);
    topPanel.setBackground(Color.LIGHT_GRAY);
    signupFrame.add(topPanel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Add some padding
    centerPanel.setBackground(Color.LIGHT_GRAY);

    JLabel firstNameLabel = new JLabel("First Name:");
    JTextField firstNameField = new JTextField();
    JLabel lastNameLabel = new JLabel("Last Name:");
    JTextField lastNameField = new JTextField();
    JLabel idLabel = new JLabel("ID:");
    JTextField idField = new JTextField();
    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField();

    centerPanel.add(firstNameLabel);
    centerPanel.add(firstNameField);
    centerPanel.add(lastNameLabel);
    centerPanel.add(lastNameField);
    centerPanel.add(idLabel);
    centerPanel.add(idField);
    centerPanel.add(emailLabel);
    centerPanel.add(emailField);
    centerPanel.add(passwordLabel);
    centerPanel.add(passwordField);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.LIGHT_GRAY);

    JButton signupButton = new JButton("Sign Up");
    signupButton.addActionListener(e -> {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String id = idField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (checkIdExists(id)) {
            JOptionPane.showMessageDialog(null, "An account with this ID already exists. Please log in.");
            return;
        }

        try {
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write(firstName + "," + lastName + "," + id + "," + email + "," + password + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Account created successfully. Please log in.");
        signupFrame.dispose();
    });

    bottomPanel.add(signupButton);
    signupFrame.add(centerPanel, BorderLayout.CENTER);
    signupFrame.add(bottomPanel, BorderLayout.SOUTH);

    signupFrame.setVisible(true);
}


private boolean checkIdExists(String id) {
        try {
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userInfo = line.split(",");
                if (userInfo[2].equals(id)) {
                    scanner.close();
                    return true;
                }
                
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
       }
       return false;
       
    }
    private void login() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 250);
        loginFrame.setLayout(new BorderLayout());
    
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.setBackground(Color.WHITE);
    
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
    
        centerPanel.add(idLabel);
        centerPanel.add(idField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
    
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
    
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String lid = idField.getText();
            String lpassword = new String(passwordField.getPassword());
    
            if (checkCredentials(lid, lpassword)) {
                if (lid.equals("256413526") && lpassword.equals("Closet185")) {
                    adminmenu();
                } else if (lid.equals("153974562") && lpassword.equals("Monasec452")) {
                    securitymenu();
                } else {
                    usermenu();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid ID or password. Please sign up.");
            }
            loginFrame.dispose();
        });
    
        bottomPanel.add(loginButton);
        loginFrame.add(centerPanel, BorderLayout.CENTER);
        loginFrame.add(bottomPanel, BorderLayout.SOUTH);
    
        loginFrame.setVisible(true);
    }
    
    private boolean checkCredentials(String id, String password) {
        try {
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userInfo = line.split(",");
                if (userInfo[2].equals(id) && userInfo[4].equals(password)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
//admin menu
public void adminmenu() {
    JFrame frame = new JFrame("Admin Menu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JButton viewIncidentReportsButton = createButton("View Incident Reports");
    JButton viewUserButton = createButton("View User");
    JButton viewFeedbackButton = createButton("View Feedback");
    JButton deleteUserButton = createButton("Delete User");
    JButton addBikeButton = createButton("Add Bike");
    JButton removeBikeButton = createButton("Remove Bike");
    JButton viewRentalHistoryButton = createButton("View Rental History");

    // Add action listeners to buttons
    viewIncidentReportsButton.addActionListener(e -> IncidentReportViewer.main(null));
    viewUserButton.addActionListener(e -> ViewUsersGUI.main(null));

    // Add buttons to panel
    panel.add(viewIncidentReportsButton);
    panel.add(viewUserButton);
    panel.add(viewFeedbackButton);
    panel.add(deleteUserButton);
    panel.add(addBikeButton);
    panel.add(removeBikeButton);
    panel.add(viewRentalHistoryButton);

    frame.add(panel);
    frame.setVisible(true);
}

// Method to create a JButton with specific styling
private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(150, 30));
    return button;
}

public void securitymenu() {
    JFrame frame = new JFrame("Security Menu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);

    JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JButton viewIncidentReportsButton = createButton1("View Incident Reports");
    JButton viewRentalHistoryButton = createButton1("View Rental History");

    // Add action listeners to buttons
    viewIncidentReportsButton.addActionListener(e -> IncidentReportViewer.main(null));
    viewRentalHistoryButton.addActionListener(e -> {
        // Code to handle the action of viewing rental history
    });

    // Add buttons to panel
    panel.add(viewIncidentReportsButton);
    panel.add(viewRentalHistoryButton);

    frame.add(panel);
    frame.setVisible(true);
}

// Method to create a JButton with specific styling
private JButton createButton1(String text) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(200, 30));
    return button;
}


//users(student and staff)
public void usermenu() {
    JFrame frame = new JFrame("User Menu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 600);

    JPanel mainPanel = new JPanel(new BorderLayout());

    // Panel for logo
    JPanel logoPanel = new JPanel();
    ImageIcon icon = new ImageIcon("Logo.png");
    JLabel label = new JLabel(icon);
    logoPanel.add(label);

    // Panel for buttons
    JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JButton paymentMethodsButton = createButton2("Add Payment Methods");
    JButton bikeRentalButton = createButton2("Bike Rental");
    JButton addFeedbackButton = createButton2("Rate Experience");
    JButton editanaccount=createButton2("Edit Account");
    JButton deleteAccountButton = createButton2("Delete Account");
    JButton incidentReportButton = createButton2("Add Incident Report");
    JButton rateBikeButton = createButton2("Rate a Bike");

    // Add action listeners to buttons
    incidentReportButton.addActionListener(e -> IncidentReport.main(null));
    rateBikeButton.addActionListener(e -> RateBike.main(null));
    // Add other action listeners as needed

    // Add buttons to panel
    buttonPanel.add(paymentMethodsButton);
    buttonPanel.add(bikeRentalButton);
    buttonPanel.add(incidentReportButton);
    buttonPanel.add(editanaccount);
    buttonPanel.add(rateBikeButton);
    buttonPanel.add(addFeedbackButton);
    buttonPanel.add(deleteAccountButton);

    mainPanel.add(logoPanel, BorderLayout.NORTH);
    mainPanel.add(buttonPanel, BorderLayout.CENTER);

    frame.add(mainPanel);
    frame.setVisible(true);
}

// Method to create a JButton with specific styling
private JButton createButton2(String text) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(200, 30));
    return button;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UWIonWheelsApp();
            }
        });
    }
}


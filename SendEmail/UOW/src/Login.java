import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Login extends JFrame
{
    private JPanel mainPanel;

    private JLabel header;


    
    private JTextField idText = new JTextField();
    private JPasswordField passwordText = new JPasswordField();;
    private JLabel passwordLabel = new JLabel("PASSWORD: ");
    private JLabel idLabel = new JLabel("ID:");
    
    private JButton loginButton;
    private JButton signUpButton;
    private JCheckBox adminorno = new JCheckBox("ADMINISTRATOR");
    private ArrayList<User>users = new ArrayList<>();
    private ArrayList<Admin>admins = new ArrayList<>();
    public static final String file = "userdata.txt";
    public static final String emailfile = "emaildata.txt";
    ArrayList<String> idmail = new ArrayList<String>();

    public Login()
    {
        //Frame
        setTitle("UWI ON WHEELS");
        setBounds(300, 90, 420,700);
        setResizable(false);

        //Login Panel with header
        header = new JLabel("UWI ON WHEELS");
        header.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        header.setBounds(120, 20, 200, 30);

        //Panel
        mainPanel = new JPanel();
        add(mainPanel);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.GREEN);
        mainPanel.add(header);



        //Admin Checkbox
        adminorno.setBounds(150, 300, 120, 25);
        mainPanel.add(adminorno);
        //Login/SignUp TextField (ID & Password)
        idText = new JTextField();
        idLabel.setBounds(20, 400, 100, 25);
        idText.setBounds(130, 400, 170, 25);

        //Adding ImgageIcon
        /* BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("SENDEMAIL/Logo.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension size = new Dimension(700, 200);
        picLabel.setPreferredSize(size);
        header.add(picLabel); */

        
        passwordLabel.setBounds(20,450,100,25);
        passwordText.setBounds(130,450, 170, 25);
        mainPanel.add(idText);
        mainPanel.add(passwordText);
        mainPanel.add(passwordLabel);
        mainPanel.add(idLabel);

        //Buttons
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(80, 510, 100, 25);
        mainPanel.add(loginButton);

        signUpButton = new JButton("SIGN UP");
        signUpButton.setBounds(200, 510, 100, 25);
        mainPanel.add(signUpButton);


        //Button Listeners
        loginButton.addActionListener(new LoginListener());
        signUpButton.addActionListener(new SignUpListener());

        setVisible(true);

        //Reading database with passenger id and email
        readFile(file);

    }

    private boolean idCheck(String id)
    {
        if (id != null && (id.length() == 9 && id.charAt(0) == '6' && id.charAt(1) == '2'))
        {
            //System.out.println(id.length());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean passwordCheck(String password)
    {
        if (password.length() >= 8 && password.length() <= 25 && password != null )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private class LoginListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String id = idText.getText();
            String password = passwordText.getText();
            System.out.println(idCheck(id));
            //MainPage page = new MainPage();
            readFile(file);

            if (idCheck(id))
            {
               System.out.println("HELLO1");
               /* User user1 = new User("Alan", "Smith", "620138741", "calal@bing.com", "56tghfi1v");
               User user2 = new User("Jane", "Potier", "620338741", "salal@bing.com", "569ghfi1v");
               User user3 = new User("Jane", "Potier", "620345741", "salal@bing.com", "");

               users.add(user1);
               users.add(user2);
               users.add(user3); */
                boolean idExists = false;
                boolean passwordExists = false;
                boolean admincheck = adminorno.isSelected();
                if(admincheck == false)
                {
                    for(User user: users)
                    {
                    
                    
                        if (user.getid().equals(id))
                        {
                            System.out.println("HELLO");
                            idExists = true;
                        }     
                        
                        if(passwordCheck(password))
                        {
                            
                            if(user.getpassword().equals(password))
                            {
                                passwordExists = true;
                            }  
            
                        }
                    

                    }

                    if(idExists && passwordExists)
                    {
                    
                    System.out.println("HELLO4");
                    MainPage main = new MainPage();    
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null, "CREATE AN ACCOUNT TO LOG IN", "NO ACCOUNT", JOptionPane.ERROR_MESSAGE);

                    }
                }
                
                else if (admincheck == true)
                {
                    boolean adminpasses = false;
                    boolean keypasses = false;
                    Admin cal = new Admin("password", "password", "620138741", "mail@mail.com", "password", "IAMADMIN");
                    admins.add(cal);

                    String passString = JOptionPane.showInputDialog("ENTER THE ADMIN ACCESSKEY");
                    for (Admin admin: admins)
                    {
                        if(admin.getid().equals(id) && admin.getpassword().equals(password))
                        {
                            adminpasses = true;
                         }    
                        if(admin.getAccesskey().equals(passString))
                        {
                            keypasses = true;
                        }
                        
                    }
                    if(adminpasses == true && keypasses)
                    {
                        MainPageAdmin mpa = new MainPageAdmin();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "THE USER DOES NOT HAVE ADMIN PRIVILEGES", "NOT ADMIN", JOptionPane.ERROR_MESSAGE);

                    }
                }
                else
                {
                        JOptionPane.showMessageDialog(null, "THE ID/PASSWORD YOU ENTERED DOES NOT EXIST \n OR DOES NOT MEET REQUIREMENTS", "NO MATCH", JOptionPane.ERROR_MESSAGE);
                } 
              
               
            
            }
            else
            {
                JOptionPane.showMessageDialog(null, "ID IS OF THE INCORRECT FORMAT", "INCORRECT ID FORMAT", JOptionPane.ERROR_MESSAGE);

            }
             
        }

    }
    private class SignUpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String id = idText.getText();
            String password = passwordText.getText();
            


            try{
                    //My idcheck was not working here so did the long way
                    if (id != null && (id.length() == 9 && id.charAt(0) == '6' && id.charAt(1) == '2') &&
                    (password.length() >= 8 && password.length() <= 25 && password != null ))
                    {
                        System.out.println("HERE1");
                        readEmailFile(emailfile);
                        
                        boolean idValid = false;
                        for (int index = 0; index < idmail.size(); index++ )
                        {
                            
                            System.out.println("MADE IT HERE");
                            String [] parts = idmail.get(index).split(" ");
                            String firstname = parts[0];
                            String lasttname = parts[1];
                            String fileid = parts[2];
                            String fileemail = parts[3];
                            /* System.out.println(emailfile.toString());
                            System.out.println(fileid);
                            System.out.println(id);
                            System.out.println(fileemail);  */
                            String code = null;
                            if (id.equals(fileid))
                            {
                                idValid = true;
                                //System.out.println("HERE3");
                                try
                                {
                                    new App(fileemail);
                                    code = App.getRandomString();
                                }
                                catch(IOException exception)
                                {

                                }
                                catch(MessagingException mException)
                                {

                                }

                                String confirmCode = JOptionPane.showInputDialog("Enter Verification Code:");
                                if (confirmCode.equals(code))
                                {
                                    JOptionPane.showMessageDialog(null, "Email Verified and Account Successfully Created", "Verification", JOptionPane.INFORMATION_MESSAGE );
                                    
                                    User user  = new User(firstname, lasttname, fileid, fileemail, password);
                                    users.add(user);
                                    
                                    addToUserData(file);
                                    
                                    MainPage main = new MainPage();
                                }
                                else
                                {
                                     JOptionPane.showMessageDialog(null, "THE VERIFICATION CODE IS INVALID", "INVALID CODE", JOptionPane.INFORMATION_MESSAGE );
                                   
                                }
                               
                                
                                
                            }
                            
                        }
                        if (idValid == false)  
                        {
                                JOptionPane.showMessageDialog(null, "THE ID ENTERED IS DOES NOT MATCH THE ID FROM FILE", "INVALID ID", JOptionPane.INFORMATION_MESSAGE );

                        }                  

                        
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "THE ID OR PASSWORD ENTERED IS INVALID OR LEFT BLANK", "ENTRY ERROR", JOptionPane.INFORMATION_MESSAGE );

                }

            }     
            catch(NullPointerException np)
            {

            }
        }
    }

//OTHER PAGES
    public class MainPage extends JFrame
    {
        private JLabel header           = new JLabel("UWI ON WHEELS");
        private JPanel  mainScreenPanel = new JPanel();
        private JButton selectBike      = new JButton("SELECT BIKE");
        private JButton paymentButton   = new JButton("PAYMENT PLAN");
        private JButton rateButton      = new JButton("RATE BIKE");
        private JButton incidentButton  = new JButton("REPORT INCIDENT");
        private JButton logout          = new JButton("LOGOUT");

        public MainPage()
        {
            setTitle("MainScreen");
            setBounds(300, 90, 420,700);
            setResizable(false);

            header.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            header.setBounds(120, 20, 200, 30);
            mainScreenPanel.add(header);
            
            add(mainScreenPanel);
            mainScreenPanel.setLayout(null);
            mainScreenPanel.setBackground(Color.GREEN);

            selectBike.setBounds(110, 150, 200, 35);
            paymentButton.setBounds(110, 250, 200, 35);
            rateButton.setBounds(110, 350, 200, 35);
            incidentButton.setBounds(110, 450, 200, 35 );
            logout.setBounds(110, 550, 200, 35);

            mainScreenPanel.add(selectBike);
            mainScreenPanel.add(paymentButton);
            mainScreenPanel.add(rateButton);
            mainScreenPanel.add(incidentButton);
            mainScreenPanel.add(logout);

            setVisible(true);
            // Button Listeners
            selectBike.addActionListener(new SelectBikeListener());
            paymentButton.addActionListener(new PaymentButtonListener());
            incidentButton.addActionListener(new IncidentButtonListener());
            rateButton.addActionListener(new RateBikeListener());
            logout.addActionListener(new LogoutListener());

        }
        private class SelectBikeListener implements ActionListener
        {
            /**
             * This method calls the select bike class
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    BicycleRental select = new BicycleRental(); 
                }
                catch(NullPointerException nulP)
                {}
            }
        }

        private class PaymentButtonListener implements ActionListener
        {
            /**
             * This method calls the select bike class
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    PaymentPlan newPay = new PaymentPlan();
                }
                catch(NullPointerException nulP)
                {}
            }
        }

         private class RateBikeListener implements ActionListener
         {
            /**
             * This method calls the select bike class
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    RateBike rate = new RateBike(); 
                }
                catch(NullPointerException nulP)
                {}
            }
        }

        private class IncidentButtonListener implements ActionListener
        {
            /**
             * This method calls the IncidentReport class 
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    IncidentReport report = new IncidentReport();
                }
                catch(NullPointerException nulP)
                {}
            }
        }

    } 

    public class MainPageAdmin extends JFrame
    {
        private JLabel header               = new JLabel("UWI ON WHEELS");
        private JPanel  mainScreenPanel     = new JPanel();
        private JButton viewUsers           = new JButton("VIEW USERS");
        private JButton deleteUsers         = new JButton("DELETE USERS");
        private JButton logout              = new JButton("LOGOUT");
        
 
        public MainPageAdmin()
        {
            setTitle("MainScreen");
            setBounds(300, 90, 420,700);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            header.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            header.setBounds(120, 20, 200, 30);
            mainScreenPanel.add(header);

            
            add(mainScreenPanel);
            mainScreenPanel.setLayout(null);
            mainScreenPanel.setBackground(Color.GREEN);

            viewUsers.setBounds(110, 300, 200, 35);
            deleteUsers.setBounds(110, 400, 200, 35);
            logout.setBounds(110,500,200,35);
            
            mainScreenPanel.add(viewUsers);
            mainScreenPanel.add(deleteUsers);
            mainScreenPanel.add(logout);

            //Button Listener
            viewUsers.addActionListener(new ViewUserListener());
            
            logout.addActionListener(new LogoutListener());

            deleteUsers.addActionListener(new DeleteListener());

            setVisible(true);
        }

        private class ViewUserListener implements ActionListener
        {
            /**
             * This method calls the select bike class
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    ViewUsers view = new ViewUsers();
                }
                catch(NullPointerException nulP)
                {}
            }
        }

        private class DeleteListener implements ActionListener
        {
            /**
             * This method calls the select bike class
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                //Implement a search for a user with a specific id
                //If the id is found, remove that person from the arraylist
                //Else, error message, person with id """" not found
                    ViewUsers view = new ViewUsers();
                    String idToDelete = JOptionPane.showInputDialog("ENTER THE ID OF THE USER TO DELETE");
                    boolean idFound = false;
                    int userIndex = 0;
                    for (int index = 0; index < users.size(); index++)
                    {
                        if (users.get(index).getid().equals(idToDelete) && !(users.size()!= 0))
                        {
                            idFound = true;
                            users.remove(userIndex);
                        }

                    }
                    if (idFound == false)
                    {
                            JOptionPane.showMessageDialog(null, "THE ID YOU ENTERED DOES NOT EXIST", "NO MATCH", JOptionPane.ERROR_MESSAGE);
                        }
                    
                    addToUserData(file);
                    view.viewData();
                }
                catch(NullPointerException nulP)
                {}
            }
        }
  
    }


    private class ViewUsers extends JFrame
    {
        public JTextArea  displayArea       = new JTextArea();
        private JLabel header = new JLabel("UWI ON WHEELS");
        private JPanel displayPanel = new JPanel();
        private JScrollPane scrollPane;
        private JButton deleteButton = new JButton("DELETE");
        private JButton logout = new JButton("LOGOUT");
        private JButton returnMain = new JButton("RETURN");

        public ViewUsers()
        {
            setBounds(300, 90, 420,700);
            setResizable(false);

            header.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
            header.setBounds(120, 20, 200, 30);
            displayPanel.add(header);

            add(displayPanel);
            displayPanel.setBackground(Color.GREEN);
            displayArea= new JTextArea(34,30);
            displayArea.setBorder(BorderFactory.createLineBorder(Color.black));  
                 
            displayPanel.add(displayArea);
            scrollPane = new JScrollPane(displayArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            displayPanel.add(scrollPane);

            logout.setBounds(10, 500, 80, 25);
            displayPanel.add(logout);
            
            
            returnMain.setBounds(10, 600, 80, 25 );
            displayPanel.add(returnMain);

            displayArea.setEditable(false);

            viewData();
            setVisible(true);
            //Button Listeners
            logout.addActionListener(new LogoutListener());
            returnMain.addActionListener(new ReturnListener());
            deleteButton.addActionListener(new DeleteListener());
        }
        private void viewData()
        {
        
        displayArea.setText("");
        for (int index = 0; index < users.size(); index++)
        {
            String textSeparator =  "\n___________________________________";
            displayArea.append( "  USER FIRSTNAME: \t" +users.get(index).getfname() + " \n  USER LASTNAME : \t" + users.get(index).getlname() + " \n  ID : " + users.get(index).getid() + 
        " \n  EMAIL: \t "+ users.get(index).getemail() + "\nPassword: \t ************" + textSeparator +"\n");
            }
        } 

        private class LogoutListener implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                MainPage main = new MainPage();
            }
        }

        private class ReturnListener implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                MainPageAdmin main = new MainPageAdmin();
            }
        }

        private class DeleteListener implements ActionListener
        {
            /**
             * This method removes a specific person from the arraylist
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                //Implement a search for a user with a specific id
                //If the id is found, remove that person from the arraylist
                //Else, error message, person with id """" not found
                    ViewUsers view = new ViewUsers();
                    String idToDelete = JOptionPane.showInputDialog("ENTER THE ID OF THE USER TO DELETE");
                    boolean idFound = false;
                    int userIndex = 0;
                    for (int index = 0; index < users.size(); index++)
                    {
                        if (users.get(index).getid().equals(idToDelete))
                        {
                            idFound = true;
                            users.remove(userIndex);
                        }

                    }
                    if (idFound == false)
                    {
                        JOptionPane.showMessageDialog(null, "THE ID YOU ENTERED DOES NOT EXIST", "NO MATCH", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    addToUserData(file);
                    view.viewData();
                }
                catch(NullPointerException nulP)
                {}
            }
        }
         
    }
    
    //Updating the data file
    private void addToUserData(String file)
    {
        try
        {
            Formatter printFile = new Formatter(file);

            for(int index = 0; index < users.size(); index++)
            {
                printFile.format(users.get(index).getfname() + " " + users.get(index).getlname() + " " +
                users.get(index).getid() + " "+ users.get(index).getemail() +" "+ users.get(index).getpassword() + "\n");
            }
            printFile.close();
        }
        catch(IOException ioException)
        {
            System.out.println("Error in writing to the file");
        }
    }


    //Read the user data file
    private void readFile(String file)
    {
        //Reads file and adds the 
        String fileData = "";

        users.clear();

        try
        {

            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);

            while(scanner.hasNext() == true)
            {
                String [] line   = scanner.nextLine().split(" ");
                String firstname = line[0];
                String lastname  = line[1];
                String id        = line[2];
                String email     = line[3];
                String password  = " ";
                if (line[4] != "")
                {
                   password = line[4];
                }
                
                //Can Probably Use one file and just not add password to the arraylist
                System.out.println(firstname);
                System.out.println(lastname);System.out.println(id);System.out.println(email);System.out.println(password);
                if (firstname.equals("") || lastname.equals("") ||
                id.equals("") || email.equals(""))
                {
                    System.out.println("UNABLE TO ADD PERSON TO ARRAYLIST");
                }
                else
                {
                    User newUser = new User(firstname, lastname, id, email,password);
                    users.add(newUser);
                    System.out.println(users);

                    
                }
                


            }
            scanner.close();
            reader.close();  
        
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + file);
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException aiob)
        {

        }

    }

    private void readEmailFile(String emailfile)
    {
        //Reads file and adds the 
        String fileData = "";

        idmail.clear();

        try
        {

            FileReader reader = new FileReader(emailfile);
            Scanner scanner = new Scanner(reader);

            while(scanner.hasNextLine() == true)
            {
                String [] line   = scanner.nextLine().split(" ");
                String firstname = line[0];
                String lastname  = line[1];
                String id = line[2];
                String email  = line[3];
                String idemail = firstname+ " "+ lastname+" "+  id + " " + email;
                //System.out.println(idemail);
                idmail.add(idemail);
                
                

                
                //Can Probably Use one file and just not add password to the arraylist
                System.out.println(id);
                System.out.println(email);
                

                    
            }
            scanner.close();
            reader.close(); 
            
        
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + file);
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException aiob)
        {

        }

    }
     
    private class LogoutListener implements ActionListener
        {
            /**
             * This method calls Login class
             */
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Login login = new Login();
                }
                catch(NullPointerException nulP)
                {}
            }
        }
}

    
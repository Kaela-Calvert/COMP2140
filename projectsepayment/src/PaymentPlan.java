import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class PaymentPlan extends JFrame {
    private static int invoicenumber;
    private int id;
    private String hours;
    private String person;
    private String baseprice;
    private int cost;

    private String cardnum;
    private String cvvnum;
    private String first;
    private String last;
    private String nameCard;
    private String expDate;
    private String paymentdata;
    private String file;

    private String start;
    private String end;
    private Date date;
    private ArrayList<String> data = new ArrayList<String>();

    private JTextField name;
    private JTextField cardnumber;
    private JTextField expiredate;
    private JTextField cvv;

    private JPanel buttons;
    private JPanel displayinfo;
    private JPanel displayheader;
    private JPanel inHeader;
    private JPanel container;
    private JScrollPane cont;
    private JPanel entire;
    private JPanel command;

    private JButton next;
    private JButton save;
    private JButton cancel;
    private JButton back;
    private JButton send;
    private JButton finish;

    private JCheckBox agree;

    private JLabel header;
    private JLabel header2;
    private JLabel invoiceheader;
    private JLabel personname;
    private JLabel inDate;

    private JTable table;

    public PaymentPlan() {
        // Adding a title to the frame
        setTitle("ADD PAYMENT INFORMATION");

        // Creating a header
        header = new JLabel("Payment");
        header2 = new JLabel("Information");

        // Formatting the header
        header.setFont(new Font("Arial", Font.BOLD, 30));
        header2.setFont(new Font("Arial", Font.BOLD, 30));
        header.setForeground(Color.gray);
        header2.setForeground(Color.GREEN);

        // Creating a panel, adding the header to it and formatting it.
        displayheader = new JPanel();
        displayheader.add(header);
        displayheader.add(header2);
        displayheader.setBackground(Color.WHITE);

        // Creating a panel and adding all four textfields to it.

        displayinfo = new JPanel();
        displayinfo.add(new JLabel("Name of Card Holder"));
        name = new JTextField(20);
        displayinfo.add(name);

        displayinfo.add(new JLabel("Card Number"));
        cardnumber = new JTextField(20);
        displayinfo.add(cardnumber);

        displayinfo.add(new JLabel("CVV"));
        cvv = new JTextField(20);
        displayinfo.add(cvv);

        displayinfo.add(new JLabel("Expiration (MM/YY)"));
        expiredate = new JTextField(20);
        displayinfo.add(expiredate);
        displayinfo.setLayout(new GridLayout(5, 2));

        // Creating and formatting a panel and buttons.
        buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        save = new JButton("Save");
        save.setBackground(Color.GREEN);
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.GREEN);
        next = new JButton("Next");
        next.setBackground(Color.GREEN);

        // Adding buttons to panel
        buttons.add(save);
        buttons.add(cancel);
        buttons.add(next);

        // Adding actionlistener to buttons
        next.addActionListener(new NextButtonListener());
        cancel.addActionListener(new CancelButtonListener());
        save.addActionListener(new SaveButtonListener());

        // Adding panels to frame
        add(displayheader, BorderLayout.NORTH);
        add(displayinfo, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        pack();

        // Formatting frame
        setSize(400, 680);
        setLocation(540, 50);
        setResizable(false);
        setVisible(true);

    }

    public PaymentPlan(int g) {
        invoicenumber++;
        id = invoicenumber;
        date = new Date();

        // Creating the panels.
        inHeader = new JPanel();
        command = new JPanel();

        container = new JPanel();
        entire = new JPanel();

        // Getting data from a file.
        getData("uow textfile.txt");

        // Creating the header.
        setTitle("Invoice");
        String numberString = "Invoice # " + String.valueOf(id);
        String billTo = "Bill to: " + first + " " + last;
        personname = new JLabel(billTo);
        String datestring = "Date: " + String.valueOf(date);
        invoiceheader = new JLabel(numberString);
        inDate = new JLabel(datestring);

        // Formatting the header text.
        invoiceheader.setFont(new Font("Arial", Font.BOLD, 30));
        invoiceheader.setForeground(Color.GREEN);

        // Formatting and adding items to the header.
        inHeader.setBackground(Color.WHITE);
        inHeader.add(invoiceheader);
        inHeader.add(personname);
        inHeader.add(inDate);

        // Calculating the cost of the ride.
        cost = Integer.parseInt(baseprice) * Integer.parseInt(hours);

        // Creating a table based on the data received from the file
        String[][] information = { { hours, baseprice, String.valueOf(cost), start, end } };
        String[] columnNames = { "Time Chosen", "Base Price", "Cost", "Start Time", "End Time" };
        table = new JTable(information, columnNames);
        cont = new JScrollPane(table);
        // Creating a checkbox.
        agree = new JCheckBox("Accept the Terms and Conditions of using the UOW system.");

        // Adding the elements to the body of the panel.
        container.add(agree);
        entire.add(cont);
        entire.add(container);

        // Creating buttons

        finish = new JButton("Finish");

        // Adding actionlisteners to the buttons

        finish.addActionListener(new FinsihButtonListener());

        // Adding color to the buttons

        finish.setBackground(Color.GREEN);

        // Adding the buttons to a panel
        command.add(finish);

        // Adding the panels to the frame
        add(inHeader, BorderLayout.NORTH);
        add(entire, BorderLayout.CENTER);
        add(command, BorderLayout.SOUTH);

        // Formatting the panels and frame.
        cont.setSize(400, 120);
        cont.setBackground(Color.WHITE);
        command.setBackground(Color.WHITE);
        inHeader.setLayout(new GridLayout(3, 1));
        container.setLayout(new GridLayout(3, 1));
        setSize(480, 680);
        setLocation(540, 50);
        setResizable(false);
        setVisible(true);

    }

    public PaymentPlan(String person) {
        // Getting data from a file.
        getData("uow textfile.txt");
        this.person = first;
        String enjoy = "Enjoy Your Ride " + person + "!";

        // Creating the message
        header = new JLabel(enjoy);
        header.setFont(new Font("Arial", Font.BOLD, 30));
        header.setForeground(Color.GREEN);
        displayheader = new JPanel();
        displayheader.add(header);

        // Adding message to the frame
        add(header, BorderLayout.CENTER);

        // Formatting the frame
        setSize(350, 200);
        setLocation(540, 50);
        setResizable(false);
        setVisible(true);

    }

    public void toFile(String paymentdata) {
        // Adds data to a file
        try (BufferedWriter writefile = new BufferedWriter(new FileWriter("storepayment.txt", true))) {
            PrintWriter p = new PrintWriter(writefile);
            p.println(paymentdata);

            // Shows the user that the data was saved.
            JOptionPane.showMessageDialog(null, "SuccessFully Saved");

            p.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void getData(String file) {
        // Get data from a file.
        Scanner scan = null;

        try {
            scan = new Scanner(new File(file));
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split(" ");
                first = line[0];
                last=line[1];
                hours = line[2];
                baseprice = line[3];
                start = line[4];
                end = line[5];

                data.add(person);
                data.add(hours);
                data.add(baseprice);

            }
            scan.close();

        } catch (Exception e) {
        }
    }

    private class SaveButtonListener implements ActionListener {
        // Adding functionality to the save button
        public void actionPerformed(ActionEvent e) {
            try {
                // Getting user input from the texfields.
                nameCard = name.getText();
                cardnum = cardnumber.getText();
                cvvnum = cvv.getText();
                expDate = expiredate.getText();

                // Checking whether digits were entered for the card number and cvv
                Long.parseLong(cardnumber.getText());
                Long.parseLong(cvv.getText());

                // Checking if the card number and cvv is of the correct length.
                if (cardnumber.getText().length() == 16 && cvv.getText().length() == 3) {

                    // Adding the data to a file.
                    paymentdata = nameCard + " " + cardnum + " " + cvvnum + " " + expDate;
                    toFile(paymentdata);

                }

                else {
                    // Prompts the user to enter a valid cvv or card number if they are not the
                    // correct length
                    JOptionPane.showMessageDialog(null, "Invalid card number or cvv");
                }

            } catch (NumberFormatException exception) {
                // Prompts the user to enter a valid cvv or card number if they contain
                // non-digit values.
                JOptionPane.showMessageDialog(null, "Invalid card number or cvv");
            }
        }
    }

    private class NextButtonListener implements ActionListener {
        // Adds functionality to the next button
        public void actionPerformed(ActionEvent e) {

            // Checking for empty textfields
            if (name.getText().isEmpty() == false || cardnumber.getText().isEmpty() == false
                    || cvv.getText().isEmpty() == false
                    || expiredate.getText().isEmpty() == false) {
                // Creates the invoice screen.

                setVisible(false);
                new PaymentPlan(id);
            }

            // Prompts users to enter data.
            else {
                JOptionPane.showMessageDialog(null, "Fields cannot be left blank.");
            }

        }
    }

    private class CancelButtonListener implements ActionListener {
        // Adds functionlity to the cancel button
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

        }
    }


    private class FinsihButtonListener implements ActionListener {
        // Adding functionality to the finish button.
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new PaymentPlan(person);
        }
    }

    public static void main(String[] arg) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaymentPlan();
            }
        });

    }
}

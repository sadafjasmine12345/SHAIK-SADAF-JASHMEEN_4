//AWT Components: The program uses Label, TextField, Checkbox, CheckboxGroup, Choice, Button, and TextArea from the AWT package.
//Event Handling: The program implements the ActionListener interface to handle button clicks.
//Exception Handling: Basic try-catch blocks handle potential IOExceptions during file operations.
//Layout Management: The GridLayout is used to arrange the components neatly within the frame.
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class RegistrationManager extends Frame implements ActionListener {
    // Declaring components
    Label nameLabel, genderLabel, countryLabel, displayLabel;
    TextField nameField;
    CheckboxGroup genderGroup;
    Checkbox male, female;
    Choice countryChoice;
    TextArea displayArea;
    Button submitButton, exportButton;
    
    // Constructor to setup the GUI
    public RegistrationManager() {
        // Setting up the frame
        setTitle("Registration Manager");
        setSize(500, 600);
        setLayout(new GridLayout(6, 2));

        // Initializing components
        nameLabel = new Label("Name:");
        nameField = new TextField();
        
        genderLabel = new Label("Gender:");
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        
        countryLabel = new Label("Country:");
        countryChoice = new Choice();
        countryChoice.add("Select Country");
        countryChoice.add("USA");
        countryChoice.add("India");
        countryChoice.add("UK");
        countryChoice.add("Australia");
        
        submitButton = new Button("Submit");
        exportButton = new Button("Export");
        
        displayLabel = new Label("User Details:");
        displayArea = new TextArea();
        displayArea.setEditable(false);

        // Adding components to the frame
        add(nameLabel);
        add(nameField);
        add(genderLabel);
        Panel genderPanel = new Panel();
        genderPanel.add(male);
        genderPanel.add(female);
        add(genderPanel);
        add(countryLabel);
        add(countryChoice);
        add(submitButton);
        add(exportButton);
        add(displayLabel);
        add(displayArea);
        
        // Adding action listeners
        submitButton.addActionListener(this);
        exportButton.addActionListener(this);
        
        // Window listener for closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Setting frame visibility
        setVisible(true);
    }

    // Action performed method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String gender = genderGroup.getSelectedCheckbox().getLabel();
            String country = countryChoice.getSelectedItem();

            if (name.isEmpty() || country.equals("Select Country")) {
                displayArea.setText("Please fill all the fields.");
            } else {
                displayArea.setText("Name: " + name + "\nGender: " + gender + "\nCountry: " + country);
            }
        } else if (e.getSource() == exportButton) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("registration.txt"));
                writer.write(displayArea.getText());
                writer.close();
                displayArea.setText("Data exported successfully.");
            } catch (IOException ioe) {
                displayArea.setText("Error exporting data.");
            }
        }
    }
  //User Input: The form collects user data such as name, gender, and country.
  //Display: Upon submission, the details are displayed in a TextArea.
  //Export: The details can be exported to a .txt file using the "Export" button.

    // Main method to run the registration manager application
    public static void main(String[] args) {
        new RegistrationManager();
    }
}

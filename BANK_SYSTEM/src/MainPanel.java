import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Woodje on 28-01-2015.
 */
public class MainPanel extends JPanel implements ActionListener {

    // Arraylist for holding the customers.
    ArrayList<Customer> customerList = new ArrayList<Customer>();

    // Create an arraylist for holding the inputed text.
    ArrayList<String> textFields = new ArrayList<String>();

    // Panel for holding the textfield panels.
    JPanel buttonPanel, textPanel;

    // ComboBox for all the customers.
    JComboBox comboBox;

    // This panels constructor.
    public MainPanel() {

        // Create a panel for button panels and add it to the main panel.
        buttonPanel = new JPanel(new GridLayout(3, 0));
        add(buttonPanel);

        // Create and add the combobox.
        comboBox = new JComboBox();
        comboBox.addActionListener(this);
        buttonPanel.add(comboBox);

        // Create and add the needed button panels.
        buttonPanel.add(new ButtonPanel("Create Customer", true, this));
        buttonPanel.add(new ButtonPanel("Delete Customer", false, this));

    }

    private void initializeCustomerPanel() {

        // Create a panel for textfield panels and add it to the main panel.
        textPanel = new JPanel(new GridLayout(4, 0));
        add(textPanel);

        // Create and add the needed textfield panels.
        textPanel.add(new TextFieldPanel("Id:", Integer.toString(customerList.size()), false));
        textPanel.add(new TextFieldPanel("AccountId:", true));
        textPanel.add(new TextFieldPanel("Name:", true));
        textPanel.add(new TextFieldPanel("Address:", true));

        // Disable the ability to press the create customer button.
        setButtonState(getComponents(), "Create Customer", false);

        // Create and add the save button panel.
        textPanel.setLayout(new GridLayout(5, 0));
        textPanel.add(new ButtonPanel("Save Customer", true, this));

    }

    private void saveCustomer() {

        // Create a customer and give it an id according to the existing entries.
        Customer customer = new Customer(customerList.size());

        // Collect the values from the textfields.
        getTextFields(getComponents());

        // Enable the ability to press the create customer button.
        setButtonState(getComponents(), "Create Customer", true);

        // Copy values from the texfields to the newly created customer.
        customer.setAccountId(convertToInteger(textFields.get(1)));
        customer.setName(textFields.get(2));
        customer.setAddress(textFields.get(3));

        // Finally add the customer to the arraylist and the combobox.
        customerList.add(customer);
        comboBox.addItem(customer.getId());
        comboBox.setSelectedIndex(customer.getId());

        // Remove the textPanel.
        remove(textPanel);

    }

    private void deleteCustomer() {

        //
        customerList.remove(comboBox.getSelectedIndex());

        //
        comboBox.removeAll();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ((Object)e.getSource() instanceof JButton) {
            // Check which button was pressed and do the appropriate action.
            if (((JButton)e.getSource()).getText() == "Create Customer")
                initializeCustomerPanel();
            if (((JButton)e.getSource()).getText() == "Save Customer")
                saveCustomer();
            if (((JButton)e.getSource()).getText() == "Delete Customer")
                deleteCustomer();
        }
        //else
            //if (((Object)e.getSource() == comboBox))

        // No matter what happened, then resize the main frame to fit only the current contents.
        ((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this)).pack();
    }

    private void getTextFields(Component[] components) {

        // Go through all component provided.
        for (Component component : components) {

            // Check if the component is in fact a textfield.
            if (component instanceof JTextField)
                // Set the text of the textfield.
                textFields.add(((JTextField) component).getText());
            else
                // If no textfield is found then go further by using recursive calls.
                getTextFields(((Container) component).getComponents());

        }

    }

    private void setTextField(Component[] components, String text) {

        // Go through all component provided.
        for (Component component : components) {

            // Check if the component is in fact a textfield.
            if (component instanceof JTextField)
                // Set the text of the textfield.
                ((JTextField) component).setText(text);
            else
                // If no textfield is found then go further by using recursive calls.
                setTextField(((Container) component).getComponents(), text);
        }

    }

    private void setButtonState(Component[] components, String button, boolean enabled) {

        // Go through all component provided.
        for (Component component : components) {

            // Check if the component is in fact a button.
            if (component instanceof JButton) {
                // Set the state of the button.
                if (((JButton) component).getText() == button)
                    ((JButton) component).setEnabled(enabled);
            }
            else
                // If no button is found then go further by using recursive calls.
                setButtonState(((Container) component).getComponents(), button, enabled);
        }

    }

    // Check if the string is an integer
    private int convertToInteger(String string) {

        // Variable for the converted string.
        int value;

        try {

            // Try to convert the provided integer to an integer.
            value = Integer.parseInt(string);

            // Catch if this goes wrong.
        } catch(NumberFormatException e) {

            // Use zero as the default value if it isn't converted to an integer.
            return 0;

        }

        // Return the converted value.
        return value;

    }

}

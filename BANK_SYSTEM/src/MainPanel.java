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

    // Arraylist for holding the accounts.
    ArrayList<Account> accountList = new ArrayList<Account>();

    // Create an arraylist for holding the inputed text.
    ArrayList<String> textFields = new ArrayList<String>();

    // Panel for holding the textfield panels.
    JPanel buttonPanel, textPanel;

    // ComboBox for all the customers.
    JComboBox comboBox;

    // This panels constructor.
    public MainPanel() {

        // Create a panel for button panels and add it to the main panel.
        buttonPanel = new JPanel(new GridLayout(4, 0));
        add(buttonPanel);

        // Create and add the combobox.
        comboBox = new JComboBox();
        comboBox.setVisible(false);
        comboBox.addActionListener(this);
        buttonPanel.add(comboBox);

        // Create and add the needed button panels.
        buttonPanel.add(new ButtonPanel("Create Customer", true, this));
        buttonPanel.add(new ButtonPanel("Delete Customer", false, this));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ((Object)e.getSource() instanceof JButton) {

            // Check which button was pressed and do the appropriate action.
            if (((JButton)e.getSource()).getText() == "Create Customer")
                initializeCreateCustomerPanel();
            else if (((JButton)e.getSource()).getText() == "Save Customer")
                saveCustomer();
            else if (((JButton)e.getSource()).getText() == "Cancel")
                cancelCreate();
            else if (((JButton)e.getSource()).getText() == "Delete Customer")
                deleteCustomer();
            else if (((JButton)e.getSource()).getText() == "Add Account")
                initializeCreateAccountPanel();
            else if (((JButton)e.getSource()).getText() == "Save Account")
                saveAccount();

        }
        else
            // Check if the combobox had actions and that the customers list isn't empty.
            if (((Object)e.getSource() == comboBox) && customerList.size() > 1) {

                // Show the selected customer.
                showSelectedCustomer(comboBox.getSelectedIndex());

            }

        // No matter what happened, then resize the main frame to fit only the current contents.
        ((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this)).pack();
    }

    private void saveAccount() {

        // Create an account and give it an id according to the existing entries.
        Account account = new Account(accountList.size());

        // Create a new list of textfields.
        textFields = new ArrayList<String>();

        // Collect the values from the textfields.
        getTextFields(getComponents());

        // Copy values from the texfields to the newly created account.
        account.setCustomerId(convertToInteger(textFields.get(5)));
        account.setBalance(convertToDouble(textFields.get(6)));

        // Remove the textPanel.
        remove(textPanel);

        // Finally add the account to the arraylist and correct the customers account id.
        accountList.add(account);
        customerList.get(account.getCustomerId()).setAccountId(account.getId());

        // Show the newly created customer.
        showSelectedCustomer(account.getCustomerId());

        // Enable the ability to press the create customer button.
        setButtonState(getComponents(), "Create Customer", true);

        // Enable the ability to press the create customer button.
        setButtonState(getComponents(), "Delete Customer", true);

        // Make the combobox visible.
        comboBox.setVisible(true);

    }

    private void initializeCreateAccountPanel() {

        // Create and add the needed textfield panels.
        textPanel.setLayout(new GridLayout(8, 0));
        textPanel.add(new TextFieldPanel("Id:", Integer.toString(accountList.size()), false));
        textPanel.add(new TextFieldPanel("CustomerId:", Integer.toString(comboBox.getSelectedIndex()), false));
        textPanel.add(new TextFieldPanel("Balance:", String.valueOf(0.0), true));

        // Disable the ability to press the create customer button.
        setButtonState(getComponents(), "Create Customer", false);

        // Disable the ability to press the delete customer button.
        setButtonState(getComponents(), "Delete Customer", false);

        // Disable the ability to press the add account button.
        setButtonState(getComponents(), "Add Account", false);

        // Make the combobox invisible.
        comboBox.setVisible(false);

        // Create and add a cancel button panel.
        textPanel.setLayout(new GridLayout(10, 0));
        textPanel.add(new ButtonPanel("Save Account", true, this));
        textPanel.add(new ButtonPanel("Cancel", true, this));

    }

    private void initializeCreateCustomerPanel() {

        // Check if the last customer is deleted, if not then remove the current showing customer.
        if (customerList.size() >= 1) {

            // Remove the textPanel.
            remove(textPanel);

        }

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

        // Disable the ability to press the delete customer button.
        setButtonState(getComponents(), "Delete Customer", false);

        // Make the combobox invisible.
        comboBox.setVisible(false);

        // Create and add save and cancel button panels.
        textPanel.setLayout(new GridLayout(6, 0));
        textPanel.add(new ButtonPanel("Save Customer", true, this));
        textPanel.add(new ButtonPanel("Cancel", true, this));

    }

    private void showSelectedCustomer(int customerId) {

        // Remove the textPanel.
        remove(textPanel);

        // Create a panel for textfield panels and add it to the main panel.
        textPanel = new JPanel(new GridLayout(4, 0));
        add(textPanel);

        // Create and add the needed textfield panels.
        textPanel.add(new TextFieldPanel("Id:", Integer.toString(customerList.get(customerId).getId()), false));
        textPanel.add(new TextFieldPanel("AccountId:", Integer.toString(customerList.get(customerId).getAccountId()), false));
        textPanel.add(new TextFieldPanel("Name:", customerList.get(customerId).getName(), false));
        textPanel.add(new TextFieldPanel("Address:", customerList.get(customerId).getAddress(), false));

        // Check if the customer has an account added.
        if (customerList.get(customerId).getAccountId() > -1) {

            // Create and add an add account button panel.
            textPanel.setLayout(new GridLayout(9, 0));
            textPanel.add(new ButtonPanel("Add Account", false, this));

            // Create and add the needed textfield panels.
            textPanel.setLayout(new GridLayout(8, 0));
            textPanel.add(new TextFieldPanel("Id:", Integer.toString(accountList.get(customerList.get(customerId).getAccountId()).getId()), false));
            textPanel.add(new TextFieldPanel("CustomerId:", Integer.toString(customerId), false));
            textPanel.add(new TextFieldPanel("Balance:", String.valueOf(accountList.get(customerList.get(customerId).getAccountId()).getBalance()), false));

        }
        else {

            // Create and add an add account button panel.
            textPanel.setLayout(new GridLayout(5, 0));
            textPanel.add(new ButtonPanel("Add Account", true, this));

        }

    }

    private void saveCustomer() {

        // Create a customer and give it an id according to the existing entries.
        Customer customer = new Customer(customerList.size());

        // Create a new list of textfields.
        textFields = new ArrayList<String>();

        // Collect the values from the textfields.
        getTextFields(getComponents());

        // Copy values from the texfields to the newly created customer.
        customer.setAccountId(convertToInteger(textFields.get(1)));
        customer.setName(textFields.get(2));
        customer.setAddress(textFields.get(3));

        // Remove the textPanel.
        remove(textPanel);

        // Finally add the customer to the arraylist and the combobox.
        customerList.add(customer);
        comboBox.addItem(customer.getId());
        comboBox.setSelectedIndex(customer.getId());

        // Show the newly created customer.
        showSelectedCustomer(customer.getId());

        // Enable the ability to press the create customer button.
        setButtonState(getComponents(), "Create Customer", true);

        // Enable the ability to press the create customer button.
        setButtonState(getComponents(), "Delete Customer", true);

        // Make the combobox visible.
        comboBox.setVisible(true);

    }

    private void cancelCreate() {

        // Remove the textPanel.
        remove(textPanel);

        // Enable the ability to press the create customer button.
        setButtonState(getComponents(), "Create Customer", true);

        // Disable the delete button and make the comboxbox invisible if the last customer is deleted.
        if (customerList.size() < 1) {

            // Disable the ability to press the delete customer button.
            setButtonState(getComponents(), "Delete Customer", false);
            comboBox.setVisible(false);

        }
        else {

            comboBox.setVisible(true);
            showSelectedCustomer(comboBox.getSelectedIndex());
        }

    }

    private void deleteCustomer() {

        // Remove the customer from the customers list.
        customerList.remove(comboBox.getSelectedIndex());

        // Remove all items from the combobox.
        comboBox.removeAllItems();

        // Fill the combobox with the current values from the customerslist.
        for (Customer customer : customerList)
            comboBox.addItem(customer.getId());

        // Disable the delete button and make the comboxbox invisible if the last customer is deleted.
        if (customerList.size() < 1) {

            // Disable the ability to press the delete customer button.
            setButtonState(getComponents(), "Delete Customer", false);
            comboBox.setVisible(false);

            // Remove the textPanel.
            remove(textPanel);

        }

    }

    private void getTextFields(Component[] components) {

        // Go through all components provided.
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

    private void setButtonState(Component[] components, String button, boolean enabled) {

        // Go through all components provided.
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

    private int convertToInteger(String string) {

        // Variable for the converted string.
        int value;

        try {

            // Try to convert the provided integer to an integer.
            value = Integer.parseInt(string);

            // Catch if this goes wrong.
        } catch(NumberFormatException e) {

            // Use negative one as the default value if it isn't converted to an integer.
            return -1;

        }

        // Return the converted value.
        return value;

    }

    private double convertToDouble(String string) {

        // Variable for the converted string.
        double value;

        try {

            // Try to convert the provided integer to an integer.
            value = Double.parseDouble(string);

            // Catch if this goes wrong.
        } catch(NumberFormatException e) {

            // Use zero as the default value if it isn't converted to a double.
            return 0.0;

        }

        // Return the converted value.
        return value;

    }

}

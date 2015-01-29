import javax.swing.*;
import java.awt.*;

/**
 * Created by Woodje on 28-01-2015.
 */
public class TextFieldPanel extends JPanel {

    // One of this panels overloaded constructors.
    public TextFieldPanel(String text, boolean enabled) {

        // Create a new label, set its preferred size and add it to the panel.
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(70, 10));
        add(label);

        // Create a new textfield, set its state to the provided values.
        JTextField textField = new JTextField(25);
        textField.setEnabled(enabled);

        // Add a new textfield to the panel.
        add(textField);

    }

    // One of this panels overloaded constructors.
    public TextFieldPanel(String text, String value, boolean enabled) {

        // Create a new label, set its preferred size and add it to the panel.
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(70, 10));
        add(label);

        // Create a new textfield, set its state to the provided value.
        JTextField textField = new JTextField(25);
        textField.setText(value);
        textField.setEnabled(enabled);

        // Add a new textfield to the panel.
        add(textField);

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Woodje on 28-01-2015.
 */
public class ButtonPanel extends JPanel {

    // This panels constructor.
    public ButtonPanel(String text, boolean enabled, ActionListener actionListener) {

        // Create a button and give it a name.
        JButton button = new JButton(text);

        // Set the buttons state.
        button.setEnabled(enabled);

        // Add the provided actionlistener to the butten.
        button.addActionListener(actionListener);

        // Add a new button to panel.
        add(button);

    }

}

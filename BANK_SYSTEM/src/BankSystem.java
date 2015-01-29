import static java.awt.EventQueue.invokeLater;

import javax.swing.*;

/**
 * Created by Woodje on 28-01-2015.
 */
public class BankSystem extends JFrame {

    // This frames constructor.
    private BankSystem() {

        // Call the superclasses constructor so a name can be specified.
        super("BANK SYSTEM");

        // Closes the program when the user closes the frame.
        // Default operation is: "HIDE_ON_CLOSE".
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the newly created JPanel to the main frame.
        getContentPane().add(new MainPanel());

        // Resize the frame to fit only the current contents.
        pack();

        // Set the app to center of the screen.
        setLocationRelativeTo(null);

    }

    public static void main(String args[]) {

        // Ask swing to run the following at any convenience.
        invokeLater(new Runnable() {

            public void run() {

                // Create a new instance of our app and make it visible.
                new BankSystem().setVisible(true);

            }

        });

    }

}

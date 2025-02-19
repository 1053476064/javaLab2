import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

// This dialog lets the user add a new event (Deadline or Meeting).
public class AddEventModal extends JDialog {
    private final EventListPanel eventListPanel;  // The main panel to update.
    private final JTextField nameField;           // Field for the event name.
    private final JTextField dateTimeField;       // Field for the start time.
    private final JTextField extraField;          // Field for extra info.
    private final JButton addButton;              // Button to add the event.
    private final JButton cancelButton;           // Button to cancel.

    // Constructor that takes the main panel.
    public AddEventModal(EventListPanel eventListPanel) {
        // Use the main panel's window as the parent.
        super((JFrame) SwingUtilities.getWindowAncestor(eventListPanel), "Add Event", true);
        this.eventListPanel = eventListPanel;
        setLayout(new GridLayout(5, 2, 5, 5)); // Use a grid layout.
        
        // Add a label and text field for the event name.
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);
        
        // Add a label and text field for the start time.
        // Use the format yyyy-MM-ddTHH:mm.
        add(new JLabel("Start DateTime (yyyy-MM-ddTHH:mm):"));
        dateTimeField = new JTextField();
        add(dateTimeField);
        
        // Add a label and text field for extra information
        add(new JLabel("Extra (Meeting End DateTime or Deadline info):"));
        extraField = new JTextField();
        add(extraField);
        
        // Add the "Add" button and set its action
        addButton = new JButton("Add");
        addButton.addActionListener((ActionEvent e) -> addEvent());
        add(addButton);
        
        // Add the "Cancel" button and set its action
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> dispose());
        add(cancelButton);
        
        pack();
        setLocationRelativeTo(null); // Center the dialog.
        setVisible(true);            // Show the dialog.
    }
    
    // This method processes the event addition
    private void addEvent() {
        String name = nameField.getText().trim();
        String dateTimeStr = dateTimeField.getText().trim();
        String extra = extraField.getText().trim();
        
        // Parse the start time using ISO_LOCAL_DATE_TIME format
        LocalDateTime startDateTime;
        try {
            startDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid DateTime format!");
            return;
        }
        
        // Try to parse the extra field.
        // If it can be parsed as a date time, create a meeting
        try {
            LocalDateTime extraDateTime = LocalDateTime.parse(extra, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            // Create a meeting with a temporary location "TBD"
            Meeting meeting = new Meeting(name, startDateTime, extraDateTime, "TBD");
            eventListPanel.addEvent(meeting);
        } catch (Exception ex) {
            // If parsing fails, create a deadline
            Deadline deadline = new Deadline(name, startDateTime);
            eventListPanel.addEvent(deadline);
        }
        
        // Close the dialog
        dispose();
    }
}

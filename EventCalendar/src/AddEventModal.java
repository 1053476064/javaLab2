import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class AddEventModal extends JDialog {
    private JTextField nameField;
    private JTextField dateTimeField;
    private JTextField extraField; // For Meeting: end time or location (we use two fields below)
    private JTextField endTimeField;
    private JTextField locationField;
    private JComboBox<String> typeDropDown;
    private JButton addButton;
    private JButton cancelButton;
    private EventListPanel eventListPanel;
    
    public AddEventModal(JFrame parent, EventListPanel eventListPanel) {
        super(parent, "Add Event", true);
        this.eventListPanel = eventListPanel;
        setLayout(new GridLayout(0, 2, 5, 5));
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        // Type selection: Deadline or Meeting.
        add(new JLabel("Event Type:"));
        typeDropDown = new JComboBox<>(new String[] {"Deadline", "Meeting"});
        add(typeDropDown);
        
        // Common field: name.
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);
        
        // Common field: date & time (for Deadline: deadline, for Meeting: start time).
        add(new JLabel("Start (yyyy-MM-dd HH:mm):"));
        dateTimeField = new JTextField();
        add(dateTimeField);
        
        // Meeting-specific fields.
        add(new JLabel("End (yyyy-MM-dd HH:mm):"));
        endTimeField = new JTextField();
        add(endTimeField);
        
        add(new JLabel("Location:"));
        locationField = new JTextField();
        add(locationField);
        
        // Buttons.
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        add(addButton);
        add(cancelButton);
        
        // Listeners.
        addButton.addActionListener(e -> addEvent());
        cancelButton.addActionListener(e -> dispose());
        
        // Hide meeting-specific fields if Deadline is selected.
        typeDropDown.addActionListener(e -> {
            boolean isMeeting = typeDropDown.getSelectedItem().equals("Meeting");
            endTimeField.setEnabled(isMeeting);
            locationField.setEnabled(isMeeting);
        });
    }
    
    private void addEvent() {
        try {
            String type = (String) typeDropDown.getSelectedItem();
            String name = nameField.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(dateTimeField.getText().trim(), formatter);
            
            if (type.equals("Deadline")) {
                Deadline deadline = new Deadline(name, start);
                eventListPanel.addEvent(deadline);
            } else {
                // Meeting: get end time and location.
                LocalDateTime end = LocalDateTime.parse(endTimeField.getText().trim(), formatter);
                String location = locationField.getText().trim();
                Meeting meeting = new Meeting(name, start, end, location);
                eventListPanel.addEvent(meeting);
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

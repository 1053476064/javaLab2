import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

// This panel shows the list of events and a button to add new events
public class EventListPanel extends JPanel {
    private final java.util.List<Event> events; // List to hold events
    private final JPanel controlPanel;          // Panel for control buttons
    private final JPanel displayPanel;          // Panel to display events
    private final JButton addEventButton;       // Button to add an event

    // Constructor to set up the panel.
    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));  // Light gray background

        // Create the control panel and button.
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(230, 230, 250)); // Light purple background
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener((ActionEvent e) -> {
            // Show the add event dialog when button is clicked
            new AddEventModal(this);
        });
        controlPanel.add(addEventButton);
        add(controlPanel, BorderLayout.NORTH);
        
        // Create the panel that displays events
        displayPanel = new JPanel();
        displayPanel.setBackground(new Color(245, 245, 245)); // Same light gray background
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);
    }

    // Add an event to the list
    public void addEvent(Event event) {
        events.add(event);
        refreshDisplay();
    }

    // Refresh the display to show all events
    public void refreshDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            // Just make sure the file and class name for EventPanel are correct
            displayPanel.add(new EventPanel(event));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}

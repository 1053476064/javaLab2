import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

// This panel shows the information for one event.
public class EventPanel extends JPanel {
    private final Event event;  // The event to show.
    private JButton completeButton; // Button to mark the event as complete

    // Constructor that sets up the panel.
    public EventPanel(Event event) {
        this.event = event;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        // Show the event name and start time
        JLabel eventLabel = new JLabel(event.getName() + " at " + event.getDateTime());
        add(eventLabel);

        // If the event is a meeting, show extra details
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            JLabel meetingLabel = new JLabel("Duration: " + meeting.getDuration().toMinutes() + " mins, Location: " + meeting.getLocation());
            add(meetingLabel);
        }

        // If the event can be completed, add a complete button.
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener((ActionEvent e) -> {
                ((Completable) event).complete();  // Mark as complete
                updateDisplay();                   // Update the panel
            });
            add(completeButton);
        }
        
        updateUrgency(); // Set the background color
    }

    // Update the display with current event info
    public void updateDisplay() {
        removeAll(); // Clear the panel.
        JLabel eventLabel = new JLabel(event.getName() + " at " + event.getDateTime());
        add(eventLabel);
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            JLabel meetingLabel = new JLabel("Duration: " + meeting.getDuration().toMinutes() + " mins, Location: " + meeting.getLocation());
            add(meetingLabel);
        }
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener((ActionEvent e) -> {
                ((Completable) event).complete();
                updateDisplay();
            });
            add(completeButton);
        }
        updateUrgency();
        revalidate();
        repaint();
    }

    // Set the background color oif this panel
    //I like it to light pink
    public void updateUrgency() {
        setBackground(new Color(255, 182, 193)); // Light pink.
    }
}

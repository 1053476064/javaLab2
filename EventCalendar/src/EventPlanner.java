import javax.swing.*;
import java.awt.*;

public class EventPlanner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Event Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            
            // Create the EventListPanel and add default events.
            EventListPanel eventListPanel = new EventListPanel();
            addDefaultEvents(eventListPanel);
            
            frame.add(eventListPanel);
            frame.setVisible(true);
        });
    }
    
    // Adds some default events (at least one Deadline and one Meeting)
    public static void addDefaultEvents(EventListPanel eventsPanel) {
        // Create default Deadline and Meeting events.
        Deadline defaultDeadline = new Deadline("Submit Report", LocalDateTime.now().plusDays(3));
        Meeting defaultMeeting = new Meeting("Team Meeting", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1), "Conference Room A");
        
        eventsPanel.addEvent(defaultDeadline);
        eventsPanel.addEvent(defaultMeeting);
    }
}

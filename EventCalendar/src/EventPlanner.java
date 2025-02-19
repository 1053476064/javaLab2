import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

// This is the main class that starts the window
public class EventPlanner {
    public static void main(String[] args) {
        // Set the Nimbus look and feel to make the window look nice
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Start the GUI in the event thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Event Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Create the event list panel and add some default events
            EventListPanel eventListPanel = new EventListPanel();
            addDefaultEvents(eventListPanel);
            
            frame.add(eventListPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // This method adds some sample events
    public static void addDefaultEvents(EventListPanel events) {
        LocalDateTime now = LocalDateTime.now();
        Deadline deadline = new Deadline("Sample Deadline", now.plusDays(1));
        Meeting meeting = new Meeting("Sample Meeting", now.plusHours(1), now.plusHours(2), "Conference Room");

        events.addEvent(deadline);
        events.addEvent(meeting);
    }
}

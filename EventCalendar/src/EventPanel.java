import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;
    
    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Build a text area with event details.
        StringBuilder details = new StringBuilder("<html>");
        details.append("<b>").append(event.getName()).append("</b><br/>");
        details.append("Starts: ").append(event.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("<br/>");
        
        // If Meeting, add end time, duration, and location.
        if (event instanceof Meeting) {
            Meeting m = (Meeting) event;
            details.append("Ends: ").append(m.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("<br/>");
            details.append("Duration: ").append(m.getDuration().toHours()).append(" hour(s)<br/>");
            details.append("Location: ").append(m.getLocation()).append("<br/>");
        }
        // For Deadline, only the start time is shown.
        details.append("Completed: ").append((event instanceof Completable && ((Completable)event).isComplete()) ? "Yes" : "No").append("<br/>");
        details.append("</html>");
        
        JLabel label = new JLabel(details.toString());
        add(label, BorderLayout.CENTER);
        
        // If the event is completable, add a complete button.
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                // Refresh the display
                label.setText(details.toString().replace("No", "Yes"));
                // Optionally, update background color (urgency) if you add that feature.
            });
            add(completeButton, BorderLayout.EAST);
        }
        
        // Update background color based on urgency (optional)
        updateUrgency();
    }
    
    // Sets background color based on urgency (red for overdue, yellow for imminent, green for distant).
    public void updateUrgency() {
        LocalDateTime now = LocalDateTime.now();
        if (event.getDateTime().isBefore(now)) {
            setBackground(Color.PINK); // overdue (red/pink)
        } else if (event.getDateTime().isBefore(now.plusHours(24))) {
            setBackground(Color.YELLOW); // imminent
        } else {
            setBackground(Color.GREEN); // distant
        }
    }
    
    public Event getEvent() {
        return event;
    }
}

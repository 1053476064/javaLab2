import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel controlPanel;
    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    private JCheckBox filterComplete;
    private JCheckBox filterDeadlines;
    private JCheckBox filterMeetings;
    private JButton addEventButton;
    
    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());
        
        // Create control panel.
        controlPanel = new JPanel();
        
        // Sort drop-down.
        sortDropDown = new JComboBox<>(new String[] {"Sort by Date", "Sort by Name", "Reverse Date", "Reverse Name"});
        sortDropDown.addActionListener(e -> sortEvents());
        controlPanel.add(sortDropDown);
        
        // Filter checkboxes.
        filterComplete = new JCheckBox("Hide Completed");
        filterDeadlines = new JCheckBox("Hide Deadlines");
        filterMeetings = new JCheckBox("Hide Meetings");
        
        // Add listeners (anonymous class) to update display when filters change.
        filterComplete.addActionListener(e -> updateDisplay());
        filterDeadlines.addActionListener(e -> updateDisplay());
        filterMeetings.addActionListener(e -> updateDisplay());
        
        controlPanel.add(filterComplete);
        controlPanel.add(filterDeadlines);
        controlPanel.add(filterMeetings);
        
        // Add event button.
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            AddEventModal modal = new AddEventModal((JFrame) SwingUtilities.getWindowAncestor(this), this);
            modal.setVisible(true);
        });
        controlPanel.add(addEventButton);
        
        add(controlPanel, BorderLayout.NORTH);
        
        // Display panel to show events.
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    // Add a new event to the list and update the display.
    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }
    
    // Sort events based on the sort drop-down selection.
    private void sortEvents() {
        String selection = (String) sortDropDown.getSelectedItem();
        if (selection.equals("Sort by Date")) {
            Collections.sort(events);
        } else if (selection.equals("Reverse Date")) {
            Collections.sort(events, Comparator.reverseOrder());
        } else if (selection.equals("Sort by Name")) {
            events.sort(Comparator.comparing(Event::getName));
        } else if (selection.equals("Reverse Name")) {
            events.sort(Comparator.comparing(Event::getName).reversed());
        }
        updateDisplay();
    }
    
    // Update the display panel according to current events and filter selections.
    public void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            // Apply filters.
            if (filterComplete.isSelected() && (event instanceof Completable && ((Completable) event).isComplete())) {
                continue;
            }
            if (filterDeadlines.isSelected() && event instanceof Deadline) {
                continue;
            }
            if (filterMeetings.isSelected() && event instanceof Meeting) {
                continue;
            }
            // Add an EventPanel for this event.
            displayPanel.add(new EventPanel(event));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}

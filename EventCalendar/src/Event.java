import java.time.LocalDateTime;

// This is an abstract class for events
// All events will extend this class
public abstract class Event implements Comparable<Event> {
    protected String name;                  // The name of the event
    protected LocalDateTime dateTime;       // The start date and time of the event

    // Constructor that sets the name and start time
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // An abstract method to get the event name
    public abstract String getName();

    // Get the start time of the event
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Set a new start time
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Set a new name
    public void setName(String name) {
        this.name = name;
    }

    // Compare events by their start time
    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}

import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;
    
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }
    
    // Abstract getter for the event name
    public abstract String getName();
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // Compare events by their start date and time.
    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}

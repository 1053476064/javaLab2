import java.time.Duration;
import java.time.LocalDateTime;

// This class represents a meeting event
public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;  // The end time of the meeting
    private String location;            // The location of the meeting
    private boolean complete;           // This flag shows if the meeting is done

    // Constructor that takes a name, start time, end time, and location
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
        this.complete = false;
    }

    // Return the meeting name
    @Override
    public String getName() {
        return name;
    }

    // Mark the meeting as complete
    @Override
    public void complete() {
        complete = true;
    }

    // Check if the meeting is complete
    @Override
    public boolean isComplete() {
        return complete;
    }

    // Get the end time of the meeting
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // Get the duration of the meeting
    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime);
    }

    // Get the location of the meeting
    public String getLocation() {
        return location;
    }

    // Set a new end time
    public void setEndDateTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    // Set a new location
    public void setLocation(String location) {
        this.location = location;
    }
}

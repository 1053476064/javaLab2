import java.time.LocalDateTime;

// This class represents a deadline event
public class Deadline extends Event implements Completable {
    private boolean complete;  // This flag shows if the task is done

    // Constructor that takes a name and a deadline.
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.complete = false;  // Initially, the task is not done
    }

    // Return the event name
    @Override
    public String getName() {
        return name;
    }

    // Mark the task as complete
    @Override
    public void complete() {
        complete = true;
    }

    // Check if the task is complete
    @Override
    public boolean isComplete() {
        return complete;
    }
}

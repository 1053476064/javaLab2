import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete;
    
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.complete = false;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void complete() {
        complete = true;
    }
    
    @Override
    public boolean isComplete() {
        return complete;
    }
    
    // Optionally, you could override toString() to help with debugging.
    @Override
    public String toString() {
        return "Deadline: " + name + " at " + dateTime + (complete ? " (Complete)" : "");
    }
}

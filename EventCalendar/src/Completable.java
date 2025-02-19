// This is an interface for things that can be completed
public interface Completable {
    // Mark the item as complete
    void complete();
    // Check if the item is complete
    boolean isComplete();
}

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

// This class tests the logic of our code.
public class EventTester {

    // We use final for values that do not change
    static final LocalDateTime deadline = LocalDateTime.of(2024, 12, 7, 17, 0);
    static final String lastDeadlineName = "Last Deadline";
    static final String lastDeadlineNameAlt = "Final Deadline";
    static final Deadline lastDeadline = new Deadline(lastDeadlineName, deadline);
    static final Deadline midDeadline = new Deadline("Mid Deadline", deadline.minusDays(10));
    static final Deadline firstDeadline = new Deadline("First Deadline", deadline.minusDays(20));
    static final int INCREMENT = 1;

    static final LocalDateTime start = LocalDateTime.of(2024, 10, 7, 15, 0);
    static final LocalDateTime end = LocalDateTime.of(2024, 10, 7, 16, 0);
    static final String location = "MCS 321";
    static final String locationAlt = "MCS 339";

    static final Meeting firstMeeting = new Meeting("First Meeting", start, end, location);
    static final Meeting lastMeeting = new Meeting("Last Meeting", start.plusDays(4), end.plusDays(4), location);
    static final Meeting midMeeting = new Meeting("Middle Meeting", start.plusDays(2), end.plusDays(2), location);

    static final Event[] events = new Event[] {
        midDeadline,
        lastMeeting,
        lastDeadline,
        firstDeadline,
        firstMeeting,
        midMeeting,
    };

    static final Deadline[] deadlines = new Deadline[] {
        firstDeadline,
        midDeadline,
        lastDeadline,
    };

    static final Meeting[] meetings = new Meeting[] {
        firstMeeting,
        midMeeting,
        lastMeeting,
    };

    // Main method that runs all tests
    public static void main(String[] args) {
        System.out.println("Testing Getters..." + (testGetters() ? "passed" : "failed"));
        System.out.println("Testing Setters..." + (testSetters() ? "passed" : "failed"));
        System.out.println("Testing Comparable..." + (testComparingEvents() ? "passed" : "failed"));
        System.out.println("Testing Meeting Duration..." + (testMeetingDuration() ? "passed" : "failed"));
        System.out.println("Testing Completable..." + (testCompletable() ? "passed" : "failed"));
    }

    // Test if getter methods work
    public static boolean testGetters() {
        return (
                lastDeadline.getName().equals(lastDeadlineName)
                && lastDeadline.getDateTime().equals(deadline)
                && firstMeeting.getEndDateTime().equals(end)
                && firstMeeting.getLocation().equals(location)
        );
    }

    // Test if setter methods change the values
    public static boolean testSetters() {
        lastDeadline.setName(lastDeadlineNameAlt);
        lastDeadline.setDateTime(deadline.minusDays(INCREMENT));
        firstMeeting.setEndDateTime(end.plusDays(INCREMENT));
        firstMeeting.setLocation(locationAlt);

        boolean passed = (
            lastDeadline.getName().equals(lastDeadlineNameAlt)
            && lastDeadline.getDateTime().equals(deadline.minusDays(INCREMENT))
            && firstMeeting.getEndDateTime().equals(end.plusDays(INCREMENT))
            && firstMeeting.getLocation().equals(locationAlt)
        );

        // Reset to original values.
        lastDeadline.setName(lastDeadlineName);
        lastDeadline.setDateTime(deadline);
        firstMeeting.setEndDateTime(end);
        firstMeeting.setLocation(location);

        return passed;
    }

    // Test if events are compared by time
    public static boolean testComparingEvents() {
        Arrays.sort(events);
        return (
            events[0] == firstMeeting
            && events[1] == midMeeting
            && events[2] == lastMeeting
            && events[3] == firstDeadline
            && events[4] == midDeadline
            && events[5] == lastDeadline
        );
    }

    // Test if the meeting lasts one hour
    public static boolean testMeetingDuration() {
        Duration duration1 = firstMeeting.getDuration();
        Duration duration2 = Duration.ofHours(1);
        return duration1.equals(duration2);
    }

    // Test if the Completable interface works
    public static boolean testCompletable() {
        for (Deadline d : deadlines) {
            d.complete();
        }
        for (Meeting m : meetings) {
            m.complete();
        }
        boolean deadlinesPass = Stream.of(deadlines).allMatch(Deadline::isComplete);
        boolean meetingsPass = Stream.of(meetings).allMatch(Meeting::isComplete);
        return deadlinesPass && meetingsPass;
    }
}

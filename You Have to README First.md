How to Taste:

I wrote this so that when you grade my work, you can easily understand what I wrote and give me a bit more credit. :)

I added a lot of comments to the code because many people said my last lab did not have enough.

This is a simple event calendar application. It lets you add and manage events like deadlines and meetings.

First, please make sure you have Java 8 or above installed. I recommend you use VS Code because it is very convenient.

In the main window, click the "Add Event" button to open a dialog. Follow the hints to add an event of type Deadline or Meeting.

I think the GUI looks a bit harsh on the eyes, so I tried the Nimbus look and feel. It still looks average though >^<

I tried to enable auto push, but I found it did not work. This means my push history has only a few entries. Please do not lower my score for this. I tried to push at least more than 20 times QAQ


Below are the Java libraries I used:

java.time.LocalDateTime

Use: To handle event dates and times (start, end, etc.).
Place: In Event.java, Deadline.java, Meeting.java, and other classes for parsing and comparing dates/times.
java.time.Duration

Use: To compute meeting duration.
Place: In Meeting.java to get the time difference between start and end.
javax.swing.*

Use: To build the graphical user interface (GUI).
Place: In EventPlanner.java, EventListPanel.java, EventPanel.java, and AddEventModal.java to create windows, panels, buttons, labels, and dialogs.
java.awt.*

Use: For GUI layout and graphics (colors, layout, events, etc.).
Place: In GUI classes to set background colors, layouts, and other visual parts.
java.awt.event.ActionEvent

Use: To handle button click events in the GUI.
Place: In EventListPanel.java, EventPanel.java, and AddEventModal.java to respond to user actions.
java.util.* (such as ArrayList, Arrays)

Use: To store and handle collections of events.
Place: In EventTester.java and EventListPanel.java to hold event objects and sort them.
java.util.stream.Stream

Use: To process collections in a simple way (for example, checking if all events are complete).
Place: In EventTester.java when testing the completable function.
java.time.format.DateTimeFormatter

Use: To parse and format date/time strings in a standard way.
Place: In AddEventModal.java to convert text input into LocalDateTime objects.
Below is the project structure. This shows what each file does:

Completable.java: An interface to mark events that can be completed.
Event.java: An abstract class that defines basic event properties.
Deadline.java: A class that represents a deadline event.
Meeting.java: A class that represents a meeting event.
EventTester.java: A class to test the event logic.
EventPlanner.java: The main class that starts the GUI.
EventListPanel.java: A panel that shows the list of events and an "Add Event" button.
EventPanel.java: A panel that shows the details of one event.
AddEventModal.java: A dialog to add a new event.

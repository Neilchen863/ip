package hiChat.event;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testMarkAsDone() {
        Task task = new Task("task");
        task.markAsDone();
        assert(task.getIsDone());
    }
}

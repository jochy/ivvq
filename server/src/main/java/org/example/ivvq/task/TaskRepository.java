package org.example.ivvq.task;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Repository that contains all tasks.
 * Simulates a database
 */
@Repository
public class TaskRepository {

    /**
     * Simulates a db
     */
    private Cache<Object, Object> db = Caffeine.newBuilder().maximumSize(100).build();

    /**
     * Add some dummy tasks
     */
    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            Task t = new Task();
            t.setId(Integer.toString(i));
            t.setTitle("The task number " + i);
            t.setUserId(UUID.randomUUID().toString());
            db.put(t.getId(), t);
        }
    }

    /**
     * Retrieves a task
     *
     * @param id the task's id
     * @return th task
     */
    public Task get(String id) {
        return (Task) db.getIfPresent(id);
    }

    /**
     * List all tasks
     *
     * @return all tasks
     */
    public List<Task> list() {
        return db.asMap()
                .values()
                .parallelStream()
                .map(Task.class::cast)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new task
     * @param task
     */
    public void add(Task task) {
        db.put(task.getId(), task);
    }
}

package org.example.ivvq.task;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Task service
 */
@Service
public class TaskService {

    /**
     * The {@link TaskRepository}
     */
    private final TaskRepository repository;

    /**
     * Constructor - autowired
     *
     * @param repository the {@link TaskRepository}
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a task by its id
     *
     * @param id the task id
     * @return the task or {@link IllegalAccessException} if not found
     */
    public Task get(String id) {
        Task task = repository.get(id);
        if (task == null) {
            throw new IllegalArgumentException("No task with the given id");
        }
        return task;
    }

    /**
     * Retrieves all tasks to do
     *
     * @return all tasks to do
     */
    public List<Task> listTodo() {
        return repository.list()
                .stream()
                .filter(t -> !t.isCompleted())
                .collect(Collectors.toList());
    }

    /**
     * Adds a new task
     * @param title the task's title
     * @return the {@link Task} added
     */
    public Task newTask(String title) {
        Task t = new Task();
        t.setId(repository.list().size() + "");
        t.setUserId(UUID.randomUUID().toString());
        t.setTitle(title);
        repository.add(t);
        return t;
    }
}

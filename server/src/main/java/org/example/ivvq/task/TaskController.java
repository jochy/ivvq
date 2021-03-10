package org.example.ivvq.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for tasks
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    /**
     * The {@link TaskService}
     */
    private final TaskService service;

    /**
     * Constructor - autowired
     *
     * @param service the {@link TaskService}
     */
    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * Gets a {@link Task} by its id
     *
     * @param id    the task ID
     * @param model the {@link Model} for Thymeleaf
     * @return the template to render
     */
    @GetMapping("/{id}")
    public String get(@PathVariable("id") String id, Model model) {
        model.addAttribute("task", service.get(id));
        return "get.html";
    }

    /**
     * Adds a new task
     * @param title the task title
     * @param model the {@link Model} for Thymeleaf
     * @return the template to display
     */
    @GetMapping("/newTask")
    public String newTask(@RequestParam("title") String title, Model model) {
        model.addAttribute("task", service.newTask(title));
        return "get.html";
    }

    /**
     * Display all tasks to do
     *
     * @param model the {@link Model} for Thymeleaf
     * @return the template to render
     */
    @GetMapping("/todo")
    public String todo(Model model) {
        model.addAttribute("tasks", service.listTodo());
        return "todo.html";
    }
}

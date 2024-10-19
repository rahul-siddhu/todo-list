package com.testProjects.todolist.controllers;

import com.testProjects.todolist.models.User;
import com.testProjects.todolist.repositories.UserRepository;
import com.testProjects.todolist.models.Task;
import com.testProjects.todolist.services.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Import for @Valid
import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    private final TaskServiceImpl taskService;

    @Autowired
    private UserRepository userRepository;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String getAllTasksForCurrentUser(Model model) {
        List<Task> tasks = taskService.getTasksForCurrentUser();
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) throws Throwable {
        Task task = (Task) taskService.findTaskById(id);
        model.addAttribute("task", task);
        return "details";
    }

    @GetMapping("/tasks/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("task", task);
            return "create"; // Show the create form with validation errors
        }

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User user = userRepository.findByUsername(currentPrincipalName).orElse(null);
            assert user != null; // Ensure user is found
            task.setUser(user); // Associate the task with the user
            taskService.saveTask(task);
            return "redirect:/" + task.getId(); // Redirect to task details page
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while creating the task.");
            return "error"; // Redirect to your error page
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) throws Throwable {
        Task task = (Task) taskService.findTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute("task") Task taskDetails, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit"; // Show the edit form with validation errors
        }

        try {
            Task task = (Task) taskService.findTaskById(id);
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            taskService.saveTask(task);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while updating the task.");
            return "error"; // Redirect to your error page
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}

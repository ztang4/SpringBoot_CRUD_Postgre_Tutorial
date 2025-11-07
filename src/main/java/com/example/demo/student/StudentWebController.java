package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentWebController {
    private final StudentService studentService;

    @Autowired
    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @GetMapping("/button-click")
    public String handleButtonClick(Model model) {
        String clickTime = java.time.LocalDateTime.now().toString();
        System.out.println("Button clicked at: " + clickTime);
        model.addAttribute("lastClickTime", clickTime);
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String dob) {
        Student student = new Student(
            name,
            email,
            java.time.LocalDate.parse(dob)
        );
        studentService.addNewStudent(student);
        return "redirect:/"; // Redirect back to the main page
    }
}
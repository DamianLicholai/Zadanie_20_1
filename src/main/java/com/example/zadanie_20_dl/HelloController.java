package com.example.zadanie_20_dl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {

    private UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/addFromForm")
    public String addFromForm(@RequestParam String name, @RequestParam String surname, @RequestParam Integer age) {
        User user = new User(name, surname, age);
        userRepository.add(user);
        return "redirect:/users";
    }

    @ResponseBody
    @RequestMapping("/users")
    public String users() {
        List<User> userList = userRepository.getAll();

        String result = "";
        for (User user1 : userList) {
            result += user1.getName() + " " + user1.getSurname() + " " + user1.getAge() + "<br/>";
        }

        return result;
    }

    @ResponseBody
    @GetMapping("/add")
    public String add(@RequestParam(name = "imie") String name, @RequestParam String surname, @RequestParam Integer age) {
        return name + " " + surname + " " + age;
    }
}

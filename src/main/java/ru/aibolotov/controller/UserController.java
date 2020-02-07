package ru.aibolotov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.aibolotov.model.Role;
import ru.aibolotov.model.User;
import ru.aibolotov.service.RoleService;
import ru.aibolotov.service.UserService;

import java.util.*;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> userList = userService.getListUsers();
        model.addAttribute("users", userList);
        return "list-users";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("rolesList", roleService.getListRoles());
        return "user-form";
    }

    @PostMapping("/saveUser")
public String saveUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("roles") String[] role) {
    Set<Role> roles = new HashSet<>();
    for (String s : role) {
        roles.add(roleService.getRoleByName(s));
    }

    User user = new User(name, password, roles);

    userService.addUser(user);

    return "redirect:/list";
    }

//    @GetMapping("/saveUser")
//    public String saveUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("roles") String[] role) {
//        Set<Role> roles = new HashSet<>();
//        for (String s : role) {
//            roles.add(roleService.getRoleByName(s));
//        }
//
//        User user = new User(name, password, roles);
//
//        userService.addUser(user);
//
//        return "redirect:/list";
//    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/list";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @RequestMapping(value = "userdata", method = RequestMethod.GET)
    public String printDataUser(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUser(auth.getName());
        List<String> messages = new ArrayList<>();
        messages.add("Hello! "+auth.getName());
        messages.add("Пароль: " + user.getPassword());
        messages.add("Роль: " + user.getRoles());
        model.addAttribute("messages", messages);
        return "userdata";
    }

    @ModelAttribute("rolesList")
    public List<Role> getUserList() {

        return roleService.getListRoles();
    }
}

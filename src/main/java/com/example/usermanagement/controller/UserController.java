package com.example.usermanagement.controller;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "用户名或密码错误，或账户已被禁用");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           Model model) {
        User user = new User(username, password, email);
        boolean success = userService.register(user);

        if (success) {
            model.addAttribute("message", "注册成功，请登录");
            return "login";
        } else {
            model.addAttribute("error", "注册失败，用户名可能已存在");
            return "register";
        }
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "dashboard";
    }

    @GetMapping("/change-password")
    public String showChangePasswordPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "新密码和确认密码不一致");
            return "change-password";
        }

        boolean success = userService.changePassword(user.getUsername(), oldPassword, newPassword);
        if (success) {
            model.addAttribute("message", "密码修改成功，请重新登录");
            session.invalidate();
            return "login";
        } else {
            model.addAttribute("error", "原密码错误，修改失败");
            return "change-password";
        }
    }

    @PostMapping("/logout-account")
    public String logoutAccount(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userService.logout(user.getUsername());
            session.invalidate();
            model.addAttribute("message", "账户已成功注销");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
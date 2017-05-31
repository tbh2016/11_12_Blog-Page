package com.sg.capstoneblog.controller;

import com.sg.capstoneblog.dao.UserDao;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.User;
import com.sg.capstoneblog.service.LinkService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserDao dao;
    private LinkService linkService;
    private PasswordEncoder encoder;

    @Inject
    public UserController(UserDao dao, LinkService linkService, PasswordEncoder encoder) {
        this.dao = dao;
        this.linkService = linkService;
        this.encoder = encoder;
    }

    // This endpoint retrieves all users from the database and puts the
    // List of users on the model
    @RequestMapping(value = "/admin/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = dao.getAllUsers();

        model.put("users", users);
        return "displayUserList";
    }
    // This endpoint just displays the Add User form

    @RequestMapping("/admin/users")
    public String users() {
        return "adminUsers";
    }

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Model model) {
        List<Link> navLinks = linkService.getMainLinks();
        model.addAttribute("navLinks", navLinks);
        return "addUserForm";
    }

    @RequestMapping(value = "/admin/displayUserForm", method = RequestMethod.GET)
    public String adminDisplayUserForm(Model model) {
//        List<Link> navLinks = linkService.getMainLinks();
//        model.addAttribute("navLinks", navLinks);
        return "adminAddUserForm";
    }
    // This endpoint processes the form data and creates a new User

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req, Model model) {
        User newUser = new User();
        // This example uses a plain HTML form so we must get values
        // from the request

        newUser.setUserName(req.getParameter("userName"));
        if (dao.getUserByName(newUser.getUserName()) != null) {
            String errorMessage = "Username already exists, please choose another";
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
        
        newUser.setFirstName(req.getParameter("firstName"));
        newUser.setLastName(req.getParameter("lastName"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin
        // box is checked
        newUser.addAuthority("ROLE_USER");

        dao.addUser(newUser);

        model.addAttribute("userName", newUser.getUserName());

        return "login";
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String adminAddUser(HttpServletRequest req) {
        User newUser = new User();
        // This example uses a plain HTML form so we must get values
        // from the request
        newUser.setUserName(req.getParameter("userName"));
        newUser.setFirstName(req.getParameter("firstName"));
        newUser.setLastName(req.getParameter("lastName"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin
        // box is checked
        newUser.addAuthority("ROLE_USER");
        newUser.addAuthority("ROLE_MANAGER");

        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        dao.addUser(newUser);

        return "redirect:/admin";
    }
    // This endpoint deletes the specified User

    @RequestMapping(value = "/admin/deactivateUser", method = RequestMethod.GET)
    public String deletUser(@RequestParam("userId") Integer userId,
            Map<String, Object> model) {

        dao.deactivateUser(userId);
        return "redirect:/user/admin/users";
    }

    @RequestMapping("/admin/getActiveUsers")
    public String getActiveUsers(Model model) {
        List<User> users = dao.getAllUsers();
        model.addAttribute("users", users);
        return "adminActiveUsers";
    }
}

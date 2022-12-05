package com.example.personalproject.controllers;

import com.example.personalproject.models.ModelUser;
import com.example.personalproject.models.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private com.example.personalproject.repos.UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String userView(Model model)
    {
        model.addAttribute("user_list", userRepository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        return "info";
    }

    @GetMapping("/{id}/update")
    public String updView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        model.addAttribute("roles", RoleEnum.values());
        return "update";
    }


    @PostMapping("/{id}/update")
    public String update_user(@RequestParam String username,
                              @RequestParam(name="roles[]", required = false) String[] roles,
                              @PathVariable Long id)
    {
        ModelUser user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);

        user.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(RoleEnum.valueOf(role));
            }
        }



        userRepository.save(user);
        return "redirect:/admin/{id}";
    }

}

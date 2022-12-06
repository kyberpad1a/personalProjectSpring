package com.example.personalproject.controllers;

import com.example.personalproject.models.*;
import com.example.personalproject.repos.PasportDataRepository;
import com.example.personalproject.repos.PrivateDataRepository;
import com.example.personalproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private com.example.personalproject.repos.UserRepository UserRepository;
    @Autowired
    private com.example.personalproject.repos.PrivateDataRepository PrivateDataRepository;
    @Autowired
    private com.example.personalproject.repos.PasportDataRepository PasportDataRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/registration")
    private String RegView(Model modelPasportData)
    {
        Iterable<ModelPrivateData> privates = PrivateDataRepository.findAll();
        modelPasportData.addAttribute("privatedata", privates);

        return "regis";
    }
    @PostMapping("/registration")
    private String Reg(@Valid ModelUser user, BindingResult bindingResult, Model model, @RequestParam String omsNumber)
    {
        ModelUser user_from_db = UserRepository.findByUsername(user.getUsername());
        if (user_from_db != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        if(bindingResult.hasErrors()) {

            return "regis";
        }

        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPrivateData(PrivateDataRepository.findByOmsNumber(omsNumber));
        UserRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/registration/pasport/add")
    public String pasportdataAddPage(@ModelAttribute("pasportdata") ModelPasportData modelPasportData)
    {
        return "pasport-add";
    }



    @PostMapping("/registration/pasport/add")
    private String pasportdataAddPage(@ModelAttribute("pasportdata") @Valid ModelPasportData modelPasportData, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "pasport-add";
        PasportDataRepository.save(modelPasportData);
        return "redirect:/registration/privatedata/add";
    }

    @GetMapping("/registration/privatedata/add")
    public String privatedataAddPage(@ModelAttribute("privatedata") ModelPrivateData modelPrivateData, Model ModelPasportData)
    {
        Iterable<ModelPasportData> pasports = PasportDataRepository.findAll();
        ModelPasportData.addAttribute("pasportdata", pasports);

        return "privatedata-add";
    }
    @PostMapping("/registration/privatedata/add")
    public String privatedataAdd(@ModelAttribute("privatedata") @Valid ModelPrivateData modelPrivateData, BindingResult bindingResult, @RequestParam Integer pasportNumber, Model ModelPasportData)
    {

        if (bindingResult.hasErrors()) {
            Iterable<ModelPasportData> pasports = PasportDataRepository.findAll();
            ModelPasportData.addAttribute("pasportdata", pasports);
            return "privatedata-add";
        }
        modelPrivateData.setEmployeePasport(PasportDataRepository.findByPasportNumber(pasportNumber));
        PrivateDataRepository.save(modelPrivateData);
        return "redirect:/registration";
    }

}

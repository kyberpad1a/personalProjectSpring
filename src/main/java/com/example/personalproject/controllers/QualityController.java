package com.example.personalproject.controllers;

import com.example.personalproject.models.ModelCertificate;
import com.example.personalproject.models.ModelGood;
import com.example.personalproject.models.ModelQuality;
import com.example.personalproject.repos.QualityRepository;
import com.example.personalproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class QualityController {
    @Autowired
    private com.example.personalproject.repos.goodRepository goodRepository;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private QualityRepository QualityRepository;
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @GetMapping("/quality")
    public String qualityMain(Model model)
    {
        Iterable<ModelQuality> qualities = QualityRepository.findAll();
        model.addAttribute("qualities", qualities);
        return "quality-main";
    }
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @GetMapping("/quality/add")
    public String qualityAddPage(@ModelAttribute("quality") ModelQuality modelQuality, Model ModelGood, Model ModelUser)
    {
        Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
        ModelGood.addAttribute("good", goods);
        Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
        ModelUser.addAttribute("user", users);
        return "quality-add";
    }
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @PostMapping("/quality/add")
    public String qualityAdd(@ModelAttribute("quality") @Valid ModelQuality modelQuality, BindingResult bindingResult, @RequestParam String username, Model ModelUser, @RequestParam String goodName, Model ModelGood)
    {

        if (bindingResult.hasErrors()) {
            Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
            ModelUser.addAttribute("user", users);
            Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
            ModelGood.addAttribute("good", goods);
            return "quality-add";
        }
        modelQuality.setUser(UserRepository.findByUsername(username));
        modelQuality.setGood(goodRepository.findByGoodName(goodName));

        QualityRepository.save(modelQuality);
        return "redirect:/quality";
    }
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @GetMapping("/quality/{ID_Quality}")
    public String qualityDetails(@PathVariable(value = "ID_Quality") long ID_Quality, Model model)
    {
        Optional<ModelQuality> quality = QualityRepository.findById(ID_Quality);
        ArrayList<ModelQuality> res = new ArrayList<>();
        quality.ifPresent(res::add);
        model.addAttribute("quality",res);
        if(!QualityRepository.existsById(ID_Quality))
        {
            return "redirect:/quality";
        }
        return "quality-details";
    }
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @GetMapping("/quality/{ID_Quality}/edit")
    public String qualityEdit(@PathVariable("ID_Quality") long ID_Quality, Model ModelQuality, @ModelAttribute("quality") ModelQuality modelQuality, Model ModelGood, Model ModelUser)
    {
        /*if(!goodRepository.existsById(ID_Good)){
            return "redirect:/good";
        }
        Optional<modelGood> good = goodRepository.findById(ID_Good);
        ArrayList<modelGood> res = new ArrayList<>();
        good.ifPresent(res::add);*/
        Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
        ModelUser.addAttribute("user", users);
        Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
        ModelGood.addAttribute("good", goods);
        ModelQuality res = QualityRepository.findById(ID_Quality).orElseThrow();
        ModelQuality.addAttribute("modelQuality",res);
        return "quality-edit";
    }
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @PostMapping("/quality/{ID_Quality}/edit")
    public String qualityUpdate(@PathVariable("ID_Quality") long ID_Quality,
                             @ModelAttribute("quality") @Valid ModelQuality modelQuality, BindingResult bindingResult, @RequestParam String username, Model ModelUser, @RequestParam String goodName, Model ModelGood)
    {
        if (bindingResult.hasErrors()) {
            Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
            ModelUser.addAttribute("user", users);
            Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
            ModelGood.addAttribute("good", goods);
            return "quality-edit";
        }
        modelQuality.setUser(UserRepository.findByUsername(username));
        modelQuality.setGood(goodRepository.findByGoodName(goodName));

        QualityRepository.save(modelQuality);
        return "quality-main";
    }
    @PreAuthorize("hasAnyAuthority('QUALITYWORKER')")
    @PostMapping("quality/{ID_Quality}/remove")
    public String qualityDelete(@PathVariable("ID_Quality") long ID_Quality, Model model){
        ModelQuality quality = QualityRepository.findById(ID_Quality).orElseThrow();
        QualityRepository.delete(quality);
        return "redirect:/quality";
    }
}

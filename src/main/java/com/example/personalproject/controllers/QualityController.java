package com.example.personalproject.controllers;

import com.example.personalproject.models.ModelCertificate;
import com.example.personalproject.models.ModelGood;
import com.example.personalproject.models.ModelQuality;
import com.example.personalproject.repos.QualityRepository;
import com.example.personalproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository UserRepository;
    private QualityRepository QualityRepository;
    @GetMapping("/quality")
    public String qualityMain(Model model)
    {
        Iterable<ModelQuality> qualities = QualityRepository.findAll();
        model.addAttribute("qualities", qualities);
        return "quality-main";
    }
    @GetMapping("/quality/add")
    public String qualityAddPage(@ModelAttribute("quality") ModelQuality modelQuality, Model ModelGood, Model ModelUser)
    {
        Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
        ModelGood.addAttribute("good", goods);
        Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
        ModelUser.addAttribute("user", users);
        return "quality-add";
    }
    @PostMapping("/good/add")
    public String qualityAdd(@ModelAttribute("quality") @Valid ModelQuality modelQuality, BindingResult bindingResult, @RequestParam String username, Model ModelUser, @RequestParam String goodName, Model ModelGood)
    {

        if (bindingResult.hasErrors()) {
            Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
            ModelUser.addAttribute("user", users);
            Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
            ModelGood.addAttribute("good", goods);
            return "good-add";
        }
        modelQuality.setUser(UserRepository.findByUsername(username));
        modelQuality.setGood(goodRepository.findByGoodName(goodName));

        QualityRepository.save(modelQuality);
        return "redirect:/good";
    }

    @GetMapping("/quality/{ID_Quality}")
    public String qualityDetails(@PathVariable(value = "ID_Quality") long ID_Quality, Model model)
    {
        Optional<ModelQuality> good = goodRepository.findById(ID_Good);
        ArrayList<ModelGood> res = new ArrayList<>();
        good.ifPresent(res::add);
        model.addAttribute("good",res);
        if(!goodRepository.existsById(ID_Good))
        {
            return "redirect:/good";
        }
        return "good-details";
    }
    @GetMapping("/good/{ID_Good}/edit")
    public String goodEdit(@PathVariable("ID_Good") long ID_Good, Model ModelGood, @ModelAttribute("good") ModelGood modelGood, Model ModelGoodType, Model ModelMaterial, Model ModelCertificate)
    {
        /*if(!goodRepository.existsById(ID_Good)){
            return "redirect:/good";
        }
        Optional<modelGood> good = goodRepository.findById(ID_Good);
        ArrayList<modelGood> res = new ArrayList<>();
        good.ifPresent(res::add);*/
        Iterable<com.example.personalproject.models.ModelCertificate> certificates = CertificateRepository.findAll();
        ModelCertificate.addAttribute("certificate", certificates);
        Iterable<com.example.personalproject.models.ModelGoodType> goodTypes = GoodTypeRepository.findAll();
        ModelGoodType.addAttribute("goodType", goodTypes);
        Iterable<com.example.personalproject.models.ModelMaterial> materials = MaterialRepository.findAll();
        ModelMaterial.addAttribute("material", materials);
        ModelGood res = goodRepository.findById(ID_Good).orElseThrow();
        ModelGood.addAttribute("modelGood",res);
        return "good-edit";
    }

    @PostMapping("/good/{ID_Good}/edit")
    public String goodUpdate(@PathVariable("ID_Good") long ID_Good,
                             @ModelAttribute("good") @Valid ModelGood modelGood, BindingResult bindingResult, @RequestParam String certificateName, Model ModelCertificate, @RequestParam String goodTypeName, Model ModelGoodType, @RequestParam String materialName, Model ModelMaterial )
    {
        if (bindingResult.hasErrors()) {
            Iterable<ModelCertificate> certificates = CertificateRepository.findAll();
            ModelCertificate.addAttribute("certificate", certificates);
            Iterable<com.example.personalproject.models.ModelGoodType> goodTypes = GoodTypeRepository.findAll();
            ModelGoodType.addAttribute("goodType", goodTypes);
            Iterable<com.example.personalproject.models.ModelMaterial> materials = MaterialRepository.findAll();
            ModelMaterial.addAttribute("material", materials);
            return "good-edit";
        }
        modelGood.setCertificate(CertificateRepository.findByCertificateName(certificateName));
        modelGood.setGoodType(GoodTypeRepository.findByGoodTypeName(goodTypeName));
        modelGood.setMaterial(MaterialRepository.findByMaterialName(materialName));
        goodRepository.save(modelGood);
        return "good-main";
    }
    @PostMapping("good/{ID_Good}/remove")
    public String goodDelete(@PathVariable("ID_Good") long ID_Good, Model model){
        ModelGood good = goodRepository.findById(ID_Good).orElseThrow();
        goodRepository.delete(good);
        return "redirect:/good";
    }
}

package com.example.personalproject.controllers;


import com.example.personalproject.models.*;
import com.example.personalproject.models.ModelGoodType;
import com.example.personalproject.models.ModelMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class GoodController {
    @Autowired
    private com.example.personalproject.repos.goodRepository goodRepository;
    @Autowired
    private com.example.personalproject.repos.CertificateRepository CertificateRepository;
    @Autowired
    private com.example.personalproject.repos.GoodTypeRepository GoodTypeRepository;
    @Autowired
    private com.example.personalproject.repos.MaterialRepository MaterialRepository;

    @GetMapping("/good")
    public String goodMain(Model model)
    {
        Iterable<ModelGood> goods = goodRepository.findAll();
        model.addAttribute("goods", goods);
        return "good-main";
    }

    @GetMapping("/good/add")
    public String goodAddPage(@ModelAttribute("good") ModelGood modelGood, Model ModelCertificate, Model ModelGoodType, Model ModelMaterial)
    {
        Iterable<com.example.personalproject.models.ModelCertificate> certificates = CertificateRepository.findAll();
        ModelCertificate.addAttribute("certificate", certificates);
        Iterable<com.example.personalproject.models.ModelGoodType> goodTypes = GoodTypeRepository.findAll();
        ModelGoodType.addAttribute("goodType", goodTypes);
        Iterable<com.example.personalproject.models.ModelMaterial> materials = MaterialRepository.findAll();
        ModelMaterial.addAttribute("material", materials);

        return "good-add";
    }

    @PostMapping("/good/add")
    public String goodAdd(@ModelAttribute("good") @Valid ModelGood modelGood, BindingResult bindingResult, @RequestParam String certificateName, Model ModelCertificate, @RequestParam String goodTypeName, Model ModelGoodType, @RequestParam String materialName, Model ModelMaterial)
    {

        if (bindingResult.hasErrors()) {
            Iterable<ModelCertificate> certificates = CertificateRepository.findAll();
            ModelCertificate.addAttribute("certificate", certificates);
            Iterable<com.example.personalproject.models.ModelGoodType> goodTypes = GoodTypeRepository.findAll();
            ModelGoodType.addAttribute("goodType", goodTypes);
            Iterable<com.example.personalproject.models.ModelMaterial> materials = MaterialRepository.findAll();
            ModelMaterial.addAttribute("material", materials);
            return "good-add";
        }
        modelGood.setCertificate(CertificateRepository.findByCertificateName(certificateName));
        modelGood.setGoodType(GoodTypeRepository.findByGoodTypeName(goodTypeName));
        modelGood.setMaterial(MaterialRepository.findByMaterialName(materialName));
        goodRepository.save(modelGood);
        return "redirect:/good";
    }

    @GetMapping("/good/filter")
    public String goodFilter(Model model)
    {
        return "good-filter";
    }

    @PostMapping("/good/filter/result")
    public String goodResult(@RequestParam String goodName, Model model)
    {
        List<ModelGood> result = goodRepository.findByGoodNameContains(goodName);
//        List<Post> result = postRepository.findLikeTitle(title);
        model.addAttribute("result", result);
        return "good-filter";
    }

    @GetMapping("/good/{ID_Good}")
    public String goodDetails(@PathVariable(value = "ID_Good") long ID_Good, Model model)
    {
        Optional<ModelGood> good = goodRepository.findById(ID_Good);
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
    @GetMapping("/material")
    public String materialMain(Model model)
    {
        Iterable<ModelMaterial> materials = MaterialRepository.findAll();
        model.addAttribute("materials", materials);
        return "material-main";
    }

    @GetMapping("/material/add")
    public String materialAddPage(@ModelAttribute("material") ModelMaterial modelMaterial)
    {
        return "material-add";
    }

    @PostMapping("/material/add")
    public String materialAdd(@ModelAttribute("material") @Valid ModelMaterial modelMaterial, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()) {
            return "material-add";
        }
        MaterialRepository.save(modelMaterial);
        return "redirect:/material";
    }
    @GetMapping("/material/{ID_Material}")
    public String materialDetails(@PathVariable(value = "ID_Material") long ID_Material, Model model)
    {
        Optional<ModelMaterial> material = MaterialRepository.findById(ID_Material);
        ArrayList<ModelMaterial> res = new ArrayList<>();
        material.ifPresent(res::add);
        model.addAttribute("material",res);
        if(!MaterialRepository.existsById(ID_Material))
        {
            return "redirect:/material";
        }
        return "material-details";
    }
    @PostMapping("material/{ID_Material}/remove")
    public String materialDelete(@PathVariable("ID_Material") long ID_Material, Model model){
        ModelMaterial material = MaterialRepository.findById(ID_Material).orElseThrow();
        MaterialRepository.delete(material);
        return "redirect:/material";
    }

    @GetMapping("/certificate")
    public String certificateMain(Model model)
    {
        Iterable<ModelCertificate> certificates = CertificateRepository.findAll();
        model.addAttribute("certificates", certificates);
        return "certificate-main";
    }

    @GetMapping("/certificate/add")
    public String certificateAddPage(@ModelAttribute("certificate") ModelCertificate modelCertificate)
    {
        return "certificate-add";
    }

    @PostMapping("/certificate/add")
    public String certificateAdd(@ModelAttribute("certificate") @Valid ModelCertificate modelCertificate, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()) {
            return "certificate-add";
        }
        CertificateRepository.save(modelCertificate);
        return "redirect:/certificate";
    }
    @GetMapping("/certificate/{ID_Certificate}")
    public String certificateDetails(@PathVariable(value = "ID_Certificate") long ID_Certificate, Model model)
    {
        Optional<ModelCertificate> certificate = CertificateRepository.findById(ID_Certificate);
        ArrayList<ModelCertificate> res = new ArrayList<>();
        certificate.ifPresent(res::add);
        model.addAttribute("certificate",res);
        if(!CertificateRepository.existsById(ID_Certificate))
        {
            return "redirect:/certificate";
        }
        return "certificate-details";
    }
    @PostMapping("certificate/{ID_Certificate}/remove")
    public String certificateDelete(@PathVariable("ID_Certificate") long ID_Certificate, Model model){
        ModelCertificate certificate = CertificateRepository.findById(ID_Certificate).orElseThrow();
        CertificateRepository.delete(certificate);
        return "redirect:/material";
    }
    @GetMapping("/goodtype")
    public String goodtypeMain(Model model)
    {
        Iterable<ModelGoodType> goodTypes = GoodTypeRepository.findAll();
        model.addAttribute("goodTypes", goodTypes);
        return "goodtype-main";
    }

    @GetMapping("/goodtype/add")
    public String goodtypeAddPage(@ModelAttribute("goodtype") ModelGoodType modelGoodType)
    {
        return "goodtype-add";
    }

    @PostMapping("/goodtype/add")
    public String goodtypeAdd(@ModelAttribute("goodtype") @Valid ModelGoodType modelGoodType, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()) {
            return "goodtype-add";
        }
        GoodTypeRepository.save(modelGoodType);
        return "redirect:/goodtype";
    }
    @GetMapping("/goodtype/{ID_GoodType}")
    public String goodtypeDetails(@PathVariable(value = "ID_GoodType") long ID_GoodType, Model model)
    {
        Optional<ModelGoodType> goodType = GoodTypeRepository.findById(ID_GoodType);
        ArrayList<ModelGoodType> res = new ArrayList<>();
        goodType.ifPresent(res::add);
        model.addAttribute("goodType",res);
        if(!GoodTypeRepository.existsById(ID_GoodType))
        {
            return "redirect:/goodtype";
        }
        return "goodtype-details";
    }
    @PostMapping("goodtype/{ID_GoodType}/remove")
    public String goodtypeDelete(@PathVariable("ID_GoodType") long ID_GoodType, Model model){
        ModelGoodType goodType = GoodTypeRepository.findById(ID_GoodType).orElseThrow();
        GoodTypeRepository.delete(goodType);
        return "redirect:/goodtype";
    }

}

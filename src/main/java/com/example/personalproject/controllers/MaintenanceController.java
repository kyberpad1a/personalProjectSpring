package com.example.personalproject.controllers;

import com.example.personalproject.models.ModelCertificate;
import com.example.personalproject.models.ModelGood;
import com.example.personalproject.models.ModelMaintenance;
import com.example.personalproject.models.ModelWarehouse;
import com.example.personalproject.repos.MaintenanceRepository;
import com.example.personalproject.repos.UserRepository;
import com.example.personalproject.repos.WarehouseRepository;
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
public class MaintenanceController {
    @Autowired
    MaintenanceRepository MaintenanceRepository;
    @Autowired
    WarehouseRepository WarehouseRepository;
    @Autowired
    UserRepository UserRepository;
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER', 'MAINTENANCEWORKER')")
    @GetMapping("/maintenance")
    public String maintenanceMain(Model model)
    {
        Iterable<ModelMaintenance> maintenances = MaintenanceRepository.findAll();
        model.addAttribute("maintenances", maintenances);
        return "maintenance-main";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/maintenance/add")
    public String maintenanceAddPage(@ModelAttribute("maintenance") ModelMaintenance modelMaintenance, Model ModelUser, Model ModelWarehouse)
    {
        Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
        ModelUser.addAttribute("users", users);
        Iterable<com.example.personalproject.models.ModelWarehouse> warehouses = WarehouseRepository.findAll();
        ModelWarehouse.addAttribute("warehouse", warehouses);

        return "maintenance-add";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @PostMapping("/maintenance/add")
    public String maintenanceAdd(@ModelAttribute("maintenance") @Valid ModelMaintenance modelMaintenance, BindingResult bindingResult, @RequestParam String warehouseAddress, Model ModelWarehouse, @RequestParam String username, Model ModelUser)
    {

        if (bindingResult.hasErrors()) {
            Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
            ModelUser.addAttribute("users", users);
            Iterable<com.example.personalproject.models.ModelWarehouse> warehouses = WarehouseRepository.findAll();
            ModelWarehouse.addAttribute("warehouse", warehouses);
            return "maintenance-add";
        }
        modelMaintenance.setWarehouse(WarehouseRepository.findByWarehouseAddress(warehouseAddress));
        modelMaintenance.setUser(UserRepository.findByUsername(username));
        MaintenanceRepository.save(modelMaintenance);
        return "redirect:/maintenance";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER', 'MAINTENANCEWORKER')")
    @GetMapping("/maintenance/{ID_Maintenance}")
    public String maintenanceDetails(@PathVariable(value = "ID_Maintenance") long ID_Maintenance, Model model)
    {
        Optional<ModelMaintenance> maintenance = MaintenanceRepository.findById(ID_Maintenance);
        ArrayList<ModelMaintenance> res = new ArrayList<>();
        maintenance.ifPresent(res::add);
        model.addAttribute("maintenance",res);
        if(!MaintenanceRepository.existsById(ID_Maintenance))
        {
            return "redirect:/maintenance";
        }
        return "maintenance-details";
    }
    @PreAuthorize("hasAnyAuthority('MAINTENANCEWORKER')")
    @GetMapping("/maintenance/{ID_Maintenance}/edit")
    public String maintenanceEdit(@PathVariable("ID_Maintenance") long ID_Maintenance, Model ModelMaintenance, @ModelAttribute("maintenance") ModelMaintenance modelMaintenance, Model ModelWarehouse, Model ModelUser)
    {
        /*if(!goodRepository.existsById(ID_Good)){
            return "redirect:/good";
        }
        Optional<modelGood> good = goodRepository.findById(ID_Good);
        ArrayList<modelGood> res = new ArrayList<>();
        good.ifPresent(res::add);*/
        Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
        ModelUser.addAttribute("users", users);
        Iterable<com.example.personalproject.models.ModelWarehouse> warehouses = WarehouseRepository.findAll();
        ModelWarehouse.addAttribute("warehouse", warehouses);
        ModelMaintenance res = MaintenanceRepository.findById(ID_Maintenance).orElseThrow();
        ModelMaintenance.addAttribute("modelMaintenance",res);
        return "maintenance-edit";
    }
    @PreAuthorize("hasAnyAuthority('MAINTENANCEWORKER')")
    @PostMapping("/maintenance/{ID_Maintenance}/edit")
    public String maintenanceUpdate(@PathVariable("ID_Maintenance") long ID_Maintenance,
                             @ModelAttribute("maintenance") @Valid ModelMaintenance modelMaintenance, BindingResult bindingResult, @RequestParam String warehouseAddress, Model ModelWarehouse, @RequestParam String username, Model ModelUser)
    {
        if (bindingResult.hasErrors()) {
            Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
            ModelUser.addAttribute("users", users);
            Iterable<com.example.personalproject.models.ModelWarehouse> warehouses = WarehouseRepository.findAll();
            ModelWarehouse.addAttribute("warehouse", warehouses);
            return "maintenance-edit";
        }
        modelMaintenance.setWarehouse(WarehouseRepository.findByWarehouseAddress(warehouseAddress));
        modelMaintenance.setUser(UserRepository.findByUsername(username));
        MaintenanceRepository.save(modelMaintenance);
        return "maintenance-main";
    }
    @PreAuthorize("hasAnyAuthority('MAINTENANCEWORKER')")
    @PostMapping("maintenance/{ID_Maintenance}/remove")
    public String maintenanceDelete(@PathVariable("ID_Maintenance") long ID_Maintenance, Model model){
        ModelMaintenance maintenance = MaintenanceRepository.findById(ID_Maintenance).orElseThrow();
        MaintenanceRepository.delete(maintenance);
        return "redirect:/maintenance";
    }
}

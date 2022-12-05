package com.example.personalproject.controllers;

import com.example.personalproject.models.ModelMaterial;
import com.example.personalproject.models.ModelQuality;
import com.example.personalproject.models.ModelShipment;
import com.example.personalproject.models.ModelWarehouse;
import com.example.personalproject.repos.*;
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
public class WarehouseController {
    @Autowired
    WarehouseRepository WarehouseRepository;
    @Autowired
    UserRepository UserRepository;
    @Autowired
    ShipmentRepository ShipmentRepository;
    @Autowired
    goodRepository goodRepository;
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/warehouse")
    public String warehouseMain(Model model)
    {
        Iterable<ModelWarehouse> warehouses = WarehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);
        return "warehouse-main";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/warehouse/add")
    public String warehouseAddPage(@ModelAttribute("warehouse") ModelWarehouse modelWarehouse)
    {
        return "warehouse-add";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @PostMapping("/warehouse/add")
    public String warehouseAdd(@ModelAttribute("warehouse") @Valid ModelWarehouse modelWarehouse, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()) {
            return "warehouse-add";
        }
        WarehouseRepository.save(modelWarehouse);
        return "redirect:/warehouse";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/warehouse/{ID_Warehouse}")
    public String warehouseDetails(@PathVariable(value = "ID_Warehouse") long ID_Warehouse, Model model)
    {
        Optional<ModelWarehouse> warehouse = WarehouseRepository.findById(ID_Warehouse);
        ArrayList<ModelWarehouse> res = new ArrayList<>();
        warehouse.ifPresent(res::add);
        model.addAttribute("warehouse",res);
        if(!WarehouseRepository.existsById(ID_Warehouse))
        {
            return "redirect:/warehouse";
        }
        return "warehouse-details";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @PostMapping("warehouse/{ID_Warehouse}/remove")
    public String warehouseDelete(@PathVariable("ID_Warehouse") long ID_Warehouse, Model model){
        ModelWarehouse warehouse = WarehouseRepository.findById(ID_Warehouse).orElseThrow();
        WarehouseRepository.delete(warehouse);
        return "redirect:/warehouse";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/shipment")
    public String shipmentMain(Model model)
    {
        Iterable<ModelShipment> shipments = ShipmentRepository.findAll();
        model.addAttribute("shipments", shipments);
        return "shipment-main";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/shipment/add")
    public String shipmentAddPage(@ModelAttribute("shipment") ModelShipment modelShipment, Model ModelGood, Model ModelUser, Model ModelWarehouse)
    {
        Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
        ModelGood.addAttribute("good", goods);
        Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
        ModelUser.addAttribute("user", users);
        Iterable<com.example.personalproject.models.ModelWarehouse> warehouses = WarehouseRepository.findAll();
        ModelUser.addAttribute("warehouse", warehouses);
        return "shipment-add";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @PostMapping("/shipment/add")
    public String shipmentAdd(@ModelAttribute("shipment") @Valid ModelShipment modelShipment, BindingResult bindingResult, @RequestParam String username, Model ModelUser, @RequestParam String goodName, Model ModelGood, @RequestParam String warehouseAddress, Model ModelWarehouse)
    {

        if (bindingResult.hasErrors()) {
            Iterable<com.example.personalproject.models.ModelUser> users = UserRepository.findAll();
            ModelUser.addAttribute("user", users);
            Iterable<com.example.personalproject.models.ModelGood> goods = goodRepository.findAll();
            ModelGood.addAttribute("good", goods);
            Iterable<com.example.personalproject.models.ModelWarehouse> warehouses = WarehouseRepository.findAll();
            ModelUser.addAttribute("warehouse", warehouses);
            return "shipment-add";
        }
        modelShipment.setUser(UserRepository.findByUsername(username));
        modelShipment.setGood(goodRepository.findByGoodName(goodName));
        modelShipment.setWarehouse(WarehouseRepository.findByWarehouseAddress(warehouseAddress));

        ShipmentRepository.save(modelShipment);
        return "redirect:/shipment";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @GetMapping("/shipment/{ID_Shipment}")
    public String shipmentDetails(@PathVariable(value = "ID_Shipment") long ID_Shipment, Model model)
    {
        Optional<ModelShipment> shipment = ShipmentRepository.findById(ID_Shipment);
        ArrayList<ModelShipment> res = new ArrayList<>();
        shipment.ifPresent(res::add);
        model.addAttribute("shipment",res);
        if(!ShipmentRepository.existsById(ID_Shipment))
        {
            return "redirect:/shipment";
        }
        return "shipment-details";
    }
    @PreAuthorize("hasAnyAuthority('WAREHOUSEWORKER')")
    @PostMapping("shipment/{ID_Shipment}/remove")
    public String shipmentDelete(@PathVariable("ID_Shipment") long ID_Shipment, Model model){
        ModelShipment shipment = ShipmentRepository.findById(ID_Shipment).orElseThrow();
        ShipmentRepository.delete(shipment);
        return "redirect:/shipment";
    }
}

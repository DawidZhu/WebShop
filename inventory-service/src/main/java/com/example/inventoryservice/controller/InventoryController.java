package com.example.inventoryservice.controller;


import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    // 称为： endpoint
    // @GetMapping("/{name}")
    // GET http://192.168.0.170:60613/api/inventory?name=ipad
    // http://localhost:8084/api/inventory?name=ipad
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
//    public List<InventoryResponse> isInStock(@PathVariable("name") String name){
    public List<InventoryResponse> isInStock(@RequestParam List<String>  name){

        log.info("RequestParam name: "+ name);
        System.out.println("RequestParam name: "+ name);

       return inventoryService.isInStock(name.toString());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public  Inventory saveInventory(@RequestBody Inventory inventory){

        return inventoryService.saveInventory(inventory);
    }
}

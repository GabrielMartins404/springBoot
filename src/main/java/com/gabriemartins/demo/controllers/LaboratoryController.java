package com.gabriemartins.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriemartins.demo.models.LaboratoryModel;
import com.gabriemartins.demo.services.LaboratoryServices;

@RestController
@RequestMapping("/laboratory")
@Validated
public class LaboratoryController {
    @Autowired
    private LaboratoryServices laboratoryServices;

    @GetMapping("/")
    public ResponseEntity<List<LaboratoryModel>> findAllLaboratories(){
        List<LaboratoryModel> laboratories = this.laboratoryServices.findAllLaboratory();
        return ResponseEntity.ok().body(laboratories);
    }

    @GetMapping("/{idLaboratory}")
    public ResponseEntity<LaboratoryModel> findLaboratoryById(@PathVariable Long idLab){
        LaboratoryModel laboratory = this.laboratoryServices.findLaboratoryById(idLab);
        return ResponseEntity.ok().body(laboratory);
    }

    //No futuro, se for implementar parte de administrador, serão feito os restantes dos CRUD
}

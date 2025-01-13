package com.gabriemartins.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriemartins.demo.models.LaboratoryModel;
import com.gabriemartins.demo.models.TimeModel;
import com.gabriemartins.demo.repositories.LaboratoryRepository;

@Service
public class LaboratoryServices {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    //Preciso instanciar TimeService. Poderia usar @Autowired, mas vou fazer normalmente
    private TimeServices timeServices = new TimeServices();

    //Método para as informações de todos os laboratórios
    public List<LaboratoryModel> findAllLaboratory(){
        List<LaboratoryModel> laboratory = this.laboratoryRepository.findAll();

        return laboratory;
    }

    //Método para buscar as informações de laboratórios especificos
    public LaboratoryModel findLaboratoryById(Long id){
        Optional<LaboratoryModel> laboratory = this.laboratoryRepository.findById(id);
        return laboratory.orElseThrow(() -> new RuntimeException("Laboratório não encontrado!!"));
    }

    //Método para retornar uma lista de horários de um laboratório em especifico
    public List<TimeModel> findTimesLab(LaboratoryModel laboratory){
        return timeServices.findTimesByLab(laboratory.getLabId());
    }
}

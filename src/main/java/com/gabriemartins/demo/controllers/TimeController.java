package com.gabriemartins.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabriemartins.demo.models.ReserveModel;
import com.gabriemartins.demo.models.TimeModel;
import com.gabriemartins.demo.services.ReserveServices;
import com.gabriemartins.demo.services.TimeServices;

@RestController
@RequestMapping("/time")
@Validated
public class TimeController {
    @Autowired
    private TimeServices timeServices;

    @Autowired
    private ReserveServices reserveServices;

    @GetMapping("/{idLab}")
    public ResponseEntity<List<TimeModel>> findTimesByLab(@PathVariable Long idLab){
        List<TimeModel> times = this.timeServices.findTimesByLab(idLab);
        return ResponseEntity.ok().body(times);
    }

    //Como vou fazer a atualização de vários horários. Preciso receber uma lista de todos os horários
    @PutMapping("/{idReserve}")
    //Basicamente, essa função recebe uma lista com todos os horários que precisam ser atualizados e id da reserva usada para atualizar. 
    public ResponseEntity<Void> updateStatusTimes(@RequestBody List<TimeModel> times, @PathVariable Long idReserve){
        ReserveModel reserve = this.reserveServices.findReserveById(idReserve);
        for (TimeModel time : times) {
            this.timeServices.updateReserveTime(time, reserve);
        }
        return ResponseEntity.noContent().build();
    }
}

package com.gabriemartins.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriemartins.demo.models.ReserveModel;
import com.gabriemartins.demo.models.TimeModel;
import com.gabriemartins.demo.models.UserModel;
import com.gabriemartins.demo.services.ReserveServices;
import com.gabriemartins.demo.services.UserServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/reserve")
@Validated
public class ReserveController {
    //A reserva poderá ser listada, criada e deletada
    @Autowired
    private ReserveServices reserveServices;

    @Autowired
    private UserServices userServices;

    //Listar a reserva por id
    @GetMapping("/{idReserve}")
    public ResponseEntity<ReserveModel> findReserveById(@PathVariable Long idReserve){
        ReserveModel reseve = this.reserveServices.findReserveById(idReserve);
        return ResponseEntity.ok().body(reseve);
    }
    
    //Inserir nova reserva
    @PostMapping("/{userId}")
    public ResponseEntity<Void> createReserve(@RequestBody List<TimeModel> times, @PathVariable long userId){
        // Valida a lista de horários
        if (times == null || times.isEmpty()) {
            throw new RuntimeException("A lista de horários não pode estar vazia!");
        }
        
        UserModel user = this.userServices.findUserById(userId);
        ReserveModel reserve = this.reserveServices.createReserveTime(times, user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idReserve}").buildAndExpand(reserve.getReserveId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Deletar reserva existente por ID
    @DeleteMapping("/{idReserve}")
    public ResponseEntity<Void> deleteReserve(@PathVariable Long idReserve){
        this.reserveServices.deleteReserve(idReserve);
        return ResponseEntity.noContent().build();
    }

}

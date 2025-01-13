package com.gabriemartins.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriemartins.demo.models.ReserveModel;
import com.gabriemartins.demo.models.TimeModel;
import com.gabriemartins.demo.models.UserModel;
import com.gabriemartins.demo.repositories.ReserveRepository;
import com.gabriemartins.demo.repositories.TimeRepository;

import jakarta.transaction.Transactional;

@Service
public class ReserveServices {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private TimeServices timeServices;

    public ReserveModel findReserveById(Long id){
        Optional<ReserveModel> reserveModel = this.reserveRepository.findById(id);
        return reserveModel.orElseThrow(() -> new RuntimeException("Erro ao listar reserva pelo id"));
    }

    //A função cria um registro no banco de dados de reserva. Após isso, a mesma deverá atualizar em cada horário, o id da reserva
    @Transactional
    public ReserveModel createReserveTime(List<TimeModel> timesReserve, UserModel user){

        ReserveModel reserveModel = new ReserveModel(null, user);
        this.reserveRepository.save(reserveModel);

        //Esse for terá como propósito, percorrer cada tempo repassado na lista e verificar disponibilidade. Caso tenha, insere a reserva, senão, não insere
        for (TimeModel time : timesReserve) {
            time = this.timeServices.findTimeById(time.getTimeId());
            if (time.getTimeStatus() == true) {
                time = timeServices.updateReserveTime(time, reserveModel);
            }else{
                throw new RuntimeException("O horário " + time.getTimeId() + " não está disponível");
            }
        }
        return reserveModel;
    }

    //Função para deletar ou cancelar reserva
    public void deleteReserve(Long idReserve){
        
        //Antes de deletar a reserva, preciso garantir que todos os meus horários sejam atualizados para disponível e que o vinculo com reserva seja quebrado
        ReserveModel reserve = findReserveById(idReserve);
        List<TimeModel> times = this.timeServices.findTimesByReserve(idReserve);
        for (TimeModel time : times) {
            //Para cada horário em que a reserva tenha vinculo, ocorrerá uma função que atualiza o id da mesma
            this.timeServices.cancelReserveTime(time);
        }

        try {
            //Aqui digo que a reserva será deletada
            this.reserveRepository.delete(reserve);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar a reserva!!");
        }
    }
}

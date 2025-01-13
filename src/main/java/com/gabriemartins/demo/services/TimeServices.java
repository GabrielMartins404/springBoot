package com.gabriemartins.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriemartins.demo.models.ReserveModel;
import com.gabriemartins.demo.models.TimeModel;
import com.gabriemartins.demo.repositories.TimeRepository;

import jakarta.transaction.Transactional;


@Service
public class TimeServices {
    @Autowired
    private TimeRepository timeRepository;

    public List<TimeModel> findTimesByLab(Long labId){
        List<TimeModel> timesLab = this.timeRepository.findTimeByLab(labId);
        return timesLab;
    }

    public List<TimeModel> findTimesByReserve(Long reserveId){
        List<TimeModel> timesReserve = this.timeRepository.findTimeByReserve(reserveId);
        return timesReserve;
    }

    public TimeModel findTimeById(Long timeId){
        Optional<TimeModel> time = this.timeRepository.findById(timeId);
        return time.orElseThrow(() -> new RuntimeException("Erro ao buscar horario!"));
    }

    //Função com o propósito para atualizar o status de disponibilidade de um horário no banco de dados
    @Transactional
    public boolean consultStatusTime(TimeModel time){
        if (time.getTimeStatus() == true) {
            return true;
        }else{
            return false;
        }
    }

    //Função com o propósito de adicionar a reserva nos horários, recebendo o horário e atualizando o mesmo
    @Transactional
    public TimeModel updateReserveTime(TimeModel time, ReserveModel reserve){
        //Esse if serve para verificar se a reserva está disponível e atualizar os seus status
        if(consultStatusTime(time)){
            time.setTimeStatus(false);
            time.setTimeReserve(reserve);
        }else{
            throw new RuntimeException("O horário já está reservado.");
        }

        this.timeRepository.save(time);

        return time;
    }

    public void cancelReserveTime(TimeModel time){
        //Basicamente, se o horário tiver disponível, não há necessidade de tirar reserva
        TimeModel newTime = time;
        if(consultStatusTime(time) == true){
            throw new RuntimeException("O horário não está vinculado a nenhuma reserva");
        }else{
            newTime.setTimeStatus(true);
            newTime.setTimeReserve(null);
        }

        this.timeRepository.save(time);
    }
}

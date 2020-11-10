package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.List;

import com.sprinboot.servicios.app.oriplan.sis.dto.TrabajadorAfpDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;


public interface PlanillaExcelDao extends JpaRepository<PlanillaExcel, Integer> {

    @Query("Select u From PlanillaExcel u where u.enabled=1")
    public List<PlanillaExcel> findAllEnabeld();

    @Query("Select u From PlanillaExcel u where u.dni=?1 and u.periodoSemanal=?2 and u.obra=?3 and u.enabled=1")
    public PlanillaExcel buscar(String dni, String semana, String obra);

    @Query("Select u From PlanillaExcel u where u.dni=?1 and u.periodoSemanal=?2 and u.obra=?3 and u.enabled=1")
    public List<PlanillaExcel> buscarparaBoletaPorDni(String dni, String semana, String obra);


    @Query("Select u From PlanillaExcel u where  trim(u.periodoSemanal)=?1 and trim(u.obra)=?2 and u.enabled=1")
    public List<PlanillaExcel> buscarparaBoleta(String semana, String obra);

    @Query("Select u From PlanillaExcel u where  trim(u.periodoSemanal)=?1 and trim(u.obra)=?2 and u.declaracion=?3 and  u.enabled=1")
    public List<PlanillaExcel> buscarparaBoleta(String semana, String obra, String declaracion);

    @Query("Select u From PlanillaExcel u where  trim(u.mes)=?1 and trim(u.anio)=?2 and trim(u.obra)=?3 and u.declaracion=?4 and  u.enabled=1")
    public List<PlanillaExcel> buscarSemanal(String mes, String anio, String obra, String declaracion);

    @Query("Select u From PlanillaExcel u where  trim(u.mes)=?1 and trim(u.anio)=?2 and trim(u.obra)=?3  and  u.enabled=1")
    public List<PlanillaExcel> buscarpormes(String mes, String anio, String obra);

    @Query("SELECT u FROM PlanillaExcel u WHERE u.enabled = 1 AND u.declaracion = 'Si' AND u.sistemaPensiones LIKE '%AFP%' AND u.anio = ?1 AND u.mes = ?2")
    public List<PlanillaExcel> listarPlanillaAfp(String anio, String mes);

    @Query("SELECT DISTINCT(u.dni), SUM(u.totalAfecto), u.cussp, u.nombresCompletos FROM PlanillaExcel u WHERE u.enabled = 1 AND u.declaracion = 'Si' AND u.sistemaPensiones LIKE '%AFP%' AND u.anio = ?1 AND u.mes = ?2 AND u.dni = ?3")
    public List<Object[]> planillaAfp(String anio, String mes, String dni);

}
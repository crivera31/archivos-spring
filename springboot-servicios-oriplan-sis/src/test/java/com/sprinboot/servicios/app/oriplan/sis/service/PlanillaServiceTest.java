package com.sprinboot.servicios.app.oriplan.sis.service;

import com.sprinboot.servicios.app.oriplan.sis.dao.PlanillaExcelDao;
import com.sprinboot.servicios.app.oriplan.sis.exceptions.ClaseException;
import com.sprinboot.servicios.app.oriplan.sis.jsons.PlanillaRest;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class PlanillaServiceTest {

    private static String ANIO = "2020";
    private static String MES = "OCTUBRE";
    private static String AFP = "AFP";
    private static List<PlanillaExcel> LIST_PLANILLAEXCEL;
    private static PlanillaExcel PLANILLA_EXCEL;

    private static Integer ID = new Integer("1");
    private static String NOMBRES_COMPLETOS = "RUIZ RUIZ ALEXANDRO";
    private static String SISTEMAS_PENSIONES = "AFP Habitat";
    private static Integer ENABLED = new Integer("1");
    private static BigDecimal TOTAL_AFECTO = new BigDecimal("100");

    private static Integer ID2 = new Integer("2");
    private static String NOMBRES_COMPLETOS2 = "VICTOR LLUEN";
    private static BigDecimal TOTAL_AFECTO2 = new BigDecimal("200");


    // inyectando un mock dao
    @Mock
    PlanillaExcelDao planillaExcelDao;


    // inyectando el funcionamiento del servicio
    @InjectMocks
    PlanillaServiceImpl planillaServiceImpl;


    // metodo before para que se ejecute antes de ejecutar los test
    @Before
    public void init() throws ClaseException {
        System.out.println("************************************");
        System.out.println("Esta inicia la función antes de las pruebas");


        MockitoAnnotations.initMocks(this);
        //INSTANCIANDO LA LISTA
        LIST_PLANILLAEXCEL = new ArrayList<>();

        //INSTANCIANDO LA ENTIDAD
        PLANILLA_EXCEL = new PlanillaExcel();

        //SETEANDO LOS VALORES
        PLANILLA_EXCEL.setId(ID);
        PLANILLA_EXCEL.setNombresCompletos(NOMBRES_COMPLETOS);
        PLANILLA_EXCEL.setSistemaPensiones(SISTEMAS_PENSIONES);
        PLANILLA_EXCEL.setEnabled(ENABLED);
        PLANILLA_EXCEL.setAnio(ANIO);
        PLANILLA_EXCEL.setMes(MES);
        PLANILLA_EXCEL.setTotalAfecto(TOTAL_AFECTO);

        //AGREGAGNDO LA ENTIDAD A LA LISTA
        LIST_PLANILLAEXCEL.add(PLANILLA_EXCEL);

        //INSTANCIANDO NUEVAMENTE PARA UNA NUEVA SETEADA
        PLANILLA_EXCEL = new PlanillaExcel();

        //SETENADO LOS VALORES
        PLANILLA_EXCEL.setId(ID2);
        PLANILLA_EXCEL.setNombresCompletos(NOMBRES_COMPLETOS2);
        PLANILLA_EXCEL.setSistemaPensiones(SISTEMAS_PENSIONES);
        PLANILLA_EXCEL.setEnabled(ENABLED);
        PLANILLA_EXCEL.setAnio(ANIO);
        PLANILLA_EXCEL.setMes(MES);
        PLANILLA_EXCEL.setTotalAfecto(TOTAL_AFECTO2);

        //AGREGAGNDO LA ENTIDAD A LA LISTA
        LIST_PLANILLAEXCEL.add(PLANILLA_EXCEL);

        //mockeando el DAO para que nos traiga la lista que se ha seteado
        Mockito.when(planillaExcelDao.listarPlanillaAfp(ANIO, MES)).thenReturn(LIST_PLANILLAEXCEL);

        //mostrar en consola
        System.out.println("Cantidad de filas en la lista: " + LIST_PLANILLAEXCEL.size());
        System.out.println("Datos: ");
        LIST_PLANILLAEXCEL.stream().forEach(result -> {
            System.out.println("id: " + result.getId());
            System.out.println("Año: " + result.getAnio() + " Mes: " + result.getMes());
            System.out.println("Nombres completos: " + result.getNombresCompletos());
            System.out.println("Sistema de Pensiones: " + result.getSistemaPensiones());
        });

        System.out.println("Esta inicia la función antes de las pruebas");
        System.out.println("************************************");
    }

    //testeando el servicio traer todas las planilla de afp
    @Test
    public void listarPlanillaAfpTest() throws ClaseException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.print("Inicio de función de Test: ");
        System.out.println("Listar trabajadores del sistema de pensiones AFP");

        final List<PlanillaRest> lstPlanillaRest = planillaServiceImpl.listarPlanillaAfp(ANIO, MES);
        // esperamos que la respuesta no sea NULl
        assertNotNull(lstPlanillaRest);

        System.out.println("cantidad de personas en AFP: " + lstPlanillaRest.size());
        System.out.println("---------------------------------");

        lstPlanillaRest.stream().forEach( planillaRest -> {

            System.out.println("id: " + planillaRest.getId());
            System.out.println("CUSSP: " + planillaRest.getCussp());
            System.out.println("Tipo Doc: " + planillaRest.getTipoDocumento());
            System.out.println("DNI: " + planillaRest.getDni());
            System.out.println("Apellido Paterno: " + planillaRest.getApellidoPaterno());
            System.out.println("Apellido Materno: " + planillaRest.getApellidoMaterno());
            System.out.println("Nombres: " + planillaRest.getNombres());
            System.out.println("Relación Laboral: " + planillaRest.getRelacionLaboral());
            System.out.println("Inicio de relación Laboral: " + planillaRest.getInicioRelacionLaboral());
            System.out.println("Cese de Relación laboral: " + planillaRest.getCeseRelacionLaboral());
            System.out.println("Excepcion de Aportar: " + planillaRest.getExcepcionAportar());
            System.out.println("Remuneración asegurable: " + planillaRest.getRemuneracionAsegurable());
            System.out.println("Aporte voluntario del afiliado con fin previsional: " + planillaRest.getAporteVoluntarioAfiConFin());
            System.out.println("Aporte voluntario del afiliado sin fin previsional: " + planillaRest.getAporteVoluntarioAfiSinFin());
            System.out.println("Aporte voluntario del empleador: " + planillaRest.getAporteVoluntarioEmpleador());
            System.out.println("Rubro/Tipo de trabajador: " + planillaRest.getTipoTrabajoRubro());
            System.out.println("AFP Conviene dejar en blanco.: " + planillaRest.getAfp());

            //boolean afp = planillaRest.getSistemaPensiones().contains(AFP);
            //esperamos que su sistemas de pensiones sea AFP
            //assertTrue(afp);
        });
        System.out.println("---------------------------------");
        //assertEquals(esperado, resultado);
        System.out.print("Fin de función de Test: ");
        System.out.println("Listar trabajadores del sistema de pensiones AFP");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    @After
    public void fin() {
        System.out.println("************************************");
        System.out.println("Esto se ejecuta despues de las pruebas");
    }


}



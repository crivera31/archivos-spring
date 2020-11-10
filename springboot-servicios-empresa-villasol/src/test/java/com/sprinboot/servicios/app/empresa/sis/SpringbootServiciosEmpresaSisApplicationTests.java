package com.sprinboot.servicios.app.empresa.sis;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sprinboot.servicios.app.empresa.villasol.controller.AsientoController;
import com.sprinboot.servicios.app.empresa.villasol.dao.AsientoDao;
import com.sprinboot.servicios.app.empresa.villasol.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.villasol.dao.VoucherDao;
import com.sprinboot.servicios.app.empresa.villasol.funciones.Funciones;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootServiciosEmpresaSisApplicationTests {

	
    
    
    /*@Test
	   public void sizeListAsiento() {
		   List<Asiento> lstAsientoEntity  = new ArrayList<>();
		   lstAsientoEntity = asientoDao.findUltimoAsiento("01", 1);
		   System.out.println("EL SIZE ES = "+lstAsientoEntity.size());
		   assertTrue(lstAsientoEntity.size()>0);
	   } */
	

	/*@Test
	   public void probarSumaTotal() {
		                
		   Float total = voucherDao.totalLibroDebeDiarioMensual("S", 1);
		   System.out.println("EL total  ES = "+ total);
		   assertTrue(total!=null);
	   }  
	*/

	/*@Test
	   public void obtenerRegistroLibro() {
		                
		   //Float total = registroLibrosDao.listarRegistroLibrosPorFiltro(1, "2020-01-24", "2020-01-24", "01", "F001-12345", "76807262");
		   //System.out.println("EL total  ES = "+ total);
		   //assertTrue(total!=null);
	   }  */
	
	
	/*@Test
	public void contextLoads() {
		//comprueba que la instancia del controlador que se crea e inyecta con exito
		assertThat(asientoController).isNotNull();
	}*/
	
	
/*
	@Test
	   public void verificarFuncionesVenta() {
		Integer campo34= 1;
		Integer oblig;
		try {
			oblig = funciones.getValidarSiEsObligatorioCampo27Ventas("07", campo34);
			System.out.println("ES = "+oblig);
		    assertEquals(1, oblig, 0.01);;
		  } catch (Exception e) {
		    fail("Lanzada excepcion no esperada Exception");
		  }
		
	}
	
	*/

	/*@Test
	   public void verificarFuncionesVenta2() {
		Integer valido;
		try {
			valido = funciones.getValidarFechaConElPeriodo("23/03/2020","2020",3,"01");
		    assertEquals(1, valido, 0.01);;
		  } catch (Exception e) {
		    fail("Lanzada excepcion no esperada Exception");
		  }
		
	}*/
	
	/*@Test
	   public void verificarFuncionesVenta34() {
		Integer valido;
		try {
			valido = funciones.getValorParaCampo34Ventas("23/02/2019",3,"2020",new BigDecimal("200"));
		    assertEquals(8, valido, 0.01);;
		  } catch (Exception e) {
		    fail("Lanzada excepcion no esperada Exception");
		  }
		
	}*/
	

}

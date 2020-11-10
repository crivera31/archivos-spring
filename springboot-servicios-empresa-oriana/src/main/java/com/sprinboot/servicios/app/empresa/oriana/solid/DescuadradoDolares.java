package com.sprinboot.servicios.app.empresa.oriana.solid;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.SecondaryTable;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.oriana.dto.Comentary;
import com.sprinboot.servicios.app.empresa.oriana.dto.ConsistenciaDto;
import com.sprinboot.servicios.app.empresa.oriana.dto.VouDescuadradoDolares;
import com.sprinboot.servicios.empresa.commons.entity.Asiento;

@Primary 
@Service("DescuadradoDolares")
public class DescuadradoDolares implements FilaConsistencia{
	
	@Override
	public List<ConsistenciaDto> proceso(List<ConsistenciaDto> lst, Asiento asiento) {
		       System.out.println("LLEGO A SOLID  ASIENTO = " +asiento.getNumAsiento() + " -  MES" + asiento.getCodMes() +  "  ORI = " + asiento.getCodOrigen() );
		       Comentary c =  new VouDescuadradoDolares();
		      if (asiento.getConsistenciaDolares().compareTo(new BigDecimal("0.00"))!=0 && asiento.getConsistenciaSoles().compareTo(new BigDecimal("0.00"))==0 ) {
		    	  ConsistenciaDto dto = new ConsistenciaDto();
		    	  dto.setOrigen(asiento.getCodOrigen());
		    	  dto.setVou(asiento.getNumAsiento());
		    	  dto.setMes(asiento.getCodMes()>9 ? "" + asiento.getCodMes() : "0"+asiento.getCodMes());
		    	  dto.setComentario(c.cadena());
		    	  dto.setTotalDebeDolares(asiento.getTotalDebeDolares());
		    	  dto.setTotalDebeSoles(asiento.getTotalDebeSoles());
		    	  dto.setTotalHaberDolares(asiento.getTotalHaberDolares());
		    	  dto.setTotalHaberSoles(asiento.getTotalHaberSoles());
		    	  lst.add(dto);
			  }
		return lst;
	}
}

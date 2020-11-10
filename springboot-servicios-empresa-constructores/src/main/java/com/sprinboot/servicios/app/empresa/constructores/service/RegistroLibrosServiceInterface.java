package com.sprinboot.servicios.app.empresa.constructores.service;

import java.util.List;

import com.sprinboot.servicios.app.empresa.constructores.dto.FiltroIgvDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.RegistroComprasDetDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.RegistroComprasDto;
import com.sprinboot.servicios.empresa.commons.entity.Libro;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

public interface RegistroLibrosServiceInterface {

	public RegistroLibros save (RegistroLibros registroLibros);
	public RegistroLibros saveManual (RegistroLibros request);
	public List<Libro> findByAllEnabled();
	public List<RegistroLibros> listarRegistroLibrosPorFiltro(FiltroIgvDto fitro);
	public RegistroComprasDetDto listarRegistroDeCompras(String doc, Integer idperiodo, Integer codmes, String anio,Integer decimales ,String codLibro,String codParametro);
	public RegistroComprasDto calcularTotales(RegistroComprasDto det,Integer decimales);
	public List<RegistroLibros> buscarVoucher(String serie,String ruc,String codDocumento);
	public RegistroComprasDetDto listarRegistroAuxiliares (String doc, Integer idperiodo, Integer codmes, String anio,Integer decimales ,String codLibro,String codParametro, String codLibroAuxiliar, String codObra) ;



}

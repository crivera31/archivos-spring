package com.sprinboot.servicios.app.empresa.sis.funciones;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import java.util.Formatter;
import java.util.regex.Pattern;

@Component
public class Funciones {
   
	/* Funcion para poder verificar si una cadena es numero o no
	 * @Param (String) una cadena para comprobar
	 * return Un resultado booleano de true si es numero , y false si no lo es
	 * */
	public boolean isNumeric(String cadena) {
		boolean resultado;
		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException e) {
			resultado = false;
		}
		System.out.println("RESULTADO ES = "+resultado);
		return resultado;
	}
	
	/* Funcion para sumar y luego redondear la suma para numeros financieros
	 * @Param (BigDecimal) primer monto 
	 * @Param (BigDecimal) segundo monto 
	 * @Param (Integer) el numero de decimales     
	 * return 
	 * */
	public BigDecimal sumarRedondear(BigDecimal numero,BigDecimal numero2 ,Integer decimales) {
		System.out.println("N1 = "+numero );
		System.out.println("N2 = "+numero2 );

		numero = numero!=null ? numero : new BigDecimal("0");
		numero2 = numero2!=null ? numero2 : new BigDecimal("0");
		System.out.println("dN1 = "+numero );
		System.out.println("dN2 = "+numero2 );
		BigDecimal resultadoSuma ;
		
			 BigDecimal a = new BigDecimal(""+numero);
	         BigDecimal b = new BigDecimal(""+numero2);
	         System.out.println("a ="+a);
	         System.out.println("b ="+b);
	         resultadoSuma = a.add(b);  
	         //HALF_DOWN Redondea hacia arriba solo si el decimal es > a 0.5
	       //HALF_up Redondea hacia arriba solo si el decimal es > a 0.5
	         resultadoSuma = resultadoSuma.setScale(decimales, RoundingMode.HALF_UP);     
	         System.out.print("Resultado Suma: "+resultadoSuma + "\n");
		
		System.out.println("RESULTADO ES = "+resultadoSuma);
		return resultadoSuma;
	}
	
	
	
	
	/* Funcion para multiplicar y luego redondear la multiplicacion numeros financieros
	 * @Param (BigDecimal) primer monto 
	 * @Param (BigDecimal) segundo monto 
	 * @Param (Integer) el numero de decimales     
	 * return 
	 * */
	public BigDecimal multiRedondear(BigDecimal numero,BigDecimal numero2 ,Integer decimales) {
		 numero = numero!=null ? numero : new BigDecimal("0");
		 numero2 = numero2!=null ? numero2 : new BigDecimal("0");
		 BigDecimal resultadoMultiplicacion ;
		
			 BigDecimal a = new BigDecimal(""+numero);
	         BigDecimal b = new BigDecimal(""+numero2);
	         System.out.println("a ="+a);
	         System.out.println("b ="+b);
	         resultadoMultiplicacion = a.multiply(b);  
	         //HALF_DOWN Redondea hacia arriba solo si el decimal es > a 0.5
	         resultadoMultiplicacion = resultadoMultiplicacion.setScale(decimales, RoundingMode.HALF_UP);     
	         System.out.print("Resultado Multiplicacion: "+resultadoMultiplicacion);
		
		System.out.println("RESULTADO ES = "+resultadoMultiplicacion);
		return resultadoMultiplicacion;
	}
	
	/* Funcion para multiplicar y luego redondear la division numeros financieros
	 * @Param (BigDecimal) primer monto 
	 * @Param (BigDecimal) segundo monto 
	 * @Param (Integer) el numero de decimales     
	 * return 
	 * */
	public BigDecimal dividirRedondear(BigDecimal numero,BigDecimal numero2 ,Integer decimales) {
		 numero = numero!=null ? numero : new BigDecimal("0");
		 numero2 = numero2!=null ? numero2 : new BigDecimal("0");
		 BigDecimal resultado ;
	
			 BigDecimal a = new BigDecimal(""+numero);
	         BigDecimal b = new BigDecimal(""+numero2);
	         System.out.println("a ="+a);
	         System.out.println("b ="+b);
	         resultado = a.divide(b,decimales,RoundingMode.HALF_UP);  
	         //HALF_DOWN Redondea hacia arriba solo si el decimal es > a 0.5
	         //resultado = resultado.setScale(decimales, RoundingMode.HALF_UP);     
	         System.out.print("Resultado Division: "+resultado);
		
		System.out.println("RESULTADO ES = "+resultado);
		return resultado;
	}
	
	/* Funcion para obtener la serie
	 * @Param (String) la serieconnumero 
	 * return solo serie
	 * */
	public String getSerie(String serie) {
		serie = serie.replace(" ", "");
		 String[] parts = serie.split("-");
		 if (parts.length>1 ) {
			return parts[0];
		} else {
			return " ";
		}
		 
		/*String serieonly ="" ;
		int i=0;
		do {
			if (serie.charAt(i)!= '-') {
				serieonly = serieonly + serie.charAt(i);
			}else {
				break;
			}
			i++;
		} while (serie.length()>i);
		return serieonly;*/
	}
	
	/* Funcion para obtener el numero y no la serie
	 * @Param (String) la serie y el numero
	 * return solo el numero
	 * */
	public String getNumero(String serie) {
		serie = serie.replace(" ", "");
		String[] parts = serie.split("-");
		 if (parts.length>1 ) {
			return parts[1];
		} else {
			return parts[0];
		}
		/*String serieonly ="" ;
		int i=0;
		boolean ban=false;
		while (serie.length()>i){
			if (ban) {
				serieonly = serieonly + serie.charAt(i);
			}
			if (serie.charAt(i)== '-') {
				ban=true;
			}
			i++;
		} ;
		return serieonly;*/
	}
	
	/* Funcion para convertir de Date a String
	 * @Param (Date) fecha
	 * return string la fecha en formato dd/MM/yyyy
	 * */
	public String getFechaToString(Date fecha) {
		if (fecha==null) {
			return "";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaComoCadena = sdf.format(fecha);
			return fechaComoCadena;
		}
	}
	
	/* Funcion para sumar y luego redondear registro libros
	 * @Param (BigDecimal) primer monto 
	 * @Param (BigDecimal) segundo monto 
	 * @Param (Integer) el numero de decimales     
	 * return 
	 * */
	public BigDecimal sumarRedondearTotalLibro(BigDecimal n,BigDecimal n2 ,BigDecimal n3, BigDecimal n4,BigDecimal n5,BigDecimal n6,BigDecimal n7,BigDecimal n8,BigDecimal n9,BigDecimal n10,BigDecimal n11,BigDecimal n12, Integer decimales) {
		

		n = n!=null ? n : new BigDecimal("0");
		n2 = n2!=null ? n2 : new BigDecimal("0");
		n3 = n3!=null ? n3 : new BigDecimal("0");
		n4 = n4!=null ? n4 : new BigDecimal("0");
		n5 = n5!=null ? n5 : new BigDecimal("0");
		n6 = n6!=null ? n6 : new BigDecimal("0");
		n7 = n7!=null ? n7 : new BigDecimal("0");
		n8 = n8!=null ? n8 : new BigDecimal("0");
		n9 = n9!=null ? n9 : new BigDecimal("0");
		n10 = n10!=null ? n10 : new BigDecimal("0");
		n11 = n11!=null ? n11 : new BigDecimal("0");
		n12 = n12!=null ? n12 : new BigDecimal("0");

		BigDecimal resultadoSuma ;
		
			 BigDecimal a = new BigDecimal(""+n);
	         BigDecimal b = new BigDecimal(""+n2);
	         BigDecimal c = new BigDecimal(""+n3);
	         BigDecimal d = new BigDecimal(""+n4);
	         BigDecimal e = new BigDecimal(""+n5);
	         BigDecimal f = new BigDecimal(""+n6);
	         BigDecimal g = new BigDecimal(""+n7);
	         BigDecimal h = new BigDecimal(""+n8);
	         BigDecimal i = new BigDecimal(""+n9);
	         BigDecimal j = new BigDecimal(""+n10);
	         BigDecimal k = new BigDecimal(""+n11);
	         BigDecimal l = new BigDecimal(""+n12);
	 
	         resultadoSuma = a.add(b).add(c).add(d).add(e).add(f).add(g).add(h).add(i).add(j).add(k).add(l);  
	         //HALF_DOWN Redondea hacia arriba solo si el decimal es > a 0.5
	       //HALF_up Redondea hacia arriba solo si el decimal es > a 0.5
	         resultadoSuma = resultadoSuma.setScale(decimales, RoundingMode.HALF_UP);     
	         System.out.print("Resultado Suma: "+resultadoSuma + "\n");
			
		System.out.println("RESULTADO ES = "+resultadoSuma);
		return resultadoSuma;
	}
	
	/* Funcion redondear bigdecimal 
	 * @Param (BigDecimal) monto 
	 * @Param (Integer) el numero de decimales     
	 * return 
	 * */
	public BigDecimal getRedondear(BigDecimal monto,Integer decimales) {
		return monto.setScale(decimales, RoundingMode.HALF_UP);     
	}
	
	
	/* Funcion para obtener el formato periodo para los libros electronicos
	 * @Param (Integer) el mes en el que se esta trabajando
	 * @Param (String) el anio en el que se esta trabajando
	 * return el formato pedido por sunat es AAAAMM00 
	 * */
	public String getPeriodoPle(Integer mes, String anio) {
		if(mes!=null && mes> 0 && mes< 13 && anio.length()==4) {
			String mesCadena =  mes < 10 ? "0"+mes : ""+mes; 
            return anio+mesCadena+"00";
		}else{
            return null;
          }
	}
	
	
	/* Funcion para obtener el formato del campo 3  para los libros electronicos ,
	 * columna 3 CUO, A = asiento apertura, M = asiento mes, C = asiento cierre
	 * @Param (String) Codigo de Origen
	 * @Param (Integer) Numero de asiento
	 * @Param (Integer) el mes en el que se esta trabajando
	 * return el formato ejmp M0100001
	 * */
	public String getCampo3Ple(String codOrigen,Integer numAsiento,Integer mes) {
		String codigo=null;
	
		if (mes> 0 && mes< 13 ) {codigo = "M";}
		else {if (mes==0) {codigo="A";}
		else {if(mes==13) { codigo ="C";}}}
		
        Formatter obj = new Formatter();
        String asientoFormato = String.valueOf(obj.format("%05d", numAsiento));
		return codigo + codOrigen+ asientoFormato;
	}
	
	
	/* Funcion para valida si el campo 11 es obligatorio o no  para los libros electronicos ,
	 * ES OBLIGAtorio excepto cuando el dato del campo 6 sea diferente a algunos codigos en el caso A
	 * propuesto por sunat, codificar el caso B es innecesario ya que el caso A lo condiciona
	 * @Param (String) Codigo del documento FACTURA BOLETA etc
	 * return 0: no obligatorio  1 : obligatorio
	 * */
	public Integer getValidarCampo11Ple(String codDocumento) {
		//String codDocumentosCasoA[] ;
		int obligatorio=1;
		
			String codDocumentosCasoA[] = {"00","03","05","06","07","08","11","12","13","14","15","16","18","19","22","23","26","28",
                    "30","34","35","36","37","55","56","87","88","91","97","98"};
			//caso A
			for (int i = 0; i < codDocumentosCasoA.length; i++) {
			     if ( codDocumentosCasoA[i].compareTo(codDocumento)==0 ) {
					  obligatorio=0;
					  break;
				}
			}
		
		return obligatorio;
	}
	
	/* Funcion para valida si el campo 10 11 12  es obligatorio o no  para los libros electronicos ventas,
	 * ES OBLIGAtorio excepto cuando el dato del campo 6 sea diferente a algunos codigos en el caso A
	 * propuesto por sunat, codificar el caso B es innecesario ya que el caso A lo condiciona
	 * @Param (String) Codigo del documento FACTURA BOLETA etc
	 * return 0: no obligatorio  1 : obligatorio
	 * */
	public Integer getValidarCampo10VentasPle(String codDocumento,BigDecimal total, BigDecimal valorExportacion) {
		//String codDocumentosCasoA[] ;
		int obligatorio=1;
		

			String codDocumentosCasoA[] = {"00","05","06","07","08","11","12","13","14","15","16","18","19","23","26","28",
                    "30","34","35","36","37","55","56","87","88"};
			//caso A
			for (int i = 0; i < codDocumentosCasoA.length; i++) {
			     if ( codDocumentosCasoA[i].compareTo(codDocumento)==0 ) {
					  obligatorio=0;
					  break;
				}
			}
				
			//caso B
			String codDocumentosCasoB[] = {"03","12"};	
			for (int i = 0; i < codDocumentosCasoB.length; i++) {
			     if ( codDocumentosCasoB[i].compareTo(codDocumento)==0 && total.compareTo(new BigDecimal("700"))==-1) {
					  obligatorio=0;
					  break;
				}
			}
			
			//caso C
			if (valorExportacion.compareTo(new BigDecimal("0"))==1) {
				obligatorio=0;
			}
			
		
		return obligatorio;
	}
	
	
	/* Funcion para convertir de Bigdecimal a un formato String con los decimales que queramos, ademas redondea,
	 * @Param (BigDecimales) Monto
	 * return String el monto
	 * */
	public String getFormatoNumber(Object a, Integer decimales) {
			
		BigDecimal b = new BigDecimal(""+a);
		String dec= decimales>-1 && decimales!=null ? "":"00";
		if (decimales!=null) { for (int i = 0; i < decimales; i++) { dec+="0";}}
        DecimalFormat df = new DecimalFormat("#0."+dec);
        return  String.valueOf(df.format(b));
	}
	
	/* Funcion para convertir a formato de moneda que pide sunat para los PLE
	 * @Param (String) moneda
	 * return formato moneda sunat
	 * */
	public String getFormatoMonedaPle(String moneda) {
		switch (moneda) {
		case "S":
			return "PEN";
		case "D":
			return "USD";
		default:
			return "";
		}
	}
	
	/* Funcion para validar a el tipo de cambio pide sunat para los PLE 
	 * El método toma como parámetro una expresión regular.
	 * Si se quiere utilizar un separador textual, se deben escapar los caracteres \ ^ $ . | ? * + ( ) , para estos
	 * usar la funcion Pattern para inclurilos al split
	 * @Param (String) moneda
	 * return valido 1  invalido= 0
	 * */
	public Integer getValidarTipoCambioPle(String tipoCambio) {
	    String separador = Pattern.quote(".");
	    String[] parts = tipoCambio.split(separador);
		String entero = parts[0]; // enteros
		String decimal = parts[1]; // decimales
		if (entero.length()==1 && decimal.length()==3) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/* Funcion para validar si la  fecha es  menor al periodo del campo 1 
	 * usar la funcion Pattern para inclurilos al split
	 * @Param (String) Fecha
	 * return valido 1  invalido= 0
	 * */
	public Integer getValidarFechaConElPeriodo(String fechaRef ,String anio, Integer mes) {
		
		Integer mesActual =  Integer.parseInt(mes < 10 ? "0"+mes : ""+mes) ; 
		Integer anioActual = Integer.parseInt(anio);
	    String separador = Pattern.quote("/");
	    String[] parts = fechaRef.split(separador);
		String dd = parts[0]; // enteros
		Integer mm = Integer.parseInt(getSinEspacios(parts[1])); // decimales
		Integer aaaa = Integer.parseInt(getSinEspacios(parts[2]));  //anio
		if ((aaaa.compareTo(anioActual) == 0  && mm<=mesActual) || (aaaa<anioActual) ) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/* Funcion para validar EL CAMPO 26 si es obligatorio o no 
	 *  NC ND  pide sunat para los PLE 
	 * usar la funcion Pattern para inclurilos al split
	 * @Param (String) Fecha
	 * return obligatorio 1  no Obligatorio= 0
	 * */
	public Integer getValidarSiEsObligatorioCampo26(String codDocumento) {
		String codDocumentos[] = {"07","08","87","88","97","98"};	
		for (int i = 0; i < codDocumentos.length; i++) {
			 if (codDocumentos[i].compareTo(codDocumento)==0) {
				 return 1;
			}
		}
		return 0;
	}
	

	
	
	
	
	/* Funcion convertir una cadena de fecha a DATE
	 * @Param (String) Fecha con formato dd/mm/aaaa
	 * return el formato en date con 00:00:000 horas
	 * */
	public Date getDate(String fecha) {
	    String separador = Pattern.quote("/");
	    String[] parts = fecha.split(separador);
		Integer dd = Integer.parseInt(parts[0]);// enteros
		Integer mm = Integer.parseInt(parts[1])-1; // decimales
		Integer aaaa = Integer.parseInt(parts[2]);  //anio
	    Calendar fechaL = Calendar.getInstance();
		fechaL.set(aaaa, mm, dd, 0, 0, 0);
		
		return fechaL.getTime();
	}
	
	
	
	/* Funcion para validar  EL CAMPO 41 de compras
	 * @Param (String) Fecha
	 * return 0: No tiene Credito Fiscal (CAMPO 14+15+16+17==0) 
	 *        1: NORMAL
	 *        6: Fecha de Emision es menor al del periodo informado(CAMPO 1)
	 *        7: Fecha de Emision es menor a 12 meses del periodo informado(CAMPO 1)
	 *        9: Es una Rectificacion, por ahora no se programara eso lo hara manualmente el contador 
	 * */
	public Integer getValorParaCampo41(String fecha, Integer mes, String anio,BigDecimal suma) {
		   
		Integer mesActual =  Integer.parseInt(mes < 10 ? "0"+mes : ""+mes) ; 
		Integer anioActual = Integer.parseInt(anio);
	    String separador = Pattern.quote("/");
	    String[] parts = fecha.split(separador);
		String dd = parts[0]; // enteros
		Integer mm = Integer.parseInt(getSinEspacios(parts[1])); // decimales
		System.out.println("mm : "+mm);
		Integer aaaa = Integer.parseInt(getSinEspacios(parts[2]));  //anio
		System.out.println("aaaa : "+aaaa);

		 if (suma.compareTo(new BigDecimal("0")) == 0) { 
	          return 0;
	     } else {
	    		if (aaaa.compareTo(anioActual)==0 && mm.compareTo(mesActual)==0) {
	    			return 1;
	    		}else {
	    			if ((aaaa.compareTo(anioActual)==0 && mm<mesActual) || (aaaa<anioActual) ) {
	    				Integer anioAnt= anioActual-1;
	    				String stringFecha = "01"+"/"+ (mes < 10 ? "0"+mes : ""+mes) +"/"+anioAnt;
	    				Date fechaLimite= getDate(stringFecha);
	    				Date fechaEmision = getDate(fecha);
	    				DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    				System.out.println("fechaEmision: "+fechaEmision);
	    		        System.out.println("fechaEmision: "+formatoFecha.format(fechaEmision));
	    		        System.out.println("fechalIMITE: "+fechaLimite);
	    		        System.out.println("fechalIMITE: "+formatoFecha.format(fechaLimite));
	    				if (fechaEmision.compareTo(fechaLimite) == 1 || fechaEmision.compareTo(fechaLimite) == 0 )  {
		    				return 6;
		    			}else {
		    				 return 7;
		    			}
	    				
    				}else {
    					return -1;
    				}
	    			
	    		}
		 } 
	
	}
	
	/* Funcion convertir una cadena sin espacios
	 * @Param (String) texto 
	 * return texo sin espacios
	 * */
	public String getSinEspacios(String text) {
		return text.replace(" ","");
	}
	
	/*Funcion para convertir un string sin espacios al final e inicio
	* @Param (String) texto 
	 * return texo sin espacios al inicio y al final
	 * */
	public String getTrim(String text) {
		return text.trim();
	}
	
	
	
	/* Funcion para validar EL CAMPO 34 de ventas si es obligatorio o no 
	 *  NC ND  pide sunat para los PLE 
	 * usar la funcion Pattern para inclurilos al split
	 * @Param (String) Fecha
	 * return obligatorio 1  no Obligatorio= 0
	 * */
	public Integer getValidarSiEsObligatorioCampo27Ventas(String codDocumento,Integer campo34) {
		String codDocumentos[] = {"07","08","87","88"};	
		for (int i = 0; i < codDocumentos.length; i++) {
			 if (codDocumentos[i].compareTo(codDocumento)==0 && campo34.compareTo(new Integer(2))!=0) {
				 return 1;
			}
		}
		return 0;
	}
	
	
	
	
	/* Funcion para validar  EL CAMPO 34 de VENTAS
	 * @Param (String) Fecha
	 * return 0: No tiene Credito Fiscal (CAMPO 14+16==0) 
	 *        1: NORMAL
	 *        2: Cuando el documento ah sido anulado // MANUALMENTE
	 *        8: Fecha de Emision es menor al del periodo informado(CAMPO 1)
	 *        9: Es una Rectificacion, por ahora no se programara eso lo hara manualmente el contador 
	 * */
	public Integer getValorParaCampo34Ventas(String fecha, Integer mes, String anio,BigDecimal suma) {
		   
		Integer mesActual =  Integer.parseInt(mes < 10 ? "0"+mes : ""+mes) ; 
		Integer anioActual = Integer.parseInt(anio);
	    String separador = Pattern.quote("/");
	    String[] parts = fecha.split(separador);
		String dd = parts[0]; // enteros
		Integer mm = Integer.parseInt(getSinEspacios(parts[1])); // decimales
		System.out.println("mm : "+mm);
		Integer aaaa = Integer.parseInt(getSinEspacios(parts[2]));  //anio
		System.out.println("aaaa : "+aaaa);

		 if (suma.compareTo(new BigDecimal("0")) == 0) { 
	          return 0;
	     } else {
	    		if (aaaa.compareTo(anioActual)==0 && mm.compareTo(mesActual)==0) {
	    			return 1;
	    		}else {
	    			if ((aaaa.compareTo(anioActual)==0 && mm<mesActual) || (aaaa<anioActual) ) {
	    				return 8;
	    				
    				}else {
    					return -1;
    				}
	    			
	    		}
		 } 
	
	}
	
	
	/* Funcion para cortar cadena desde el final si este excede los 200
	 * @Param (int) Maximo
	 * @Param (String) cadena
	 * return obligatorio 1  no Obligatorio= 0
	 * */
	public String analizarLongitudCadena(int maximo, String cadena)
	{
    try {
    	if (cadena==null) {
			return "";
		}else {
			if (cadena.length()> maximo) {
				return cadena.substring(0,maximo);
			}else {
				return cadena;
			}
		}
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	/* Funcion para validar  EL CAMPO 21 de DIARIO
	 * @Param (String) Fecha
	 * return 
	 *        1: NORMAL
	 *        8: Fecha de Emision es menor al del periodo informado(CAMPO 1)
	 *        9: Es una Rectificacion, por ahora no se programara eso lo hara manualmente el contador 
	 * */
	public Integer getValidarCampo21LEdiario(String fecha, Integer mes, String anio) {
		   
		Integer mesActual =  Integer.parseInt(mes < 10 ? "0"+mes : ""+mes) ; 
		Integer anioActual = Integer.parseInt(anio);
	    String separador = Pattern.quote("/");
	    String[] parts = fecha.split(separador);
		String dd = parts[0]; // enteros
		Integer mm = Integer.parseInt(getSinEspacios(parts[1])); // decimales
		System.out.println("mm : "+mm);
		Integer aaaa = Integer.parseInt(getSinEspacios(parts[2]));  //anio
		System.out.println("aaaa : "+aaaa);

		// bandera cuando esta vacio 
		 if (fecha.compareTo("01/01/1970") == 0) { 
	          return 1;
	     } else {
	    		if (aaaa.compareTo(anioActual)==0 && mm.compareTo(mesActual)==0) {
	    			return 1;
	    		}else {
	    			if ((aaaa.compareTo(anioActual)==0 && mm<mesActual) || (aaaa<anioActual) ) {
	    				return 8;
	    				
    				}else {
    					return -1;
    				}
	    			
	    		}
		 } 
	
	}
	
	
	
	/* Funcion para sacar el codigo libro sunat
	 * @Param (String) cadena
	 * return el codigo segun sunat para LIBROS ELECTRONICOS
	 * */
	public String getCodigoLibroPLE (String cadena)
	{
     if (cadena.compareTo("01")==0) {
		return "080100";
	  }else {
		if (cadena.compareTo("02")==0) {
			return "140100";
		}else {
			return null;
		}
	}
	}
	
	
	/* Funcion para obtener el formato periodo para los libros electronicos DIARIO PLAN CONTABLE
	 * @Param (Integer) el mes en el que se esta trabajando
	 * @Param (String) el anio en el que se esta trabajando
	 * return el formato pedido por sunat es AAAAMMDD
	 * */
	public String getPeriodoFomatoAAAAMMDD (Integer mes, String anio) {
		if(mes!=null && mes> 0 && mes< 13 && anio.length()==4) {
			String mesCadena =  mes < 10 ? "0"+mes : ""+mes; 
            return anio+mesCadena+"01";
		}else{
            return null;
          }
	}
	
	/* Funcion para sumar y luego redondear la suma para numeros financieros
	 * @Param (BigDecimal) primer monto 
	 * @Param (BigDecimal) segundo monto 
	 * @Param (Integer) el numero de decimales     
	 * return 
	 * */
	public BigDecimal restarRedondear(BigDecimal numero,BigDecimal numero2 ,Integer decimales) {
		System.out.println("N1 = "+numero );
		System.out.println("N2 = "+numero2 );

		numero = numero!=null ? numero : new BigDecimal("0");
		numero2 = numero2!=null ? numero2 : new BigDecimal("0");
		System.out.println("dN1 = "+numero );
		System.out.println("dN2 = "+numero2 );
		BigDecimal resultadoSuma ;
		
			 BigDecimal a = new BigDecimal(""+numero);
	         BigDecimal b = new BigDecimal(""+numero2);
	         System.out.println("a ="+a);
	         System.out.println("b ="+b);
	         resultadoSuma = a.subtract(b);  
	         //HALF_DOWN Redondea hacia arriba solo si el decimal es > a 0.5
	       //HALF_up Redondea hacia arriba solo si el decimal es > a 0.5
	         resultadoSuma = resultadoSuma.setScale(decimales, RoundingMode.HALF_UP);     
	         System.out.print("Resultado Suma: "+resultadoSuma + "\n");
		
		System.out.println("RESULTADO ES = "+resultadoSuma);
		return resultadoSuma;
	}
	
	
	/* Funcion para obtener el formato de codigo de cuenta para los libros electronicos DIARIO PLAN CONTABLE
	 * @Param (String) codigo de cuenta
	 * return codigo con longitud de 5
	 * */
	public String getCuentaParaPle (String cuenta) {
		cuenta = cuenta.replace(" ","");
		//return String.format("%-5s", cuenta).replace(' ', '0');
		return cuenta.length() < 3 ? String.format("%-5s", cuenta).replace(' ', '0') : cuenta;
	}
	
	/* Funcion para verificar si el codigo tiene menos de tres esto es para los libros electronicos DIARIO PLAN CONTABLE
	 * @Param (String) codigo de cuenta
	 * return true : cumple   false: no cumple
	 * */
	public boolean validarCuentaTresDigitos (String cuenta) {
		cuenta = cuenta.replace(" ","");
		return (cuenta.length() < 3 ? false : true);
	}
	
	/* Funcion para dar formato al mes
	 * @Param (INteger) num mes
	 * return formato mes
	 * */
	public String getFomartoMes(Integer mes) {
		return mes < 10 ? "0"+mes : ""+mes;
	}
	
	/* Funcion para buscar una palabra dentro de un string EN LAS Primeras dos posiciones
	 * @Param (String) palabra
	 * return verdad o false
	 * */
	public Boolean getEsIgual(String cadenadondebuscar,String loquerquierobuscar) {
		cadenadondebuscar = cadenadondebuscar==null ? "" : cadenadondebuscar.trim();
		loquerquierobuscar = loquerquierobuscar==null ? "" : loquerquierobuscar.trim();
		if (loquerquierobuscar.length()>cadenadondebuscar.length() || cadenadondebuscar.length()==0 || loquerquierobuscar.length()==0 ) {
			return false;
		}else {
			String separador = cadenadondebuscar.substring(0, loquerquierobuscar.length());
			return separador.trim().compareTo(loquerquierobuscar.trim())==0;
		}
		
	}
	
	
	   private static final int  Unidad = 1;
	   private static final int  Decena = 10;
	   private static final int  Centena = 100;
		/* Funcion para convetir un numero a letra
		 * @Param (String) palabra
		 * return verdad o false
		 * */
	       
	  public  String readNumber( String Number, String sepDecimal, String sMoney ){
	       
	      String  V[] = initVector(), s = "", z = "", c = "", e = " ", t;
	      int l = Number.length(), k = Number.indexOf( sepDecimal ), u = 1, n = 0, j = 0, b = 0, d, p, r;
	      
	      try{
	            if( k >= 0 ) { c = Number.substring( k + 1, l );  l = k; }
	            
	            if ( l <= 15 )
	            {
	                        for( int i = l ; i >= 1; i-- ){
	                            d = Integer.parseInt( String.valueOf( Number.charAt( i - 1 ) )); 
	                            n = d * u + n;

	                                switch( u ){
	                                    case Unidad: 
	                                        s = V[ n ]; 
	                                        if ( i == l && n == 1 ) b++;
	                                    break;
	                                    case Decena:                                                
	                                        p = d - 2;
	                                    
	                                        if( p < 0 )   
	                                            s = V[ n ];                                      
	                                        else{ 
	                                            t =  V[ 20 + p ];
	                                            
	                                            if( n % 10 != 0 )
	                                               s  =  (d == 2)? "veinti" + s : t + " y " + s;
	                                            else    
	                                               s = t;
	                                         }
	                                    break;
	                                    case Centena:
	                                       p = d - 1; 
	                                       t = V[ 30 + p ];
	         
	                                       if( n % 100  == 0 ) 
	                                       { s = ""; e = ""; }  
	                                       else 
	                                         if( d == 1 ) t += "to";  
	                                       
	                                       s = t + e + s;                                       
	                                       z = ( s + z );   
	                                    break;          
	                                }  
	                                
	                                e = " ";     
	                              //ini. calcula los miles, millones, billones
	                                r = l - i;                                
	                                if( r > 0 && r % 3 == 0  ){
	                                        p = ( r > 10 )?  2 : j++ & 1;     
	                                        t = V[ 40 + p ];
	                                        
	                                        if( p > 0 )
	                                           if( ( n == 1 && i > 1 ) || n > 1  ) t += "es";
	                                        
	                                        z = e + t + e + z;
	                                }
	                              //fin.
	                                
	                                // reiniciar variables
	                               if ( u == Centena ){  u = 1;  n = 0;  s = "";  } else u *= 10;                                 
	                         } 
	                       
	             }      

	           //ini. adiciona la moneda y los centavos
	                if ( !c.equals("") ) c = " con " + c + "";            
	                if ( !sMoney.equals("") )        
	                    sMoney = " " + sMoney; 
	                else
	                    if( b > 0 ) z += "o";  
	           //fin. 
	            
	            z = ( s + z ) + sMoney + c;
	      }
	      catch(NumberFormatException ex){
	            z = "ERROR [readNumber]: Formato numerico incorrecto.";
	      }
	      
	     return z;
	     
	   }
	 
	   
	   
	   private String[] initVector(  ){
	       String V[] = new String[43];
	        
	        V[0] = "cero";
	        V[1] = "";
	        V[2] = "dos";
	        V[3] = "tres";
	        V[4] = "cuatro";
	        V[5] = "cinco";
	        V[6] = "seis";
	        V[7] = "siete";
	        V[8] = "ocho";
	        V[9] = "nueve";
	        V[10] = "diez";
	        V[11] = "once";
	        V[12] = "doce";
	        V[13] = "trece";
	        V[14] = "catorce";
	        V[15] = "quince";
	        V[16] = "dieciseis";
	        V[17] = "diecisiete";
	        V[18] = "dieciocho";
	        V[19] = "diecinueve";
	        V[20] = "veinte";
	        V[21] = "treinta";
	        V[22] = "cuarenta";
	        V[23] = "cincuenta";
	        V[24] = "sesenta";
	        V[25] = "setenta";
	        V[26] = "ochenta";
	        V[27] = "noventa";
	        V[28] = "";
	        V[29] = "";
	        V[30] = "cien";
	        V[31] = "doscientos";
	        V[32] = "trescientos";
	        V[33] = "cuatrocientos";
	        V[34] = "quinientos";
	        V[35] = "seiscientos";
	        V[36] = "setecientos";
	        V[37] = "ochocientos";
	        V[38] = "novecientos";
	        V[39] = "";
	        V[40] = "mil";
	        V[41] = "millon";
	        V[42] = "billon";
	        
	        return V;
	   }
	
}

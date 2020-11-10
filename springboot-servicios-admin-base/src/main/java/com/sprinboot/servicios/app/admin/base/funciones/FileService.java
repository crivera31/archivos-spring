package com.sprinboot.servicios.app.admin.base.funciones;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class FileService {

	private enum ResourceType {
		FILE_SYSTEM,
		CLASSPATH
	}

	private static final String FILE_DIRECTORY = "/var/files/";

	/**
	 * @param filename : Un String que es el nombre del fichero ejm: libroElectronico.txt 
	 * @param response : Es un servlet Http response propio de Java , es la respuesta del servidor hacia el cliente.
	 * @param tipoArchivo : Ejemplo : text/csv, text/plain etc
	 * @return El fichero que esta en el servidor por ejemplo se puede usar para manuales.
	 */
	public Resource getFileSystem(String filename, HttpServletResponse response,String tipoArchivo) {
		return getResource(filename, response, ResourceType.FILE_SYSTEM,tipoArchivo);
	}

	/**
     * @param filename : Un String que es el nombre del fichero ejm: libroElectronico.txt 
	 * @param response : Es un servlet Http response propio de Java , es la respuesta del servidor hacia el cliente.
	 * @param tipoArchivo : Ejemplo : text/csv, text/plain etc
	 * @return El fichero que esta en el en el microservicio en la parte de resources/data, ya que segun el framework solo 
	 * 		   leera archivos que esten en esa seccion.
	 */
	public Resource getClassPathFile(String filename, HttpServletResponse response , String tipoArchivo) {
		return getResource(filename, response, ResourceType.FILE_SYSTEM,tipoArchivo);
	}

	
	public Resource getResource(String filename, HttpServletResponse response, ResourceType resourceType,String tipoArchivo) {
		response.setContentType(tipoArchivo + "; charset=utf-8");
		//response.setContentType("text/csv; charset=utf-8");

		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		response.setHeader("filename", filename);

		Resource resource = null;
		switch (resourceType) {
			case FILE_SYSTEM:
				resource = new FileSystemResource(FILE_DIRECTORY + filename);
				break;
			case CLASSPATH:
				resource = new ClassPathResource("data/" + filename);
				break;
		}

		return resource;
	}
}
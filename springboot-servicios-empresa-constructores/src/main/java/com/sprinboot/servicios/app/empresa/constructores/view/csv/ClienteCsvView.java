package com.sprinboot.servicios.app.empresa.constructores.view.csv;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Component("/api/sistema-empresa/ple")
public class ClienteCsvView extends AbstractView {

	
	public ClienteCsvView() {
		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("LLEGO A COMPONENETE VIEW");
		response.setHeader("Content-disposition", "attachment; filename=\"ple.txt\"");
		response.setContentType(getContentType());
		//@SuppressWarnings("unchecked")
		
		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		String[] header = {"id","holi"};
		beanWriter.writeHeader(header);
		beanWriter.close();
		
	}

}

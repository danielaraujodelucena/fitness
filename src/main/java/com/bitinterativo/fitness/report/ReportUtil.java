package com.bitinterativo.fitness.report;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public byte[] gerarRelatorio(List dados, String relatorio, ServletContext servletContext) throws Exception {
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(dados);
		
		String urlJasper = servletContext.getRealPath("reports") + File.separator + relatorio + ".jasper";
		
		JasperPrint impressora = JasperFillManager.fillReport(urlJasper, new HashMap(), jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressora);
	}
}

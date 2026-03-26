package eccs.com.core.services;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import eccs.com.core.dtos.ResponseDto;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Servicio para generación de reportes y respuestas estandarizadas
 */
@Service
public class ResponseService {

	@Autowired
	private DataSource dataSource;

	/**
	 * Genera un PDF desde un archivo JRXML codificado en Base64
	 * @param JRXML - Plantilla JasperReports en Base64
	 * @param parameters - Parámetros para el reporte
	 * @return byte[] - PDF generado
	 */
	public byte[] generatePdfFromJrxml(String JRXML, Map<String, Object> parameters) throws Exception {
		byte[] jrxml = Base64.getDecoder().decode(JRXML);
		JasperReport reportJrxml = JasperCompileManager.compileReport(new ByteArrayInputStream(jrxml));
		
		try (Connection connection = dataSource.getConnection()) {
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportJrxml, parameters, connection);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		}
	}
	
	/**
	 * Crea una respuesta estandarizada exitosa
	 * @param responseDto - DTO de respuesta
	 * @param titulo - Título del mensaje
	 * @param mensaje - Mensaje descriptivo
	 * @param response - Datos de respuesta
	 * @return ResponseEntity con status 200 OK
	 */
	public ResponseEntity<ResponseDto<Object>> ServiceResponse(
			ResponseDto<Object> responseDto, 
			String titulo, 
			String mensaje, 
			Object response) {
		
		responseDto.setSuccess(true);
		responseDto.setTitulo(titulo);
		responseDto.setMensaje(mensaje);
		responseDto.setResponse(response);
		return ResponseEntity.ok(responseDto);
	}
}

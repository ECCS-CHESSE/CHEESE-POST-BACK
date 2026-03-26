package eccs.com.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import eccs.com.core.dtos.ResponseDto;

/**
 * Manejador global de excepciones para toda la aplicación
 * Captura errores y devuelve respuestas consistentes al cliente
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja errores cuando el body del request está vacío o mal formado
     * Ejemplo: JSON inválido, campos faltantes
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto<Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        logger.error("Request body error: {}", ex.getMessage());
        
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(false);
        response.setTitulo("ECCS - ERROR EN LA SOLICITUD");
        response.setMensaje("El cuerpo de la solicitud es requerido o está mal formado");
        response.setResponse(null);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja errores de argumentos inválidos
     * Ejemplo: parámetros incorrectos, validaciones fallidas
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Client configuration error: {}", ex.getMessage());
        
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(false);
        response.setTitulo("ERROR DE CONFIGURACIÓN");
        response.setMensaje("Error en la configuración: " + ex.getMessage());
        response.setResponse(null);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja cualquier error no capturado por otros handlers
     * Última línea de defensa para errores inesperados
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime error: {}", ex.getMessage());
        
        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(false);
        response.setTitulo("Error del Sistema");
        response.setMensaje("Error interno: " + ex.getMessage());
        response.setResponse(null);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

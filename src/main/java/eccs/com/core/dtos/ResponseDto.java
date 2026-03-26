package eccs.com.core.dtos;

/**
 * DTO genérico para respuestas estandarizadas de la API
 * @param <T> Tipo de dato que contiene la respuesta
 */
public class ResponseDto<T> {
    
    private boolean success;
    private String titulo;
    private String mensaje;
    private T response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}

package eccs.com.modules.facturacion.timbrar.service;

public class TimbradoRechazadoException extends RuntimeException {

    private final Object respuestaRigel;

    public TimbradoRechazadoException(String mensaje, Object respuestaRigel) {
        super(mensaje);
        this.respuestaRigel = respuestaRigel;
    }

    public Object getRespuestaRigel() {
        return respuestaRigel;
    }
}

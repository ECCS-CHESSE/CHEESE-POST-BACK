package eccs.com.auth.login.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private boolean success;
    private String mensaje;
    private UsuarioDto usuario;

    @Data
    public static class UsuarioDto {
        private Long id_eccs_empleado;
        private Long id_estatus_empleado;
        private String eccs_estatus;
        private Long id_eccs_sucursal;
        private Long id_eccs_sucursal_domicilio;
        private String empleado;
        private String eccs_url;     
        private Boolean requiereNip;     
    }
}

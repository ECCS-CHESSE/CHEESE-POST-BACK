package eccs.com.modules.controlsync.service;

import eccs.com.core.dtos.ResponseDto;

public interface SyncService {
    void registrarQuery(String query);
    ResponseDto<Object> importarSync(String sqlContent, Integer idSucursal);
    ResponseDto<Object> getLogs();
    ResponseDto<Object> getLogsPendientes();
    ResponseDto<Object> getStatusSucursal(Integer idSucursal);
    ResponseDto<Object> marcarResuelto(Long id);
}

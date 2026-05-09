package eccs.com.modules.controlsync.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlsync.entity.SyncLogEntity;
import eccs.com.modules.controlsync.query.SyncLogQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncServiceImpl implements SyncService {

    private final SyncLogQuery syncLogQuery;

    @Value("${sync.sql.path:C:/app/sync.sql}")
    private String syncSqlPath;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void registrarQuery(String query) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(syncSqlPath, true))) {
            pw.println(query + ";" );
        } catch (Exception e) {
            log.error("Error al escribir en sync.sql: {}", e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto<Object> importarSync(String sqlContent, Integer idSucursal) {
        ResponseDto<Object> response = new ResponseDto<>();
        List<String> errores = new ArrayList<>();
        List<String> exitosos = new ArrayList<>();

        String[] queries = sqlContent.split(";");

        for (String query : queries) {
            String q = query.trim();
            if (q.isEmpty()) continue;

            try {
                entityManager.createNativeQuery(q).executeUpdate();
                exitosos.add(q);
            } catch (Exception e) {
                SyncLogEntity logEntry = new SyncLogEntity();
                logEntry.setQueryFallido(q);
                logEntry.setErrorMsg(e.getMessage());
                logEntry.setFecha(LocalDateTime.now());
                logEntry.setResuelto(false);
                logEntry.setIdSucursal(idSucursal);
                syncLogQuery.save(logEntry);
                errores.add(q);
            }
        }

        response.setSuccess(true);
        response.setTitulo("ECCS - CONTROL SYNC");
        response.setMensaje("Sync completado. Exitosos: " + exitosos.size() + " | Fallidos: " + errores.size());
        response.setResponse(null);
        return response;
    }

    @Override
    public ResponseDto<Object> getLogs() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(syncLogQuery.getLogs());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getLogsPendientes() {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(syncLogQuery.getLogsPendientes());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getStatusSucursal(Integer idSucursal) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(syncLogQuery.getStatusSucursal(idSucursal));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    @Transactional
    public ResponseDto<Object> marcarResuelto(Long id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            SyncLogEntity log = syncLogQuery.findById(id).orElseThrow();
            log.setResuelto(true);
            syncLogQuery.save(log);
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("Registro marcado como resuelto");
            response.setResponse(null);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

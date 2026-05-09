package eccs.com.modules.controlsync.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlsync.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final SyncService syncService;

    // Java LOCAL llama este endpoint cuando detecta internet
    @PostMapping("/import")
    public ResponseEntity<ResponseDto<Object>> importarSync(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id_sucursal") Integer idSucursal) {
        try {
            String sqlContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(syncService.importarSync(sqlContent, idSucursal));
        } catch (Exception e) {
            ResponseDto<Object> response = new ResponseDto<>();
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL SYNC");
            response.setMensaje("Error al leer el archivo: " + e.getMessage());
            response.setResponse(null);
            return ResponseEntity.ok(response);
        }
    }

    // Frontend consulta si la sucursal tiene errores pendientes
    @GetMapping("/status/{idSucursal}")
    public ResponseEntity<ResponseDto<Object>> getStatusSucursal(@PathVariable Integer idSucursal) {
        return ResponseEntity.ok(syncService.getStatusSucursal(idSucursal));
    }

    // Admin ve todos los logs
    @GetMapping("/logs")
    public ResponseEntity<ResponseDto<Object>> getLogs() {
        return ResponseEntity.ok(syncService.getLogs());
    }

    // Admin ve solo los pendientes (errores sin resolver)
    @GetMapping("/logs/pendientes")
    public ResponseEntity<ResponseDto<Object>> getLogsPendientes() {
        return ResponseEntity.ok(syncService.getLogsPendientes());
    }

    // Admin marca un error como resuelto
    @PatchMapping("/logs/{id}/resuelto")
    public ResponseEntity<ResponseDto<Object>> marcarResuelto(@PathVariable Long id) {
        return ResponseEntity.ok(syncService.marcarResuelto(id));
    }
}

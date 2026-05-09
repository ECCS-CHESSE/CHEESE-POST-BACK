package eccs.com.modules.controlsync.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@ConditionalOnProperty(name = "sync.scheduler.enabled", havingValue = "true")
public class SyncScheduler {

    @Value("${sync.sql.path:C:/app/sync.sql}")
    private String syncSqlPath;

    @Value("${sync.sql.procesados:C:/app/procesados/}")
    private String procesadosPath;

    @Value("${sync.prod.url:http://localhost:8080/api/sync/import}")
    private String prodUrl;

    @Value("${sucursal.id:0}")
    private Integer sucursalId;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelay = 30000) // cada 30 segundos
    public void verificarYSincronizar() {
        if (!hayInternet()) return;

        File syncFile = new File(syncSqlPath);
        if (!syncFile.exists() || syncFile.length() == 0) return;

        log.info("Internet detectado. Enviando sync.sql a producción...");

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(syncFile));
            body.add("id_sucursal", sucursalId);

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(prodUrl, request, String.class);

            archivarSync(syncFile);
            log.info("Sync completado exitosamente.");
        } catch (Exception e) {
            log.error("Error al sincronizar: {}", e.getMessage());
        }
    }

    private boolean hayInternet() {
        try {
            return InetAddress.getByName("8.8.8.8").isReachable(2000);
        } catch (Exception e) {
            return false;
        }
    }

    private void archivarSync(File syncFile) throws Exception {
        Files.createDirectories(Paths.get(procesadosPath));
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path destino = Paths.get(procesadosPath + "sync_" + timestamp + ".sql");
        Files.move(syncFile.toPath(), destino);
        log.info("Archivo archivado en: {}", destino);
    }
}

package eccs.com.modules.controlventas.folio.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.folio.dto.FolioRequestDto;
import eccs.com.modules.controlventas.folio.query.FolioQuery;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FolioServiceImplTest {

    @Test
    void cancelarFolio_delegaEnLaFuncionDeBaseYDevuelveRespuestaExitosa() {
        FolioQuery folioQuery = mock(FolioQuery.class);
        JsonParserMiddleware jsonParserMiddleware = mock(JsonParserMiddleware.class);
        FolioServiceImpl service = new FolioServiceImpl(folioQuery, jsonParserMiddleware);

        FolioRequestDto request = new FolioRequestDto();
        request.setFolio(123);

        when(folioQuery.cancelarVenta(123)).thenReturn(List.of(Map.of("result", "ok")));
        when(jsonParserMiddleware.parseFunction(anyList())).thenReturn(Map.of("result", "ok"));

        ResponseDto<Object> response = service.cancelarVenta(request);

        assertTrue(response.isSuccess());
        verify(folioQuery).cancelarVenta(123);
        verify(jsonParserMiddleware).parseFunction(anyList());
    }
}

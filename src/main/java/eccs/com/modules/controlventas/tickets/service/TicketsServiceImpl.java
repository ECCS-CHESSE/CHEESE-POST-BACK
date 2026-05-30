package eccs.com.modules.controlventas.tickets.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.tickets.dto.TicketsRequestDto;
import eccs.com.modules.controlventas.tickets.query.TicketsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {

    private final TicketsQuery ticketsQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getTickets(TicketsRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(ticketsQuery.getTickets(request.getId_sucursal()));
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - TICKETS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - TICKETS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

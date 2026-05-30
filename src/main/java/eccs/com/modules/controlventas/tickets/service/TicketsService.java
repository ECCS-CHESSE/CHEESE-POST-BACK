package eccs.com.modules.controlventas.tickets.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.tickets.dto.TicketsRequestDto;

public interface TicketsService {
    ResponseDto<Object> getTickets(TicketsRequestDto request);
}

package eccs.com.modules.controlventas.tickets.controller;

import eccs.com.modules.controlventas.tickets.service.TicketsService;
import eccs.com.modules.controlventas.tickets.dto.TicketsRequestDto;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class TicketsController {

    private final TicketsService ticketsService;

    @PostMapping("/tickets")
    public ResponseEntity<ResponseDto<Object>> getTickets(@Valid @RequestBody TicketsRequestDto request) {
        return ResponseEntity.ok(ticketsService.getTickets(request));
    }
}

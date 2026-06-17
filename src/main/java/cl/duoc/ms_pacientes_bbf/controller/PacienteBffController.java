package cl.duoc.ms_pacientes_bff.controller;

import cl.duoc.ms_pacientes_bff.service.PacienteBffService;
import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/pacientes")
public class PacienteBffController {

    @Autowired
    private PacienteBffService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteBffDto> registrarPacienteBff(@RequestBody PacienteBffDto dto) {
        
        PacienteBffDto pacienteRegistrado = pacienteService.registrarPaciente(dto);
        
        return new ResponseEntity<>(pacienteRegistrado, HttpStatus.CREATED);
    }
}
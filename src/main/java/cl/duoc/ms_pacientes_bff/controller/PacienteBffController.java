package cl.duoc.ms_pacientes_bff.controller;

import cl.duoc.ms_pacientes_bff.service.PacienteBffService;
import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import cl.duoc.ms_pacientes_bff.dto.PacienteConCitasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteBffController {

    @Autowired
    private PacienteBffService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteBffDto> registrarPacienteBff(@RequestBody PacienteBffDto dto) {
        PacienteBffDto pacienteRegistrado = pacienteService.registrarPaciente(dto);
        return new ResponseEntity<>(pacienteRegistrado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteBffDto>> listarPacientes() {
        List<PacienteBffDto> pacientes = pacienteService.obtenerTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/con-citas")
    public ResponseEntity<List<PacienteConCitasDto>> listarPacientesConCitas() {
        List<PacienteConCitasDto> pacientes = pacienteService.obtenerTodosConCitas();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteBffDto> obtenerPaciente(@PathVariable Long id) {
        PacienteBffDto paciente = pacienteService.obtenerPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/con-citas")
    public ResponseEntity<PacienteConCitasDto> obtenerPacienteConCitas(@PathVariable Long id) {
        PacienteConCitasDto paciente = pacienteService.obtenerPorIdConCitas(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteBffDto> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteBffDto dto) {
        PacienteBffDto pacienteActualizado = pacienteService.editarPaciente(id, dto);
        return ResponseEntity.ok(pacienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}

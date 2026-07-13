package cl.duoc.ms_pacientes_bff.client;

import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-pacientes-bs", url = "${ms-pacientes-bs.url:http://localhost:8081/api/pacientes}")
public interface PacienteClient {

    @PostMapping
    PacienteBffDto registrarPaciente(@RequestBody PacienteBffDto dto);

    @GetMapping
    List<PacienteBffDto> obtenerTodos();

    @GetMapping("/{id}")
    PacienteBffDto obtenerPorId(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    PacienteBffDto editarPaciente(@PathVariable("id") Long id, @RequestBody PacienteBffDto datosNuevos);

    @DeleteMapping("/{id}")
    void eliminarPaciente(@PathVariable("id") Long id);
}
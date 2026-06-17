package cl.duoc.ms_pacientes_bff.client;

import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-pacientes-bs", url = "localhost:8081/api/pacientes")
public interface PacienteCliente {

    @PostMapping
    PacienteBffDto registrarPaciente(@RequestBody PacienteBffDto dto);
}
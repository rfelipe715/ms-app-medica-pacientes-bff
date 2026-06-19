package cl.duoc.ms_pacientes_bff.client;

import cl.duoc.ms_pacientes_bff.dto.CitaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "citas-bff", url = "http://localhost:8090/api/v1/citas")
public interface CitasRestClient {

    @GetMapping("/listar")
    List<CitaDto> listarCitas();

}

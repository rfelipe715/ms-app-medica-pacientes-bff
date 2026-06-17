package cl.duoc.ms_pacientes_bff.service;

import cl.duoc.ms_pacientes_bff.client.PacienteClient;
import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteBffService {

    @Autowired
    private PacienteClient pacienteCliente;

    public PacienteBffDto registrarPaciente(PacienteBffDto dto) {
    
        return pacienteClient.registrarPaciente(dto);
    }
}
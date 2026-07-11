package cl.duoc.ms_pacientes_bff.service;

import cl.duoc.ms_pacientes_bff.client.PacienteClient;
import cl.duoc.ms_pacientes_bff.client.CitasRestClient;
import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import cl.duoc.ms_pacientes_bff.dto.PacienteConCitasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteBffService {

    @Autowired
    private PacienteClient pacienteClient;

    @Autowired
    private CitasRestClient citasRestClient;

    public PacienteBffDto registrarPaciente(PacienteBffDto dto) {
        return pacienteClient.registrarPaciente(dto);
    }

    public List<PacienteBffDto> obtenerTodos() {
        return pacienteClient.obtenerTodos();
    }

    public List<PacienteConCitasDto> obtenerTodosConCitas() {
        return pacienteClient.obtenerTodos().stream()
            .map(this::enriquecerPaciente)
            .collect(Collectors.toList());
    }

    public PacienteBffDto obtenerPorId(Long id) {
        return pacienteClient.obtenerPorId(id);
    }

    public PacienteConCitasDto obtenerPorIdConCitas(Long id) {
        PacienteBffDto paciente = pacienteClient.obtenerPorId(id);
        if (paciente != null) {
            return enriquecerPaciente(paciente);
        }
        return null;
    }

    public PacienteBffDto editarPaciente(Long id, PacienteBffDto datosNuevos) {
        return pacienteClient.editarPaciente(id, datosNuevos);
    }

    public void eliminarPaciente(Long id) {
        pacienteClient.eliminarPaciente(id);
    }

    private PacienteConCitasDto enriquecerPaciente(PacienteBffDto pacienteDto) {
        PacienteConCitasDto conCitas = new PacienteConCitasDto();
        conCitas.setId(pacienteDto.getId());
        conCitas.setRun(pacienteDto.getRun());
        conCitas.setNumeroRegistro(pacienteDto.getNumeroRegistro());
        conCitas.setNumeroFichaClinica(pacienteDto.getNumeroFichaClinica());
        conCitas.setNombres(pacienteDto.getNombres());
        conCitas.setApellidos(pacienteDto.getApellidos());
        conCitas.setSexo(pacienteDto.getSexo());
        conCitas.setFechaNacimiento(pacienteDto.getFechaNacimiento());
        conCitas.setDireccion(pacienteDto.getDireccion());
        conCitas.setTelefonoContacto(pacienteDto.getTelefonoContacto());
        
        try {
            conCitas.setCitas(citasRestClient.listarCitas().stream()
                .filter(c -> c.getPacienteId().equals(pacienteDto.getId()))
                .collect(Collectors.toList()));
        } catch (Exception e) {
            // Silenciar error si no encuentra citas
        }
        
        return conCitas;
    }
}

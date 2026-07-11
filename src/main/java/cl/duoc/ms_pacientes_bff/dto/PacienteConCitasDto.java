package cl.duoc.ms_pacientes_bff.dto;

import lombok.*;
import java.util.List;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteConCitasDto {

    private Long id;
    private String run;
    private String numeroRegistro;
    private String numeroFichaClinica;
    private String nombres;
    private String apellidos;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefonoContacto;
    
    // Citas relacionadas
    private List<CitaDto> citas;
}

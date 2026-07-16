package cl.duoc.ms_pacientes_bff.controller;

import cl.duoc.ms_pacientes_bff.service.PacienteBffService;
import cl.duoc.ms_pacientes_bff.dto.PacienteBffDto;
import cl.duoc.ms_pacientes_bff.dto.PacienteConCitasDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes del sistema hospitalario")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SecurityRequirement(name = "bearerAuth")
@OpenAPIDefinition(servers = @Server(url = "${api-gateway.url:http://localhost:8080}"))
public class PacienteBffController {

    @Autowired
    private PacienteBffService pacienteService;

    @Operation(summary = "Registrar un nuevo paciente", description = "Crea un nuevo paciente en el sistema.")
    @ApiResponse(responseCode = "201", description = "Paciente registrado exitosamente")
    @PostMapping
    public ResponseEntity<PacienteBffDto> registrarPacienteBff(@RequestBody PacienteBffDto dto) {
        PacienteBffDto pacienteRegistrado = pacienteService.registrarPaciente(dto);
        return new ResponseEntity<>(pacienteRegistrado, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los pacientes", description = "Retorna la lista completa de pacientes registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<PacienteBffDto>> listarPacientes() {
        List<PacienteBffDto> pacientes = pacienteService.obtenerTodos();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Listar pacientes con sus citas", description = "Retorna los pacientes enriquecidos con las citas asociadas a cada uno.")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes con citas obtenida exitosamente")
    @GetMapping("/con-citas")
    public ResponseEntity<List<PacienteConCitasDto>> listarPacientesConCitas() {
        List<PacienteConCitasDto> pacientes = pacienteService.obtenerTodosConCitas();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Buscar paciente por ID", description = "Retorna los datos de un paciente específico según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteBffDto> obtenerPaciente(@PathVariable Long id) {
        PacienteBffDto paciente = pacienteService.obtenerPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar paciente con sus citas", description = "Retorna un paciente específico enriquecido con las citas asociadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @GetMapping("/{id}/con-citas")
    public ResponseEntity<PacienteConCitasDto> obtenerPacienteConCitas(@PathVariable Long id) {
        PacienteConCitasDto paciente = pacienteService.obtenerPorIdConCitas(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar un paciente existente", description = "Actualiza los datos de un paciente ya registrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PacienteBffDto> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteBffDto dto) {
        PacienteBffDto pacienteActualizado = pacienteService.editarPaciente(id, dto);
        return ResponseEntity.ok(pacienteActualizado);
    }

    @Operation(summary = "Eliminar un paciente", description = "Elimina de forma permanente un paciente del sistema, identificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente eliminado exitosamente, sin contenido de respuesta"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}

package syn.capa.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import syn.capa.apirest.beans.Paciente;
import syn.capa.apirest.beans.PacienteConHistorial;
import syn.capa.apirest.repo.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	
	@Autowired
	private PacienteRepository  pacienteRepository;
	
	@GetMapping("/listar")
	@Operation(description = "Muestra todos los pacientes", summary = "Muestra una lista de pacientes en la base de datos")
	public List<Paciente> listaPacientes() {
		return pacienteRepository.mostrarPacientes();
	}
	
	
	@GetMapping("/{id}")
	@Operation(description = "Encuentra un paciente por ID", summary = "Proporciona un ID para buscar un paciente específico en la base de datos")
	public Paciente obtenerPacientePorId(@PathVariable Integer id) {
		return pacienteRepository.searchById(id);
	}
	
	
	@PostMapping("/registrar")
	@Operation(description = "Crea un nuevo paciente", summary = "Registra un nuevo paciente en la base de datos")
	public String registrarPacientes(@RequestBody Paciente paciente) {
		return pacienteRepository.crearPaciente(paciente);
	}
	
	@PutMapping("/actualizar")
	@Operation(description = "Actualiza datos de un paciente", summary = "Actualiza los datos de un paciente en la base de datos")
	public Paciente actualizarPacientes(@RequestBody Paciente paciente) {
		return pacienteRepository.actualizarPaciente(paciente);
	}
	@DeleteMapping("/{id}")
	@Operation(description = "Elimina un paciente por ID", summary = "Proporciona un ID para eliminar un usuario específico en la base de datos")
	public String eliminarPacientes(@PathVariable Integer id) {
		return pacienteRepository.eliminarPaciente(id);
	}
	
	@GetMapping("/consultarHistorial")
	@Operation(description = "Muestra todos los historiales de un paciente", summary = "Proporciona un ID para buscar un usuario específico en la base de datos")
	public List<PacienteConHistorial> obtenerPacientesConHistorial() {
	    return pacienteRepository.obtenerPacientesConHistorial();
	}
	
}

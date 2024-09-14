
package syn.capa.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.v3.oas.annotations.Operation;
import syn.capa.apirest.beans.Paciente;
import syn.capa.apirest.beans.PacienteConHistorial;
import syn.capa.apirest.repo.PacienteRepository;

@Controller
public class PacienteViewController {
	
	@Autowired
	private PacienteRepository  pacienteRepository;

	
	
	@GetMapping("/paciente/{id}")
	@Operation(description = "Encuentra un paciente por ID", summary = "Proporciona un ID para buscar un paciente específico en la base de datos")
	public String obtenerPacientePorId(@PathVariable Integer id, Model model) {
	    Paciente paciente = pacienteRepository.searchById(id);
	    model.addAttribute("paciente", paciente);
	    return "pacientes";
	}
	
	@GetMapping("/paciente/listar")
	@Operation(description = "Muestra todos los pacientes", summary = "Muestra una lista de pacientes en la base de datos")
	public String listaPacientes(Model model) {
	    List<Paciente> pacientes = pacienteRepository.mostrarPacientes();
	    model.addAttribute("pacientes", pacientes);
	    return "listaPacientes";
	}
	
	@GetMapping("/paciente/consultarHistorial")
	@Operation(description = "Muestra todos los historiales de un paciente", summary = "Proporciona un ID para buscar un usuario específico en la base de datos")
	public String obtenerPacientesConHistorial( Model model) {
	    List<PacienteConHistorial> pacientesConHistorial = pacienteRepository.obtenerPacientesConHistorial();
	    model.addAttribute("pacientesConHistorial", pacientesConHistorial);
	    return "pacienteConHistorial";
	}
	
	
	@DeleteMapping("/paciente/{id}")
	public String eliminarPaciente(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
	    pacienteRepository.eliminarPaciente(id);
	    redirectAttributes.addFlashAttribute("mensaje", "Paciente eliminado con éxito");
	    return "redirect:/paciente/listar";
	}
	
	
}



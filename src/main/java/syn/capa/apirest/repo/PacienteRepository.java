package syn.capa.apirest.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import syn.capa.apirest.beans.Paciente;
import syn.capa.apirest.beans.PacienteConHistorial;
import syn.capa.apirest.service.impl.PacienteServiceImpl;

@Service
public class PacienteRepository {

	@Autowired
	private PacienteServiceImpl pacienteServiceImpl;
	
	public Paciente searchById(Integer id) {
		return pacienteServiceImpl.searchById(id);
	}
	
	public String crearPaciente(Paciente paciente) {
		return pacienteServiceImpl.crearPaciente(paciente);
	}
	
	public List<Paciente> mostrarPacientes() {
		return pacienteServiceImpl.mostrarPacientes();
	}
	
	public Paciente actualizarPaciente(Paciente paciente) {
		return pacienteServiceImpl.actualizarPaciente(paciente);
	}
	
	public String eliminarPaciente(Integer id) {
		return pacienteServiceImpl.eliminarPaciente(id);
	}
	
	public List<PacienteConHistorial> obtenerPacientesConHistorial() {
	    return pacienteServiceImpl.obtenerPacientesConHistorial();
	}
	

}

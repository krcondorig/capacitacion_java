package syn.capa.apirest.service;

import java.util.List;

import syn.capa.apirest.beans.Paciente;
import syn.capa.apirest.beans.PacienteConHistorial;


public interface PacienteService {

	public Paciente searchById(Integer id);
	public String crearPaciente(Paciente paciente);
	public List<Paciente> mostrarPacientes();
	public Paciente actualizarPaciente(Paciente paciente);
	public String eliminarPaciente(Integer id);
	public List<PacienteConHistorial> obtenerPacientesConHistorial();

}

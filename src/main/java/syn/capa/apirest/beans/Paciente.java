package syn.capa.apirest.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paciente {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String genero;
	@JsonIgnore
	private HistorialMedico historialMedico;

}

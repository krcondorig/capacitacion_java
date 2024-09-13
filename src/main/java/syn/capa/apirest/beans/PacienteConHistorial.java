package syn.capa.apirest.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteConHistorial {
	
	private Integer id;
    private String nombre;
    private String apellido;
    private String fecha;
    private String descripcion;
    private String diagnostico;
    private String tratamiento;
}

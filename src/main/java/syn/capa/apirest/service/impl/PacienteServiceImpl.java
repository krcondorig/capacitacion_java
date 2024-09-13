package syn.capa.apirest.service.impl;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import syn.capa.apirest.beans.HistorialMedico;
import syn.capa.apirest.beans.Paciente;
import syn.capa.apirest.beans.PacienteConHistorial;
import syn.capa.apirest.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
    public Paciente searchById(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BUSCAR_PACIENTE_POR_ID(?)}")) 
            {
                cs.setInt(1, id);
                try (ResultSet rs = cs.executeQuery()) 
                {
                    if (rs.next()) 
                    {
                        return mapearPaciente(rs); 
                    } 
                    else 
                    {
                        return null;
                    }
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
                return null;
            }
        });
    }
    
    public Paciente mapearPaciente(ResultSet rs) throws SQLException {
    	Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setApellido(rs.getString("apellido"));
        paciente.setTelefono(rs.getString("telefono"));
		paciente.setGenero(rs.getString("genero"));
        return paciente;
    }

	public String crearPaciente(Paciente paciente) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_CREAR_PACIENTE(?,?,?,?)}")) 
			{
				cs.setString(1, paciente.getNombre());
				cs.setString(2, paciente.getApellido());
				cs.setString(3, paciente.getTelefono());
				cs.setString(4, paciente.getGenero());
				cs.execute();
				
				return "Paciente creado con exito";
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
		});
	}

	public List<Paciente> mostrarPacientes() {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_LISTAR_PACIENTES()}")) {
				cs.execute();
				try (ResultSet rs = cs.getResultSet()) {
					List<Paciente> pacientes = new ArrayList<>();
					while (rs.next()) {
						Paciente paciente = new Paciente();
						paciente.setId(rs.getInt("id"));
						paciente.setNombre(rs.getString("nombre"));
						paciente.setApellido(rs.getString("apellido"));
						paciente.setTelefono(rs.getString("telefono"));
						paciente.setGenero(rs.getString("genero"));
						pacientes.add(paciente);
					}
					return pacientes;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		});
	}

	public Paciente actualizarPaciente(Paciente paciente) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (CallableStatement cs = conn.prepareCall("{call SP_ACTUALIZAR_PACIENTE(?,?,?,?,?)}")) 
			{
				cs.setInt(1, paciente.getId());
				cs.setString(2, paciente.getNombre());
				cs.setString(3, paciente.getApellido());
				cs.setString(4, paciente.getTelefono());
				cs.setString(5, paciente.getGenero());
				cs.execute();
				
				return paciente;
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
		});
	}

	public String eliminarPaciente(Integer id) {
		return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_ELIMINAR_PACIENTE(?)}")) 
            {
                cs.setInt(1, id);
                cs.execute();
				return "Paciente eliminado con exito";

            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
                return null;
            }
        });
		
	}
	
	/*public List<Paciente> obtenerPacientesConHistorial() {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_OBTENER_PACIENTES_CON_HISTORIAL()}")) {
                try (ResultSet rs = cs.executeQuery()) {
                    List<Paciente> pacientes = new ArrayList<>();
                    while (rs.next()) {
                        Paciente paciente = new Paciente();
                        paciente.setId(rs.getInt("id_paciente"));
                        paciente.setNombre(rs.getString("nombre"));
                        paciente.setApellido(rs.getString("apellido"));
                        paciente.setTelefono(rs.getString("telefono"));
                        paciente.setGenero(rs.getString("genero"));

                        HistorialMedico historial = new HistorialMedico();
                        historial.setId(rs.getInt("id_historial"));
                        historial.setIdPaciente(rs.getInt("id_paciente"));
                        historial.setFecha(rs.getString("fecha"));
                        historial.setDescripcion(rs.getString("descripcion"));
                        historial.setDiagnostico(rs.getString("diagnostico"));
						historial.setTratamiento(rs.getString("tratamiento"));

                        paciente.setHistorialMedico(historial);

                        pacientes.add(paciente);
                    }
                    return pacientes;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
    */
	
	public List<PacienteConHistorial> obtenerPacientesConHistorial() {
	    return jdbcTemplate.execute((Connection conn) -> {
	        try (CallableStatement cs = conn.prepareCall("{call SP_OBTENER_PACIENTES_CON_HISTORIAL_MEDICO()}")) {
	            cs.execute();
	            try (ResultSet rs = cs.getResultSet()) {
	                List<PacienteConHistorial> pacientesConHistorial = new ArrayList<>();
	                while (rs.next()) {
	                    PacienteConHistorial pacienteConHistorial = new PacienteConHistorial();
	                    pacienteConHistorial.setId(rs.getInt("id"));
	                    pacienteConHistorial.setNombre(rs.getString("nombre"));
	                    pacienteConHistorial.setApellido(rs.getString("apellido"));
	                    pacienteConHistorial.setFecha(rs.getString("fecha"));
	                    pacienteConHistorial.setDescripcion(rs.getString("descripcion"));
	                    pacienteConHistorial.setDiagnostico(rs.getString("diagnostico"));
	                    pacienteConHistorial.setTratamiento(rs.getString("tratamiento"));
	                    pacientesConHistorial.add(pacienteConHistorial);
	                }
	                return pacientesConHistorial;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    });
	}

}

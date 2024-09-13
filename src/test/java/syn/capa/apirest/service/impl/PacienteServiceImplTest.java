package syn.capa.apirest.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import syn.capa.apirest.beans.Paciente;
import syn.capa.apirest.beans.PacienteConHistorial;

public class PacienteServiceImplTest {

	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@Mock
	private Connection connection;
	
	@Mock
	private CallableStatement callableStatement;
	
	@Mock
	private ResultSet resultSet;
	
	@InjectMocks
	private PacienteServiceImpl pacienteServiceImpl;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testMapearPaciente() throws SQLException {
		// Simula los datos que devolvería el ResultSet
    	when(resultSet.getInt("id")).thenReturn(1);
    	when(resultSet.getString("nombre")).thenReturn("renzo");
    	when(resultSet.getString("apellido")).thenReturn("condori");
    	when(resultSet.getString("telefono")).thenReturn("935379113");
    	when(resultSet.getString("genero")).thenReturn("M");
    	
    	// Llama al método bajo prueba
        Paciente result = pacienteServiceImpl.mapearPaciente(resultSet);
        
        // Verifica que el resultado no sea null
        assertNotNull(result);
        
        // Verifica que los datos mapeados sean los esperados
        assertEquals(1, result.getId());
        assertEquals("renzo", result.getNombre());
        assertEquals("condori", result.getApellido());
        assertEquals("935379113", result.getTelefono());
        assertEquals("M", result.getGenero());
        
        // Verifica que se llamaron los métodos correctos en el ResultSet
        verify(resultSet).getInt("id");
        verify(resultSet).getString("nombre");
        verify(resultSet).getString("apellido");
        verify(resultSet).getString("telefono");
        verify(resultSet).getString("genero");
	}
	
	@Test
	void testSearchById() throws SQLException {
		Integer pacienteId = 1;
		//Paciente mockPaciente = new Paciente();
		
		// Simula los datos que devolvería el ResultSet
        when(resultSet.next()).thenReturn(true); // El usuario existe
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nombre")).thenReturn("renzo");
        when(resultSet.getString("apellido")).thenReturn("condori");
        when(resultSet.getString("telefono")).thenReturn("935379113");
        when(resultSet.getString("genero")).thenReturn("M");
        
        // Configura la simulación del CallableStatement y Connection
        when(connection.prepareCall("{call SP_BUSCAR_PACIENTE_POR_ID(?)}")).thenReturn(callableStatement);
        when(callableStatement.executeQuery()).thenReturn(resultSet);
        
        // Simula la ejecución del jdbcTemplate.execute()
        when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
            var action = (Object) invocation.getArgument(0);
            return ((ConnectionCallback) action).doInConnection(connection);
        });
        
        // Llama al método bajo prueba
        Paciente result = pacienteServiceImpl.searchById(pacienteId);
        
        // Verifica que se hayan llamado los métodos correctos
        verify(callableStatement).setInt(1, pacienteId);
        assertNotNull(result);
        assertEquals("renzo", result.getNombre()); // Asegúrate que sea el nombre esperado
        assertEquals("condori", result.getApellido());
        assertEquals("935379113", result.getTelefono());
        assertEquals("M", result.getGenero());
	}
	
	@Test
	void testCrearPaciente() throws SQLException {
		// Crea un objeto Paciente mock
	    Paciente mockPaciente = new Paciente();
	    mockPaciente.setNombre("Juan");
	    mockPaciente.setApellido("Perez");
	    mockPaciente.setTelefono("123456789");
	    mockPaciente.setGenero("M");
		
	    // Configura la simulación del CallableStatement y Connection
        when(connection.prepareCall("{call SP_CREAR_PACIENTE(?,?,?,?)}")).thenReturn(callableStatement);
        when(callableStatement.execute()).thenReturn(true);
	    
	    // Simula la ejecución del jdbcTemplate.execute()
        when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
            var action = (Object) invocation.getArgument(0);
            return ((ConnectionCallback) action).doInConnection(connection);
        });
        
     // Llama al método bajo prueba
        String result = pacienteServiceImpl.crearPaciente(mockPaciente);
        
     // Verifica que se haya llamado el método correctamente
        verify(callableStatement).setString(1, mockPaciente.getNombre());
        verify(callableStatement).setString(2, mockPaciente.getApellido());
        verify(callableStatement).setString(3, mockPaciente.getTelefono());
        verify(callableStatement).setString(4, mockPaciente.getGenero());
        assertEquals("Paciente creado con exito", result);
	}
	
	
	@Test
	void testMostrarPacientes() throws SQLException {
	    
		// Simula los datos que devolvería el ResultSet
	    when(resultSet.next()).thenReturn(true).thenReturn(false); // Devuelve true para la primera fila y false para indicar que no hay más filas
	    when(resultSet.getInt("id")).thenReturn(1);
	    when(resultSet.getString("nombre")).thenReturn("Juan");
	    when(resultSet.getString("apellido")).thenReturn("Perez");
	    when(resultSet.getString("telefono")).thenReturn("123456789");
	    when(resultSet.getString("genero")).thenReturn("M");
		
		// Mockea el CallableStatement y Connection
	    when(connection.prepareCall("{call SP_LISTAR_PACIENTES()}")).thenReturn(callableStatement);
	    when(callableStatement.execute()).thenReturn(true);

	    // Mockea el ResultSet
	    when(callableStatement.getResultSet()).thenReturn(resultSet);

	    // Simula la ejecución del jdbcTemplate.execute()
	    when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
	        var action = (Object) invocation.getArgument(0);
	        return ((ConnectionCallback) action).doInConnection(connection);
	    });

	    // Llama al método bajo prueba
	    List<Paciente> result = pacienteServiceImpl.mostrarPacientes();

	    // Verifica que se haya llamado el método correctamente
	    verify(callableStatement).execute();
	    assertNotNull(result);
	    assertEquals(1, result.size());

	    // Verifica que los pacientes sean los esperados
	    Paciente paciente = result.get(0);
	    assertEquals(1, paciente.getId());
	    assertEquals("Juan", paciente.getNombre());
	    assertEquals("Perez", paciente.getApellido());
	    assertEquals("123456789", paciente.getTelefono());
	    assertEquals("M", paciente.getGenero());
	}
	
	@Test
	void testActualizarPaciente() throws SQLException {
	    // Crea un objeto Paciente mock
	    Paciente paciente = new Paciente();
	    paciente.setId(1);
	    paciente.setNombre("Juan");
	    paciente.setApellido("Perez");
	    paciente.setTelefono("123456789");
	    paciente.setGenero("M");

	    // Mockea el CallableStatement y Connection
	    when(connection.prepareCall("{call SP_ACTUALIZAR_PACIENTE(?,?,?,?,?)}")).thenReturn(callableStatement);
	    when(callableStatement.execute()).thenReturn(true);

	    // Simula la ejecución del jdbcTemplate.execute()
	    when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
	        var action = (Object) invocation.getArgument(0);
	        return ((ConnectionCallback) action).doInConnection(connection);
	    });

	    // Llama al método bajo prueba
	    Paciente result = pacienteServiceImpl.actualizarPaciente(paciente);

	    // Verifica que se haya llamado el método correctamente
	    verify(callableStatement).setInt(1, paciente.getId());
	    verify(callableStatement).setString(2, paciente.getNombre());
	    verify(callableStatement).setString(3, paciente.getApellido());
	    verify(callableStatement).setString(4, paciente.getTelefono());
	    verify(callableStatement).setString(5, paciente.getGenero());
	    assertEquals(paciente, result);
	}
	
	@Test
	void testEliminarPaciente() throws SQLException {
	    // Mockea el CallableStatement y Connection
	    when(connection.prepareCall("{call SP_ELIMINAR_PACIENTE(?)}")).thenReturn(callableStatement);
	    when(callableStatement.execute()).thenReturn(true);

	    // Simula la ejecución del jdbcTemplate.execute()
	    when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
	        var action = (Object) invocation.getArgument(0);
	        return ((ConnectionCallback) action).doInConnection(connection);
	    });

	    // Llama al método bajo prueba
	    String result = pacienteServiceImpl.eliminarPaciente(1);

	    // Verifica que se haya llamado el método correctamente
	    verify(callableStatement).setInt(1, 1);
	    assertEquals("Paciente eliminado con exito", result);
	}
	
	@Test
	void testObtenerPacientesConHistorial() throws SQLException {
	    // Mockea los objetos
	    when(connection.prepareCall("{call SP_OBTENER_PACIENTES_CON_HISTORIAL_MEDICO()}")).thenReturn(callableStatement);
	    when(callableStatement.execute()).thenReturn(true);
	    when(callableStatement.getResultSet()).thenReturn(resultSet);

	    // Simula los datos que devolvería el resultSet
	    when(resultSet.next()).thenReturn(true).thenReturn(false); // Devuelve true para la primera fila y false para indicar que no hay más filas
	    when(resultSet.getInt("id")).thenReturn(1);
	    when(resultSet.getString("nombre")).thenReturn("Juan");
	    when(resultSet.getString("apellido")).thenReturn("Perez");
	    when(resultSet.getString("fecha")).thenReturn("2022-01-01");
	    when(resultSet.getString("descripcion")).thenReturn("Consulta inicial");
	    when(resultSet.getString("diagnostico")).thenReturn("Gripe");
	    when(resultSet.getString("tratamiento")).thenReturn("Medicación");

	    // Simula la ejecución del jdbcTemplate.execute()
	    when(jdbcTemplate.execute(any(ConnectionCallback.class))).thenAnswer(invocation -> {
	        var action = (Object) invocation.getArgument(0);
	        return ((ConnectionCallback) action).doInConnection(connection);
	    });

	    // Llama al método bajo prueba
	    List<PacienteConHistorial> result = pacienteServiceImpl.obtenerPacientesConHistorial();

	    // Verifica que se haya llamado correctamente
	    verify(callableStatement).execute();
	    assertNotNull(result);
	    assertEquals(1, result.size());

	    // Verifica que los pacientes sean los esperados
	    PacienteConHistorial pacienteConHistorial = result.get(0);
	    assertEquals(1, pacienteConHistorial.getId());
	    assertEquals("Juan", pacienteConHistorial.getNombre());
	    assertEquals("Perez", pacienteConHistorial.getApellido());
	    assertEquals("2022-01-01", pacienteConHistorial.getFecha());
	    assertEquals("Consulta inicial", pacienteConHistorial.getDescripcion());
	    assertEquals("Gripe", pacienteConHistorial.getDiagnostico());
	    assertEquals("Medicación", pacienteConHistorial.getTratamiento());
	}
}

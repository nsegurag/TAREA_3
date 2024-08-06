package TAREA_3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                estudiante.setGenero(rs.getString("genero"));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setCorreoElectronico(rs.getString("correo_electronico"));
                estudiante.setGrado(rs.getString("grado"));
                estudiante.setFechaInscripcion(rs.getString("fecha_inscripcion"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    public boolean crearEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, genero, direccion, telefono, correo_electronico, grado, fecha_inscripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getFechaNacimiento());
            pstmt.setString(4, estudiante.getGenero());
            pstmt.setString(5, estudiante.getDireccion());
            pstmt.setString(6, estudiante.getTelefono());
            pstmt.setString(7, estudiante.getCorreoElectronico());
            pstmt.setString(8, estudiante.getGrado());
            pstmt.setString(9, estudiante.getFechaInscripcion());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarEstudiante(int id, String nombre, String apellido, String fechaNacimiento, String genero, String direccion, String telefono, String correoElectronico, String grado, String fechaInscripcion) {
        Connection conn = null;
		try {
			conn = ConexionDB.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        if (conn != null) {
            try {
                String sql = "UPDATE estudiantes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, genero = ?, direccion = ?, telefono = ?, correo_electronico = ?, grado = ?, fecha_inscripcion = ? WHERE id = ?";
                
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                pstmt.setString(1, nombre);
                pstmt.setString(2, apellido);
                pstmt.setString(3, fechaNacimiento);
                pstmt.setString(4, genero);
                pstmt.setString(5, direccion);
                pstmt.setString(6, telefono);
                pstmt.setString(7, correoElectronico);
                pstmt.setString(8, grado);
                pstmt.setString(9, fechaInscripcion);
                pstmt.setInt(10, id);
                
                int rowsUpdated = pstmt.executeUpdate();
                
                pstmt.close();
                conn.close();
                
                return rowsUpdated > 0;

            } catch (SQLException e) {
                System.out.println("Error al actualizar estudiante: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
            return false;
        }
    }

    public boolean eliminarEstudiante(int id) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

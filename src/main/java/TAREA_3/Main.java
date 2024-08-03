package TAREA_3;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        // Crear un nuevo estudiante
        Estudiante nuevoEstudiante = new Estudiante();
        nuevoEstudiante.setNombre("Keny");
        nuevoEstudiante.setApellido("Brol");
        nuevoEstudiante.setFechaNacimiento(new Date());
        nuevoEstudiante.setGenero("F");
        nuevoEstudiante.setDireccion("BRASIL");
        nuevoEstudiante.setTelefono("45632897");
        nuevoEstudiante.setCorreoElectronico("keny@miumg.edu.gt");
        nuevoEstudiante.setGrado("10mo");
        nuevoEstudiante.setFechaInscripcion(new Date());

        // Intentar crear un estudiante
        try {
            boolean creado = estudianteDAO.crearEstudiante(nuevoEstudiante);
            System.out.println("Estudiante creado: " + creado);
        } catch (Exception e) {
            System.err.println("Error al crear el estudiante: " + e.getMessage());
            e.printStackTrace();
        }

        // Obtener y mostrar todos los estudiantes
        List<Estudiante> estudiantes = null;
        try {
            estudiantes = estudianteDAO.obtenerEstudiantes();
            if (estudiantes.isEmpty()) {
                System.out.println("No hay estudiantes en la base de datos.");
            } else {
                for (Estudiante e : estudiantes) {
                    System.out.println("ID: " + e.getId());
                    System.out.println("Nombre: " + e.getNombre());
                    System.out.println("Apellido: " + e.getApellido());
                    System.out.println("Fecha de Nacimiento: " + e.getFechaNacimiento());
                    System.out.println("Género: " + e.getGenero());
                    System.out.println("Dirección: " + e.getDireccion());
                    System.out.println("Teléfono: " + e.getTelefono());
                    System.out.println("Correo Electrónico: " + e.getCorreoElectronico());
                    System.out.println("Grado: " + e.getGrado());
                    System.out.println("Fecha de Inscripción: " + e.getFechaInscripcion());
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener estudiantes: " + e.getMessage());
            e.printStackTrace();
        }

        // Actualizar el estudiante
        if (!estudiantes.isEmpty()) {
            Estudiante estudianteExistente = estudiantes.get(0);
            estudianteExistente.setNombre("Actualizado");
            try {
                boolean actualizado = estudianteDAO.actualizarEstudiante(estudianteExistente);
                System.out.println("Estudiante actualizado: " + actualizado);
            } catch (Exception e) {
                System.err.println("Error al actualizar el estudiante: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Eliminar el estudiante
        if (!estudiantes.isEmpty()) {
            int idEliminar = estudiantes.get(0).getId();
            try {
                boolean eliminado = estudianteDAO.eliminarEstudiante(idEliminar);
                System.out.println("Estudiante eliminado: " + eliminado);
            } catch (Exception e) {
                System.err.println("Error al eliminar el estudiante: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

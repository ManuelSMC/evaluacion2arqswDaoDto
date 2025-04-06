package org.ejemplo.idgs13_2_01.evaluacion.lizard2.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.UsuarioModel;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Maestro;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Grupo;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Materia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Asistencia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Calificacion;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Alumno;

@WebServlet(name = "MaestroController", urlPatterns = {"/maestros", "/gruposMaestro", "/materiasGrupoMaestro", "/asignarGrupoMateria", "/asistenciasMateriaMaestro",
    "/registrarAsistencia", "/calificacionesMateriaMaestro", "/registrarCalificacion", "/registrarMaestro"})
public class MaestroController extends HttpServlet {

    String ruta = "";

    List<Materia> materias;
    List<Asistencia> asistencias;
    List<Calificacion> calificaciones;
    List<Alumno> alumnos;

    Asistencia asistencia = new Asistencia();
    Calificacion calificacion = new Calificacion();
    Alumno alumno = new Alumno();
    Maestro maestro = new Maestro();
    Materia materia = new Materia();
    Grupo grupo = new Grupo();
    UsuarioModel usuario = new UsuarioModel();

    String nombreMaestro;
    int idMateria;
    String intIdMaestro;
    int idMaestro;
    String nombreMateria;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        ruta = request.getServletPath();

        switch (ruta) {
            case "/maestros":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                List<Maestro> maestros = maestro.getMaestros();
                request.setAttribute("maestros", maestros);

                request.getRequestDispatcher("/WEB-INF/maestros/maestros.jsp").forward(request, response);
                break;
            case "/gruposMaestro":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }

                if (usuario.getRol().equals("Maestro")) {
                    //System.out.println("IdUsuario: " + usuario.getId());
                    idMaestro = maestro.getIdMaestroPorUsuario(usuario.getId());
                    //System.out.println("IdMaestro: " + idMaestro);
                } else {
                    intIdMaestro = request.getParameter("maestroId");
                    idMaestro = Integer.parseInt(intIdMaestro);
                }

                request.setAttribute("idMaestro", idMaestro);

                nombreMaestro = maestro.getMaestro(idMaestro);
                request.setAttribute("nombreMaestro", nombreMaestro);

                List<Grupo> grupos = maestro.obtenerGrupos(idMaestro);
                request.setAttribute("grupos", grupos);

                // datos para los inserts del modal
                materias = materia.getMaterias();
                request.setAttribute("materias", materias);

                List<Grupo> gruposTotales = grupo.getGrupos();
                request.setAttribute("gruposTotales", gruposTotales);

                request.getRequestDispatcher("/WEB-INF/maestros/gruposMaestro.jsp").forward(request, response);
                break;

            case "/materiasGrupoMaestro":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                // System.out.println("Entre al doGet de materiasGrupoMaestro");
                intIdMaestro = request.getParameter("idMaestro");
                request.setAttribute("idMaestro", intIdMaestro);
                nombreMaestro = maestro.getMaestro(idMaestro);
                request.setAttribute("nombreMaestro", nombreMaestro);

                idMaestro = Integer.parseInt(intIdMaestro);
                //System.out.println("idMaestro: " + idMaestro);

                String intIdGrupo = request.getParameter("idGrupo");
                int idGrupo = Integer.parseInt(intIdGrupo);
                //System.out.println("idGrupo: " + idGrupo);

                String nombreGrupo = grupo.getGrupo(idGrupo);
                request.setAttribute("nombreGrupo", nombreGrupo);
                request.setAttribute("idGrupo", idGrupo);

                materias = maestro.obtenerMateriasGrupoMaestro(idMaestro, idGrupo);
                request.setAttribute("materias", materias);

                request.getRequestDispatcher("/WEB-INF/materias/materiasGrupoMaestro.jsp").forward(request, response);
                break;

            case "/asistenciasMateriaMaestro":
                idMateria = Integer.parseInt(request.getParameter("idMateria"));
                idMaestro = Integer.parseInt(request.getParameter("idMaestro"));
                idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
                request.setAttribute("idGrupo", idGrupo);
                request.setAttribute("idMaestro", idMaestro);
                request.setAttribute("idMateria", idMateria);

                List<Asistencia> asistencias = asistencia.obtenerAsistenciasPorMateriaGrupo(idMateria, idGrupo, idMaestro);
                request.setAttribute("asistencias", asistencias);

                nombreMateria = materia.findById(idMateria);
                request.setAttribute("nombre_materia", nombreMateria);

                alumnos = alumno.getAlumnosGrupoMaestro(idGrupo, idMaestro, idMateria);
                request.setAttribute("alumnos", alumnos);

                request.getRequestDispatcher("/WEB-INF/asistencias/asistenciasMateriaMaestro.jsp").forward(request, response);
                break;

            case "/calificacionesMateriaMaestro":
                idMateria = Integer.parseInt(request.getParameter("idMateria"));
                idMaestro = Integer.parseInt(request.getParameter("idMaestro"));
                idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
                request.setAttribute("idGrupo", idGrupo);
                request.setAttribute("idMaestro", idMaestro);
                request.setAttribute("idMateria", idMateria);

                calificaciones = calificacion.obtenerCalificacionesPorMateriaGrupo(idMateria, idGrupo, idMaestro);
                request.setAttribute("calificaciones", calificaciones);

                nombreMateria = materia.findById(idMateria);
                request.setAttribute("nombre_materia", nombreMateria);

                alumnos = alumno.getAlumnosGrupoMaestro(idGrupo, idMaestro, idMateria);
                request.setAttribute("alumnos", alumnos);

                request.getRequestDispatcher("/WEB-INF/calificaciones/calificacionesMateriaMaestro.jsp").forward(request, response);
                break;

            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/asignarGrupoMateria".equals(ruta)) {
            // Obtener parámetros del formulario
            int idMaestro = Integer.parseInt(request.getParameter("idMaestro"));
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            boolean asignado = maestro.asignarGrupoMateriaMaestro(idMaestro, idMateria, idGrupo);
            if (asignado) {
                response.sendRedirect("gruposMaestro?maestroId=" + idMaestro + "&msg=success");
            } else {
                response.sendRedirect("gruposMaestro?maestroId=" + idMaestro + "&msg=exists");
            }

        } else if ("/registrarAsistencia".equals(ruta)) {
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            int idMaestro = Integer.parseInt(request.getParameter("idMaestro"));
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            String fecha = request.getParameter("fecha");

            String[] alumnosSeleccionados = request.getParameterValues("alumnosSeleccionados");

            if (alumnosSeleccionados != null) {
                for (String idAlumnoStr : alumnosSeleccionados) {
                    int idAlumno = Integer.parseInt(idAlumnoStr);
                    String estado = request.getParameter("estado_" + idAlumno);

                    Asistencia asistencia = new Asistencia();
                    boolean registroExitoso = asistencia.registrarAsistencia(idAlumno, idGrupo, idMateria, idMaestro, fecha, estado);

                    if (!registroExitoso) {
                        request.setAttribute("error", "Ocurrió un error al registrar la asistencia.");
                        request.getRequestDispatcher("/WEB-INF/asistencias/asistenciasMateriaMaestro.jsp").forward(request, response);
                        return;
                    }
                }
            }

            response.sendRedirect("asistenciasMateriaMaestro?idMateria=" + idMateria + "&idGrupo=" + idGrupo + "&idMaestro=" + idMaestro + "&msg=successRegister");
            
        } else if ("/registrarCalificacion".equals(ruta)) {
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            int idMaestro = Integer.parseInt(request.getParameter("idMaestro"));
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
            double calificacion = Double.parseDouble(request.getParameter("calificacion"));

            Calificacion calif = new Calificacion();
            calif.registrarCalificacion(idAlumno, idGrupo, idMaestro, idMateria, calificacion);

            response.sendRedirect("calificacionesMateriaMaestro?idMateria=" + idMateria + "&idGrupo=" + idGrupo + "&idMaestro=" + idMaestro + "&msg=success");
            
        }else if("/registrarMaestro".equals(ruta)){
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            String rol = request.getParameter("rol");

            usuario = usuario.addUsuario(nombre, correo, contrasena, rol);
            maestro.addMaestro(usuario.getId());
            
            response.sendRedirect("maestros");
        }

        
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de maestros";
    }
}

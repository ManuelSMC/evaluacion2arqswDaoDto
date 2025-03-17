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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Grupo;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Horario;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Materia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Calificacion;


@WebServlet(name = "GrupoController", urlPatterns = {"/grupos", "/horarioGrupo", "/asignarHorario", "/reporteCalificaciones"})
public class GrupoController extends HttpServlet {
    Grupo grupo = new Grupo();
    Horario horario = new Horario();
    Materia materia = new Materia();
    Calificacion calificacion = new Calificacion();
    String intIdGrupo;
    int idGrupo;
    String nombreGrupo;
    
    
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/grupos":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                List<Grupo> grupos = grupo.getGrupos();
                
                request.setAttribute("grupos", grupos);
                request.getRequestDispatcher("/WEB-INF/grupos/grupos.jsp").forward(request, response);
                break;
                
            case "/horarioGrupo":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                intIdGrupo = request.getParameter("idGrupo");
                idGrupo = Integer.parseInt(intIdGrupo);
                request.setAttribute("idGrupo", idGrupo);
                
                nombreGrupo = grupo.getGrupo(idGrupo);
                request.setAttribute("nombreGrupo", nombreGrupo);
                
                List<Horario> horarioGrupo = horario.getHorarioGrupo(idGrupo);
                
                // datos para los inserts del modal
                List<Materia> materias = materia.getMaterias();
                request.setAttribute("materias", materias);

                List<Grupo> gruposTotales = grupo.getGrupos();
                request.setAttribute("gruposTotales", gruposTotales);
                
                request.setAttribute("horarioGrupo", horarioGrupo);
                request.getRequestDispatcher("/WEB-INF/horarios/horarioGrupo.jsp").forward(request, response);
                break;
                
            case "/reporteCalificaciones":
                
                intIdGrupo = request.getParameter("idGrupo");
                idGrupo = Integer.parseInt(intIdGrupo);
                request.setAttribute("idGrupo", idGrupo);
                
                nombreGrupo = grupo.getGrupo(idGrupo);
                request.setAttribute("nombreGrupo", nombreGrupo);
                
                List<Calificacion> calificaciones = calificacion.getCalificacionGrupo(idGrupo);
                request.setAttribute("calificaciones", calificaciones);
                request.getRequestDispatcher("/WEB-INF/reportes/reporteCalificacionesGrupo.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/asignarHorario".equals(ruta)) {
            // Obtener parámetros del formulario
            System.out.println("entró a asignarHorario");
            System.out.println("idGrupo: " + request.getParameter("idGrupo"));
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            System.out.println("idMateria: " + request.getParameter("idMateria"));
            int idMateria1 = Integer.parseInt(request.getParameter("idMateria"));
            materia = materia.obtenerMateriaPorId(idMateria1);
            int idMateria = materia.getId();
            String dia = request.getParameter("dia");
            String hora_inicio = request.getParameter("horaInicio");
            String hora_fin = request.getParameter("horaFin");

            boolean asignado = horario.addHorario(idGrupo, idMateria, dia, hora_inicio, hora_fin);
            
            response.sendRedirect("horarioGrupo?idGrupo=" + idGrupo + "&msg=success");
            
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de grupos";
    }
}

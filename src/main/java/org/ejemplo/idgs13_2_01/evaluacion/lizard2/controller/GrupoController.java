package org.ejemplo.idgs13_2_01.evaluacion.lizard2.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.UsuarioModel;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.UsuarioModelDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Grupo;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.GrupoDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Horario;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.HorarioDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Materia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.MateriaDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Calificacion;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.CalificacionDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Carrera;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.CarreraDao;


@WebServlet(name = "GrupoController", urlPatterns = {"/grupos", "/horarioGrupo", "/asignarHorario", "/reporteCalificaciones", "/registrarGrupo"})
public class GrupoController extends HttpServlet {
    Grupo grupo = new Grupo();
    GrupoDao grupoDao = new GrupoDao();
    Horario horario = new Horario();
    HorarioDao horarioDao = new HorarioDao();
    Materia materia = new Materia();
    MateriaDao materiaDao = new MateriaDao();
    Calificacion calificacion = new Calificacion();
    CalificacionDao calificacionDao = new CalificacionDao();
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
                
                if (usuario.getRol().equals("Servicios Escolares")) {
                    
                    Carrera carrera = new Carrera();
                    CarreraDao carreraDao = new CarreraDao();
                    List<Carrera> carreras;
                    
                    carreras = carreraDao.getCarreras();
                    request.setAttribute("carreras", carreras);
                }
                
                List<Grupo> grupos = grupoDao.obtenerGrupos();
                
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
                
                nombreGrupo = grupoDao.getGrupo(idGrupo);
                request.setAttribute("nombreGrupo", nombreGrupo);
                
                List<Horario> horarioGrupo = horarioDao.getHorarioGrupo(idGrupo);
                
                List<Materia> materias = materiaDao.getMaterias();
                request.setAttribute("materias", materias);

                List<Grupo> gruposTotales = grupoDao.getGrupos();
                request.setAttribute("gruposTotales", gruposTotales);
                
                request.setAttribute("horarioGrupo", horarioGrupo);
                request.getRequestDispatcher("/WEB-INF/horarios/horarioGrupo.jsp").forward(request, response);
                break;
                
            case "/reporteCalificaciones":
                
                intIdGrupo = request.getParameter("idGrupo");
                idGrupo = Integer.parseInt(intIdGrupo);
                request.setAttribute("idGrupo", idGrupo);
                
                nombreGrupo = grupoDao.getGrupo(idGrupo);
                request.setAttribute("nombreGrupo", nombreGrupo);
                
                List<Calificacion> calificaciones = calificacionDao.getCalificacionGrupo(idGrupo);
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
            System.out.println("entró a asignarHorario");
            System.out.println("idGrupo: " + request.getParameter("idGrupo"));
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            System.out.println("idMateria: " + request.getParameter("idMateria"));
            int idMateria1 = Integer.parseInt(request.getParameter("idMateria"));
            materia = materiaDao.obtenerMateriaPorId(idMateria1);
            int idMateria = materia.getId();
            String dia = request.getParameter("dia");
            String hora_inicio = request.getParameter("horaInicio");
            String hora_fin = request.getParameter("horaFin");

            boolean asignado = horarioDao.addHorario(idGrupo, idMateria, dia, hora_inicio, hora_fin);
            
            response.sendRedirect("horarioGrupo?idGrupo=" + idGrupo + "&msg=success");
            
            
        }else if ("/registrarGrupo".equals(ruta)) {
            int idCarrera = Integer.parseInt(request.getParameter("idCarrera"));
            String nombreGrupo = request.getParameter("nombreGrupo");
            
            
            grupoDao.addGrupo(idCarrera, nombreGrupo);
            
            response.sendRedirect("grupos?msg=success");
            
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de grupos";
    }
}

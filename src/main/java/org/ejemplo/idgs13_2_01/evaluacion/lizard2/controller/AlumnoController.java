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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Alumno;

@WebServlet(name = "AlumnoController", urlPatterns = {"/alumnos", "/registrarAlumno"})
public class AlumnoController extends HttpServlet {
    Alumno alumnoModel = new Alumno();
    UsuarioModel alumno = new UsuarioModel();
    List<UsuarioModel> alumnos;
    
    Grupo grupo = new Grupo();
    List<Grupo> grupos;
    
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/alumnos":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                alumnos = alumno.getUsuarios("Alumno");
                request.setAttribute("alumnos", alumnos);
                
                grupos = grupo.getGrupos();
                request.setAttribute("grupos", grupos);
                
                request.getRequestDispatcher("/WEB-INF/alumnos/alumnos.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/registrarAlumno".equals(ruta)) {
            
            String nombreAlumno = request.getParameter("nombreAlumno");
            String correoAlumno = request.getParameter("correoAlumno");
            String contrasenaAlumno = request.getParameter("contrasenaAlumno");
            String intIdGrupo = request.getParameter("idGrupo");
            int idGrupo = Integer.parseInt(intIdGrupo);
            
                   //usuario
            alumno = alumno.addUsuario(nombreAlumno, correoAlumno, contrasenaAlumno, "Alumno");
            alumnoModel.addAlumno(alumno.getId(),idGrupo);
            
            response.sendRedirect("alumnos?msg=success");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de carreras";
    }
}

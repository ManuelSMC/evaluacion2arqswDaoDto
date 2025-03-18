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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Padre;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Alumno;

@WebServlet(name = "PadresController", urlPatterns = {"/padres", "/registrarPadre"})
public class PadresController extends HttpServlet {
    Alumno alumno = new Alumno();
    Padre padreModel = new Padre();
    List<UsuarioModel> padres;
    List<UsuarioModel> alumnos;
    
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        UsuarioModel usuarioModel = new UsuarioModel();
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/padres":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                padres = usuarioModel.getUsuarios("Padre");
                request.setAttribute("padres", padres);
                
                alumnos = usuarioModel.getUsuariosAlumnos("Alumno");
                request.setAttribute("alumnos", alumnos);
                
                
                
                request.getRequestDispatcher("/WEB-INF/padres/padres.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();
        
        UsuarioModel usuarioModel = new UsuarioModel();
        if ("/registrarPadre".equals(ruta)) {
            
            String nombrePadre = request.getParameter("nombrePadre");
            System.out.println("NombrePadre: " + nombrePadre);
            String correoPadre = request.getParameter("correoPadre");
            System.out.println("correoPadre: " + correoPadre);
            String contrasenaPadre = request.getParameter("contrasenaPadre");
            System.out.println("contrasenaPadre: " + contrasenaPadre);
            String intHijoPadre = request.getParameter("hijoPadre");
            int IdUsuarioHijo = Integer.parseInt(intHijoPadre);
            System.out.println("IdUsuarioHijo: " + IdUsuarioHijo);

            alumno = alumno.getById(IdUsuarioHijo);
            System.out.println("alumnoId: " + alumno.getId());
            
            //usuario
            usuarioModel = usuarioModel.addUsuario(nombrePadre, correoPadre, contrasenaPadre, "Padre");
            System.out.println("idUsuario: " + usuarioModel.getId());
                                              //usuario
            padreModel = padreModel.addPadre(usuarioModel.getId());
            padreModel.addPadreAlumno(padreModel.getId(), alumno.getId());
            response.sendRedirect("padres?msg=success");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de carreras";
    }
}

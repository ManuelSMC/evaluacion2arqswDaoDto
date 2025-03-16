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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Alumno;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Calificacion;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Asistencia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Materia;

@WebServlet(name = "MaestroController", urlPatterns = {"/maestros"})
public class MaestroController extends HttpServlet {
    UsuarioModel usuarioModel = new UsuarioModel();
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        Alumno alumno = new Alumno();
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/maestros":
                String error = request.getParameter("error");
                request.setAttribute("error", error);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/maestros".equals(ruta)) {
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de usuarios y materias.";
    }
}

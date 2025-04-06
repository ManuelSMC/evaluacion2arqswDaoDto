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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Carrera;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.CarreraDao;

@WebServlet(name = "CarreraController", urlPatterns = {"/carreras", "/registrarCarrera"})
public class CarreraController extends HttpServlet {
    Carrera carrera = new Carrera();
    CarreraDao carreraDao = new CarreraDao();
    List<Carrera> carreras;
    
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/carreras":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                carreras = carreraDao.getCarreras();
                
                request.setAttribute("carreras", carreras);
                request.getRequestDispatcher("/WEB-INF/carreras/carreras.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/registrarCarrera".equals(ruta)) {
            String nombreCarrera = request.getParameter("nombre");
            
            carreraDao.addCarrera(nombreCarrera);

            response.sendRedirect("carreras?msg=success");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de carreras";
    }
}

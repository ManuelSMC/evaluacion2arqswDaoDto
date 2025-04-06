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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Materia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.MateriaDao;

@WebServlet(name = "MateriaController", urlPatterns = {"/materias", "/registrarMateria"})
public class MateriaController extends HttpServlet {
    Materia materia = new Materia();
    MateriaDao materiaDao = new MateriaDao();
    List<Materia> materias;
    
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/materias":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                materias = materiaDao.getMaterias();
                
                request.setAttribute("materias", materias);
                request.getRequestDispatcher("/WEB-INF/materias/materias.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/registrarMateria".equals(ruta)) {
            String nombreMateria = request.getParameter("nombreMateria");
            
            materiaDao.addMateria(nombreMateria);

            response.sendRedirect("materias?msg=success");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de materias";
    }
}

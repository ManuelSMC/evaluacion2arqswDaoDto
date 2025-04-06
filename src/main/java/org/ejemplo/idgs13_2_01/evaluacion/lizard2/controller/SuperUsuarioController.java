package org.ejemplo.idgs13_2_01.evaluacion.lizard2.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.UsuarioModel;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.UsuarioModelDao;

@WebServlet(name = "SuperUsuarioController", urlPatterns = {"/tipoUsuario", "/registrarUsuario"})
public class SuperUsuarioController extends HttpServlet {
    
    UsuarioModel usuario = new UsuarioModel();
    UsuarioModelDao usuarioDao = new UsuarioModelDao();
    List<UsuarioModel> usuarios;
    
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/tipoUsuario":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                String rolUsuario = request.getParameter("rol");
                request.setAttribute("rol", rolUsuario);
                usuarios = usuarioDao.getUsuarios(rolUsuario);
                
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/WEB-INF/usuarios/usuarios.jsp").forward(request, response);
                break;
            
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/registrarUsuario".equals(ruta)) {
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            String rol = request.getParameter("rol");
            
            usuarioDao.addUsuario(nombre, correo, contrasena, rol);

            response.sendRedirect("tipoUsuario?rol=" + URLEncoder.encode(rol, "UTF-8"));
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de carreras";
    }
}

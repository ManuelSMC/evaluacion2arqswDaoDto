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

@WebServlet(name = "MaestroController", urlPatterns = {"/maestros", "/gruposMaestro", "/materiasGrupoMaestro", "/asignarGrupoMateria"})
public class MaestroController extends HttpServlet {
    String ruta = "";
    Maestro maestro = new Maestro();
    Materia materia = new Materia();
    Grupo grupo = new Grupo();
    String nombreMaestro;
    List<Materia> materias;

    String intIdMaestro;
    int idMaestro;
    
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
                
                intIdMaestro = request.getParameter("maestroId");
                idMaestro = Integer.parseInt(intIdMaestro);
                request.setAttribute("idMaestro", idMaestro);
                
                nombreMaestro = maestro.getMaestro(idMaestro);
                request.setAttribute("nombreMaestro", nombreMaestro);
                
                List<Grupo> grupos = maestro.obtenerGrupos(idMaestro);
                request.setAttribute("grupos", grupos);
                
                // Datos para los inserts del modal
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
                
                materias = maestro.obtenerMateriasGrupoMaestro(idMaestro, idGrupo);
                request.setAttribute("materias", materias);
                
                request.getRequestDispatcher("/WEB-INF/materias/materiasGrupoMaestro.jsp").forward(request, response);
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
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de maestros";
    }
}

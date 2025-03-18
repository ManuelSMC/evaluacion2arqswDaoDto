<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Panel Principal</title>
        
    </head>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="container text-center mt-5">
            <h1 class="mb-4">- ${usuario.rol} -</h1>
            <h1 class="mb-4">Bienvenid@, <span class="text-primary">${usuario.nombre}</span></h1>
            <p class="lead">¿A dónde quieres ir hoy?</p>

            <div class="row justify-content-center mt-4">

                <c:if test="${usuario.rol == 'Coordinador'}">
                    <div class="col-md-3">
                        <a href="maestros" class="btn btn-primary btn-lg w-100">Maestros</a>
                    </div>
                    <div class="col-md-3">
                        <a href="grupos" class="btn btn-primary btn-lg w-100">Grupos</a>
                    </div>
                    <div class="col-md-3">
                        <a href="materias" class="btn btn-primary btn-lg w-100">Materias</a>
                    </div>
                </c:if>

                <c:if test="${usuario.rol == 'Recursos Humanos'}">
                    <div class="col-md-3">
                        <a href="maestros" class="btn btn-primary btn-lg w-100">Maestros</a>
                    </div>
                    <div class="col-md-3">
                        <a href="carreras" class="btn btn-primary btn-lg w-100">Carreras</a>
                    </div>
                </c:if>

                <c:if test="${usuario.rol == 'SuperUsuario'}">
                    <div class="col-md-3">
                        <a href="tipoUsuario?rol=Coordinador" class="btn btn-primary btn-lg w-100">Coordinadores</a>
                    </div>
                    <div class="col-md-3">
                        <a href="tipoUsuario?rol=Director" class="btn btn-primary btn-lg w-100">Directores</a>
                    </div>
                    <div class="col-md-3">
                        <a href="tipoUsuario?rol=${fn:escapeXml('Recursos Humanos')}" class="btn btn-primary btn-lg w-100">Recursos Humanos</a>
                    </div>
                    <div class="col-md-3">
                        <a href="tipoUsuario?rol=${fn:escapeXml('Servicios Escolares')}" class="btn btn-primary btn-lg w-100">Servicios Escolares</a>
                    </div>
                </c:if>
                
                <c:if test="${usuario.rol == 'Servicios Escolares'}">
                    <div class="col-md-3">
                        <a href="grupos" class="btn btn-primary btn-lg w-100">Grupos</a>
                    </div>
                    <div class="col-md-3">
                        <a href="alumnos" class="btn btn-primary btn-lg w-100">Alumnos</a>
                    </div>
                    <div class="col-md-3">
                        <a href="padres" class="btn btn-primary btn-lg w-100">Padres</a>
                    </div>
                </c:if>
            </div>
        </div>

    </body>
</html>

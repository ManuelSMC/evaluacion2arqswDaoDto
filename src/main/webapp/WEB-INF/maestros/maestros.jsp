<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Maestros</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="container mt-5">
            <h1 class="text-center mb-4">Lista de Maestros</h1>
            <div class="text-center mt-3">
                <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
                <c:if test="${usuario.rol == 'Recursos Humanos'}">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registroMaestroModal">Registrar maestro</button>
                </c:if>
            </div>
            <br>
            <div class="row">
                <c:if test="${empty maestros}">
                    <div class="alert alert-warning text-center w-100">
                        No hay maestros registrados en el sistema.
                    </div>
                </c:if>
                <c:forEach var="maestro" items="${maestros}">
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title text-truncate" style="font-size: 1.1rem;">${maestro.usuario.nombre}</h5>
                                <p class="card-text text-truncate" style="font-size: 0.9rem;">ID: ${maestro.id}</p>
                                <c:if test="${usuario.rol == 'Coordinador'}">
                                    <a href="gruposMaestro?maestroId=${maestro.id}" class="btn btn-primary btn-sm w-100">Ver Grupos</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="registroMaestroModal" tabindex="-1" aria-labelledby="registroMaestroModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="registroMaestroModalLabel">Registrar Maestro</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="registrarMaestro" method="post">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                            <div class="mb-3">
                                <label for="correo" class="form-label">Correo</label>
                                <input type="email" class="form-control" id="correo" name="correo" required>
                            </div>
                            <div class="mb-3">
                                <label for="contrasena" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                            </div>
                            <input type="hidden" name="rol" value="Maestro">
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary">Registrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
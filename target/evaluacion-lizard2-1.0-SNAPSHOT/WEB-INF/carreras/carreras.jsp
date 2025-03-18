<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carreras</title>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Lista de Carreras</h1>
        <div class="text-center mt-3">
            <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
            <c:if test="${usuario.rol == 'Recursos Humanos'}">
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registroCarreraModal">Registrar Carrera</button>
            </c:if>
        </div>
        <br>
        <div class="row">
            <c:if test="${empty carreras}">
                <div class="alert alert-warning text-center w-100">
                    No hay carreras registradas en el sistema.
                </div>
            </c:if>
            <c:forEach var="carrera" items="${carreras}">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${carrera.nombre}</h5>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="registroCarreraModal" tabindex="-1" aria-labelledby="registroCarreraModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="registroCarreraModalLabel">Registrar Carrera</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="registrarCarrera" method="post">
                        <div class="mb-3">
                            <label for="nombreCarrera" class="form-label">Nombre de la Carrera</label>
                            <input type="text" class="form-control" id="nombreCarrera" name="nombre" required>
                        </div>
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

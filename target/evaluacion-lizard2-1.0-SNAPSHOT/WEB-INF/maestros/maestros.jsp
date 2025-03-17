<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Maestros</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="container mt-5">
            <h1 class="text-center mb-4">Lista de Maestros</h1>
            <div class="text-center mt-3">
                <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
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
                                <a href="gruposMaestro?maestroId=${maestro.id}" class="btn btn-primary btn-sm w-100">Ver Grupos</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

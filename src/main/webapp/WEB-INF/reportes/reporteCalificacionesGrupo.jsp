<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reporte de Calificaciones por Grupo</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="container mt-4">
            <div class="card shadow-lg p-4">
                <h2 class="text-center text-primary">Reporte de Calificaciones</h2>
                <h4 class="text-center text-secondary">Grupo: ${nombreGrupo}</h4>
                <div class="text-center mt-3">
                    <a href="grupos" class="btn btn-secondary">Volver a Grupos</a>
                </div>

                <div class="table-responsive mt-4">
                    <table class="table table-bordered table-hover">
                        <thead class="table-primary text-center">
                            <tr>
                                <th>Alumno</th>
                                <th>Materia</th>
                                <th>Calificación</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="calificacion" items="${calificaciones}">
                                <tr>
                                    <td>${calificacion.nombreAlumno}</td>
                                    <td>${calificacion.nombreMateria}</td>
                                    <td class="text-center">
                                        <span class="badge 
                                            <c:choose>
                                                <c:when test="${calificacion.calificacion >= 8}">bg-success</c:when>
                                                <c:when test="${calificacion.calificacion >= 6}">bg-warning</c:when>
                                                <c:otherwise>bg-danger</c:otherwise>
                                            </c:choose>">
                                            ${calificacion.calificacion}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                
            </div>
        </div>

    </body>
</html>

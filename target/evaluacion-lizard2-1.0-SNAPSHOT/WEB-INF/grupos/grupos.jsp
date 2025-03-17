<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Grupos del Maestro</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <c:if test="${param.msg == 'exists'}">
        <div class="alert alert-warning text-center">
            La materia ya está asignada a este maestro en ese grupo.
        </div>
    </c:if>
    <c:if test="${param.msg == 'success'}">
        <div class="alert alert-success text-center">
            Grupo y materia asignados correctamente.
        </div>
    </c:if>
    
    <div class="container my-5">
        <h2 class="text-center">Lista de grupos</h2>
        <div class="text-center mt-3">
            <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
        </div>
        <br>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Grupo</th>
                    <th>Carrera</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="grupo" items="${grupos}">
                    <tr>
                        <td>${grupo.nombre}</td>
                        <td>${grupo.idCarrera}</td>
                        <td>
                            <a href="horarioGrupo?idGrupo=${grupo.id}" class="btn btn-info btn-sm">Horario</a>
                            <a href="reporteCalificaciones?idGrupo=${grupo.id}" class="btn btn-info btn-sm">Reporte de calificaciones</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Asistencias del Alumno</title>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Asistencias de la Materia - <span class="text-primary">${nombre_materia}</span></h2>
        <div class="text-center mt-3">
            
            <c:if test="${usuario.rol != 'Servicios Escolares'}">
                <a href="materiasAlumno" class="btn btn-secondary">Volver a materias</a>
            </c:if>
            
            <c:if test="${usuario.rol == 'Servicios Escolares'}">
                <a href="materiasAlumno?idUsuario=${idUsuario}" class="btn btn-secondary">Volver a materias</a>
            </c:if>
                
        </div>
        <br>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="asistencia" items="${asistencias}">
                    <tr>
                        <td>${asistencia.fecha}</td>
                        <td>${asistencia.estado}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>

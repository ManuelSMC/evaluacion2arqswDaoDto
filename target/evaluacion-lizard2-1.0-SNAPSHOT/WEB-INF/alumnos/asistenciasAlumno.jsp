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
        <h2 class="text-center text-primary">Asistencias de la Materia - ${nombre_materia}</h2>

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

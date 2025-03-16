<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Materias del Alumno</title>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Materias de <span class="text-primary">${usuario.nombre}</span></h2>

        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Materia</th>
                    <th>Calificación</th>
                    <th>Asistencias</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="materiaCalificacion" items="${calificaciones}">
                    <tr>
                        <td>${materiaCalificacion.materia.nombre}</td>
                        <td>${materiaCalificacion.calificacion}</td>
                        <td>
                            <a href="asistenciasAlumno?idMateria=${materiaCalificacion.id}" class="btn btn-info btn-sm">Ver Asistencias</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

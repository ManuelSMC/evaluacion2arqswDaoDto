<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Materias del Grupo</title>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Materias del grupo: <span class="text-primary">${nombreGrupo}</span></h2>
        <h4 class="text-center">Profesor: <span class="text-primary">${nombreMaestro}</span></h4>
        
        <div class="text-center mt-3">
            <a href="gruposMaestro?maestroId=${idMaestro}" class="btn btn-secondary">Volver a Grupos</a>
        </div>
        <br>
        <table class="table table-striped table-bordered mt-4">
            <thead class="table-dark">
                <tr>
                    <th>Materia</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="materia" items="${materias}">
                    <tr>
                        <td>${materia.nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

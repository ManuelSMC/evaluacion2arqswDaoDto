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
        <h2 class="text-center">Grupos de <span class="text-primary">${nombreMaestro}</span></h2>
        <div class="text-center mt-3">
            <a href="maestros" class="btn btn-secondary">Volver a Maestros</a>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#asignarModal">Asignar grupo y materia</button>
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
                            <a href="materiasGrupoMaestro?idGrupo=${grupo.id}&idMaestro=${idMaestro}" class="btn btn-info btn-sm">Ver Materias</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal  -->
    <div class="modal fade" id="asignarModal" tabindex="-1" aria-labelledby="asignarModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="asignarModalLabel">Asignar Grupo y Materia</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="asignarForm" action="asignarGrupoMateria" method="POST">
                        <input type="hidden" name="idMaestro" value="${idMaestro}">
                        
                        <div class="mb-3">
                            <label for="materia" class="form-label">Selecciona Materia:</label>
                            <select class="form-select" id="materia" name="idMateria">
                                <c:forEach var="mat" items="${materias}">
                                    <option value="${mat.id}">${mat.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="grupo" class="form-label">Selecciona Grupo:</label>
                            <select class="form-select" id="grupo" name="idGrupo">
                                <c:forEach var="g" items="${gruposTotales}">
                                    <option value="${g.id}">${g.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary" form="asignarForm">Asignar</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

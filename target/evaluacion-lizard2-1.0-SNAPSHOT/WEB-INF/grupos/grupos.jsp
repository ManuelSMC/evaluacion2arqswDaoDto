<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Grupos del Maestro</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <c:if test="${param.msg == 'exists'}">
        <div class="alert alert-warning text-center">
            La materia ya está asignada a este maestro en ese grupo.
        </div>
    </c:if>
    <c:if test="${param.msg == 'success' && usuario.rol != 'Servicios Escolares'}">
        <div class="alert alert-success text-center">
            Grupo y materia asignados correctamente.
        </div>
    </c:if>
    
    <c:if test="${param.msg == 'success' && usuario.rol == 'Servicios Escolares'}">
        <div class="alert alert-success text-center">
            Grupo asignado correctamente.
        </div>
    </c:if>

    <div class="container my-5">
        <h2 class="text-center">Lista de grupos</h2>

        <c:if test="${usuario.rol == 'Coordinador' || usuario.rol == 'Servicios Escolares'}">
            <div class="text-center mt-3">
                <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
                <c:if test="${usuario.rol == 'Servicios Escolares'}">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#modalRegistrarGrupo">Registrar grupo</button>
                </c:if>
            </div>
        </c:if>

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
                        <td>${grupo.carrera.nombre}</td>
                        <td>
                            <a href="horarioGrupo?idGrupo=${grupo.id}" class="btn btn-info btn-sm">Horario</a>
                            <a href="reporteCalificaciones?idGrupo=${grupo.id}" class="btn btn-info btn-sm">Reporte de calificaciones</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalRegistrarGrupo" tabindex="-1" role="dialog" aria-labelledby="modalGrupoLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalGrupoLabel">Registrar Grupo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formRegistrarGrupo" method="post" action="registrarGrupo">
                        <div class="form-group">
                            <label for="nombreGrupo">Nombre del Grupo:</label>
                            <input type="text" id="nombreGrupo" name="nombreGrupo" class="form-control" placeholder="Ingrese el nombre del grupo" required>
                        </div>

                        <div class="form-group">
                            <label for="idCarrera">Seleccionar Carrera:</label>
                            <select id="idCarrera" name="idCarrera" class="form-control" required>
                                <option value="" disabled selected>Seleccione una carrera</option>
                                <c:forEach var="carrera" items="${carreras}">
                                    <option value="${carrera.id}">${carrera.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-success">Guardar Grupo</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

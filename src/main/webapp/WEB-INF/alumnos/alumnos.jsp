<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alumnos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <c:if test="${param.msg == 'success'}">
        <div class="alert alert-success text-center">
            Alumno registrado correctamente.
        </div>
    </c:if>

    <div class="container my-5">
        <h2 class="text-center">Lista de alumnos</h2>
            <div class="text-center mt-3">
                <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#modalRegistrarGrupo">Registrar alumno</button>
            </div>
        <br>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${alumnos}">
                    <tr>
                        <td>${alumno.nombre}</td>
                        <td>${alumno.correo}</td>
                        <td>
                            <a href="materiasAlumno?idUsuario=${alumno.id}" class="btn btn-info btn-sm">Ver materias del alumno</a>
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
                    <h5 class="modal-title" id="modalGrupoLabel">Registrar Alumno</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formRegistrarAlumno" method="post" action="registrarAlumno">
                        <div class="form-group">
                            <label for="nombreAlumno">Nombre del Alumno</label>
                            <input type="text" id="nombreAlumno" name="nombreAlumno" class="form-control" required>
                            <label for="correoAlumno">Correo del Alumno</label>
                            <input type="email" id="correoAlumno" name="correoAlumno" class="form-control" required>
                            <label for="contrasenaAlumno">Contraseña del Alumno</label>
                            <input type="password" id="contrasenaAlumno" name="contrasenaAlumno" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="idGrupo">Selecciona el grupo</label>
                            <select id="idGrupo" name="idGrupo" class="form-control" required>
                                <option value="" disabled selected>Seleccione un grupo</option>
                                <c:forEach var="grupo" items="${grupos}">
                                    <option value="${grupo.id}">${grupo.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-success">Registrar alumno</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Calificaciones de la Materia - ${nombre_materia}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Calificaciones de la Materia - <span class="text-primary">${nombre_materia}</span></h2>
        
        <div class="text-center mt-3">
            <a href="materiasGrupoMaestro?idGrupo=${idGrupo}&idMaestro=${idMaestro}" class="btn btn-secondary">Volver a Materias</a>
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarCalificacion">Agregar Calificación</button>
        </div>
        <br>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Alumno</th>
                    <th>Calificación</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="calificacion" items="${calificaciones}">
                    <tr>
                        <td>${calificacion.nombreAlumno}</td>
                        <td>${calificacion.calificacion}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal para agregar calificación -->
    <!-- Modal -->
    <div class="modal fade" id="modalAgregarCalificacion" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Registrar Calificación</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formCalificacion" method="post" action="registrarCalificacion">
                        <input type="hidden" name="idGrupo" value="${idGrupo}">
                        <input type="hidden" name="idMaestro" value="${idMaestro}">
                        <input type="hidden" name="idMateria" value="${idMateria}">

                        <div class="form-group">
                            <label for="idAlumno">Seleccionar Alumno:</label>
                            <select id="idAlumno" name="idAlumno" class="form-control" required>
                                <option value="" disabled selected>Seleccione un alumno</option>
                                <c:forEach var="alumno" items="${alumnos}">
                                    <option value="${alumno.id}">${alumno.usuario.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="calificacion">Calificación:</label>
                            <input type="number" step="0.1" id="calificacion" name="calificacion" class="form-control" min="0" max="100" placeholder="Ingrese calificación" required>
                        </div>

                        <button type="submit" class="btn btn-success">Guardar Calificación</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

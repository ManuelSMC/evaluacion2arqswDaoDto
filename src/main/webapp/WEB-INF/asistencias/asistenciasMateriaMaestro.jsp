<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Asistencias de la Materia - ${nombre_materia}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Asistencias de la Materia - <span class="text-primary">${nombre_materia}</span></h2>

        <div class="text-center mt-3">
            <a href="materiasGrupoMaestro?idGrupo=${idGrupo}&idMaestro=${idMaestro}" class="btn btn-secondary">Volver a Materias</a>
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarAsistencia">Agregar Asistencia</button>
        </div>
        <br>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Alumno</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="asistencia" items="${asistencias}">
                    <tr>
                        <td>${asistencia.nombreAlumno}</td>
                        <td>${asistencia.fecha}</td>
                        <td>${asistencia.estado}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal para agregar asistencia -->
    <div class="modal fade" id="modalAgregarAsistencia" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Registrar Asistencia</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formAsistencia" method="post" action="registrarAsistencia">
                        <input type="hidden" name="idGrupo" value="${idGrupo}">
                        <input type="hidden" name="idMaestro" value="${idMaestro}">
                        <input type="hidden" name="idMateria" value="${idMateria}">
                        <div class="form-group">
                            <label for="fecha">Fecha:</label>
                            <input type="date" class="form-control" name="fecha" required>
                        </div>
                        <div class="form-group">
                            <label>Seleccionar alumnos y estado:</label>
                            <c:forEach var="alumno" items="${alumnos}">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="alumnosSeleccionados" value="${alumno.id}">
                                    <label class="form-check-label">${alumno.usuario.nombre}</label>
                                    <select name="estado_${alumno.id}" class="form-control form-control-sm d-inline w-auto ml-2">
                                        <option value="Presente">Presente</option>
                                        <option value="Ausente">Ausente</option>
                                        <option value="Retardo">Retardo</option>
                                    </select>
                                </div>
                            </c:forEach>
                        </div>
                        <button type="submit" class="btn btn-success">Guardar Asistencia</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            let today = new Date().toISOString().split('T')[0];
            document.querySelector('input[name="fecha"]').value = today;
        });
    </script>
</body>
</html>

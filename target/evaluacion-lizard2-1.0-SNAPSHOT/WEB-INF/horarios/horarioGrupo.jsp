<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Horario del Grupo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Horario del Grupo: <span class="text-primary">${nombreGrupo}</span></h2>
        
        <div class="text-center mt-3">
            <a href="grupos" class="btn btn-secondary">Volver a Grupos</a>
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#asignarModal">Asignar Horario</button>
        </div>
        <br>
        <table class="table table-striped table-bordered mt-4">
            <thead class="table-dark">
                <tr>
                    <th>Materia</th>
                    <th>Día</th>
                    <th>Hora Inicio</th>
                    <th>Hora Fin</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="horario" items="${horarioGrupo}">
                    <tr>
                        <td>${horario.materia.nombre}</td>
                        <td>${horario.dia}</td>
                        <td>${horario.horaInicio}</td>
                        <td>${horario.horaFin}</td>
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
                    <h5 class="modal-title" id="asignarModalLabel">Asignar Horario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="asignarForm" action="asignarHorario" method="POST">
                        <input type="hidden" name="idGrupo" value="${idGrupo}">
                        
                        <div class="mb-3">
                            <label for="idMateria" class="form-label">Selecciona Materia:</label>
                            <select class="form-select" id="idMateria" name="idMateria">
                                <c:forEach var="mat" items="${materias}">
                                    <option value="${mat.id}">${mat.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="dia" class="form-label">Selecciona Día:</label>
                            <select class="form-select" id="dia" name="dia">
                                <option value="Lunes">Lunes</option>
                                <option value="Martes">Martes</option>
                                <option value="Miércoles">Miércoles</option>
                                <option value="Jueves">Jueves</option>
                                <option value="Viernes">Viernes</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="horaInicio" class="form-label">Hora Inicio:</label>
                            <input type="time" class="form-control" id="horaInicio" name="horaInicio" required>
                        </div>
                        <div class="mb-3">
                            <label for="horaFin" class="form-label">Hora Fin:</label>
                            <input type="time" class="form-control" id="horaFin" name="horaFin" required>
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

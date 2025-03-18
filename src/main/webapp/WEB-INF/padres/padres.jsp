<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Padres</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <c:if test="${param.msg == 'success'}">
        <div class="alert alert-success text-center">
            Padre registrado correctamente.
        </div>
    </c:if>

    <div class="container my-5">
        <h2 class="text-center">Lista de Padres</h2>

        <div class="text-center mt-3">
            <a href="homepage" class="btn btn-secondary">Ir a homepage</a>
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalRegistrarPadre">Registrar Nuevo Padre</button>
        </div>

        <br>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Correo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="padre" items="${padres}">
                    <tr>
                        <td>${padre.nombre}</td>
                        <td>${padre.correo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalRegistrarPadre" tabindex="-1" role="dialog" aria-labelledby="modalPadreLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalPadreLabel">Registrar Nuevo Padre</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formRegistrarPadre" method="post" action="registrarPadre">
                        <div class="form-group">
                            <label for="nombrePadre">Nombre del Padre:</label>
                            <input type="text" id="nombrePadre" name="nombrePadre" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="correoPadre">Correo Electrónico:</label>
                            <input type="email" id="correoPadre" name="correoPadre" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="contrasenaPadre">Contraseña:</label>
                            <input type="password" id="contrasenaPadre" name="contrasenaPadre" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="hijoPadre">Seleccionar Hijo (Alumno):</label>
                            <select id="hijoPadre" name="hijoPadre" class="form-control" required>
                                <option value="">Busca al alumno</option>
                                <c:forEach var="alumno" items="${alumnos}">
                                    <option value="${alumno.id}">${alumno.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-success">Registrar Padre</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

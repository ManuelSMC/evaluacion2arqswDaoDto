<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Usuarios - ${rol}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jsp" %>

    <div class="container my-5">
        <h2 class="text-center">Usuarios - <span class="text-primary">${rol}</span></h2>

        <div class="text-center my-3">
            <a href="homepage" class="btn btn-secondary">Volver</a>
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalRegistrarUsuario">Registrar Usuario</button>
        </div>

        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Rol</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.correo}</td>
                        <td>${usuario.rol}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalRegistrarUsuario" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Registrar Usuario - ${rol}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="formUsuario" method="post" action="registrarUsuario">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Ingrese el nombre" required>
                        </div>

                        <div class="form-group">
                            <label for="correo">Correo:</label>
                            <input type="email" id="correo" name="correo" class="form-control" placeholder="Ingrese el correo" required>
                        </div>

                        <div class="form-group">
                            <label for="contrasena">Contraseña:</label>
                            <input type="password" id="contrasena" name="contrasena" class="form-control" placeholder="Ingrese la contraseña" required>
                        </div>

                        <div class="form-group">
                            <label for="rol">Rol:</label>
                            <select id="rol" name="rol" class="form-control" required>
                                <option value="" disabled selected>Seleccione un rol</option>
                                <option value="Coordinador">Coordinador</option>
                                <option value="Director">Director</option>
                                <option value="Recursos Humanos">Recursos Humanos</option>
                                <option value="Servicios Escolares">Servicios Escolares</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-success">Guardar Usuario</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>

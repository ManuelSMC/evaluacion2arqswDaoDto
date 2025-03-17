<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestión de Materias</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>
        
        
        
        <div class="container mt-4">
            <div class="card shadow-lg p-4">
                <c:if test="${param.msg == 'success'}">
                    <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">
                        Materia registrada correctamente.
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <h2 class="text-center text-primary">Lista de Materias</h2>
                <div class="text-center mt-3">
                    <a href="homepage" class="btn btn-secondary">Volver a homepage</a>
                </div>
                <div class="mb-3">
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalRegistrarMateria">
                        Registrar Materia
                    </button>
                </div>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="table-primary text-center">
                            <tr>
                                <th>Nombre de la Materia</th>
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
            </div> 
       </div>

        <!-- Modal -->
        <div class="modal fade" id="modalRegistrarMateria" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLabel">Registrar Nueva Materia</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <form action="registrarMateria" method="post">
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="nombreMateria" class="form-label">Nombre de la Materia:</label>
                                <input type="text" class="form-control" id="nombreMateria" name="nombreMateria" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary">Guardar Materia</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio Carrito</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/index">Inicio</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/usuario">Usuarios</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/producto">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/categoria">Categoria</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/carrito">Carrito de compras</a>
        </li>
                
        </ul>
        <c:if test="${sessionScope.registrado == 0}"> 
	        <ul class="navbar-nav pull-xs-right">
	        <li class="nav-item">
	          <a class="nav-link active" href="/login" tabindex="-1" >Ingresar</a>
	        </li>
	         <li class="nav-item">
	          <a class="nav-link active" href="/usuario" tabindex="-1" >Registrarse</a>
	        </li>
	        </ul>
        </c:if>

      <c:if test="${sessionScope.registrado == 1}">
        	
        	<ul class="navbar-nav  pull-xs-right">
        	<li class="nav-item">
        	<a class="nav-link disabled" href="" tabindex="-1" aria-disabled="true" >Bienvenido <c:out value="${nombreUsuario}" ></c:out></a>
	          
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/cerrarSesion" tabindex="-1" >Cerrar Sesion</a>
	        </li>
	        </ul>
        </c:if>
              <form class="d-flex" action="/producto/buscar" method="POST" >
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="palabra" id="palabra">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
 <br>
 <br>
 <div class="container mt-3" >
 	<div class="row">
 	<h2>Tabla de carritos</h2>
 	</div>
 	<div class="row">
 		<table class="table  responsive">
 			<thead>
 				<tr>
 					<th>ID</th>
 					<th>Usuario</th>
 					<th>Productos</th>
 					<th>Total compra</th>
 				</tr>
 			</thead>
 			<tbody> 

 			<c:forEach var="carrito" items="${carritos}" >
				<c:if test="${sessionScope.usuarioId == carrito.usuario.id}">
				
			
 				<tr>
	 				 	<td><c:out value="${carrito.id}" ></c:out></td>
						<td><c:out value="${carrito.usuario.nombre}" ></c:out></td>
	 					<td>
	 					 <c:set var="total" scope="application" value="${0}"/>
	 					<c:forEach var="producto" items="${carrito.productos}" >
	 						<c:set var="total" scope="application" value="${total+ producto.precio}"/>
	 						
	 						<p><span><c:out value="${producto.nombre}" ></c:out></span><span> <a href="/carrito/eliminarProducto/${producto.id}" class="btn btn-danger" >X</a> </span></p>
	 						
	 					</c:forEach>
	 					
	 					
	 					</td>
	 					<td><c:out value="${total}" ></c:out></td>
	 					<td><a href="/carrito/editar/${carrito.id}" class="btn btn-warning" >Editar</a></td>
	 			
 				</tr>
 					</c:if>
 			
 				</c:forEach>
 				
 			</tbody>
 		</table>
 			
 	</div>
 
 </div>

</body>
</html>
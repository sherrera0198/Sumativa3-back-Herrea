<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Producto</title>
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
    </div>
  </div>
</nav>
	<div class="container">
	 <div class="row">
		<h2>Editar Producto</h2>
	</div>
	
		<form:form action="/producto/update" method="post" modelAttribute="producto">
	<br>
	
			<form:hidden path="id"/>
	 <div class="row">
			<form:label path="nombre">Nombre Producto: </form:label>
			<form:input path="nombre" />
	</div>
			<br>
	<div class="row">
			<form:label path="descripcion">Desc Producto: </form:label>
			<form:input path="descripcion"  />
	</div>
				<br>
				
	 <div class="row">
			<form:label path="precio">Precio Producto: </form:label>
			<form:input path="precio" />
	</div>
			<br>
	 <div class="row">
			<form:label path="categorias">Categoria: </form:label>
			
			<form:select path="categorias">
				<option value="0">Seleccione categoria...</option>
				<c:forEach var="categoria" items="${listaCategorias}">
					<option value="<c:out value="${categoria.id}"></c:out>"><c:out value="${categoria.nombre}"></c:out> </option>
				</c:forEach>
			</form:select>
		</div>
		 <div class="row">
			<input type="submit" value="Modificar" class="btn btn-success btn-block mt-3">
		</div>
		</form:form>
		<br>

	</div>
</body>
</html>
  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto ASR new...</title>
</head>
<body>
<h1>Ejemplo de Proyecto de ASR con Cloudant ahora con DevOps Practica 11 </h1>
<hr />
<p>Opciones sobre la base de datos Cloudant de Andrea Farina</p>
<ul>
<li><a href="listar">Listar</a></li>
<li>Palabra en espa�ol:
<form action="insertar" method="post">
	<input type="text" name="palabra">
	<button type="submit">Guardar en Cloudant</button>
</form>
</li>
<li>Crear audio:
<form action="hablar" method="post">
	<input type="text" name="palabra_audio">
	<button type="submit">Reproducir en ingl�s</button>
</form>
</li>
</ul>
</body>
</html>
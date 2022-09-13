<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap css -->
<link rel="stylesheet"  href="resources/css/bootstrap.min.css">
<link rel="stylesheet"  href="resources/css/styles-error.css">
<title>Sistema ATS</title>
</head>
<body>

	<div class="card">
	
		<h3>PÁGINA DE ERRO</h3>

		<div class="card-body">
			<h4 class="msgErro">Ops, Ocorreu um erro!!</h4>
			<h5 class="card-title">Entre em contato com a equipe de suporte do sistema.</h5>
			<textarea class="form-control" id="message" cols="30" rows="5">${msg}</textarea>
		</div>

		<div class="card-footer">
			<div class="d-grid gap-2 botao">
				<a id="botaoEntrar" type="submit" href="<%= request.getContextPath() %>/principal/principal.jsp"
				 class="btn btn-danger">Voltar à página inicial</a>
			</div>
		</div>
	</div>
	
	 <script type="text/javascript" src="resources/js/bootstrap.bundle.min.js">
		
	    
	  	
    </script>

</body>
</html>
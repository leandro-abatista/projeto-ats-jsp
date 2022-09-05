<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap css -->
<link rel="stylesheet"  href="resources/css/bootstrap.min.css">
<link rel="stylesheet"  href="resources/css/styles.css">
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<title>Sistema ATS</title>
</head>
<body>

	<div class="card">
	
		<h3>AUTÊNTICAÇÃO DE USUÁRIO</h3>
	
		<div class="card-body">
		
			<form id="formUserAut"
				class="row g-3 needs-validation" novalidate>
				
				<div class="mb-3">
					<label for="login" class="form-label">Login:</label> 
					<input id="login" type="text" name="login" class="form-control" 
						placeholder="Digite seu login" required="required" autofocus="autofocus"
						maxlength="50">
					<div class="invalid-feedback">
				       Campo obrigatório
				    </div>
				</div>
				
				<div class="mb-3">
					<label for="senha" class="form-label">Senha:</label> 
					<input id="senha" type="password" name="senha" class="form-control"
						placeholder="Digite sua senha" required="required" maxlength="10">
					<div class="invalid-feedback">
				      Campo obrigatório
				    </div>
				</div>
				
				<div class="d-grid gap-2">
					<input id="botaoEntrar" type="submit" value="Acessar" class="btn btn-primary">
				</div>
			
			</form>
		
		</div>
	
	</div>
	
	 <script type="text/javascript">
		
	    (function () {
	  	  'use strict'
	
	  	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  	  var forms = document.querySelectorAll('.needs-validation')
	
	  	  // Loop over them and prevent submission
	  	  Array.prototype.slice.call(forms)
	  	    .forEach(function (form) {
	  	      form.addEventListener('submit', function (event) {
	  	        if (!form.checkValidity()) {
	  	          event.preventDefault()
	  	          event.stopPropagation()
	  	        }
	
	  	        form.classList.add('was-validated')
	  	      }, false)
	  	    })
	  	})()
	  	
    </script>

</body>
</html>
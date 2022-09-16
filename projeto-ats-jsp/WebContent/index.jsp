<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap css -->
<link rel="stylesheet"  href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet"  href="<%= request.getContextPath() %>/resources/css/styles.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/notify.min.js"></script>
<title>Sistema ATS</title>
</head>
<body>
	
	<div id="mensagem">
		<span>${msg}</span>
	</div>

	<div class="card">
	
		<h3>AUTÊNTICAÇÃO DE USUÁRIO</h3>
	
		<div class="card-body">
		
			<form id="formUserAut" method="post"
				action="<%= request.getContextPath() %>/ServletLogin"
				class="row g-3 needs-validation" novalidate>
				
				<input type="hidden" value="<%= request.getParameter("url") %>">
				
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
					<input id="botaoEntrar" type="submit" onclick="mostrarMensagem()" value="Acessar" class="btn btn-primary">
					<input id="botaoFechar" onclick="fecharPag()" value="Fechar" class="btn btn-danger">
				</div>
			
			</form>
		
		</div>
	
	</div>
	
	 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>	
	 <script src="<%= request.getContextPath() %>/resources/js/bootstrap.bundle.min.js"></script>	
	 <script type="text/javascript">

	 	var mensagem = $('#mensagem').val();
		if (mensagem != null) {
			$.notify('#mensagem', "info");
		}
		
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
	  	
	  	function mostrarMensagem() {
		  	var login = document.getElementById('login').value;
		  	var senha = document.getElementById('senha').value;

		  	if (login == null || login == '') {
				swal('Mensagem','Informe o login!', {icon: "warning"});
				login.focus();
			} else if(senha == null || senha == ''){
				swal('Mensagem','Informe a senha!', {icon: "warning"});
			}

		}

	    function fecharPag() {
		  	
			swal('Mensagem','Tem certeza que deseja fechar o sistema?',{
				icon: "warning",
				buttons: true,
				dangerMode: true,
				buttons: ["Não desejo fechar!", "Sim, fechar a página!"]
			});
		}

		
    </script>

</body>
</html>
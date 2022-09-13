<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt_BR">

<jsp:include page="head.jsp"></jsp:include>

<body>

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbar-main-menu.jsp"></jsp:include>

					<div class="pcoded-content">

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
										

											<div class="card-block">
												<h3 class="sub-title titulo1">[ Cadastro de Usuário ]</h3>
												
												<div id="mensagem">
													<span>${msg}</span>
												</div>
											</div>
											
											<div class="card">
												
												<form id="formUser" action="<%= request.getContextPath()%>/ServletUsuarioController"
													method="post">
													
													<div class="card-block">
													
														<div class="form-group">
															<button type="button" id="botaoPesquisar"
																class="btn btn-info waves-effect waves-light botao">Pesquisar</button>
														</div>
													
														<div class="form-group row">
															<label for="id" class="form-control-label">Código:</label>
															<div class="col-md-2">
																<input id="id" type="text" name="id" class="form-control" 
																	readonly="readonly" 
																	value="${objetoUsuario.id}">
															</div>
														</div>
														
														<div class="form-group row">
															<label for="nome" class="form-control-label">Nome:</label>
															<div class="col-md-6">
																<input id="nome" type="text" name="nome" class="form-control" 
																	maxlength="120" required="required" 
																	value="${objetoUsuario.nome}">
															</div>
															
															<label for="cpf" class="form-control-label">CPF:</label>
															<div class="col-md-3">
																<input id="cpf" type="text" name="cpf" class="form-control" 
																	maxlength="14" required="required" 
																	value="${objetoUsuario.cpf}">
															</div>	
														</div>
														
														<div class="form-group row">
															<label for="email" class="form-control-label">E-mail:</label> 
															<div class="col-md-6">
																<input type="email" name="email" class="form-control"
																	id="email" autocomplete="off" required="required" 
																	value="${objetoUsuario.email}">
															</div>
														</div>
														
														<div class="form-group row">
															<label for="login" class="form-control-label">Login:</label>
															<div class="col-md-5">
																<input id="login" type="text" name="login" class="form-control" 
																	maxlength="50" autocomplete="off" required="required" 
																	value="${objetoUsuario.login}">
															</div>
													
															<label for="senha" class="form-control-label">Senha:</label>
															<div class="col-md-4">
																<input id="senha" type="password" name="senha" class="form-control" 
																	maxlength="10" autocomplete="off" required="required" 
																	value="${objetoUsuario.senha}">
															</div>
														</div>
													</div>
													
													
													<div class="form-group">
													
														<button type="button" onclick="limparFormulario()" 
															class="btn btn-primary waves-effect waves-light botao">Novo</button>
															
														<button type="button"  
															class="btn btn-danger waves-effect waves-light botao">Excluir</button>
														
														<button type="submit"
															class="btn btn-success waves-effect waves-light botao">Salvar</button>
															
													</div>
												
												</form>
												
											</div>

										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

<script src="resources/js/bootstrap.bundle.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery.maskedinput-1.1.4.pack.js"></script>	
<script type="text/javascript">

	$(document).ready(function () {
		$('#cpf').mask('999.999.999-99');
	});

	var mensagem = $('#mensagem').val();
	if (mensagem != null) {
		$.notify('#mensagem', "info");
	}

	function limparFormulario() {
		var elementosHtml = document.getElementById('formUser').elements;
		for (var posicao = 0; posicao < elementosHtml.length; posicao++) {
			elementosHtml[posicao].value = '';
		}

		document.getElementById('nome').focus();
	}

	
</script>

</html>

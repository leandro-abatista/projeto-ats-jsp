<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
											
											<div class="card">
											
												<div class="card-block">
													<h3 class="sub-title titulo1">[ Cadastro de Usuário ]</h3>
													
													<div id="mensagem">
														<span>${msg}</span>
													</div>
												</div>
												
												<form id="formUser" action="<%= request.getContextPath()%>/ServletUsuarioController"
													method="post">
													
													<input type="hidden" id="acao" name="acao" value="">
													
													<div class="card-block">
													
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
																	maxlength="120"  
																	value="${objetoUsuario.nome}" required="required">
															</div>
															
															<label for="cpf" class="form-control-label">CPF:</label>
															<div class="col-md-3">
																<input id="cpf" type="text" name="cpf" class="form-control" 
																	maxlength="14"  
																	value="${objetoUsuario.cpf}" required="required">
															</div>	
														</div>
														
														<div class="form-group row">
															<label for="email" class="form-control-label">E-mail:</label> 
															<div class="col-md-6">
																<input type="email" name="email" class="form-control"
																	id="email" autocomplete="off"  
																	value="${objetoUsuario.email}" required="required">
															</div>
														</div>
														
														<div class="form-group row">
															<label for="login" class="form-control-label">Login:</label>
															<div class="col-md-5">
																<input id="login" type="text" name="login" class="form-control" 
																	maxlength="50" autocomplete="off"  
																	value="${objetoUsuario.login}" required="required">
															</div>
													
															<label for="senha" class="form-control-label">Senha:</label>
															<div class="col-md-4">
																<input id="senha" type="password" name="senha" class="form-control" 
																	maxlength="10" autocomplete="off" 
																	value="${objetoUsuario.senha}" required="required">
															</div>
														</div>
														
														<div class="form-group">
													
														<button type="button" onclick="limparFormulario()" 
															class="btn btn-primary waves-effect waves-light botao">Novo</button>
															
														<button type="button"  
															class="btn btn-danger waves-effect waves-light botao" onclick="criarDeleteComAjax()">Excluir</button>
														
														<button type="submit" 
															class="btn btn-success waves-effect waves-light botao">Salvar</button>
															
														<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#dialog">
																 Pesquisar
														</button>
													</div>
														
													</div><!-- fim do card-block -->

												</form>
												
											</div>

											<div class="card tabela">
												<!-- tabela de dados -->
												<div style="height: 20rem; overflow: scroll;">
													<table id="tabelaUser"
														class="table table-success table-hover">
														<thead>
															<!-- cabeçalho da tabela -->
															<tr>
																<th scope="col" style="width: 60px">Código</th>
																<th scope="col">Nome</th>
																<th scope="col">CPF</th>
																<th scope="col">Opções</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${listaUsers}" var="user">
																<tr>
																	<td><c:out value="${user.id}"></c:out></td>
																	<td><c:out value="${user.nome}"></c:out></td>
																	<td><c:out value="${user.cpf}"></c:out></td>
																	<td>
																		<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${user.id}" 
																			class="btn btn-dark waves-effect waves-light">
																			<i class="fa fa-user"></i>	
																		</a>
																		
																		<button type="button"  
																			class="btn btn-dark waves-effect waves-light">
																			<i class="fa fa-trash"></i>	
																		</button>
																		
																		<button type="button"  
																			class="btn btn-dark waves-effect waves-light">
																			<i class="fa fa-pencil-square-o"></i>
																		</button>
																		
																		<button type="button"  
																			class="btn btn-dark waves-effect waves-light">
																			<i class="fa fa-print"></i>	
																		</button>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
														<tfoot>
														</tfoot>
													</table>
												</div>
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
	
	<!-- Modal -->
	<div class="modal fade" id="teste" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Pesquisar Usuário</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        ...
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Início Modal 1 -->
	<div class="modal fade bd-example-modal-lg" id="dialog" aria-hidden="true" aria-labelledby="modalToggleLabel" tabindex="-1">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalToggleLabel">Pesquisa de Usuário</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
				</div>
				<div class="modal-body">
					<!-- corpo da página -->
					<div class="input-group mb-3">
						<input type="text" class="form-control"
							placeholder="Informe o nome para pesquisar"
							aria-label="Informe o nome para pesquisar"
							aria-describedby="button-addon2"
							style="font-size: 16px" id="nomeBusca">
							
						<button class="btn btn-success waves-effect waves-light"
							type="button" id="button-addon2" onclick="buscarUsuario();">Buscar</button>
					</div>

					<!-- tabela de dados -->
					<div style="height: 20rem; overflow: scroll; ">
						<table id="tabelaUserModal" class="table table-success table-hover">
							<thead><!-- cabeçalho da tabela -->
								<tr>
									<th scope="col" style="width: 60px">Código</th>
									<th scope="col">Nome</th>
									<th scope="col">CPF</th>
									<th scope="col">Opções</th>
								</tr>
							</thead>
							<tbody><!-- dados da tabela -->
							</tbody>
							<tfoot>
							</tfoot>
						</table>
					</div>
					<!-- corpo da página -->
				</div>
				
				<nav aria-label="Page navigation example">
					<ul id="ulPaginacaoUserAjax" class="pagination pagination-sm justify-content-center">

				 	</ul>
				</nav>
				
				<div style="font-size: 14px; margin-left: 15px; font-weight: 500">
					<!-- total de registros buscados -->
					<p id="totalResultados"></p>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- fim Modal 1  -->
	
	
</body>

<script src="<%= request.getContextPath() %>/resources/js/bootstrap.bundle.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery.maskedinput-1.1.4.pack.js"></script>	
<script src="<%= request.getContextPath() %>/resources/js/notify.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>	
<script type="text/javascript">

	$(document).ready(function () {
		$('#cpf').mask('999.999.999-99');
	});

	function limparFormulario() {
		var elementosHtml = document.getElementById('formUser').elements;
		for (var posicao = 0; posicao < elementosHtml.length; posicao++) {
			elementosHtml[posicao].value = '';
		}

		document.getElementById('nome').focus();
	}

	function criarDeleteComAjax() {

		swal({
			  title: "Mensagem de Confirmação",
			  text: "Deseja realmente excluir o registro selecionado?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {

				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({/*Método ajax*/
					method: "GET",
					url: urlAction,
					data: "id=" + idUser + "&acao=deletarUserComAjax",
					success: function (response) {
						limparFormulario();/*limpa os campos do formulário*/
						swal("Registro excluído com sucesso!", {icon: "success"});
					}
				}).fail(function (xhr, status, errorThrown) {
					swal("Ocorreu um erro ao tentar deletar o registro!", {icon: "error"});
				});
				
			    
			  } else {
			    swal("Operação cancelada!", {icon: "error"});
			  }
			});
	}

	/*Método para buscar os dados do objeto usuário*/
	function buscarUsuario() {
		var nomeBusca = document.getElementById('nomeBusca').value;

		if (nomeBusca == null || nomeBusca == '') {
			$.notify('Informe um nome no campo de busca para que seja realizada a pesquisa no banco de dados!', "info");
		}

		/*Validando o nomeBusca para buscar no banco de dados*/
		if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {

			var urlAction = document.getElementById('formUser').action;
			
			$.ajax({/*Método ajax*/
				method: "GET",
				url: urlAction,
				data: "nomeBusca=" + nomeBusca + "&acao=buscarUserComAjax",
				success: function (response) {

					var json = JSON.parse(response);

					/*Usando o jquery*/
					$('#tabelaUserModal > tbody > tr').remove();
					for (var p = 0; p < json.length; p++) {
						$('#tabelaUserModal > tbody').append('<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td> <td>'+json[p].cpf+'</td> <td><button type="button" onclick="verEditar('+json[p].id+')" class="btn btn-primary">Visualizar</button></td> </tr>')
					}
					document.getElementById('totalResultados').textContent = 'Total de resultados encontrados na busca: ' + json.length;
					limparCampoNomeBusca();
					
				}
			
			}).fail(function (xhr, status, errorThrown) {
				swal("Ocorreu um erro ao tentar buscar registro por nome!", {icon: "error"});
			});
		}
	}

	/*Método para limpar o campo de busca, após a busca ser realizada e carregar os dados no modal*/
	function limparCampoNomeBusca() {
		document.getElementById('nomeBusca').value = '';
		document.getElementById('nomeBusca').focus();
	}

	function verEditar(id) {
		
		var urlAction = document.getElementById('formUser').action;
		window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
	}

</script>

</html>

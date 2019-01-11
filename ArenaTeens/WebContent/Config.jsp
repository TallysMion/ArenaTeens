<!DOCTYPE html>
<html lang="pt-br">
<head>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<meta charset="utf-8">
	<meta name="viewport" content="width-device-width, initial-scale = 1, shrink-to-fit=no">
	<title>Configurações</title>
	
	
	<style type="text/css">
		.col-center{
			float: none;
			margin: 0 auto;
		}
	</style>
	
</head>

<%
HttpSession sess = request.getSession();
br.com.tallys.ibel.arena.chamada.model.User usuario = (br.com.tallys.ibel.arena.chamada.model.User) sess.getAttribute("user"); 
%>

<body class="p-3 mb-2 bg-dark text-white">
	<div class="container">
			<div class="row">
			<div class="col">
				<div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto border border-primary">
					
					<form action="UpdateData" method="POST" data-toggle="validator" role="form" class="bootstrap-form" novalidate>
		    			<div class="form-group">
  							<label for="UpdateData">Nome:</label>
  							<input type="text" name="updnome" class="form-control" id="UpdateDataNome" placeholder="<%out.println(usuario.getNome());%>" required>

  							<label for="UpdateData">Data de nascimento:</label>
  							<input type="text" name="upddataNasc" class="form-control" id="UpdateDataNasc" onfocus="(this.type='date')" placeholder="<%out.println("" + usuario.getNasc().getDate() +"/"+ (usuario.getNasc().getMonth()+1) + "/" + (usuario.getNasc().getYear()+1900));%>" required>

  							<label for="UpdateData">Telefone:</label>
  							<input type="text" name="updtelefone" class="form-control" id="UpdateDataTelefone" placeholder="<%out.println(usuario.getNasc());%>" required>

  							<label for="UpdateData">Digite a Senha:</label>
    						<input type="password" name="updSenha" class="form-control" id="UpdateDataSenha" placeholder="Senha" data-minlength="6" required>
  						</div>
  						<div class="row w-100 p-3 col-center" >
  							<div class="col"><input class="btn btn-primary" style="width: 100%" type="submit" value="Alterar" /></div>
  						</div>	
					</form>

				</div>
				<br>
				<div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto border border-primary">

					<form action="UpdateKey" method="POST" data-toggle="validator" role="form" class="bootstrap-form" novalidate>
		    			<div class="form-group">
		    				<label for="UpdateKey">Senha Antiga:</label>
    						<input type="password" name="updsenhaAntiga" class="form-control" id="UpdateKeySenha" placeholder="Senha Antiga" data-minlength="6" required>

  							<label for="UpdateKey">Nova Senha:</label>
    						<input type="password" name="updnovaSenha" class="form-control" id="UpdateKeySenhaNova" placeholder="Nova Senha" data-minlength="6" required>

    						
    						<label for="UpdateKey">Confirmação da Nova Senha:</label>
    						<input type="password" name="updsenhaConfirm" class="form-control" id="UpdateKeySenhaNovaB" placeholder="Confirme sua senha" data-match="#ConfigInputSenha" data-match-error="Atenção! As senhas não estão iguais." required>
    						
  						</div>
  						<div class="row w-100 p-3 col-center" >
  							<div class="col"><input class="btn btn-primary" style="width: 100%" type="submit" value="Alterar" /></div>
  						</div>	
					</form>
				</div>
				<br>
				<div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto">
					<div class="col"><a class="btn btn-primary" style="width: 100%" href="Login" role="button">Home</a></div>
				</div>
			</div>
			</div>
	</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="js/errors.js"></script>
</body>
</html>
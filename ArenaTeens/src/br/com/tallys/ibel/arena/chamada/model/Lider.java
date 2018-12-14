package br.com.tallys.ibel.arena.chamada.model;

import java.io.IOException;
import java.sql.SQLException;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Lider extends User {
	
	private Arena arena;
	
	//Verificar Métodos Nescessários

	@Override
	public String mainPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Lider(String login, String senha, String nome, int externalID, Arena arena) {
		super(login, senha, nome, UserType.Lider, externalID);
		this.arena = arena;
	}

	@Override
	public String toHTML() {
		// TODO Auto-generated method stub
		String result;
		result= "<!DOCTYPE html>\r\n" + 
				"<html lang=\"pt-br\">\r\n" + 
				"<head>\r\n" + 
				"	<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + 
				"	<meta charset=\"utf-8\">\r\n" + 
				"	<meta name=\"viewport\" content=\"width-device-width, initial-scale = 1, shrink-to-fit=no\">\r\n" + 
				"	<title>" + this.nome + "</title>\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		.col-center{\r\n" + 
				"			float: none;\r\n" + 
				"			margin: 0 auto;\r\n" + 
				"		}\r\n" + 
				"	</style>\r\n" + 
				"	\r\n" + 
				"</head>\r\n" + 
				"<body class=\"p-3 mb-2 bg-dark text-white\">\r\n" + 
				"	<div class=\"container\">\r\n" + 
				"			<div class=\"row\">\r\n" + 
				"				\r\n" + 
				"				<div class=\"col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto\">\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"						<div class=\"col-4\">\r\n" + 
				"							<text>"+ this.nome +"</text>\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"col-4\">\r\n" + 
				"							<input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Config\" />\r\n" + //mudar para link A 
				"						</div>\r\n" + 
				"						<div class=\"col-4\">\r\n" + 
				"<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"logout\" role=\"button\">Log out</a>\r\n" +
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Grupos de Amizade\" disabled/>\r\n" + //mudar para link A fazer Funcionar
				"							</div>\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Relatórios\" />\r\n" + //mudar para link A *fazer funcionar
				"							</div>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<ul class=\"list-group text-dark\">\r\n";
		
		if(this.arena.ga.size() == 0) {
			result+="<li class=\"list-group-item\">Nenhum GA Encontrado</li>\r\n";
		}else {
			for(GA ga:this.arena.ga) {
				try {
					result+="<li class=\"list-group-item\">"+ ga.toString(true) + " </li>\r\n";
				} catch (ClassNotFoundException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		
										
//				"  						<li class=\"list-group-item\">Arroz</li>\r\n" + 
//				"  						<li class=\"list-group-item\">Feijão</li>\r\n" + 
//				"  						<li class=\"list-group-item\">Batata</li>\r\n" + 
//				"  						<li class=\"list-group-item\">Macarrão</li>\r\n" + 
				
		result+="					</ul>\r\n" + 
				"					<ul class=\"list-group text-dark\" hidden>	\r\n";
		
		if(this.arena.relatorios.size() == 0) {
			result+="<li class=\"list-group-item\">Nenhum Relatorio Encontrado</li>\r\n";
		}else {
			for(Fechamento fx:this.arena.relatorios) {
				result+="<li class=\"list-group-item\">"+ fx.toString() + " </li>\r\n";
			}
		}
		
//				"  						<li class=\"list-group-item\">Arroz</li>\r\n" + 
//				"  						<li class=\"list-group-item\">Feijão</li>\r\n" + 
//				"  						<li class=\"list-group-item\">Batata</li>\r\n" + 
//				"  						<li class=\"list-group-item\">Macarrão</li>\r\n" + 
//				"					</ul>\r\n" + 
		
		result+="					</ul>\r\n" +
				"					<br>\r\n" + 
				"					<div class=\"col\">\r\n" + 
				"						<div class=\"col\">\r\n" + 
				"							<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"cria_ga.html\" role=\"button\">Criar novo GA</a>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"			</div>\r\n" + 
				"	</div>\r\n" + 
				"\r\n" + 
				"<script src=\"js/jquery-3.2.1.min.js\"></script>\r\n" + 
				"<script src=\"js/popper.min.js\"></script>\r\n" + 
				"<script src=\"js/bootstrap.min.js\"></script>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		return result;
	}

	@Override
	public int getExtId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setArena(Arena arena) {
		this.arena = arena;		
	}
	
	@Override
	public UserType getType() {
		return UserType.Lider;
	}

	public Arena getArena() {
		return arena;
	}

	

}

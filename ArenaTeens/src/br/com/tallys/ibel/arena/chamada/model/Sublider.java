package br.com.tallys.ibel.arena.chamada.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Sublider extends User {

	private GA grupo;
	
	//Desenvolver Métodos
	
	@Override
	public String mainPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toHTML(boolean page) {
		String result = "";
		try {
			result = "<!DOCTYPE html>\r\n" + 
					"<html lang=\"pt-br\">\r\n" + 
					"<head>\r\n" + 
					"	<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + 
					"	<meta charset=\"utf-8\">\r\n" + 
					"	<meta name=\"viewport\" content=\"width-device-width, initial-scale = 1, shrink-to-fit=no\">\r\n" + 
					"	<title>Sublíder</title>\r\n" + 
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
					"							<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"Config.jsp\" role=\"button\">Config</a>\r\n" + 
					"						</div>\r\n" + 
					"						<div class=\"col-4\">\r\n" + 
					"						<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"logout\" role=\"button\">Log out</a>\r\n" + 
					"						</div>\r\n" + 
					"					</div>\r\n" + 
					"					<br>\r\n" + 
					"					<ul class=\"list-group text-dark\">\r\n" + 
					"  						<li class=\"list-group-item\">"+ this.grupo.toTitle(false) +"</li>\r\n" + 
					"					</ul>\r\n" + 
					"					<br>\r\n" + 
					"					<div class=\"row\">\r\n" + 
					"							<div class=\"col\">\r\n" + 
					"								<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"chamada_arena\" role=\"button\">Chamada Arena</a>\r\n" + 
					"							</div>\r\n" + 
					"					</div>\r\n" + 
					"					<br>\r\n" + 
					"					<ul class=\"list-group text-dark\">\r\n";
					
					if(this.grupo.getTeens().size() == 0) {
						result += "<li class=\"list-group-item\">Nenhum Teen Encontrado</li>\r\n";
					}else {
						LinkedList<Teens> ts = this.grupo.getTeens();
						for(Teens t:ts) {
							result += t.toString();
						}
					}							
//							"  						<li class=\"list-group-item\">Arroz</li>\r\n" + 
//							"  						<li class=\"list-group-item\">Feijão</li>\r\n" + 
//							"  						<li class=\"list-group-item\">Batata</li>\r\n" + 
//							"  						<li class=\"list-group-item\">Macarrão</li>\r\n" +
			result+="					</ul>\r\n" + 					
					"			</div>\r\n" + 
					"	</div>\r\n" + 
					"\r\n" + 
					"<script src=\"js/jquery-3.2.1.min.js\"></script>\r\n" + 
					"<script src=\"js/popper.min.js\"></script>\r\n" + 
					"<script src=\"js/bootstrap.min.js\"></script>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"";
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	@Override
	public int getExtId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Sublider(String login, String senha, String nome, int externalID,String telefone, Date nasc, GA grupo) {
		super(login, senha, nome, UserType.Sublider, externalID, telefone, nasc);
		this.grupo = grupo;
	}

	public void setGA(GA ga) {
		this.grupo = ga;		
	}

	@Override
	public UserType getType() {
		return UserType.Sublider;
	}

	@Override
	public String toHTML() {
		return this.toHTML(false);
	}

	public GA getGa() {
		return this.grupo;
	}
	
}

package br.com.tallys.ibel.arena.chamada.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Lider extends User {
	
	private Arena arena;
	
	//Verificar Métodos Nescessários

	public Lider(String login, String senha, String nome, int externalID, String telefone, Date nasc, Arena arena) {
		super(login, senha, nome, UserType.Lider, externalID, telefone, nasc);
		this.arena = arena;
	}

	public String toHTML(boolean page) {
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
				"							<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"Config.jsp\" role=\"button\">Config</a>\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"col-4\">\r\n" + 
				"							<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"logout\" role=\"button\">Log out</a>\r\n" +
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"chamada_imersao\" role=\"button\">Chamada Imersao</a>\r\n" +
				"							</div>\r\n" +  
				"					</div>\r\n" +
				"					<br>\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"fecha_bim\" role=\"button\">Fechar Bimestre</a>\r\n" +
				"							</div>\r\n" +  
				"							<div class=\"col\">\r\n" + 
				"								<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"fecha_year\" role=\"button\">Fechar Ano</a>\r\n" +
				"							</div>\r\n" +  
				"					</div>\r\n" +
				"					<br>\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<a class=\"btn btn-primary "+ (page?"":"disabled") + " \" type=\"button\" style=\"width: 100%\" href=\"Login\" role=\"button\" >Grupos de Amizade</a>\r\n" + //mudar para link A fazer Funcionar
				"							</div>\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<a class=\"btn btn-primary "+ (!page?"":"disabled") + " \" type=\"button\" style=\"width: 100%\" href=\"Login2\" role=\"button\" >Relatorios</a>\r\n" + //mudar para link A fazer Funcionar
				"							</div>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<ul class=\"list-group text-dark\" "+ (!page?"":"hidden") +" >\r\n";
		
		if(this.arena.ga.size() == 0) {
			result+="<li class=\"list-group-item\">Nenhum GA Encontrado</li>\r\n";
		}else {
			for(GA ga:this.arena.ga) {
				try {
					result+="<li class=\"list-group-item\"> <a href=\"InfoGa?id=" + ga.getId() + "\">"  + ga.toString(true) + "</a></li>\r\n";
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
				"					<ul class=\"list-group text-dark\" "+ (page?"":"hidden") +">	\r\n";
		
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

	@Override
	public String toHTML() {
		return this.toHTML(false);
	}

	

}

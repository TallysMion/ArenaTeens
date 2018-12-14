package br.com.tallys.ibel.arena.chamada.model;

import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

public class Teens extends User {

	private GA grupo;
	private LinkedList<Relatorio> relatorios;
	private int bim;
	private int anual;
	
	//Desenvolver Métodos
	
	@Override
	public String mainPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toHTML() {
		if(this.bim < 0 || this.anual < 0) {
			this.bim = 0;
			this.anual = 0;
			for(Relatorio rl:relatorios) {
				int i = rl.getPontuacao();
				if(!rl.closed) {
					this.anual+=rl.getPontuacao();
				}
				if(!rl.closedBim) {
					this.bim+=rl.getPontuacao();
				}
			}
		}
		String result;
		
		result = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"pt-br\">\r\n" + 
				"<head>\r\n" + 
				"	<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + 
				"	<meta charset=\"utf-8\">\r\n" + 
				"	<meta name=\"viewport\" content=\"width-device-width, initial-scale = 1, shrink-to-fit=no\">\r\n" + 
				"	<title>Teen</title>\r\n" + 
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
				"							<input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Config\" />\r\n" + 
				"						</div>\r\n" + 
				"						<div class=\"col-4\">\r\n" + 
				"						<a class=\"btn btn-primary\" style=\"width: 100%\" href=\"logout\" role=\"button\">Log out</a>\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"							<div class=\"col-6\">\r\n" + 
				"								<div class=\"row\">\r\n" + 
				"									<label>Pontuacao bimestral:</label>\r\n" + 
				"								</div>\r\n" + 
				"								<div class=\"row\">\r\n" + 
				"									<label>"+ this.bim +"</label>\r\n" + 
				"								</div>\r\n" + 
				"							</div>\r\n" + 
				"							<div class=\"col-6\">\r\n" + 
				"								<div class=\"row\">\r\n" + 
				"									<label>Pontuacao anual:</label>\r\n" + 
				"								</div>\r\n" + 
				"								<div class=\"row\">\r\n" + 
				"									<label>"+ this.anual +"</label>\r\n" + 
				"								</div>\r\n" + 
				"							</div>\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"row\">\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Relatório Bimestral\" disabled/>\r\n" + 
				"							</div>\r\n" + 
				"							<div class=\"col\">\r\n" + 
				"								<input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Relatório Anual\" />\r\n" + 
				"							</div>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<ul class=\"list-group text-dark\">\r\n"; 
				
					if(this.relatorios.size() == 0) {
						result += "<li class=\"list-group-item\">Nenhum Relatorio Encontrado</li>\r\n";
					}else {
						LinkedList<Relatorio> rl = this.relatorios;
						for(Relatorio r:rl) {
							if(!r.closedBim)
							result += r.toString();
						}
					}
				
		result+="					</ul>\r\n" + 
				"					<ul class=\"list-group text-dark\" hidden>	\r\n"; 
				if(this.relatorios.size() == 0) {
					result += "<li class=\"list-group-item\">Nenhum Relatorio Encontrado</li>\r\n";
				}else {
					LinkedList<Relatorio> rl = this.relatorios;
					for(Relatorio r:rl) {
						if(!r.closed)
						result += r.toString();
					}
				} 
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
		
		
		return result;
	}

	@Override
	public int getExtId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Teens(String login, String senha, String nome, int externalID, GA grupo,
			LinkedList<Relatorio> relatorios) {
		super(login, senha, nome, UserType.Teen, externalID);
		this.grupo = grupo;
		this.relatorios = relatorios;
		if(this.relatorios == null) {
			this.relatorios = new LinkedList<Relatorio>();
		}
		this.bim = -1;
		this.anual = -1;
	}

	public void setGA(GA ga) {
		this.grupo = ga;		
	}

	@Override
	public UserType getType() {
		return UserType.Teen;
	}
	
	public String toString() {
		
		if(this.bim < 0 || this.anual < 0) {
			this.bim = 0;
			this.anual = 0;
			for(Relatorio rl:relatorios) {
				int i = rl.getPontuacao();
				if(!rl.closed) {
					this.anual+=rl.getPontuacao();
				}
				if(!rl.closedBim) {
					this.bim+=rl.getPontuacao();
				}
			}
		}
		
		String result = "<li class=\"list-group-item\">"+ this.nome + "[ " + this.bim + " | " + this.anual + " ]</li>\r\n";
		
		return result;		
	}
	

}

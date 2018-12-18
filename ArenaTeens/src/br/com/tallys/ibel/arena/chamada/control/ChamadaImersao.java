package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;

@WebServlet("/chamada_imersao")
public class ChamadaImersao extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String page;
		HttpSession sess = request.getSession();
		User usuario = (User) sess.getAttribute("user");
		if(usuario == null) {
			response.sendRedirect("Login");
			return;
		}
		
		Lider l = (Lider) usuario;
		LinkedList<GA> gas = l.getArena().getGa();
		LinkedList<Teens> teens = new LinkedList<Teens>();
		for(GA g:gas)
			teens.addAll(g.getTeens());
		
		page = "<!DOCTYPE html>\r\n" + 
				"<html lang=\"pt-br\">\r\n" + 
				"<head>\r\n" + 
				"	<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + 
				"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css\" integrity=\"sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ\" crossorigin=\"anonymous\">\r\n" + 
				"	<meta charset=\"utf-8\">\r\n" + 
				"	<meta name=\"viewport\" content=\"width-device-width, initial-scale = 1, shrink-to-fit=no\">\r\n" + 
				"	<title>Chamada</title>\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		.col-center{\r\n" + 
				"			float: none;\r\n" + 
				"			margin: 0 auto;\r\n" + 
				"		}\r\n" + 
				"			.btn span.glyphicon {    			\r\n" + 
				"		opacity: 0;				\r\n" + 
				"	}\r\n" + 
				"	.btn.active span.glyphicon {				\r\n" + 
				"		opacity: 1;				\r\n" + 
				"	}\r\n" + 
				"	</style>\r\n" + 
				"	\r\n" + 
				"</head>\r\n" + 
				"<body class=\"p-3 mb-2 bg-dark text-white\">\r\n" + 
				"	<div class=\"container\">\r\n" + 
				"			<div class=\"row\">\r\n" + 
				"				\r\n" + 
				"				<div class=\"col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto\">\r\n" + 
				"					<form action=\"ChamadaImersao\" method=\"POST\" data-toggle=\"validator\" role=\"form\" class=\"bootstrap-form\" novalidate>\r\n" + 
				"		    			\r\n" + 
				"		    			<ul class=\"list-group\">\r\n" + 
				"  						\r\n"; 
		
		for(Teens t:teens)
			page += t.toChamadaImersao();
		
		page += "							\r\n" + 
				"														\r\n" + 
				"							\r\n" + 
				"							\r\n" + 
				"							\r\n" + 
				"							\r\n" + 
				"						</ul>\r\n" + 
				"						\r\n" + 
				"						\r\n" + 
				"  						<div class=\"row w-100 p-3 col-center\" >\r\n" + 
				"  							<div class=\"col\"><input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Confirmar\" /></div>\r\n" + 
				"  							<div class=\"col\"><a class=\"btn btn-primary\" style=\"width: 100%\" href=\"Login\" role=\"button\">Cancelar</a></div>\r\n" + 
				"  						</div>	\r\n" + 
				"					</form>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"	</div>\r\n" + 
				"\r\n" + 
				"<script src=\"js/jquery-3.2.1.min.js\"></script>\r\n" + 
				"<script src=\"js/popper.min.js\"></script>\r\n" + 
				"<script src=\"js/bootstrap.min.js\"></script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		out.println(page);
		
	}

}

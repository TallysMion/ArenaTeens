package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tallys.ibel.arena.chamada.Dao.UserDao;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.utils.Codigo;
import br.com.tallys.ibel.arena.chamada.utils.CodigoType;

@WebServlet("/Login")
public class Login extends HttpServlet{

	//auto-generated SerialKey
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String page;
		HttpSession sess = request.getSession();
		User usuario = (User) sess.getAttribute("user");
		
		String login="", senha="";
		if(usuario != null) {
			login = usuario.getLogin();
			senha = usuario.getSenha();
		}else {
			//recupera login e senha
			login = request.getParameter("login");
			senha = request.getParameter("senha");
		}
		
		
		
		
		
		
		//chama função de login
		try {
			usuario = new UserDao().login(login, senha);
			if(usuario == null) {
				throw new Exception();
			}
			page = usuario.toHTML(false);
			sess.setAttribute("user", usuario);
		} catch (Exception e) {
			page =    "<!DOCTYPE html>"
					+ "<html lang=\"pt-br\">"
					+ "<head>"
					+ "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">"
					+ "<meta charset=\"utf-8\">"
					+ "<meta name=\"viewport\" content=\"width-device-width, initial-scale = 1, shrink-to-fit=no\">"
					+ "</head>"
					+ "<body class=\"p-3 mb-2 bg-dark text-white\">" 
					+ "		<div class=\"container\">" 
					+ "			<div class=\"row\">" 
					+ "				<div class=\"col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto\">"
					+ "					<h1 class=\"text-center\">usuario não encontrado - " + e.toString() + "</h1>"//pagina Login -> usuario não encontrado"
					+ "					<div class=\"col\"><a class=\"btn btn-primary\" style=\"width: 100%\" href=\"index.html\" role=\"button\">Login</a></div>"
					+ "             </div>"
					+ "         </div>"
					+ "     </div>"
					+ "		<script src=\"js/jquery-3.2.1.min.js\"></script>"
					+ "		<script src=\"js/popper.min.js\"></script>"
					+ "		<script src=\"js/bootstrap.min.js\"></script>"
					+ "</body>"
					+ "</html>";
		}
		//imprime a pagina do usuario
		out.println(page);
		
	}
	
}

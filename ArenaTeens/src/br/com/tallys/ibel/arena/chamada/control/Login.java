package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tallys.ibel.arena.chamada.Dao.UserDao;
import br.com.tallys.ibel.arena.chamada.model.User;

@WebServlet("/Login")
public class Login extends HttpServlet{

	//auto-generated SerialKey
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		//recupera login e senha
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String page = "" + login + " / " + senha;//temporario
		
		
		//chama função de login
		try {
			User usuario = new UserDao().login(login, senha);
			if(usuario == null) {
				throw new Exception();
			}
			page = usuario.toHTML();
		} catch (Exception e) {
			page = "usuario não encontrado";//pagina Login -> usuario não encontrado
		}
		
		//monta a pagina do usuario
		
		//imprime a pagina do usuario
		out.println(page);
		
	}
	
}

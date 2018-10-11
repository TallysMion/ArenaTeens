package br.com.tallys.ibel.arena.chamada;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet{

	//auto-generated SerialKey
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		//recupera login e senha
		String Login = request.getParameter("login");
		String Senha = request.getParameter("senha");
		String page = "" + Login + " / " + Senha;//temporario
		
		//chama função de login
		
		//monta a pagina do usuario
		
		//imprime a pagina do usuario
		out.println(page);
		
	}
	
}

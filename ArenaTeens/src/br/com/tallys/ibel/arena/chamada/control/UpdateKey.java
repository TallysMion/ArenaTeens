package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tallys.ibel.arena.chamada.Dao.UserDao;
import br.com.tallys.ibel.arena.chamada.model.User;

@WebServlet("/UpdateKey")
public class UpdateKey extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		HttpSession sess = request.getSession();
		User usuario = (User) sess.getAttribute("user");
		
		if(usuario == null) {
			response.sendRedirect("index");
			return;
		}
		
		String login, senha;
		login = usuario.getLogin();
		senha = request.getParameter("updsenhaAntiga");
		User aux = null;
		try {
			aux= UserDao.login(login, senha);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//lançar um toast ou pop-up
			out.println("Erro no database");
			return;
		}
		
		if(!usuario.getLogin().equals(aux.getLogin())) {
			//lançar um toast ou pop-up
			out.println("Erro na Senha");
			return;
		}
		
		
		String nSenha, nSenhaConf;
		nSenha = request.getParameter("updnovaSenha");
		nSenhaConf = request.getParameter("updsenhaConfirm");
		
		if(nSenha == null || nSenhaConf == null) {
			//Toast, senhas diferentes
			out.println("Senha(s) Nula(s)");
			return;
		}
		
		if(!nSenha.equals(nSenhaConf)) {
			//Toast, senhas diferentes
			out.println("Senhas Diferentes");
			return;
		}
		
		
		
		try {
			UserDao.updateKey(login, senha, User.encrypt(login, nSenha));
			usuario.setSenha(nSenha);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//lançar um toast ou pop-up
		response.sendRedirect("Login");
		return;
	}

}

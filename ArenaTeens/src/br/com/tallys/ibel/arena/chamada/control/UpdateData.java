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

@WebServlet("/UpdateData")
public class UpdateData  extends HttpServlet{
	
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
		senha = request.getParameter("updSenha");
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
		
		
		String nlogin, nTel;
		Date nNasc = null;

		nlogin = request.getParameter("updnome");
		if(nlogin.equals(""))
			nlogin = usuario.getLogin();
		
		String nascStr  = request.getParameter("upddataNasc");
		if(!nascStr.equals("")) {
			DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
			try {
				nNasc = (Date)formatter.parse(nascStr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			nNasc = usuario.getNasc();
		}
		
		nTel   = request.getParameter("updtelefone");
		if(nTel.equals(""))
			nTel = usuario.getTelefone();
		
		try {
			UserDao.updateData(login, senha, nlogin, nNasc, nTel);
			usuario.setNome(nlogin);
			usuario.setNasc(nNasc);
			usuario.setTelefone(nTel);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//lançar um toast ou pop-up
		response.sendRedirect("Login");
		return;
	}
	
}

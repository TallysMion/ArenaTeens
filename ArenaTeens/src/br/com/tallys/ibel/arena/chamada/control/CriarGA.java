package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tallys.ibel.arena.chamada.Dao.GADao;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;
import br.com.tallys.ibel.arena.chamada.utils.Codigo;
import br.com.tallys.ibel.arena.chamada.utils.CodigoType;

@WebServlet("/criar_ga")
public class CriarGA extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession sess = request.getSession();
		User usuario = (User) sess.getAttribute("user");
		if(usuario == null || !usuario.getType().equals(UserType.Lider)) {
			response.sendRedirect("Login");
			return;
		}
		String nomeGa = request.getParameter("nome_ga");
		try {
			if(GADao.isUsed(nomeGa)) {
				//gerar Toast
				response.sendRedirect("cria_ga.html");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			//gerar Toast
			response.sendRedirect("cria_ga.html");
			e.printStackTrace();
			return;
		}
		GA ga = new GA(nomeGa);
		try {
			GADao.Cadastrar(ga);
			ga.setCodigoTeen(GADao.codGATeen(ga));
			ga.setCodigoSublider(GADao.codGASublider(ga));
		} catch (ClassNotFoundException | SQLException e) {
			//gerar Toast
			response.sendRedirect("cria_ga.html");
			e.printStackTrace();
			return;
		}
		Lider ld = (Lider) usuario;
		ld.getArena().ga.add(ga);		
		response.sendRedirect("Login");
		return;
	}

}

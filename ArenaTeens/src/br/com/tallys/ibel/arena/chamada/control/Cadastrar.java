package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tallys.ibel.arena.chamada.Dao.ArenaDao;
import br.com.tallys.ibel.arena.chamada.Dao.CodigoDao;
import br.com.tallys.ibel.arena.chamada.Dao.GADao;
import br.com.tallys.ibel.arena.chamada.Dao.UserDao;
import br.com.tallys.ibel.arena.chamada.model.Arena;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.Sublider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;
import br.com.tallys.ibel.arena.chamada.utils.Codigo;

@WebServlet("/Cadastro")
public class Cadastrar  extends HttpServlet{
	//auto-generated SerialKey
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		String login  = request.getParameter("login");
		String senha  = request.getParameter("senha");
		String senha2 = request.getParameter("senhaConfirm");
		String nome   = request.getParameter("nome");
		String codigo = request.getParameter("codigo");
		
		try {
			if(UserDao.isUsed(login)) {
				out.println("Usuario já esta em Uso");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(senha.length() < 6) {
			out.println("Senhas Muito Curta");
			return;
		}
		
		if(!senha.equals(senha2)) {
			out.println("Senhas Não Batem");
			return;
		}
		
		Codigo cd = null;
		try {
			cd = CodigoDao.getCodigoDB(codigo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cd==null) {
			out.println("Codigo Invalido");
			return;
		}
		
		String encript = User.encrypt(login, senha);
		GA ga = null;
		User result = null;
		UserType tipo = null;
		int extId = -1;
		switch(cd.getTipo()) {
		case Lider: 	Arena arena = null;
			try {
				arena = ArenaDao.getArena();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						result = new Lider(login, encript, nome, cd.getExtId(), arena);	
						extId = 0;
						tipo = UserType.Lider;
						break;
		case Sublider:  try {
				ga = GADao.getGA(cd.getExtId());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						result = new Sublider(login, encript, nome, cd.getExtId(), ga);
						extId = cd.getExtId();
						tipo = UserType.Sublider;
						break;
		case Teen: 		try {
				ga = GADao.getGA(cd.getExtId());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						result = new Teens(login, encript, nome, cd.getExtId(), ga,  null);
						extId = cd.getExtId();
						tipo = UserType.Teen;
						break;
		}
		
		try {
			UserDao.Cadastrar(login, encript, tipo.ordinal(), nome, extId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("Cadastro Completo");
		
	}

}




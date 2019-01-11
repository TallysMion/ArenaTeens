package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.util.Data;

import br.com.tallys.ibel.arena.chamada.Dao.RelatorioDao;
import br.com.tallys.ibel.arena.chamada.model.GA;
import br.com.tallys.ibel.arena.chamada.model.Lider;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.RelatorioImersao;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;

@WebServlet("/ChamadaImersaoEx")
public class ChamadaImersaoEx extends HttpServlet {
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
		
		Date data = new Date(System.currentTimeMillis());
		LinkedList<Relatorio> result = new LinkedList<>();
		
		for(Teens t:teens) {
			String ctrPres = "pres_" + t.getId();
			String ctrPe   = "pe_" + t.getId();
			
			String pres = (String) request.getParameter(ctrPres);
			String pe = (String) request.getParameter(ctrPe);
			if(pe.equals("")) pe="0";
			
			boolean presenca = pres!=null?pres.equals("on"):false;
			int pont = Integer.parseInt(pe);
			
			Relatorio r = new RelatorioImersao(data, null, null, t, presenca, pont);	
			result.add(r);
			t.getRelatorios().add(r);
		}
		
		for(Relatorio rl:result) {
			try {
				RelatorioDao.addRelatorio(rl);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		response.sendRedirect("Login");
		return;
		
				
	}

}

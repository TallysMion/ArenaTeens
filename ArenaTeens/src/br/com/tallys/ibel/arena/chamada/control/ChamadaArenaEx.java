package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tallys.ibel.arena.chamada.Dao.RelatorioDao;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.RelatorioArena;
import br.com.tallys.ibel.arena.chamada.model.Sublider;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.User;

@WebServlet("/ChamadaArenaEx")
public class ChamadaArenaEx extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		String page;
		HttpSession sess = request.getSession();
		User usuario = (User) sess.getAttribute("user");
		if(usuario == null) {
			response.sendRedirect("Login");
			return;
		}
		
		Sublider sl = (Sublider) usuario;
		
		Date data = new Date(System.currentTimeMillis());
		LinkedList<Relatorio> result = new LinkedList<>();
		
		for(Teens t:sl.getGa().getTeens()) {
			
			String ctrPres 	= "pres_" + t.getId();
			String ctrMed	= "med_" + t.getId();
			String ctrPont	= "pont_" + t.getId();
			String ctrAnotD	= "anot_domingo_" + t.getId();
			String ctrAnotA	= "anot_arena_" + t.getId();
			String ctrVer	= "versId_" + t.getId(); 
			String ctrPe   	= "pe_" + t.getId();
			
			ctrPres 	= (String) request.getParameter(ctrPres);
			ctrMed		= (String) request.getParameter(ctrMed);
			ctrPont		= (String) request.getParameter(ctrPont);
			ctrAnotD	= (String) request.getParameter(ctrAnotD);
			ctrAnotA	= (String) request.getParameter(ctrAnotA);
			ctrVer		= (String) request.getParameter(ctrVer); 	if(ctrVer == null || ctrVer.equals("")) ctrVer = "0";
			ctrPe   	= (String) request.getParameter(ctrPe);		if(ctrPe  == null || ctrPe.equals(""))  ctrPe  = "0";
			
			boolean presenca		=	ctrPres!=null  ? ctrPres.equals("on")  : false;
			boolean meditacao		=	ctrMed!=null   ? ctrMed.equals("on")   : false;
			boolean pontualidade	=	ctrPont!=null  ? ctrPont.equals("on")  : false;
			boolean anotDomingo		=	ctrAnotD!=null ? ctrAnotD.equals("on") : false;
			boolean anotArena		=	ctrAnotA!=null ? ctrAnotA.equals("on") : false;
			int 	versiculos		=	Integer.parseInt(ctrVer);
			int 	pontoExtra		=	Integer.parseInt(ctrPe);
			Relatorio r = new RelatorioArena(data, null, null, t, presenca, meditacao, pontualidade, anotDomingo, anotArena, versiculos, pontoExtra); 
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

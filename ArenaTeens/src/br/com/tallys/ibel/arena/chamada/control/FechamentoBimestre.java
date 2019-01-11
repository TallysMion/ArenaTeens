package br.com.tallys.ibel.arena.chamada.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.tallys.ibel.arena.chamada.Dao.RelatorioDao;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.User;
import br.com.tallys.ibel.arena.chamada.model.Enum.UserType;

@WebServlet("/fecha_bim")
public class FechamentoBimestre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Date inic =null, end=null;
		
		HttpSession sess = request.getSession();
		User usuario = (User) sess.getAttribute("user");
		if(usuario == null || !usuario.getType().equals(UserType.Lider)) {
			response.sendRedirect("Login");
			return;
		}
		LinkedList<Relatorio> relatorios = null;
		
		try {
			relatorios = RelatorioDao.getAllRelatorios();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedList<Relatorio> aux = new LinkedList<>();
		
		for(Relatorio r:relatorios)
			if(r.getFechamentoBim()==null)
				aux.add(r);
		
		aux.sort(new Comparator<Relatorio>() {
			@Override
			public int compare(Relatorio arg0, Relatorio arg1) {
				float res = (arg0.getData().getTime() - arg1.getData().getTime());
				return res<0?-1:res>0?1:0;
			}
		});
		
		String strInic  = request.getParameter("inic");
		String strEnd  = request.getParameter("end");
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
	
		if(strInic != null) {
			try {
				inic = (Date)formatter.parse(strInic);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(strEnd != null) {
			try {
				end = (Date)formatter.parse(strEnd);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(inic == null)
			inic = aux.getFirst().getData(); 
		
		if(end == null)
			end  = aux.getLast().getData();
		
		relatorios.clear();
		for(Relatorio r:aux)
			if(r.getData().after(inic) && r.getData().before(end) || r.getData().compareTo(inic)==0 || r.getData().compareTo(end)==0)
				relatorios.add(r);
		
		sess.setAttribute("fechaBim", relatorios);
		
		//tenho inic, end e relatorios filtrado

		
		String page = 
				"<!DOCTYPE html>\r\n" + 
				"<html lang=\"pt-br\">\r\n" + 
				"<head>\r\n" + 
				"	<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + 
				"	<meta charset=\"utf-8\">\r\n" + 
				"	<meta name=\"viewport\" content=\"width-device-width, initial-scale = 1, shrink-to-fit=no\">\r\n" + 
				"	<title>Fechamento Bimestral</title>\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	<style type=\"text/css\">\r\n" + 
				"		.col-center{\r\n" + 
				"			float: none;\r\n" + 
				"			margin: 0 auto;\r\n" + 
				"		}\r\n" + 
				"	</style>\r\n" + 
				"	\r\n" + 
				"</head>\r\n" + 
				"<body class=\"p-3 mb-2 bg-dark text-white\">\r\n" + 
				"	<div class=\"container\">\r\n" + 
				"			<div class=\"row\">\r\n" + 
				"				\r\n" + 
				"				<div class=\"col-xs-12 col-sm-10 col-md-8 col-lg-6 col-xl-4 mx-auto\">\r\n" + 
				"					<div class=\"row justify-content-center\" align=\"center\">\r\n" + 
				"							<h3>Fechamento Bimestral</h3>\r\n" + 
				"					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<form action=\"fecha_bim\" method=\"POST\" data-toggle=\"validator\" role=\"form\" class=\"bootstrap-form\" novalidate>\r\n" + 
				"						<div class=\"row form-group\">\r\n" + 
				"							<div class=\"col-6\">\r\n" + 
				"								<input type=\"text\" name=\"inic\" class=\"form-control\" id=\"UpdateDataNasc\" onfocus=\"(this.type='date')\" placeholder=\"12/06/1998\" required>\r\n" + 
				"							</div>\r\n" + 
				"							<div class=\"col-6\">\r\n" + 
				"								<input type=\"text\" name=\"end\" class=\"form-control\" id=\"UpdateDataNasc\" onfocus=\"(this.type='date')\" placeholder=\"12/06/1999\" required>\r\n" + 
				"							</div>\r\n" + 
				"						</div>\r\n" +
				"						<div class=\"col-12\">\r\n" + 
				"							<div class=\"col\"><input class=\"btn btn-primary\" style=\"width: 100%\" type=\"submit\" value=\"Filtrar\" /></div>\r\n" + 
				"						</div>\r\n" + 
				"					</form>" +	
				"					<br>\r\n" + 
				"					<div class=\"row w-100 p-3 col-center\" >\r\n" + 
				"  							<div class=\"col\"><a class=\"btn btn-primary\" style=\"width: 100%\" href=\"FechaBimEx\" role=\"button\">Confirmar</a></div>\r\n" + 
				"  							<div class=\"col\"><a class=\"btn btn-primary\" style=\"width: 100%\" href=\"Login\" role=\"button\">Voltar</a></div>\r\n" + 
				"  					</div>\r\n" + 
				"					<br>\r\n" + 
				"					<ul class=\"list-group text-dark\">\r\n";
		
		for(Relatorio r:relatorios) {
			page += r.toString();
		}
		
		
		page+=	"					</ul>\r\n" + 
				"					<br>\r\n" + 
				"					<div class=\"row w-100 p-3 col-center\" >\r\n" + 
				"  							<div class=\"col\"><a class=\"btn btn-primary\" style=\"width: 100%\" href=\"FechaBimEx\" role=\"button\">Confirmar</a></div>\r\n" + 
				"  							<div class=\"col\"><a class=\"btn btn-primary\" style=\"width: 100%\" href=\"Login\" role=\"button\">Voltar</a></div>\r\n" + 
				"  					</div>\r\n" + 
				"			</div>\r\n" + 
				"	</div>\r\n" + 
				"\r\n" + 
				"<script src=\"js/jquery-3.2.1.min.js\"></script>\r\n" + 
				"<script src=\"js/popper.min.js\"></script>\r\n" + 
				"<script src=\"js/bootstrap.min.js\"></script>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n";
		
		PrintWriter out = response.getWriter();
		out.println(page);
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contoller;

import dao.DAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author dskaster
 */
@MultipartConfig()
@WebServlet(name = "UsuarioController", urlPatterns = {"/usuario",
    "/usuario/create",
    "/usuario/update",
    "/usuario/delete",
    "/usuario/read"
})
public class UsuarioController extends HttpServlet {

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vão ser
     * salvos na pasta de build do servidor. Ao limpar pode-se perder estes
     * arquivos, façam backup antes de limpar.
     */
    private static final String SAVE_DIR = "uploads";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Usuario> dao;
        Usuario usuario;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    List<Usuario> usuarioList = dao.all();
                    request.setAttribute("usuarioList", usuarioList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
                dispatcher.forward(request, response);
                break;

            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);

                break;

            case "/usuario/update":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    usuario = dao.read(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("usuario", usuario);

                    dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;

            case "/usuario/delete":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();
                    dao.delete(Integer.parseInt(request.getParameter("id")));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;

            case "/usuario/read":
                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    usuario = dao.read(Integer.parseInt(request.getParameter("id")));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(usuario);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO dao;
        Usuario usuario = new Usuario();
        HttpSession session = request.getSession();
        String nascimento;

        switch (request.getServletPath()) {
            case "/usuario/create": {
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setNome(request.getParameter("nome"));
                nascimento = request.getParameter("nascimento");
                // Upload do avatar
                String fileName = uploadFile(request);
                if (fileName != null) {
                    usuario.setAvatar(fileName);
                }

                try (DAOFactory daoFactory = new DAOFactory()) {
                    System.err.println(nascimento);
                    java.util.Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
                    usuario.setNascimento(new Date(dataNascimento.getTime()));

                    dao = daoFactory.getUsuarioDAO();

                    dao.create(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario");
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario/create");
                } catch (ParseException ex) {
                    session.setAttribute("error", "O formato de data aceito é dd/mm/aaaa. Por favor, tente novamente.");
                    response.sendRedirect(request.getContextPath() + "/usuario/create");
                }

                break;
            }

            case "/usuario/update": {
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setLogin(request.getParameter("login"));
                usuario.setNome(request.getParameter("nome"));
                nascimento = request.getParameter("nascimento");

                if (!request.getParameter("senha").isEmpty()) {
                    usuario.setSenha(request.getParameter("senha"));
                }

                try (DAOFactory daoFactory = new DAOFactory()) {
                    java.util.Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
                    usuario.setNascimento(new Date(dataNascimento.getTime()));

                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario");
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario/update?id=" + usuario.getId());
                } catch (ParseException ex) {
                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa");
                    response.sendRedirect(request.getContextPath() + "/usuario/update?id=" + usuario.getId());
                }

                break;
            }
            case "/usuario/delete": {
                String[] usuarios = request.getParameterValues("delete");

                try (DAOFactory daoFactory = new DAOFactory()) {
                    dao = daoFactory.getUsuarioDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String usuarioId : usuarios) {
                            dao.delete(Integer.parseInt(usuarioId));
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        // Pegar o caminho absoluto da aplicação
        String appPath = request.getServletContext().getRealPath("");
        // Adicionar a pasta de upload no caminho absoluto
        String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part part = request.getPart("avatar");
        String fileName = extractFileName(part);
        if (fileName != null) {
            try {
                part.write(savePath + File.separator + fileName);
                return "/" + SAVE_DIR + "/" + fileName;
            } catch (IOException ex) {
                System.err.println(ex.getLocalizedMessage());
                HttpSession session = request.getSession();
                session.setAttribute("error", ex.getLocalizedMessage());
            }
        }

        return null;
    }

    /**
     * Extrair o nome do arquivo do cabeçalho HTTP 'content-disposition'
     *
     * @param part a Part do form contendo o arquivo
     * @return O nome completo do arquivo.
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }

}

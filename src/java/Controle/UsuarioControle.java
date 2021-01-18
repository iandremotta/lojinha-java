package java.Controle;

import java.DAO.UsuarioDAO;
import java.Modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danil
 */
@WebServlet(name = "UsuarioControle", urlPatterns = {"/login", "/logout", "/cadastroUser"})
public class UsuarioControle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String url = request.getRequestURI();

        if (url.equals(request.getContextPath() + "/login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (url.equals(request.getContextPath() + "/logout")) {
            logout(request, response);
        } else {
            response.sendRedirect("erro.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        try {
            String url = request.getRequestURI();

            if (url.equals(request.getContextPath() + "/login")) {
                login(request, response);
            }else if (url.equals(request.getContextPath() + "/cadastroUser")) {
                cadastroUser(request, response);
            } else {
                response.sendRedirect("erro.jsp");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsuarioDAO dao = new UsuarioDAO();
        usuario = dao.validarLogin(usuario);

        if (usuario != null) {
            request.getSession(true).setAttribute("usuarioLogado", usuario);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("msgErro", "Dados incorretos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void cadastroUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        Usuario user = new Usuario();
        user.setEmail(request.getParameter("email"));
        user.setSenha(request.getParameter("senha"));

        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastroUser(user);

        request.setAttribute("msg", "Usuário cadastrado com sucesso!");

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("usuarioLogado");
        request.getSession().removeAttribute("total");
        request.getSession().removeAttribute("lista");
        response.sendRedirect(".");

    }

}

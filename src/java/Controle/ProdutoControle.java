package java.Controle;

import java.Modelo.Produto;
import java.DAO.ProdutoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danil
 */
@WebServlet(urlPatterns = {"/excluirProduto", "/listarProdutos", "/iniciarEdicaoProduto", "/cadastrarProduto", "/editarProduto", "/adicionarNoCarrinho", "/removerProduto", "/finalizarCompra"})
public class ProdutoControle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/excluirProduto")) {
                excluir(request, response);
            } else if (uri.equals(request.getContextPath() + "/listarProdutos")) {
                listarTodos(request, response);
            } else if (uri.equals(request.getContextPath() + "/iniciarEdicaoProduto")) {
                iniciarEdicao(request, response);
            } else if (uri.equals(request.getContextPath() + "/adicionarNoCarrinho")) {
                Carrinho(request, response);
            } else if (uri.equals(request.getContextPath() + "/removerProduto")) {
                RemoverProduto(request, response);
            } else {
                listarTodos(request, response);
            }
        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
            response.sendRedirect("erro.jsp");
            System.out.println(e.getMessage());
        }
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto p = new Produto();
        p.setId(id);

        ProdutoDAO dao = new ProdutoDAO();
        dao.excluir(p);

        response.sendRedirect("listarProdutos");

    }

    private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {

        ProdutoDAO p = new ProdutoDAO();

        List<Produto> pr = p.consultarTodos();

        request.setAttribute("produto", pr);

        request.getRequestDispatcher("listarProdutos.jsp").forward(request, response);
    }

    private void iniciarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto p = new Produto();
        p.setId(id);
        ProdutoDAO dao = new ProdutoDAO();
        p = dao.consultarPorId(p);

        request.setAttribute("produto", p);

        request.getRequestDispatcher("iniciarEdicao.jsp").forward(request, response);
    }

    public void RemoverProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        Produto produto = new Produto();
        produto.setId(Integer.parseInt(request.getParameter("id")));
        ProdutoDAO dao = new ProdutoDAO();
        produto = dao.consultarPorId(produto);

        HttpSession session = request.getSession(true);
        List<Produto> retornoLista = (List<Produto>) session.getAttribute("lista");

        Double preco = Double.parseDouble(request.getParameter("preco"));
        preco = (Double) session.getAttribute("total") - preco;
        session.setAttribute("total", preco);

        retornoLista.remove(produto);

        session.setAttribute("lista", retornoLista);

        request.getRequestDispatcher("carrinho.jsp").forward(request, response);
    }

    public void Carrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        Produto produto = new Produto();
        produto.setId(Integer.parseInt(request.getParameter("id")));
        ProdutoDAO dao = new ProdutoDAO();
        produto = dao.consultarPorId(produto);

        HttpSession session = request.getSession(true);
        List<Produto> retornoLista = (List<Produto>) session.getAttribute("lista");

        Double preco = Double.parseDouble(request.getParameter("preco"));

        if (session.getAttribute("total") != null) {
            preco = preco + (Double) session.getAttribute("total");
        }

        retornoLista = AdicionaItens(produto, retornoLista);

        session.setAttribute("lista", retornoLista);
        session.setAttribute("total", preco);

        request.getRequestDispatcher("carrinho.jsp").forward(request, response);
    }

    public List<Produto> AdicionaItens(Produto produto, List<Produto> lista) {

        if (lista == null) {

            lista = new ArrayList<>();

        }

        lista.add(produto);

        return lista;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String uri = request.getRequestURI();

            if (uri.equals(request.getContextPath() + "/cadastrarProduto")) {
                cadastrar(request, response);
            } else if (uri.equals(request.getContextPath() + "/editarProduto")) {
                confirmarEdicao(request, response);
            } else if (uri.equals(request.getContextPath() + "/finalizarCompra")) {
                finalizarCompra(request, response);
            } else {
                response.sendRedirect("erro.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        Produto produto = new Produto();
        produto.setDescricao(request.getParameter("descricao"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setUnidade(request.getParameter("unidade"));

        ProdutoDAO dao = new ProdutoDAO();
        dao.cadastrar(produto);

        response.sendRedirect("listarProdutos");

    }

    private void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
        HttpSession session = request.getSession(true);
        List<Produto> retornoLista = (List<Produto>) session.getAttribute("lista");

        retornoLista.removeAll(retornoLista);

        request.getSession().removeAttribute("total");

        request.setAttribute("msg", "Sua compra foi finalizada com sucesso. Obrigado!");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void confirmarEdicao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {

        Produto produto = new Produto();
        produto.setId(Integer.parseInt(request.getParameter("id")));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setUnidade(request.getParameter("unidade"));

        ProdutoDAO dao = new ProdutoDAO();
        dao.editar(produto);

        response.sendRedirect("listarProdutos");

    }

}

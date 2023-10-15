package control;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ordine;
import model.OrdineDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/visualizza-ordini")
public class ViewOrdini extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_Client = Integer.parseInt(request.getParameter("idCliente"));

        OrdineDAO ordineDAO = new OrdineDAO();

        List<Ordine> ordini = ordineDAO.doRetrieveByIdCliente(id_Client);

        request.setAttribute("listaOrdini", ordini);

        String address = "/WEB-INF/visualizzaOrdini.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

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

//solo per ADMIN
@WebServlet("/showall-ordini")
public class showAllOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdineDAO ord = new OrdineDAO();

        List<Ordine> ordini = ord.doRetriveAll();

        request.setAttribute("ordini", ordini);

        String address = "/WEB-INF/gestione/showAllOrdini.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

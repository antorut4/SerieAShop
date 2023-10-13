package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-prodotto")
public class DeleteProdotto extends HttpServlet {
    private ProdottoDAO prodottoDAO; // Oggetto per accedere ai dati dei prodotti

    @Override
    public void init() throws ServletException {
        prodottoDAO = new ProdottoDAO();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/WEB-INF/HomeAdmin.jsp";
        int prodottoId = Integer.parseInt(request.getParameter("id"));

        Prodotto prodotto = null;
        try {
            prodotto = prodottoDAO.deleteProdotto(prodottoId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(prodotto != null){
            request.setAttribute("prodottoEliminato", prodotto);
        }else {
            String errorMessage = "Prodotto non trovato, id non presente nel database";
            request.setAttribute("errorDeleteID", errorMessage);
        }


        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
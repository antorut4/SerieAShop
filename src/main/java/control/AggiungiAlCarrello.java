package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.ProdottiCarrello;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/aggiungi-al-carrello")
public class AggiungiAlCarrello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String address = "";


        Prodotto prod;

        prod=(Prodotto)request.getSession().getAttribute("prodAgg");

        Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
        ProdottiCarrello prodottiCarrello=new ProdottiCarrello();
        prodottiCarrello.setIdCarrello(carrello.getIdCarrello());
        prodottiCarrello.setIdProdotto(prod.getId());
        prodottiCarrello.setQuantita(1);

        carrello.addProdotto(prod);


        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
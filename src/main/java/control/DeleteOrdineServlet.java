package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cancella-ordine")
public class DeleteOrdineServlet extends HttpServlet {
    private OrdineDAO ordineDAO;

    public void init(){
        this.ordineDAO = new OrdineDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
        int idClient = Integer.parseInt(request.getParameter("idCliente"));
        request.setAttribute("idCliente", idClient);
        String address = "/visualizza-ordini";

        Ordine ordine = ordineDAO.doRetrieveById(idOrdine); //per rollback salvo prima di cancellare

        CarrelloDAO cdao = new CarrelloDAO();
        int idCarrello = ordine.getIdCarrello();
        try {
            Carrello carrello = cdao.doRetrieveById(idCarrello);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ProdottiCarrelloDAO pdDao = new ProdottiCarrelloDAO();
        List<ProdottiCarrello> carrelloList = pdDao.doRetriveAllById(idCarrello);

        ProdottoDAO prodottoDAO = new ProdottoDAO();

        //cancella prodottiCarrello di quell'ordine
        // riaggiunge la quantità dei prodotti nel DB
        for(ProdottiCarrello proCarrDB : carrelloList){

            int idpro = proCarrDB.getIdProdotto();
            Prodotto prodDB = null;
            try {
                prodDB = prodottoDAO.doRetrieveById(idpro);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            prodDB.setQuantita( prodDB.getQuantita() + proCarrDB.getQuantita());
            prodottoDAO.doUpdateQuantita(prodDB);

            int idProdCarr = proCarrDB.getIdProdCarr();
            pdDao.deleteProdottoCarrello(idProdCarr);
        }

        ordineDAO.deleteOrdine(idOrdine);
        cdao.deleteCarrello(idCarrello);
        //fine rollback da ordine

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

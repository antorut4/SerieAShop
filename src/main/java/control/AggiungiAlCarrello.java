package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/aggiungi-al-carrello")
public class AggiungiAlCarrello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        int prodottoId = Integer.parseInt(request.getParameter("idProd"));
        System.out.println(prodottoId);
        String address = "";

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto = null;
        try {
            prodotto = prodottoDAO.doRetrieveById(prodottoId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (prodotto != null ) {

            HttpSession session = request.getSession();
            Carrello carrello = (Carrello) session.getAttribute("carrello");

            if (carrello == null) {
                carrello = new Carrello();
                prodotto.setQuantita(1);
                carrello.addProdotto(prodotto);

                session.setAttribute("carrello", carrello);
                //per stampa conferma prodotto
                request.setAttribute("prodotto", prodotto);

            }else{//carrello esiste già
                //siccome prodotto è quello da database, lo cerco e lo prendo dal carrello
                Prodotto prodCarr = carrello.getProdottoById(prodotto.getId());

                if(prodCarr != null){ //se prodotto già presente aumento quantità

                    prodCarr.setQuantita(prodCarr.getQuantita() + 1);
                    //carrello.addProdotto(prodCarr);

                }else { //se prodotto ancora non presente lo aggiungo
                    prodCarr = prodotto.cloneProd();
                    prodCarr.setQuantita(1);
                    carrello.addProdotto(prodCarr);
                }

                request.setAttribute("prodotto", prodCarr);
                session.setAttribute("carrello", carrello);

            }

            //per stampa in carrello.jsp
            List<Prodotto> carrelloProd = carrello.getCarrello();
            session.setAttribute("carrelloProd", carrelloProd);

            address = "WEB-INF/index.jsp";
        } else {
            // Reindirizza a una pagina di errore o messaggio di prodotto non trovato
            address = "./WEB-INF/pagine/ErrorePage.jsp";
        }


        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
package control;

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

@WebServlet("/carrello-servlet")
public class CarrelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "./WEB-INF/pagine/carrello.jsp";

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        User user = (User) request.getSession().getAttribute("cliente");


        if(user != null) {
            if (carrello != null) {
                CarrelloDAO cdao = new CarrelloDAO();

                String username = user.getUsername();

                //controllo e aggiornamento sui prezzi dal database
                List<Prodotto> prodList = carrello.getCarrello();
                ProdottoDAO pdao = new ProdottoDAO();
                for (Prodotto prodotto : prodList){
                    int id = prodotto.getId();
                    Double prezzoCarr = prodotto.getPrezzo();
                    Prodotto prodDataBase = null;
                    try {
                        prodDataBase = pdao.doRetrieveById(id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Double prezzDB = prodDataBase.getPrezzo();
                    if( !(prezzoCarr.equals(prezzDB)) ){
                        prodotto.setPrezzo(prezzDB);
                    }
                }
                carrello.setCarrello(prodList);

                Double costo = carrello.sumPrezzi();
                carrello.setTotale(costo);
                carrello.setUsername(username);

                int id = carrello.getIdCarrello();
                Carrello carrelloTest = null;
                try {
                    carrelloTest = cdao.doRetrieveById(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(carrelloTest != null ){
                    cdao.doUpdateCarrello(carrello);
                }else{
                    cdao.doSave(carrello);
                }

                request.getSession().setAttribute("carrello", carrello);

                //salvare prodotti in prodottiCarrello nel DB
                ProdottiCarrelloDAO pdDao = new ProdottiCarrelloDAO();
                List<Prodotto> prodottiList = carrello.getCarrello();
                int idCarr = carrello.getIdCarrello();
                ProdottiCarrello prodCarrello  = new ProdottiCarrello();
                for( Prodotto prodSession : prodottiList) {

                    prodCarrello.setQuantita(prodSession.getQuantita());
                    prodCarrello.setIdCarrello(idCarr);
                    prodCarrello.setIdProdotto(prodSession.getId());

                    ProdottiCarrello pcTest = pdDao.doRetrieveByCarrelloAndProdotto(idCarr, prodSession.getId());
                    if(pcTest != null){
                        pdDao.doUpdateProdottiCarrello(prodCarrello);
                    }else {
                        pdDao.doSaveProdottoCarrello( prodCarrello);
                    }
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

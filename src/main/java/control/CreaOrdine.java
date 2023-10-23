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
import java.util.Date;
import java.util.List;

@WebServlet("/crea-ordine")
public class CreaOrdine extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        int idCarrello = carrello.getIdCarrello();
        Double costo = carrello.sumPrezzi();
        carrello.setTotale(costo);

        User cliente = (User) request.getSession().getAttribute("cliente");
        String idCliente = cliente.getUsername();
        String spedizione = cliente.getIndirizzo();
        String pagamento = request.getParameter("metodoPagamento");

        Date data = new Date();

        Ordine ordine = new Ordine();
        ordine.setTotale(costo);
        ordine.setDataOrd(data);
        ordine.setPagamento(pagamento);
        ordine.setSpedizione(spedizione);
        ordine.setIdCarrello(idCarrello);
        ordine.setIdCliente(idCliente);

        //Controllo presenza prodotti nel database magazzino
        List<Prodotto> carrelloList = carrello.getCarrello();
        ProdottoDAO pdao = new ProdottoDAO();
        int idPro = 0;
        boolean test = true;
        for(Prodotto proTest : carrelloList){
            idPro = proTest.getId();
            Prodotto proDatabase = null;
            try {
                proDatabase = pdao.doRetrieveById(idPro);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(!(proTest.getQuantita() <= proDatabase.getQuantita())){
                test = false;
                break;
            }
        }
        if(test){

            OrdineDAO odao = new OrdineDAO();
            odao.doSave(ordine);

            //prodotto va sottratta quantià DB
            int id;
            for(Prodotto proTest : carrelloList){
                id = proTest.getId();
                Prodotto proDatabase = null;
                try {
                    proDatabase = pdao.doRetrieveById(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                proDatabase.setQuantita(proDatabase.getQuantita() - proTest.getQuantita());
                pdao.doUpdateQuantita(proDatabase);
            }

            request.getSession().removeAttribute("carrello"); //Essi vengono rimossi perché così il cliente può iniziare una nuova sessione senza prodotti nel carrello
            request.getSession().removeAttribute("carrelloProd");
            String conferma = "true";
            request.getSession().setAttribute("confermaOrdine", conferma);

        }else {
            Prodotto prodotto= null;
            try {
                prodotto = pdao.doRetrieveById(idPro);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String nome = prodotto.getNome();
            int quantitaMax =  prodotto.getQuantita();

            String errorOrdine = "Sono disponibili solamente " + quantitaMax + " unità del prodotto " + nome + ".";

            request.setAttribute("errorOrdine", errorOrdine);

        }


        RequestDispatcher rd = request.getRequestDispatcher("/carrello-servlet");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;

@WebServlet("/manage-prodotto-carrello")

public class ManageProdottoCarrello extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ciao");
        String scelta=request.getParameter("valore");
        int idProdotto=Integer.parseInt(request.getParameter("Prod"));
        Carrello carrello= (Carrello)request.getSession().getAttribute("carrello");
        ProdottiCarrelloDAO pcdao= new ProdottiCarrelloDAO();
        ProdottiCarrello pc;
        if(carrello!=null){
            switch (scelta) {
                case "rimuovi": {
                    Prodotto pr = carrello.removeProdotto(idProdotto);

                    User user = (User) request.getSession().getAttribute("user");
                    if (user != null) {
                        ProdottiCarrello proCarDB = pcdao.doRetrieveByCarrelloAndProdotto(carrello.getIdCarrello(), idProdotto);
                        pcdao.deleteProdottoCarrello(proCarDB.getIdProdotto());
                    }


                    request.getSession().setAttribute("carrello", carrello);
                    if (carrello.isEmpty()) {
                        request.getSession().removeAttribute("carrello");
                    }
                }
                    break;

                case "meno":{
                    pc=pcdao.doRetrieveByCarrelloAndProdotto(carrello.getIdCarrello(), idProdotto);
                    pc.setQuantita(pc.getQuantita()-1);

                    if(pc.getQuantita()==0){
                        pcdao.deleteProdottoCarrello(idProdotto);
                    }else{
                        pcdao.doUpdateProdottiQuantita(pc);
                    }
                }
                break;

                case"piu":{
                    pc=pcdao.doRetrieveByCarrelloAndProdotto(carrello.getIdCarrello(), idProdotto);
                    pc.setQuantita(pc.getQuantita()+1);
                    pcdao.doUpdateProdottiQuantita(pc);
                    if(pc.getQuantita()==0){
                        pcdao.deleteProdottoCarrello(idProdotto);
                    }
                }
                break;
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher("/carrello-servlet");
        rd.forward(request,response);
    }


}

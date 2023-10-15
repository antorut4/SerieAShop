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
        String scelta=request.getParameter("manage");
        int idProdotto= Integer.parseInt(request.getParameter("prodManage"));
        Carrello carrello= (Carrello)request.getSession().getAttribute("carrello");
        ProdottiCarrelloDAO pcdao= new ProdottiCarrelloDAO();

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

                case "abbassa":{
                    carrello.abbassaQuantitaProdotto(idProdotto);
                    request.getSession().setAttribute("carrello",carrello);
                    if(carrello.isEmpty()){
                        request.getSession().removeAttribute("carrello");
                    }

                    //se la quantita prodotto arriva a 0, lo cancella
                    Prodotto prodotto=carrello.getProdottoById(idProdotto);
                    User user=(User)request.getSession().getAttribute("User");
                    if(prodotto == null && user!=null){
                        ProdottiCarrello proCarDB= pcdao.doRetrieveByCarrelloAndProdotto(carrello.getIdCarrello(),idProdotto);
                        pcdao.deleteProdottoCarrello(idProdotto);

                    }
                }
                break;

                case"alza":{
                    carrello.alzaQuantitaProdotto(idProdotto);
                    request.getSession().setAttribute("carrello",carrello);
                }
                break;
            }
        }
        RequestDispatcher rd=request.getRequestDispatcher("/carrello-servlet");
        rd.forward(request,response);
    }


}

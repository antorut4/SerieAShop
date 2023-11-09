package control;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import javax.swing.text.Caret;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/crea-ordine")
public class CreaOrdine extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = null;
        ProdottiCarrelloDAO pdao=new ProdottiCarrelloDAO();


        String via = request.getParameter("address");
        String city = request.getParameter("city");
        String cap = request.getParameter("cap");
        String card = request.getParameter("card-num");
        String cvc = request.getParameter("security");
        String scad=request.getParameter("scad");
        String button = request.getParameter("button");
        int totale=(int)request.getSession().getAttribute("totale");

        List<String> errori=new ArrayList<>();
        //controlli
        if(via.isEmpty()){
            errori.add("campila il campo indirizzo");
        }
        if(city.isEmpty()){
            errori.add("compila il campo città");
        }
        if(cap.isEmpty()){
            errori.add("Compila il campo cap");
        }
        if(card.isEmpty()){
            errori.add("compila il campo numero carta");
        }
        if(scad.isEmpty()){
            errori.add("compila il campo scadenza");
        }
        if(cvc.isEmpty()){
            errori.add("compila il campo cvc");
        }
        if(!isValidAddress(via)){
            errori.add("compila correttamente il campo indirizzo");
        }
        if(!isValidCity(city)){
            errori.add("compila correttamente il campo citta");
        }
        if(!isValidCap(cap)){
            errori.add("compila correttamente il campo cap");
        }
        if(!isValidCard(card)){
            errori.add("compila correttamente il campo numero carta");
        }
        if(!isValidScad(scad)){
            errori.add("compila correttamente il campo scadenza");
        }
        if(!isValidCvc(cvc)){
            errori.add("compila correttamente il campo cvc");
        }

        if(!errori.isEmpty()){
            request.getSession().setAttribute("errori",errori);
            address="WEB-INF/Errorepage.jsp";
            RequestDispatcher rd= request.getRequestDispatcher(address);
            rd.forward(request,response);
        }else {


            Ordine ordine = new Ordine();
            OrdineDAO ordineDAO = new OrdineDAO();
            List<ProdottiCarrello> p = (List<ProdottiCarrello>) request.getSession().getAttribute("prodottiDaStampare");
            Calendar calendar = Calendar.getInstance();
            Date data = new Date(calendar.getTime().getTime());
            User user = (User) request.getSession().getAttribute("user");
            Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
            List<ProdottiCarrello> pc = new ArrayList<>();
            ProdottiCarrelloDAO pcdao = new ProdottiCarrelloDAO();
            String prodotti = "";

            if (button.equals("back")) {
                address = "/WEB-INF/carrello.jsp";
            } else {
                ordine.setIdCliente(user.getUsername());
                ordine.setIdCarrello(carrello.getIdCarrello());
                ordine.setSpedizione(via + " " + city + " " + cap);
                ordine.setPagamento("Numero Carta: " + card + " Scad: " + scad + " CVC: " + cvc);
                ordine.setTotale(totale);
                ordine.setDataOrd(data);
                pc = pcdao.doRetriveAllById(carrello.getIdCarrello());
                for (ProdottiCarrello ps : pc) {
                    prodotti += "Id prodotto: " + Integer.toString(ps.getIdProdotto()) + ", quantita': " + Integer.toString(ps.getQuantita()) + ", taglia: " + ps.getTaglia() + "\r\n";
                }
                ordine.setProdotti(prodotti);
                ordineDAO.doSave(ordine);
                address = "/WEB-INF/index.jsp";
                pdao.deleteProdottoCarrelloByCarrello(carrello.getIdCarrello());
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }
    public static boolean isValidAddress(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]{3,}$");
        return pattern.matcher(password).matches();
    }
    public static boolean isValidCity(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]{3,}$");
        return pattern.matcher(password).matches();
    }
    public static boolean isValidCap(String password) {
        Pattern pattern = Pattern.compile("^[0-9]{3}$");
        return pattern.matcher(password).matches();
    }
    public static boolean isValidCard(String password) {
        Pattern pattern = Pattern.compile("^[0-9]{16}$");
        return pattern.matcher(password).matches();
    }
    public static boolean isValidScad(String password) {
        Pattern pattern = Pattern.compile("^([0-9]{2})+/+([0-9]{2})$");
        return pattern.matcher(password).matches();
    }
    public static boolean isValidCvc(String password) {
        Pattern pattern = Pattern.compile("^[0-9]{3}$");
        return pattern.matcher(password).matches();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

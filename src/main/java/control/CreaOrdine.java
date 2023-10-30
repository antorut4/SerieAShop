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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet("/crea-ordine")
public class CreaOrdine extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = null;

        String via = request.getParameter("address");
        String city = request.getParameter("city");
        String cap = request.getParameter("cap");
        String card = request.getParameter("card-num");
        String cvc = request.getParameter("security");
        String button = request.getParameter("button");
        int totale=(int)request.getSession().getAttribute("totale");
        Ordine ordine = new Ordine();
        OrdineDAO ordineDAO = new OrdineDAO();

        Date data = new Date();

        User user = (User) request.getSession().getAttribute("user");
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        if(button.equals("back")){
            address = "/WEB-INF/carrello.jsp";
        } else{
            ordine.setIdCliente(user.getUsername());
            ordine.setIdCarrello(carrello.getIdCarrello());
            ordine.setSpedizione(via + " " + city + " " + cap);
            ordine.setPagamento("Numero Carta: " + card + " CVC: " + cvc);
            ordine.setTotale(totale);
            ordine.setDataOrd(data);
            ordineDAO.doSave(ordine);
            address = "/WEB-INF/index.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

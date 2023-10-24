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
import java.util.ArrayList;
import java.util.List;
@WebServlet("/prodotto-singolo")
public class ProdottoSingolo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address="WEB-INF/index.jsp";
        int idProdotto=Integer.parseInt(request.getParameter("idProdotto"));
        Prodotto prodotto=new Prodotto();
        ProdottoDAO pdao= new ProdottoDAO();
        try {
            prodotto=pdao.doRetrieveById(idProdotto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(prodotto!=null){
            request.getSession().setAttribute("prodotto",prodotto);
            address="WEB-INF/prodottoSingolo.jsp";
        }
        RequestDispatcher rd= request.getRequestDispatcher(address);
        rd.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

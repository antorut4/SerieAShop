package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ConPool;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/modifica-prodotto")
public class UpdateProdotto extends HttpServlet {

    private ProdottoDAO prodottoDAO;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/WEB-INF/HomeAdmin.jsp";


        System.out.println(request.getParameter("idp"));
        String nomeProdotto= request.getParameter("nome");
        System.out.println(request.getParameter("nome"));
        int prodottoId = Integer.parseInt(request.getParameter("idp"));
        int prezzo=Integer.parseInt(request.getParameter("prezzo"));
        String descrizione=request.getParameter("descrizione");
        String categoria=request.getParameter("categoria");
        int quantita=Integer.parseInt(request.getParameter("quantita"));

        Prodotto p=new Prodotto();
        p.setCategoria(categoria);
        p.setDescrizione(descrizione);
        p.setId(prodottoId);
        p.setPrezzo((double) prezzo);
        p.setNome(nomeProdotto);
        p.setQuantita(quantita);

        ProdottoDAO pdao=new ProdottoDAO();
        p=pdao.doUpdateProdotto(p);

        if(p!=null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
            rd.forward(request, response);
        }else{
            address="/WEB-INF/Errorepage.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
            rd.forward(request, response);
        }



    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}


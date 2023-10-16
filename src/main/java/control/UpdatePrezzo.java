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

    @WebServlet("/update-prezzo")
    public class UpdatePrezzo extends HttpServlet {

        private ProdottoDAO prodottoDAO;

        @Override
        public void init() throws ServletException {
            prodottoDAO = new ProdottoDAO();
        }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String address = "/WEB-INF/HomeAdmin.jsp";
            int prodottoId = Integer.parseInt(request.getParameter("idProdotto"));
            if(request.getParameter("prezzo")!=null) {
                double prezzoprodotto = Double.parseDouble(request.getParameter("prezzo"));
            }else System.out.println("la stringa è vuota");
            double prezzoprodotto = Double.parseDouble(request.getParameter("prezzo"));
            Prodotto prodotto = null;
            try {
                prodotto = prodottoDAO.doUpdatePrezzo(prezzoprodotto, prodottoId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (prodotto != null) {
                request.setAttribute("prodottoAggiornato", prodotto);
            } else {
                String errorMessage = "Prodotto non trovato, id non presente nel database";
                request.setAttribute("errorUpdateID", errorMessage);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
            rd.forward(request, response);
        }
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

    }


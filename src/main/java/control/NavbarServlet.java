package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SquadraDAO;
import model.Squadra;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="navbar", value="/nav")
public class NavbarServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address="/WEB-INF/nav.jsp";

        //carico immagini loghi
        List<Squadra> squadre = new ArrayList<>();
        SquadraDAO sdao=new SquadraDAO();

        squadre=sdao.doSave();
        //creo la sessione se non esiste
        HttpSession session = request.getSession(true);

        //associo l'attributo squadre alla sessione
        session.setAttribute("squadre", squadre);
        RequestDispatcher dispatcher=request.getRequestDispatcher(address);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
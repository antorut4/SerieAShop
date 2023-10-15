package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/elenco-servlet")
public class ElencoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("elenco");
        String address = "./WEB-INF/index.jsp";

        switch (check){
            case "uomo" : address =  "./WEB-INF/uomo.jsp"; break;
            case "nike": address = "./WEB-INF/donna.jsp"; break;
            case "adidas": address = "./WEB-INF/bambini.jsp"; break;
            case "t-shirt" : address = "./WEB-INF/t-shirt.jsp"; break;
            case "retro" : address = "./WEB-INF/retro.jsp"; break;
            case "saldi" : address = "./WEB-INF/saldi.jsp"; break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

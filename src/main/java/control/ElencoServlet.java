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
        String check = request.getParameter("buttonName");
        System.out.println(check);

        if (check != null) {
            String address = "./WEB-INF/index.jsp";

            switch (check) {
                case "uomo" : address =  "./WEB-INF/WorkInProgress.jsp"; break;
                case "donna": address = "./WEB-INF/WorkInProgress.jsp"; break;
                case "bambino": address = "./WEB-INF/WorkInProgress.jsp"; break;
                case "t-shirt" : address = "./WEB-INF/t-shirt.jsp"; break;
                case "retro" : address = "./WEB-INF/retro.jsp"; break;
                case "saldi" : address = "./WEB-INF/saldi.jsp"; break;
            }

            RequestDispatcher rd = request.getRequestDispatcher(address);
            rd.forward(request, response);
        } else {
            response.getWriter().write("Parametro 'ButtonName' mancante o nullo.");
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

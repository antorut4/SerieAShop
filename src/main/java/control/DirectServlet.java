package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet("/direct-servlet")
public class DirectServlet extends HttpServlet {

    public  void init(){}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address="WEB-INF/account.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

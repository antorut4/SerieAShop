package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-user")
public class DeleteUser extends HttpServlet {
    private UserDAO userDAO;

    public void init() throws ServletException{
        this.userDAO=new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address="/WEB-INF/successo.jsp";
        HttpSession session= request.getSession();
        User user= (User) session.getAttribute("user");
        try {
            this.userDAO.deleteUser(user.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher r= request.getRequestDispatcher(address);
        r.forward(request,response);
    }


}

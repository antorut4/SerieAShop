package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloDAO;
import model.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/log-in")
public class LogIn extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String address="/WEB-INF/index.jsp";
            System.out.println("entro");

            HttpSession session = request.getSession();

            String username=request.getParameter("logusername");
            String password=request.getParameter("logpassword");


            UserDAO rd= new UserDAO();
            User user = null;
            try {
                user = rd.doRetrieveByUsername(username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(user == null) {
                String error = "Credenziali errate o Account Inesistente. Riprova o registrati";
                request.setAttribute("errorMessageLogin", error);
            }else{
                if(username.equals(user.getUsername())){
                    if(password.equals(user.getPassword())){
                        if(user.isAdmin()){
                            request.getSession().setAttribute("user",user);
                            address="/WEB-INF/HomeAdmin.jsp";
                            System.out.println(address);
                        }else {
                            // Se l'utente ha già effettuato l'accesso, reindirizzarlo alla pagina utente
                            request.getSession().setAttribute("user", user);
                            CarrelloDAO cdao=new CarrelloDAO();
                            Carrello carrello;
                            carrello=cdao.doRetriveByUsername(user.getUsername());
                            request.getSession().setAttribute("carrello",carrello);
                            address = "/WEB-INF/account.jsp";
                            System.out.println(address);
                        }
                    }else{
                        String error="Credenziali errate o Account Inesistente. Riprova o registrati";
                        request.setAttribute("errorMessage", error);
                    }
                }
            }

            RequestDispatcher r=request.getRequestDispatcher(address);
            r.forward(request,response);
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
        }
    }


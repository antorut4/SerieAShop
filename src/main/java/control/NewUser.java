package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/new-user")
public class NewUser extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;

        if( request.getParameter("verifica") == null){
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String username= request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String indirizzo = request.getParameter("indirizzo");
        String telefono = request.getParameter("telefono");

        User user;

        String errore = "";


            if(nome.length()<1 || nome.length()>50)
                errore += "Nome digitato troppo breve o troppo lungo";

            if(cognome.length()<1 || cognome.length()>50)
                errore += "Cognome digitato troppo breve o troppo lungo";

            if(email.length()<8 || cognome.length()>100)
                errore += "Email digitata troppo breve o troppo lunga";

            if(username.isEmpty() || username.length()>25){
                errore+="Username digitato troppo breve o troppo lungo";
            }

            if(password.length()<4 || password.length()>50)
                errore += "Password digitata troppo breve o troppo lunga";

            if(email.length()<4 || email.length()>100)
                errore += "Email digitata troppo breve o troppo lunga";

            if(indirizzo.length()<4 || indirizzo.length()>100)
                errore += "Indirizzo digitato troppo breve o troppo lungo";

            if(telefono.length() != 10)
                errore += "Numero di telefono digitato errato";

        if(!errore.equals(""))
        {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher("/WEB-INF/Errorepage.jsp?errore="+errore);
            dispatcher.forward(request, response);


        }

                user= new User();

                user.setNome(nome);
                user.setCognome(cognome);
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setIndirizzo(indirizzo);
                user.setTelefono(telefono);

            UserDAO udao = new UserDAO();

            try {
                udao.doSave(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            address = "/WEB-INF/index.jsp";
            request.setAttribute("cliente", user);
            request.getSession().setAttribute("cliente", user);

        } else {
            // I dati non sono validi, mostra la pagina di registrazione con il messaggio di errore
            address = "/WEB-INF/newUser.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
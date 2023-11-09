package control;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Carrello;
import model.CarrelloDAO;
import model.User;
import model.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/new-user")
public class NewUser extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String indirizzo = request.getParameter("indirizzo");
        String telefono = request.getParameter("telefono");

        User user;
        List<String> errori = new ArrayList<>();

        if (username.isEmpty()) {
            errori.add("Devi compilare il campo username");
        }
        if (!isValidUsername(username)) {
            errori.add("Devi compilare correttamente il campo username");
        }
        if (email.isEmpty()) {
            errori.add("Devi compilare il campo email");
        }
        if (!isValidEmail(email)) {
            errori.add("Devi compilare correttamente il campo email");
        }
        if (!isValidPassword(password)) {
            errori.add("Devi compilare correttamente il campo password");
        }
        if (password.isEmpty()) {
            errori.add("Devi compilare il campo password");
        }
        if (nome.isEmpty()) {
            errori.add("Devi compilare il campo nome");
        }
        if (!isValidName(nome)) {
            errori.add("Devi compilare correttamente il campo nome");
        }
        if (cognome.isEmpty()) {
            errori.add("Devi compilare il campo cognome");
        }
        if (!isValidSurname(cognome)) {
            errori.add("Devi compilare correttamente il campo cognome");
        }
        if (!isValidAddress(indirizzo)) {
            errori.add("Devi compilare correttamente il campo indirizzo");
        }
        if (indirizzo.isEmpty()) {
            errori.add("Devi compilare il campo indirizzo");
        }
        if (!isValidPhoneNumber(telefono)) {
            errori.add("Devi compilare correttamente il campo numero di telefono");
        }
        if (telefono.isEmpty()) {
            errori.add("Devi compilare il campo numero di telefono");
        }

        System.out.println();

        if (!errori.isEmpty()) {
            request.getSession().setAttribute("errori", errori);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Errorepage.jsp");
            dispatcher.forward(request, response);
        }else {

            user = new User();

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
            CarrelloDAO cdao = new CarrelloDAO();
            Carrello carrello = new Carrello(username);
            cdao.doCreateCarrello(carrello);
            carrello = cdao.doRetriveByUsername(username);
            address = "/WEB-INF/account.jsp";
            request.getSession().setAttribute("carrello", carrello);
            request.setAttribute("user", user);
            request.getSession().setAttribute("user", user);


            RequestDispatcher rd = request.getRequestDispatcher(address);
            rd.forward(request, response);
        }
    }





    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._]+$");
        return pattern.matcher(username).matches();
    }
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
        return pattern.matcher(email).matches();
    }
    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+]).{8,}$");
        return pattern.matcher(password).matches();
    }
    public static boolean isValidName(String nome) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        return pattern.matcher(nome).matches();
    }

    public static boolean isValidSurname(String cognome) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        return pattern.matcher(cognome).matches();
    }
    public static boolean isValidAddress(String indirizzo) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
        return pattern.matcher(indirizzo).matches();
    }
    public static boolean isValidPhoneNumber(String numero_telefono) {
        Pattern pattern = Pattern.compile("^(\\+?[0-9]{1,3})?[0-9]{10}$");
        return pattern.matcher(numero_telefono).matches();
    }


}
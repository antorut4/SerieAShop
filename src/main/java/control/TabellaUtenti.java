package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TabellaUtenti", urlPatterns = {"/TabellaUtenti"})
public class TabellaUtenti extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();
        List<User> utenti = userDAO.doRetriveAll();

        // Set the content type of the response to HTML
        response.setContentType("text/html");

        // Get the PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Create a string containing the HTML table to display order data
        StringBuilder tableHTML = new StringBuilder();
        tableHTML.append("<table class='table'>");
        tableHTML.append("<thead>");
        tableHTML.append("<tr>");
        tableHTML.append("<th>Username</th>");
        tableHTML.append("<th>Nome</th>");
        tableHTML.append("<th>Cognome</th>");
        tableHTML.append("<th>Email</th>");
        tableHTML.append("<th>Telefono</th>");
        tableHTML.append("<th>Indirizzo</th>");
        tableHTML.append("<th>Password</th>");
        tableHTML.append("</tr>");
        tableHTML.append("</thead>");
        tableHTML.append("<tbody>");

        // Populate the table with order data
        for (User u : utenti) {
            tableHTML.append("<tr>");
            tableHTML.append("<td>").append(u.getUsername()).append("</td>");
            tableHTML.append("<td>").append(u.getNome()).append("</td>");
            tableHTML.append("<td>").append(u.getCognome()).append("</td>");
            tableHTML.append("<td>").append(u.getEmail()).append("</td>");
            tableHTML.append("<td>").append(u.getTelefono()).append("</td>");
            tableHTML.append("<td>").append(u.getIndirizzo()).append("</td>");
            tableHTML.append("<td>").append(u.getPassword()).append("</td>");
            tableHTML.append("</tr>");
        }

        tableHTML.append("</tbody>");
        tableHTML.append("</table>");

        // Write the HTML table to the response
        out.println(tableHTML.toString());
    }
}

package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TabellaProdotti", urlPatterns = {"/TabellaProdotti"})
public class TabellaProdotti extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProdottoDAO pDAO = new ProdottoDAO();
        List<Prodotto> prodotti = pDAO.doRetriveAll();

        // Set the content type of the response to HTML
        response.setContentType("text/html");

        // Get the PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Create a string containing the HTML table to display product data
        StringBuilder tableHTML = new StringBuilder();
        tableHTML.append("<table class='table'>");
        tableHTML.append("<thead>");
        tableHTML.append("<tr>");
        tableHTML.append("<th>ID</th>");
        tableHTML.append("<th>Nome</th>");
        tableHTML.append("<th>Descrizione</th>");
        tableHTML.append("<th>Prezzo</th>");
        tableHTML.append("<th>Quantita</th>");
        tableHTML.append("<th>Immagine</th>");
        tableHTML.append("<th>Categoria</th>");
        tableHTML.append("</tr>");
        tableHTML.append("</thead>");
        tableHTML.append("<tbody>");

        // Populate the table with product data
        for (Prodotto p : prodotti) {
            tableHTML.append("<tr>");
            tableHTML.append("<td>").append(p.getId()).append("</td>");
            tableHTML.append("<td>").append(p.getNome()).append("</td>");
            tableHTML.append("<td>").append(p.getDescrizione()).append("</td>");
            tableHTML.append("<td>").append(p.getPrezzo()).append("</td>");
            tableHTML.append("<td>").append(p.getQuantita()).append("</td>");
            tableHTML.append("<td>").append(p.getImg()).append("</td>");
            tableHTML.append("<td>").append(p.getCategoria()).append("</td>");
            tableHTML.append("</tr>");
        }

        tableHTML.append("</tbody>");
        tableHTML.append("</table>");

        // Write the HTML table to the response
        out.println(tableHTML.toString());
    }
}

package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ordine;
import model.OrdineDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TabellaOrdini", urlPatterns = {"/TabellaOrdini"})
public class TabellaOrdini extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrdineDAO ordineDAO = new OrdineDAO();
        List<Ordine> ordini = ordineDAO.doRetriveAll();

        // Set the content type of the response to HTML
        response.setContentType("text/html");

        // Get the PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Create a string containing the HTML table to display order data
        StringBuilder tableHTML = new StringBuilder();
        tableHTML.append("<table class='table'>");
        tableHTML.append("<thead>");
        tableHTML.append("<tr>");
        tableHTML.append("<th>ID Ordine</th>");
        tableHTML.append("<th>Totale Ordine</th>");
        tableHTML.append("<th>Data Ordine</th>");
        tableHTML.append("<th>Pagamento</th>");
        tableHTML.append("<th>Spedizione</th>");
        tableHTML.append("<th>ID Carrello</th>");
        tableHTML.append("<th>ID Cliente</th>");
        tableHTML.append("</tr>");
        tableHTML.append("</thead>");
        tableHTML.append("<tbody>");

        // Populate the table with order data
        for (Ordine ordine : ordini) {
            tableHTML.append("<tr>");
            tableHTML.append("<td>").append(ordine.getId()).append("</td>");
            tableHTML.append("<td>").append(ordine.getTotale()).append("</td>");
            tableHTML.append("<td>").append(ordine.getDataOrd()).append("</td>");
            tableHTML.append("<td>").append(ordine.getPagamento()).append("</td>");
            tableHTML.append("<td>").append(ordine.getSpedizione()).append("</td>");
            tableHTML.append("<td>").append(ordine.getIdCarrello()).append("</td>");
            tableHTML.append("<td>").append(ordine.getIdCliente()).append("</td>");
            tableHTML.append("</tr>");
        }

        tableHTML.append("</tbody>");
        tableHTML.append("</table>");

        // Write the HTML table to the response
        out.println(tableHTML.toString());
    }
}

package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class TabellaProdotti extends HttpServlet {

    public static String stampa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ProdottoDAO pDAO=new ProdottoDAO();
        List<Prodotto> prodotti=new ArrayList<>();

        prodotti=pDAO.doRetriveAll();

        // Crea una tabella HTML per visualizzare i dati dei prodotti
        PrintWriter out = response.getWriter();
        String tableHTML = "<table class='table'>";
        tableHTML += "<thead>";
        tableHTML += "<tr>";
        tableHTML += "<th>ID</th>";
        tableHTML += "<th>Nome</th>";
        tableHTML += "<th>Descrizione</th>";
        tableHTML += "<th>Prezzo</th>";
        tableHTML += "<th>Quantita</th>";
        tableHTML += "<th>Immagine</th>";
        tableHTML += "<th>Categoria</th>";
        tableHTML += "</tr>";
        tableHTML += "</thead>";
        tableHTML += "<tbody>";


        // Popola la tabella con i dati dei prodotti
        for(Prodotto p: prodotti){
            tableHTML += "<tr>";
            tableHTML += "<td>" + p.getId() + "</td>";
            tableHTML += "<td>" + p.getNome() + "</td>";
            tableHTML += "<td>" + p.getDescrizione() + "</td>";
            tableHTML += "<td>" + p.getPrezzo() + "</td>";
            tableHTML += "<td>" + p.getQuantita() + "</td>";
            tableHTML += "<td>" + p.getImg() + "</td>";
            tableHTML += "<td>" + p.getCategoria() + "</td>";
            tableHTML += "</tr>";
        }

        tableHTML+=("</tbody>");
        tableHTML+=("</table>");
        response.setContentType("text/html");
        return tableHTML;
    }
}

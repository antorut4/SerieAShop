package control;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String searchQuery = request.getParameter("query");

        List<Prodotto> searchResults = performSearch(searchQuery);

        // Imposta l'intestazione della risposta per il tipo di contenuto JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Converti i risultati in JSON e inviali come risposta
        String searchResultsJSON = convertToJSON(searchResults);
        response.getWriter().write(searchResultsJSON);
    }

    private List<Prodotto> performSearch(String query) {
        List<Prodotto> allProducts = getProductsFromDatabase();

        List<Prodotto> searchResults = new ArrayList<>();

        for (Prodotto prodotto : allProducts) {
            if (prodotto.getNome().toLowerCase().contains(query.toLowerCase()) || prodotto.getDescrizione().toLowerCase().contains((query.toLowerCase())) || prodotto.getIdSquadra().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(prodotto);
            }
        }
        return searchResults;
    }

    private List<Prodotto> getProductsFromDatabase() {

        List<Prodotto> products = new ArrayList<>();
        ProdottoDAO pdao=new ProdottoDAO();
        products=pdao.doRetriveAll();
        return products;
    }

    private String convertToJSON(List<Prodotto> searchResults) {
        Gson gson = new Gson();
        return gson.toJson(searchResults);
    }
}

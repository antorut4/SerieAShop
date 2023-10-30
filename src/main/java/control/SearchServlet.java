package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import org.json.JSONArray;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Ottieni la query dall'input del form
        String query = request.getParameter("query");

        // Invia la query al database
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM products WHERE name LIKE '%" + query + "%'");
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Elabora il risultato della query
        List<Prodotto> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                prodott.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}

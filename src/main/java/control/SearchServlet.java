package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        // Esempio: Simula una ricerca restituendo risultati JSON
        JSONArray results = new JSONArray();
        results.put("Risultato 1");
        results.put("Risultato 2");
        results.put("Risultato 3");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(results);
        out.flush();
    }
}

package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;

@WebServlet("/new-prodotto")
public class AddProdotto extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;

        if( request.getParameter("verifica") == null){
            String nome = request.getParameter("nome");
            String prezzoString = (request.getParameter("prezzo"));
            String desc = request.getParameter("descrizione");
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            String img = request.getParameter("image");
            String categoria= request.getParameter("categoria");
            String idSquadra= request.getParameter("idSquadra");

            Prodotto prodotto;
            double prezzo;

            String errore = "";

            if(nome == null)
                errore += "Nome del prodotto non definito ";

            if(nome.isEmpty() || nome.length()>256)
                errore += "Nome del prodotto troppo breve o troppo lungo";

            try {
                prezzo = Double.parseDouble(String.valueOf(prezzoString));
                if (prezzo < 0.01) {
                    errore += "Prezzo non corretto";
                }
            } catch (NumberFormatException e) {
                errore += "Prezzo non valido";
            }


            if(desc.length()>400)
                errore += "Descrizione troppo lunga";

            if (img != null && !img.isEmpty()) {
                String extension = img.substring(img.lastIndexOf(".") + 1);
                if (!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg") && !extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("gif")){
                    errore += "Immagine non valida";
                }
            }


            if(!errore.equals(""))
            {
                RequestDispatcher dispatcher =
                        getServletContext().getRequestDispatcher("/WEB-INF/Errorpage.jsp?errore="+errore);
                dispatcher.forward(request, response);


            }

            prodotto = new Prodotto();

            prodotto.setNome(nome);
            prodotto.setPrezzo(Double.valueOf(prezzoString));
            prodotto.setDescrizione(desc);
            prodotto.setQuantita(quantita);
            prodotto.setImg(img);
            prodotto.setCategoria(categoria);
            prodotto.setIdSquadra(idSquadra);


            ProdottoDAO cdao = new ProdottoDAO();
            cdao.doSave(prodotto);

            address = "/WEB-INF/HomeAdmin.jsp";
            request.setAttribute("prodotto", prodotto);

        }else { //(request.getParameter("verifica").equals("true"))
            address = "/HomeAdmin.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

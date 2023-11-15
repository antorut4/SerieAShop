package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/new-prodotto")
@MultipartConfig
public class AddProdotto extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;


            String nome = request.getParameter("nome");
            System.out.println(nome);
            String desc = request.getParameter("descrizione");
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            String categoria= request.getParameter("categoria");
            String idSquadra= request.getParameter("idSquadra");
            double prezzo=Double.parseDouble(request.getParameter("prezzo"));

            Prodotto prodotto;

            String errore = "";

            if(nome == null)
                errore += "Nome del prodotto non definito ";

            if(nome.isEmpty() || nome.length()>256)
                errore += "Nome del prodotto troppo breve o troppo lungo";

            try {
                if (prezzo==0) {
                    errore += "Prezzo non corretto";
                }
            } catch (NumberFormatException e) {
                errore += "Prezzo non valido";
            }


            if(desc.length()>3000)
                errore += "Descrizione troppo lunga";



            if(!errore.equals(""))
            {
                RequestDispatcher dispatcher =
                        getServletContext().getRequestDispatcher("/WEB-INF/Errorpage.jsp?errore="+errore);
                dispatcher.forward(request, response);


            }

            prodotto = new Prodotto();

            prodotto.setNome(nome);
            prodotto.setPrezzo(prezzo);
            prodotto.setDescrizione(desc);
            prodotto.setQuantita(quantita);
            prodotto.setCategoria(categoria);
            prodotto.setIdSquadra(idSquadra);


            ProdottoDAO cdao = new ProdottoDAO();
            cdao.doSave(prodotto);


            // creo una cartella e inserisco l'immagine
            File folder = new File(String.valueOf(prodotto.getId()));
            folder.mkdir();

            // Ottieni l'immagine da caricare
            Part imagePart = request.getPart("image");

            // Salva l'immagine nella cartella appena creata
            String fileName = imagePart.getSubmittedFileName();
            File imageFile = new File(folder, fileName);
            imagePart.write(String.valueOf(imageFile));


            address = "/WEB-INF/HomeAdmin.jsp";
            request.setAttribute("prodotto", prodotto);

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

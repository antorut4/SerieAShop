package control;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import static com.mysql.cj.xdevapi.Session.*;

@WebServlet("/new-prodotto")
@MultipartConfig
public class AddProdotto extends HttpServlet {

    private ProdottoObservable observable = new ProdottoObservable();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;
        observable.addObserver(new LoggerObserver());
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

            if(!errore.isEmpty())
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Errorepage.jsp");
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

                String path = "/image/PathOggetti/" + prodotto.getId();

                // creo una cartella e inserisco l'immagine
                File folder = new File(getServletContext().getRealPath(path));
                boolean success = folder.mkdir();

                if (success) {
                    System.out.println("La cartella è stata creata");
                } else {
                    System.out.println("La scelta non è stata creata");
                }


                Collection<Part> imageParts=request.getParts();
                for(Part part: imageParts){
                    String fileName=extractFileNamePart(part);
                    String filePath=folder.getAbsolutePath()+File.separator+fileName;
                    part.write(filePath);
                }

        observable.notifyObservers(prodotto);

        address = "/WEB-INF/HomeAdmin.jsp";
                request.setAttribute("prodotto", prodotto);

                RequestDispatcher rd = request.getRequestDispatcher(address);
                rd.forward(request, response);

    }

    private String extractFileNamePart(Part part){
        String cd= part.getHeader("content-disposition");
        String [] items= cd.split(";");
        for(String s: items){
            if(s.trim().startsWith("filename")){
                return s.substring(s.indexOf("=")+2, s.length()-1);
            }
        }
        return null;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

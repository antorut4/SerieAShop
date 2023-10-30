package model;

import java.sql.Date;
import java.util.Objects;

public class Ordine {
    private int id;
    private int totale;
    private Date dataOrd;
    private String pagamento;
    private String spedizione;
    private String idCliente;
    private int idCarrello;


    public Ordine() {
    }

    public Ordine(int id, int costo, Date dataOrd, String pagamento, String spedizione, int idCarrelloello, String idClientee) {
        this.id = id;
        this.totale = costo;
        this.dataOrd = dataOrd;
        this.pagamento = pagamento;
        this.spedizione = spedizione;
        this.idCarrello = idCarrelloello;
        this.idCliente = idClientee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int costo) {
        this.totale = costo;
    }

    public Date getDataOrd() {
        return dataOrd;
    }

    public void setDataOrd(Date dataOrd) {
        this.dataOrd = dataOrd;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(String spedizione) {
        this.spedizione = spedizione;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrelloello) {
        this.idCarrello = idCarrelloello;
    }

    public String getIdCliente() { return idCliente; }

    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return id == ordine.id && Double.compare(ordine.totale, totale) == 0 && idCarrello == ordine.idCarrello && Objects.equals(dataOrd, ordine.dataOrd) && Objects.equals(pagamento, ordine.pagamento) && Objects.equals(spedizione, ordine.spedizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totale, dataOrd, pagamento, spedizione, idCarrello, idCliente);
    }
}

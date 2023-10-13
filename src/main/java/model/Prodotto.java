package model;

import java.util.Objects;

public class Prodotto {
    private int id;
    private String nome;
    private Double prezzo;
    private String descrizione;
    private int quantita;
    private String img;
    private String idSquadra;
    private String categoria;


    public Prodotto() {
    }

    public Prodotto(int id, String nome, Double prezzo, String descrizione, int quantita, String image, String idSquadra, String categoria) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.img = image;
        this.idSquadra = idSquadra;
        this.categoria = categoria;
    }

    public String getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(String idSquadra) {
        this.idSquadra = idSquadra;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getImg() {
        return img;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setImg(String image) {
        this.img = image;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id && Double.compare(prodotto.prezzo, prezzo) == 0 && quantita == prodotto.quantita && Objects.equals(nome, prodotto.nome) && Objects.equals(descrizione, prodotto.descrizione) && Objects.equals(img, prodotto.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, prezzo, descrizione, quantita, img);
    }

    public Prodotto cloneProd() {
        Prodotto prodotto;
        prodotto = new Prodotto(this.id, this.nome, this.prezzo, this.descrizione, this.quantita, this.img, this.idSquadra, this.categoria);
        return prodotto;
    }

}

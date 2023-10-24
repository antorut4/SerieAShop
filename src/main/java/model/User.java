package model;

import java.util.Date;
public class User {
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String password;
    private String telefono;
    private boolean admin;

    public User(){
    }
    public User(int id, String username, String password, String nome, String cognome, String email, String telefono, String indirizzo) {
        this.username = username;
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
        this.password = password;
        this.telefono=telefono;
        this.indirizzo=indirizzo;
        this.admin=false;
    }

    // Metodi getter e setter per username e password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }


    public String getIndirizzo() {
        return indirizzo;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

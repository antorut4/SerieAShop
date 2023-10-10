package model;

public class Squadra {
    private String pathLogo;
    private String nomeSquadra;

    public Squadra(){
    }
    public Squadra(String pathLogo, String nomeSquadra) {
        this.pathLogo = pathLogo;
        this.nomeSquadra = nomeSquadra;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }
}

package Model;

public class Langue {
    String nomLangue;
    int niveau;

    public Langue(String nomLangue,int niveau)
    {
        this.nomLangue = nomLangue;
        this.niveau = niveau;
    }

    public String getNomLangue() {
        return nomLangue;
    }

    public void setNomLangue(String nomLangue) {
        this.nomLangue = nomLangue;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Langue{" +
                "nomLangue='" + nomLangue + '\'' +
                ", niveau=" + niveau +
                '}';
    }
}

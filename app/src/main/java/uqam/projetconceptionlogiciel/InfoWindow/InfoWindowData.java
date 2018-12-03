package uqam.projetconceptionlogiciel.InfoWindow;

public class InfoWindowData {

    public InfoWindowData(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    private String titre;
    private String description;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

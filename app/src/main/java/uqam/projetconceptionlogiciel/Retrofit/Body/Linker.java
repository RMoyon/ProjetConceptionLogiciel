package uqam.projetconceptionlogiciel.Retrofit.Body;

public class Linker {
    private int id;

    private int inversedId;

    public Linker(int id, int inversedId) {
        this.id = id;
        this.inversedId = inversedId;
    }

    public int getId() {
        return id;
    }

    public int getInversedId() {
        return inversedId;
    }
}

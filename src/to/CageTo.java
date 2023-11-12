package to;

public class CageTo {
    private String id;
    private String name;
    private int animalCount;

    public CageTo(String id, String name, int animalCount) {
        this.id = id;
        this.name = name;
        this.animalCount = animalCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(int animalCount) {
        this.animalCount = animalCount;
    }
}

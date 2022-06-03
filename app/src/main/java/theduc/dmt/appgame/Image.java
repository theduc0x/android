package theduc.dmt.appgame;

public class Image {
    private int idImage;
    private String name;

    public Image(int idImage, String name) {
        this.idImage = idImage;
        this.name = name;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package inovace.masudias.com.dynamicrecyclerview;


public class ListObject {
    private String description;
    private String title;

    public ListObject(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

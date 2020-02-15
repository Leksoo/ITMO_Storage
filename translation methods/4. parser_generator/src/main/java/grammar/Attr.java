package grammar;

public class Attr {
    private String name;
    private String type;

    public Attr(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Attr(String str) {
        String[] attrs = Util.cutEdges(str).split(":");
        this.name = attrs[0].trim();
        this.type = attrs[1].trim();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

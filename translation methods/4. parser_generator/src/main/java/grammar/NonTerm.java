package grammar;

public class NonTerm extends RulePart{
    private Attr attr;

    public NonTerm(String name) {
        super(name);
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }




}

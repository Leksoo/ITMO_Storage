package grammar;

import java.util.ArrayList;

public class RulesBlock {
    private String name;
    private Attr inheritedAttrs;
    private Attr returnAttrs;
    private ArrayList<Rule> rules = new ArrayList<Rule>();

    public RulesBlock(String name) {
        this.name = name;
    }

    public void setInheritedAttrs(String inheritedAttrs) {
        this.inheritedAttrs = new Attr(inheritedAttrs);
    }

    public void setReturnAttrs(String returnAttrs) {
        this.returnAttrs = new Attr(returnAttrs);

    }

    public void addRule(Rule rule){
        rules.add(rule);
    }

    public String getName() {
        return name;
    }

    public Attr getInheritedAttrs() {
        return inheritedAttrs;
    }

    public Attr getReturnAttrs() {
        return returnAttrs;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
}

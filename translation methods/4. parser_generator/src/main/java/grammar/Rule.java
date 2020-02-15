package grammar;

import java.util.ArrayList;
import java.util.HashSet;

public class Rule {
    ArrayList<RulePart> parts = new ArrayList<RulePart>();
    HashSet<String> first = new HashSet<String>();
    Boolean isEpsRule = false;


    public void addNonTerm(String str){
        parts.add(new NonTerm(str));
    }

    public void addTerm(String str){
        parts.add(Util.termNameToValue.get(str));
    }
    public void addCode(String str){
        parts.get(parts.size()-1).setCode(Util.cutEdges(str));
    }

    public void addAttr(String str){
        ((NonTerm)parts.get(parts.size()-1)).setAttr(new Attr(str));
    }

    public void addEps(){
        isEpsRule = true;
        parts.add(new Term("EPS","EPS"));
    }

    public Boolean getEpsRule() {
        return isEpsRule;
    }

    public ArrayList<RulePart> getParts() {
        return parts;
    }

    public HashSet<String> getFirst() {
        return first;
    }
}

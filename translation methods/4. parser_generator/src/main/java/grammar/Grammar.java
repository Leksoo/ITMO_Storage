package grammar;


import java.util.ArrayList;

public class Grammar {
    private String headerCode;
    private String startNonTerm;
    private ArrayList<Term> terms = new ArrayList<Term>();
    private ArrayList<RulesBlock> rulesBlocks = new ArrayList<RulesBlock>();


    public void setHeader(String str) {
        headerCode = Util.cutEdges(str);
    }

    public void setStart(String str){
        startNonTerm = str;
    }

    public void addTerm(Term term, boolean isSkipped){
        term.setSkipped(isSkipped);
        Util.addTerm(term);
        terms.add(term);
    }

    public void addRulesBlock(RulesBlock block){
        rulesBlocks.add(block);
    }

    public String getHeaderCode() {
        return headerCode;
    }

    public String getStartNonTerm() {
        return startNonTerm;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public ArrayList<RulesBlock> getRulesBlocks() {
        return rulesBlocks;
    }
}

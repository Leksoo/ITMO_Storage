package grammar;

import java.util.Objects;

public class Term extends RulePart {
    private String value;
    private boolean skipped;
    public Term(String name, String value){
       super(name);
        this.value = value.equals("EPS") ? value : Util.cutEdges(value);
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public String getValue() {
        return value;
    }

    public boolean isSkipped() {
        return skipped;
    }


}


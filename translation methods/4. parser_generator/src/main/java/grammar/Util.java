package grammar;

import java.util.HashMap;
import java.util.Map;

class Util {
    static String cutEdges(String str) {
        return str.substring(1, str.length() - 1);
    }

     static Map<String, Term> termNameToValue = new HashMap<String, Term>();

    static void addTerm(Term term) {
        termNameToValue.put(term.getName(), term);
    }
}

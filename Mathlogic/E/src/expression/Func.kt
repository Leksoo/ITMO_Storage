package expression

import java.util.ArrayList

open class Func(val str: String, val terms: ArrayList<Expression>) : Expression {

    override fun toString(): String {
        return "$str(${terms.joinToString(",") { it.toString() }})"
    }

    override fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<String, Expression>
    ): Boolean {
        return true
    }

    override fun chainedVars(): Set<Variable> {
        HashSet<Variable>().let { set ->
            terms.forEach { set.addAll(it.chainedVars()) }
            return set
        }
    }

    override fun allVars(): Set<Variable> {
        HashSet<Variable>().let { set ->
            terms.forEach { set.addAll(it.allVars()) }
            return set
        }
    }

    override fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>) {
        if (this.javaClass == other.javaClass && other is Func) {
            if (str != other.str || terms.size != other.terms.size) {
                res.add(Pair(this, other))
                return
            }
            for ((i, v) in terms.withIndex()) {
                v.diff(other.terms[i], res)
            }
        } else {
            res.add(Pair(this, other))
        }
    }

    override fun replace(
        from: Variable,
        setChained : HashSet<Variable>,
        replacementFreeVars: Set<Variable>
    ): Boolean {
        for (el in terms) {
            if (!el.replace(from, setChained, replacementFreeVars)) {
                return false
            }
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Func

        if (str != other.str) return false
        if (terms != other.terms) return false

        return true
    }

    override fun hashCode(): Int {
        var result = str.hashCode()
        result = 31 * result + terms.hashCode()
        return result
    }

    override fun checkFreedom(x: Variable, set: HashSet<Variable>): Boolean {
        for (el in terms) {
            if (el.checkFreedom(x, set)) {
                return true
            }
        }
        return false

    }

    override fun freeVars(setChained: HashSet<Variable>, resSet: HashSet<Variable>) {
        for(el in terms){
            el.freeVars(setChained,resSet);
        }
    }
}
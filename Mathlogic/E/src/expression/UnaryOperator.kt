package expression

import java.util.ArrayList

sealed class UnaryOperator(
    val right: Expression,
    val symbol: String
) : Expression {

    override fun toString(): String {
        return "($symbol $right)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnaryOperator

        if (right != other.right) return false
        if (symbol != other.symbol) return false

        return true
    }

    override fun hashCode(): Int {
        var result = right.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }

//    override fun freeVars(busyVariables: ArrayList<Variable>): Set<Variable> {
//        HashSet<Variable>().let {
//            it.addAll(right.freeVars(busyVariables))
//            return it
//        }
//    }

    override fun chainedVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.addAll(right.chainedVars())
            return it
        }
    }

    override fun allVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.addAll(right.allVars())
            return it
        }
    }

    override fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<String, Expression>
    ): Boolean {
        if (this.javaClass == other.javaClass && other is UnaryOperator) {
            return right.equalByTree(other.right, mapVarToExpr)
        }
        return false
    }

    override fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>) {
        if (this.javaClass == other.javaClass && other is UnaryOperator) {
            right.diff(other.right, res)
        } else {
            res.add(Pair(this, other))
        }
    }

    override fun replace(
        from: Variable,
        setChained : HashSet<Variable>,
        replacementFreeVars: Set<Variable>
    ): Boolean {
        return right.replace(from, setChained, replacementFreeVars)
    }

    override fun checkFreedom(x: Variable, set: HashSet<Variable>): Boolean {
        return right.checkFreedom(x, set)
    }

    override fun freeVars(setChained: HashSet<Variable>, resSet: HashSet<Variable>) {
        right.freeVars(setChained, resSet)
    }
}

class Not(right: Expression) : UnaryOperator(right, "!")
class Apostrophe(right: Expression) : UnaryOperator(right, "'") {
    override fun toString(): String {
        return "$right$symbol"
    }
}
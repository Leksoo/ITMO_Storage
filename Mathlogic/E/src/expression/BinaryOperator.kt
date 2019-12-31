package expression

import java.util.ArrayList

sealed class BinaryOperator(
    val left: Expression,
    val right: Expression,
    val symbol: String
) : Expression {

    override fun toString(): String {
        return "($left $symbol $right)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BinaryOperator

        if (left != other.left) return false
        if (right != other.right) return false
        if (symbol != other.symbol) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }

    override fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<String, Expression>
    ): Boolean {
        if (this.javaClass == other.javaClass && other is BinaryOperator) {
            return right.equalByTree(other.right, mapVarToExpr)
                    && left.equalByTree(other.left, mapVarToExpr)
        }
        return false
    }


    override fun chainedVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.addAll(left.chainedVars())
            it.addAll(right.chainedVars())
            return it
        }
    }

    override fun allVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.addAll(left.allVars())
            it.addAll(right.allVars())
            return it
        }
    }

    override fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>) {
        if (this.javaClass == other.javaClass && other is BinaryOperator) {
            left.diff(other.left, res)
            right.diff(other.right, res)
        } else {
            res.add(Pair(this, other))
        }
    }

    override fun replace(
        from: Variable,
        setChained: HashSet<Variable>,
        replacementFreeVars: Set<Variable>
    ): Boolean {
        return left.replace(from, setChained, replacementFreeVars) &&
                right.replace(from, setChained, replacementFreeVars)
    }

    override fun checkFreedom(x: Variable, set: HashSet<Variable>): Boolean {
        return left.checkFreedom(x, set) || right.checkFreedom(x, set)
    }

    override fun freeVars(setChained: HashSet<Variable>, resSet: HashSet<Variable>) {
        left.freeVars(setChained, resSet)
        right.freeVars(setChained, resSet)
    }
}

class And(left: Expression, right: Expression) : BinaryOperator(left, right, "&")
class Equal(left: Expression, right: Expression) : BinaryOperator(left, right, "=")
class Impl(left: Expression, right: Expression) : BinaryOperator(left, right, "->")
class Mul(left: Expression, right: Expression) : BinaryOperator(left, right, "*")
class Or(left: Expression, right: Expression) : BinaryOperator(left, right, "|")
class Sum(left: Expression, right: Expression) : BinaryOperator(left, right, "+")
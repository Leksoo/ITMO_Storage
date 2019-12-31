package expression

sealed class Quantifier(val left: Variable, val right: Expression, val symbol: String) : Expression {

    override fun toString(): String {
        return "$symbol$left$right"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Quantifier

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
        if (this.javaClass == other.javaClass && other is Quantifier) {
            return right.equalByTree(other.right, mapVarToExpr)
                    && left.equalByTree(other.left, mapVarToExpr)
        }
        return false
    }


//    override fun freeVars(busyVariables: ArrayList<Variable>): Set<Variable> {
//        HashSet<Variable>().let {
//            it.addAll(right.freeVars(busyVariables))
//            return it
//        }
//    }

    override fun chainedVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.add(left)
            it.addAll(right.chainedVars())
            return it
        }
    }

    override fun allVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.addAll(right.allVars())
            it.add(left)
            return it
        }
    }

    override fun replace(
        from: Variable,
        setChained : HashSet<Variable>,
        replacementFreeVars: Set<Variable>
    ): Boolean{
        val newSet = HashSet<Variable>(setChained)
        newSet.add(left)
        return right.replace(from, newSet, replacementFreeVars)
    }

    override fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>) {
        if (this.javaClass == other.javaClass && other is Quantifier) {
            left.diff(other.left, res)
            right.diff(other.right, res)

        } else {
            res.add(Pair(this, other))
        }
    }

    override fun checkFreedom(x: Variable, set: HashSet<Variable>): Boolean {
        if(this.left == x){
            return false
        }
        val newSet = HashSet<Variable>(set)
        newSet.add(left)
        return right.checkFreedom(x, newSet)
    }

    override fun freeVars(setChained: HashSet<Variable>, resSet: HashSet<Variable>) {
        val newChainedSet = HashSet<Variable>(setChained)
        newChainedSet.add(left)
        right.freeVars(newChainedSet, resSet)
    }
}

class Exist(left: Variable, right: Expression) : Quantifier(left, right, "?")
class All(left: Variable, right: Expression) : Quantifier(left, right, "@")
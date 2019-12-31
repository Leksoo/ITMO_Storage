package expression

class Zero : Expression {
    override fun toString(): String {
        return "0"
    }

    override fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<String, Expression>): Boolean {
        return equals(other)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

//    override fun freeVars(busyVariables: ArrayList<Variable>): Set<Variable> {
//        return HashSet()
//
//    }

    override fun chainedVars(): Set<Variable> {
        return HashSet()
    }

    override fun allVars(): Set<Variable> {
        return HashSet()

    }

    override fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>) {
        if (this.javaClass == other.javaClass && other is Zero) {

        }
        else{
            res.add(Pair(this,other))
        }
    }


    override fun replace(
        from: Variable,
        setChained : HashSet<Variable>,
        replacementFreeVars: Set<Variable>
    ): Boolean {
        return true
    }

    override fun checkFreedom(x: Variable, set: HashSet<Variable>): Boolean {
        return false
    }

    override fun freeVars(setChained: HashSet<Variable>, resSet: HashSet<Variable>) {
    }
}
package expression

class Variable(
     str: String
) : Func(str,ArrayList()) {

    override fun toString(): String {
        return str
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Variable

        if (str != other.str) return false

        return true
    }

    override fun hashCode(): Int {
        return str.hashCode()
    }

    override fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<String, Expression>): Boolean {
        return if (mapVarToExpr.containsKey(str)) {
            mapVarToExpr[str] == other
        } else {
            mapVarToExpr[str] = other
            true
        }

    }

//    override fun freeVars(busyVariables: ArrayList<Variable>): Set<Variable> {
//        HashSet<Variable>().let {
//            if(!busyVariables.contains(this)){
//                it.add(this)
//            }
//            return it
//        }
//    }

    override fun chainedVars(): Set<Variable> {
        return HashSet()
    }

    override fun allVars(): Set<Variable> {
        HashSet<Variable>().let {
            it.add(this)
            return it
        }
    }

    override fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>) {
        if (this.javaClass == other.javaClass && other is Variable && str == other.str) {
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
        if(this == from && !setChained.contains(this)) {
            if(!(replacementFreeVars.intersect(setChained)).isEmpty()){
                return false
            }
            return true
        }
        return true
    }

    override fun checkFreedom(x: Variable, set: HashSet<Variable>): Boolean {
        if(x == this) {
            if (!set.contains(this)) {
                return true
            }
            return false
        }
        return false
    }

    override fun freeVars(setChained: HashSet<Variable>, resSet: HashSet<Variable>) {
        if(!setChained.contains(this)){
            resSet.add(this)
        }
    }
}
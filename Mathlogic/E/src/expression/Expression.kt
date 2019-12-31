package expression

import java.util.ArrayList


interface Expression {

    fun equalByString(other: Expression) = toString() == other.toString()
    fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<String, Expression>): Boolean

    fun freeVars(setChained : HashSet<Variable>, resSet : HashSet<Variable>)
    fun replace(
        from: Variable,
        setChained : HashSet<Variable>,
        replacementFreeVars: Set<Variable>
    ): Boolean

    fun chainedVars(): Set<Variable>
    fun allVars(): Set<Variable>
    fun diff(other: Expression, res: ArrayList<Pair<Expression, Expression>>)
    fun checkFreedom(x : Variable, set : HashSet<Variable>) : Boolean
}
package expression

interface Expression {

    fun getAsString(sb: StringBuilder)
    fun equalByString(other: Expression) = toString() == other.toString()
    fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<Variable, Expression>,
        canLetterBeAnExpr: Boolean
    ): Boolean

    fun toStr(): String {
        val sb = StringBuilder()
        getAsString(sb)
        return sb.toString()
    }

    fun getNewExpr(
        letterToExpr: Map<String, Expression>
    ): Expression
}
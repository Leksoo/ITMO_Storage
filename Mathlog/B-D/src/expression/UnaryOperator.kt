package expression

abstract class UnaryOperator(
    val right: Expression,
    val symbol: String
) : Expression {

    override fun getAsString(sb: StringBuilder) {
        sb.append(symbol)
        right.getAsString(sb)
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

    override fun equalByTree(
        other: Expression,
        mapVarToExpr: HashMap<Variable, Expression>,
        canLetterBeAnExpr: Boolean
    ): Boolean {
        if (this.javaClass == other.javaClass && other is UnaryOperator) {
            return right.equalByTree(other.right, mapVarToExpr, canLetterBeAnExpr)
        }
        return false
    }

}
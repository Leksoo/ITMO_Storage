package expression

abstract class BinaryOperator(
    val left: Expression,
    val right: Expression,
    val symbol: String
) : Expression {



    override fun getAsString(sb: StringBuilder) {
        sb.append('(')
        left.getAsString(sb)
        sb.append(" $symbol ")
        right.getAsString(sb)
        sb.append(')')
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
        mapVarToExpr: HashMap<Variable, Expression>, canLetterBeAnExpr: Boolean
    ): Boolean {
        if (this.javaClass == other.javaClass && other is BinaryOperator) {
            return right.equalByTree(other.right, mapVarToExpr, canLetterBeAnExpr)
                    && left.equalByTree(other.left, mapVarToExpr, canLetterBeAnExpr)
        }
        return false
    }
}
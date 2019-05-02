package expression

class Variable(
    val str: String
) : Expression {

    override fun getNewExpr(letterToExpr: Map<String, Expression>): Expression {
        return letterToExpr[str] ?: Variable(str)
    }

    override fun getAsString(sb: StringBuilder) {
        sb.append(str)
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
        mapVarToExpr: HashMap<Variable, Expression>,
        canLetterBeAnExpr: Boolean
    ): Boolean {
        if (!canLetterBeAnExpr) {
            if (other is Variable && str == other.str) return true
            return false
        }
        return if (mapVarToExpr.containsKey(this)) {
            mapVarToExpr[this] == other
        } else {
            mapVarToExpr[this] = other
            true
        }

    }

}
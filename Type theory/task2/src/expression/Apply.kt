package expression

class Apply(var left:Expression,var right:Expression) : Expression{

    override fun contains(variable: Variable): Boolean {
        return left.contains(variable)|| right.contains(variable)
    }

    override fun copy(): Apply {
        return Apply(left.copy(),right.copy())
    }

    override fun toString(): String {
        return "(${left} ${right})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Apply

        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }
}
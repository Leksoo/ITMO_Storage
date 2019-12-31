package expression

class Wrapper(val expr: Expression) : Expression(){

    override fun contains(variable: Variable): Boolean {
        return expr.contains(variable)
    }

    override fun copy(): Wrapper {
        return Wrapper(expr.copy())
    }

    override fun toString(): String {
        return expr.toString()
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Wrapper

        if (expr != other.expr) return false

        return true
    }

    override fun hashCode(): Int {
        return expr.hashCode()
    }
}
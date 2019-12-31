package expression

class Abstr(val str: Variable, val right: Expression) : Expression() {
    override fun contains(variable: Variable): Boolean {
        return str.contains(variable) || right.contains(variable)
    }

    override fun copy(): Abstr {
        return Abstr(str.copy(), right.copy())
    }

    override fun toString(): String {
        return "(\\$str.$right)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Abstr

        if (str != other.str) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = str.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }
}
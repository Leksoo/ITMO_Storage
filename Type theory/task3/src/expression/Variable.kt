package expression

class Variable(
     val str: String
) : Expression() {

    override fun contains(variable: Variable): Boolean {
        return this == variable
    }

    override fun copy(): Variable{
        return Variable(str)
    }

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

}
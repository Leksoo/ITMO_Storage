package type

class Impl(val left: Type, val right: Type) : Type() {
    override fun substitute(map: Map<TypeVar, Type>): Type {
        return Impl(
            left.substitute(map),
            right.substitute(map)
        )
    }

    override fun contains(v: TypeVar): Boolean {
        return left.contains(v) || right.contains(v)
    }

    override fun toString(): String {
        return "($left -> $right)"
    }

    override fun substitute(what: TypeVar, to: Type): Pair<Type, Boolean> {

        val l = left.substitute(what, to)
        val r = right.substitute(what, to)
        var done = false
        if(l.second || r.second){
            done = true
        }
        return Pair(Impl(l.first,r.first),done)
    }

    override fun copy(): Impl {
        return Impl(left.copy(), right.copy())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Impl

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
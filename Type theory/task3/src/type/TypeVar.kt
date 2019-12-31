package type

class TypeVar(val str:String) : Type() {
    override fun substitute(map: Map<TypeVar, Type>): Type {
        return map[this] ?: this
    }

    override fun toString(): String {
        return str
    }

    override fun contains(v: TypeVar): Boolean {
        return this == v
    }

    override fun substitute(what: TypeVar, to: Type) : Pair<Type,Boolean>{
        if(this == what){
            return Pair(to.copy(),true)
        }
        return Pair(this.copy(),false)
    }

    override fun copy(): TypeVar{
        return TypeVar(str)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TypeVar

        if (str != other.str) return false

        return true
    }

    override fun hashCode(): Int {
        return str.hashCode()
    }
}
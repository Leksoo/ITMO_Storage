package type

abstract class Type {
    abstract fun copy(): Type
    abstract fun contains(v: TypeVar): Boolean
    abstract fun substitute(what: TypeVar, to: Type): Pair<Type,Boolean>
    abstract fun substitute(map : Map<TypeVar, Type>): Type
}
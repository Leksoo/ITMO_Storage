package expression

import type.TypeInfo


abstract class Expression {
    lateinit var typeInfo: TypeInfo
    abstract fun contains(variable: Variable): Boolean
    abstract fun copy(): Expression

}
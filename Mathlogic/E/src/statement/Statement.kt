package statement

import expression.Expression
import parse

abstract class Statement(
    val expr : Expression
){
    constructor(str : String) : this(parse(str))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Statement

        if (expr != other.expr) return false

        return true
    }

    override fun hashCode(): Int {
        return expr.hashCode()
    }


}
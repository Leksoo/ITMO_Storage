package statement

import expression.Expression
import parsers.Parser

abstract class Statement(
    var expr : Expression
){
    constructor(str : String) : this(Parser.parse(str))

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

    override fun toString(): String {
        return expr.toStr()
    }


}
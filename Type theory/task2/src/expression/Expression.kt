package expression

import java.util.ArrayList


interface Expression {
    fun contains(variable: Variable): Boolean
    fun copy(): Expression

}
package structure

import appendX1
import join

sealed class JavaItem {
    class Variable(
        private val modifiers: String,
        private val variableBody: VariableBody

    ) : JavaItem() {
        override fun toString(): String {
            return join(" ", modifiers, variableBody)
        }
    }

    class FuncCall(
        private val new: String,
        private val name: String,
        private val args: List<Param>
    ) : JavaItem() {
        override fun toString(): String {
            return join(" ", new, name) + "(${args.joinToString(", ") { it.toString() }})"

        }
    }

    class Return(
        private val name: String
    ) : JavaItem() {
        override fun toString(): String {
            return join(" ", "return", name)
        }
    }

    class While(
        private val condition: String,
        private val body: List<JavaItem>
    ) : JavaItem() {
        override fun toString(): String {
            val res = StringBuilder()
            res.append("while ($condition) {\n")
            if (body.isEmpty()) {
                res.append("}")
                return res.toString()
            }
            body.forEach { res.appendX1(if(it is JavaItem.IfElse || it is JavaItem.While) "$it" else "$it;") }
            res.append("")
            res.append("}")
            return res.toString()
        }
    }

    class IfElse(
        private val condition: String,
        private val bodyIf: List<JavaItem>,
        private val hasElse: String,
        private val bodyElse: List<JavaItem>
    ) :JavaItem(){
        override fun toString(): String {
            val res = StringBuilder()
            res.append("if ($condition) {\n")
            bodyIf.forEach { res.appendX1(if(it is JavaItem.IfElse || it is JavaItem.While) "$it" else "$it;") }
            res.append("}")
            if (hasElse.isNotEmpty()) {
                res.append(" else {\n")
                bodyElse.forEach { res.appendX1(if(it is JavaItem.IfElse || it is JavaItem.While) "$it" else "$it;") }
                res.append("}")
            }
            return res.toString()
        }
    }

    class Condition(
        val left: String,
        val right: String
    ) : JavaItem() {
        override fun toString(): String {
            return join(" ", left, if (right.isEmpty()) "" else "==", right)
        }
    }
}
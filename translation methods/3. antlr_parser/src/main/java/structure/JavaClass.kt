package structure

import appendX1
import join
import java.lang.StringBuilder

class JavaClass(
    private val name: String,
    private val modifier: String,
    private val superclass: String,
    private val interfaces: List<String>,
    private val fields: List<JavaItem.Variable>,
    private val methods: List<Method>
) {

    override fun toString(): String {
        val res = StringBuilder()
        res.append(join(" ", modifier, "class", name))
        if (superclass.isNotEmpty()) res.append(" extends $superclass")
        if (interfaces.isNotEmpty()) res.append(" implements ${interfaces.joinToString(", ")}")
        res.append(" {\n")
        if (fields.isEmpty() && methods.isEmpty()) {
            res.append("}")
            return res.toString()
        }
        fields.forEach { res.appendX1("$it;")}
        if (fields.isNotEmpty()) res.append("\n")
        methods.filter { it.name == name }.forEach { res.appendX1(it.toString()).append("\n") }
        methods.filter { it.name != name }.forEach { res.appendX1(it.toString()).append("\n") }
        return "${res.dropLast(1)}}"
    }
}




class VariableBody(
    private val type: String,
    private val name: String,
    private val assign: Assign?
) {
    override fun toString(): String {
        return join(" ", type, name, assign ?: "")
    }
}

class Assign(
    private val body: String
) {
    constructor(funcCall: JavaItem.FuncCall) : this(funcCall.toString())

    override fun toString(): String {
        return join(" ","=",body)
    }
}



class Method(
    private val modifiers: String,
    private val returnType: String,
    val name: String,
    private val arguments: List<Argument>,
    private val body: List<JavaItem>
) {
    override fun toString(): String {
        val res = StringBuilder()
        res.append("${join(" ", modifiers, returnType, name)}(")
        res.append(arguments.joinToString(", "))
        res.append(") {")
        if (body.isEmpty()) {
            res.append("}")
            return res.toString()
        }
        res.append("\n")
        body.forEach { res.appendX1(if(it is JavaItem.IfElse || it is JavaItem.While) "$it" else "$it;")}
        res.append("}")
        return res.toString()
    }
}

class Argument(
    private val type: String,
    private val name: String
) {
    override fun toString(): String {
        return join(" ", type, name)
    }
}

class Param(
    private val name: String
) {
    override fun toString(): String {
        return join("", name)
    }
}
import java.lang.StringBuilder

fun<T> join(separator : String, vararg args: T) : String{
    return args.map { it.toString().trim() }.filter { it != "" }.joinToString(separator)
}

fun StringBuilder.appendX1(obj : String) : StringBuilder{
    val str = obj
    str.split("\n").forEach { this.append("    ").append(it).append("\n") }
    return this
}
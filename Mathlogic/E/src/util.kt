import expression.Expression
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import parser.ExpressionParser
import parser.ExpressionLexer



fun parse(str : String) : Expression{
   // println("parsing: $str")
    val `is` = ANTLRInputStream(str)
    val lexer = ExpressionLexer(`is`)
    val ts = CommonTokenStream(lexer)
    val parser = ExpressionParser(ts)
    try {
        val a = parser.expression().expr
        return a
    }
    catch (e: Exception){
        System.err.println("error parsing $str")
        throw e
    }

}
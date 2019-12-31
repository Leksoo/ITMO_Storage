import expression.Expression
import expression.Variable
//import org.antlr.v4.runtime.CharStreams
//import org.antlr.v4.runtime.CommonTokenStream
//import parser.ExpressionParser
//import parser.ExpressionLexer
import parsers.Parser


fun parse(str : String) : Expression{
   // println("parsing: $str")
//    val `is` = CharStreams.fromString(str)
//    val lexer = ExpressionLexer(`is`)
//    val ts = CommonTokenStream(lexer)
//    val parser = ExpressionParser(ts)
    try {
        val a = Parser.parse(str)
        return a
    }
    catch (e: Exception){
        println("error parsing $str")
        return Variable("a")
    }

}
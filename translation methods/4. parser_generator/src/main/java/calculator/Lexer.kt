package calculator
import generator.LexerException
import java.io.FileReader
import java.util.regex.Pattern
import kotlin.collections.HashMap
enum class Token(val value: String){
    NUMBER("[0-9]+"), PLUS("[+]"), MINUS("[-]"), MULT("[*]"), LP("[(]"), RP("[)]"), END("END")
}

class Lexer(
    private val path : String,
    private val fromFile : Boolean
) {
    var input : String
    val matcher = Pattern.compile("").matcher("")
    val skipPattern = Pattern.compile(
    "([ \t\r\n]+)"
    )
    private val tokens = HashMap<Token, Pattern>()
    
    init{
         if(fromFile) input = FileReader(path).readLines().joinToString()
         else input = path
         Token.values().dropLast(1).forEach {
             tokens[it] = Pattern.compile(it.value)
         }
    }
    
    fun next(): Pair<String, Token> {
        matcher.usePattern(skipPattern)
        matcher.reset(input)
        while (matcher.lookingAt()) {
            input = input.substring(matcher.end())
            matcher.reset(input)
        }
        Token.values().filter { it != Token.END }
            .forEach { token ->
                val regex = tokens[token]
                matcher.usePattern(regex)
                matcher.reset(input)
                if(matcher.lookingAt()){
                    return Pair(input.substring(0,matcher.end()),token).also {
                        input = input.substring(matcher.end())
                    }
                }
            }
        if(input.isEmpty()){
            return Pair("",Token.END)
        }
        else{
            throw LexerException("invalid token in $input")
        }
    }               
}
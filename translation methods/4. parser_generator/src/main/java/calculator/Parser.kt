package calculator
import generator.END_INPUT_ERROR
import generator.ParserException
import generator.parserError
import java.io.FileReader
import java.util.regex.Pattern
import kotlin.collections.HashMap
class Parser(
    private val lexer : Lexer
){
    private lateinit var curToken : Token
    private lateinit var curValue : String
    
    private fun next() : Token {
       lexer.next().let { 
          curToken=it.second
          curValue=it.first
       }
        return curToken
    }
    
    fun parse() : Int{
        next()
        val num = parse_e()
        if(curToken != Token.END){
            parserError(END_INPUT_ERROR + curValue)
        }
        return num
    }

    private fun parse_e() : Int {
        var num : Int? = null
        when(curToken){
            Token.NUMBER, Token.LP -> {
            
                val t = parse_t()
                
                val e_ = parse_e_(t)
                num = e_
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return num!!
    }
    private fun parse_e_(acc : Int) : Int {
        var num : Int? = null
        when(curToken){
            Token.PLUS -> {
            
                val PLUS = curValue
                if(curToken.name != "PLUS") parserError("unexpected token: $curValue")
                next()
                
                val t = parse_t()
                
                val e_ = parse_e_(acc + t)
                num = e_
                
            }
            Token.MINUS -> {
            
                val MINUS = curValue
                if(curToken.name != "MINUS") parserError("unexpected token: $curValue")
                next()
                
                val t = parse_t()
                
                val e_ = parse_e_(acc - t)
                num = e_
                
            }
            
            Token.END, Token.RP -> {

                
    num = acc
    
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return num!!
    }
    private fun parse_t() : Int {
        var num : Int? = null
        when(curToken){
            Token.NUMBER, Token.LP -> {
            
                val f = parse_f()
                
                val t_ = parse_t_(f)
                num = t_
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return num!!
    }
    private fun parse_t_(acc : Int) : Int {
        var num : Int? = null
        when(curToken){
            Token.MULT -> {
            
                val MULT = curValue
                if(curToken.name != "MULT") parserError("unexpected token: $curValue")
                next()
                
                val f = parse_f()
                
                val t_ = parse_t_(acc * f)
                num = t_
                
            }
            
            Token.END, Token.RP, Token.PLUS, Token.MINUS -> {

                
    num = acc
    
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return num!!
    }
    private fun parse_f() : Int {
        var num : Int? = null
        when(curToken){
            Token.NUMBER -> {
            
                val NUMBER = curValue
                if(curToken.name != "NUMBER") parserError("unexpected token: $curValue")
                next()
                num = NUMBER.toInt()
                
            }
            Token.LP -> {
            
                val LP = curValue
                if(curToken.name != "LP") parserError("unexpected token: $curValue")
                next()
                
                val e = parse_e()
                
                val RP = curValue
                if(curToken.name != "RP") parserError("unexpected token: $curValue")
                next()
                num = e
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return num!!
    }
}
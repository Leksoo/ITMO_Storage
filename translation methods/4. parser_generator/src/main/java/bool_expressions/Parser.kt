package bool_expression
import Tree
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
    
    fun parse() : Tree{
        next()
        val tree = parse_e()
        if(curToken != Token.END){
            parserError(END_INPUT_ERROR + curValue)
        }
        return tree
    }

    private fun parse_e() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.NOT, Token.LP, Token.VAR -> {
            
                val x = parse_x()
                
                val e_ = parse_e_()
                tree = Tree("E",arrayOf(x, e_))
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_e_() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.OR -> {
            
                val OR = curValue
                if(curToken.name != "OR") parserError("unexpected token: $curValue")
                next()
                
                val x = parse_x()
                
                val e_ = parse_e_()
                tree = Tree("E^",arrayOf(Tree("or"),x,e_))
                
            }
            
            Token.END, Token.RP -> {

                
    tree = Tree()
    
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_x() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.NOT, Token.LP, Token.VAR -> {
            
                val a = parse_a()
                
                val x_ = parse_x_()
                tree= Tree("X",arrayOf(a,x_))
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_x_() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.XOR -> {
            
                val XOR = curValue
                if(curToken.name != "XOR") parserError("unexpected token: $curValue")
                next()
                
                val a = parse_a()
                
                val x_ = parse_x_()
                tree = Tree("X^",arrayOf(Tree("xor"),a,x_))
                
            }
            
            Token.OR, Token.END, Token.RP -> {

                
    tree = Tree()
    
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_a() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.NOT, Token.LP, Token.VAR -> {
            
                val n = parse_n()
                
                val a_ = parse_a_()
                tree= Tree("A",arrayOf(n,a_))
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_a_() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.AND -> {
            
                val AND = curValue
                if(curToken.name != "AND") parserError("unexpected token: $curValue")
                next()
                
                val n = parse_n()
                
                val a_ = parse_a_()
                tree = Tree("A^",arrayOf(Tree("and"),n,a_))
                
            }
            
            Token.OR, Token.XOR, Token.END, Token.RP -> {

                
    tree = Tree()
    
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_n() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.NOT -> {
            
                val NOT = curValue
                if(curToken.name != "NOT") parserError("unexpected token: $curValue")
                next()
                
                val n = parse_n()
                tree = Tree("N",arrayOf(Tree("not"),n))
                
            }
            Token.LP, Token.VAR -> {
            
                val v = parse_v()
                tree = v
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
    private fun parse_v() : Tree {
        var tree : Tree? = null
        when(curToken){
            Token.VAR -> {
            
                val VAR = curValue
                if(curToken.name != "VAR") parserError("unexpected token: $curValue")
                next()
                 tree = Tree("V",arrayOf(Tree(VAR)))
                
            }
            Token.LP -> {
            
                val LP = curValue
                if(curToken.name != "LP") parserError("unexpected token: $curValue")
                next()
                
                val e = parse_e()
                
                val RP = curValue
                if(curToken.name != "RP") parserError("unexpected token: $curValue")
                next()
                tree = Tree("V",arrayOf(Tree("("), e ,Tree(")")))
                
            }
            else ->{parserError("unexpected token: $curValue")}
        }
        return tree!!
    }
}
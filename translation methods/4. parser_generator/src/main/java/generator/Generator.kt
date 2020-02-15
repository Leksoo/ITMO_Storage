package generator

import gen.parser.InputGrammarLexer
import gen.parser.InputGrammarParser
import java.nio.file.Path
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.CharStreams
import java.nio.file.Files
import java.nio.file.Paths


object Generator {
    fun generate(gr : String, out : String){
        val input: Path
        val output: Path
        val lexer: InputGrammarLexer
        try {
            input = Paths.get(gr)
            output = Paths.get(out)
            lexer = InputGrammarLexer(CharStreams.fromPath(input))
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
        val parser = InputGrammarParser(CommonTokenStream(lexer))
        val grammar = parser.inputGrammar().gr
        try {
            Files.createDirectories(output)
            LexerGenerator(out,grammar).generate()
            ParserGenerator(out,grammar).apply {
                generate()
            }
        }
        catch (e : Exception){
            e.printStackTrace()
        }
    }
}
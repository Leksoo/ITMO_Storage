// Generated from C:/programming/MT/task4/src/main/java/parser\InputGrammar.g4 by ANTLR 4.7.2
package gen.parser;

import grammar.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InputGrammarParser}.
 */
public interface InputGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InputGrammarParser#inputGrammar}.
	 * @param ctx the parse tree
	 */
	void enterInputGrammar(InputGrammarParser.InputGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputGrammarParser#inputGrammar}.
	 * @param ctx the parse tree
	 */
	void exitInputGrammar(InputGrammarParser.InputGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputGrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(InputGrammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputGrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(InputGrammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputGrammarParser#enter_point}.
	 * @param ctx the parse tree
	 */
	void enterEnter_point(InputGrammarParser.Enter_pointContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputGrammarParser#enter_point}.
	 * @param ctx the parse tree
	 */
	void exitEnter_point(InputGrammarParser.Enter_pointContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputGrammarParser#lex}.
	 * @param ctx the parse tree
	 */
	void enterLex(InputGrammarParser.LexContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputGrammarParser#lex}.
	 * @param ctx the parse tree
	 */
	void exitLex(InputGrammarParser.LexContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputGrammarParser#rules_block}.
	 * @param ctx the parse tree
	 */
	void enterRules_block(InputGrammarParser.Rules_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputGrammarParser#rules_block}.
	 * @param ctx the parse tree
	 */
	void exitRules_block(InputGrammarParser.Rules_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputGrammarParser#rule_single}.
	 * @param ctx the parse tree
	 */
	void enterRule_single(InputGrammarParser.Rule_singleContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputGrammarParser#rule_single}.
	 * @param ctx the parse tree
	 */
	void exitRule_single(InputGrammarParser.Rule_singleContext ctx);
}
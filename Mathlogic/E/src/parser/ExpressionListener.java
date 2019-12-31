// Generated from Expression.g4 by ANTLR 4.7.1

package parser;
import expression.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(ExpressionParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(ExpressionParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(ExpressionParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(ExpressionParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(ExpressionParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(ExpressionParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(ExpressionParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(ExpressionParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#manyterm}.
	 * @param ctx the parse tree
	 */
	void enterManyterm(ExpressionParser.ManytermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#manyterm}.
	 * @param ctx the parse tree
	 */
	void exitManyterm(ExpressionParser.ManytermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ExpressionParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ExpressionParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterSum(ExpressionParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitSum(ExpressionParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(ExpressionParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(ExpressionParser.MulContext ctx);
}
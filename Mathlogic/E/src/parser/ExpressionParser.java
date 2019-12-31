// Generated from Expression.g4 by ANTLR 4.7.1

package parser;
import expression.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPLIES=1, OR=2, AND=3, NOT=4, OB=5, CB=6, VAR=7, PRED=8, ALL=9, EXIST=10, 
		DOT=11, COMMA=12, EQUALS=13, PLUS=14, MULT=15, ZERO=16, NEXT=17, WS=18;
	public static final int
		RULE_expression = 0, RULE_disjunction = 1, RULE_conjunction = 2, RULE_unary = 3, 
		RULE_variable = 4, RULE_predicate = 5, RULE_manyterm = 6, RULE_term = 7, 
		RULE_sum = 8, RULE_mul = 9;
	public static final String[] ruleNames = {
		"expression", "disjunction", "conjunction", "unary", "variable", "predicate", 
		"manyterm", "term", "sum", "mul"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'|'", "'&'", "'!'", "'('", "')'", null, null, "'@'", "'?'", 
		"'.'", "','", "'='", "'+'", "'*'", "'0'", "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IMPLIES", "OR", "AND", "NOT", "OB", "CB", "VAR", "PRED", "ALL", 
		"EXIST", "DOT", "COMMA", "EQUALS", "PLUS", "MULT", "ZERO", "NEXT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public Expression expr;
		public DisjunctionContext disj;
		public DisjunctionContext disj1;
		public ExpressionContext exp1;
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public TerminalNode IMPLIES() { return getToken(ExpressionParser.IMPLIES, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				((ExpressionContext)_localctx).disj = disjunction(0);
				((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).disj.expr;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				((ExpressionContext)_localctx).disj1 = disjunction(0);
				setState(24);
				match(IMPLIES);
				setState(25);
				((ExpressionContext)_localctx).exp1 = expression();
				((ExpressionContext)_localctx).expr =  new Impl(((ExpressionContext)_localctx).disj1.expr, ((ExpressionContext)_localctx).exp1.expr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjunctionContext extends ParserRuleContext {
		public Expression expr;
		public DisjunctionContext disj1;
		public ConjunctionContext conj;
		public ConjunctionContext conj1;
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public TerminalNode OR() { return getToken(ExpressionParser.OR, 0); }
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		return disjunction(0);
	}

	private DisjunctionContext disjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, _parentState);
		DisjunctionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_disjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(31);
			((DisjunctionContext)_localctx).conj = conjunction(0);
			((DisjunctionContext)_localctx).expr =  ((DisjunctionContext)_localctx).conj.expr;
			}
			_ctx.stop = _input.LT(-1);
			setState(41);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DisjunctionContext(_parentctx, _parentState);
					_localctx.disj1 = _prevctx;
					_localctx.disj1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_disjunction);
					setState(34);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(35);
					match(OR);
					setState(36);
					((DisjunctionContext)_localctx).conj1 = conjunction(0);
					((DisjunctionContext)_localctx).expr =  new Or(((DisjunctionContext)_localctx).disj1.expr, ((DisjunctionContext)_localctx).conj1.expr);
					}
					} 
				}
				setState(43);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public Expression expr;
		public ConjunctionContext conj1;
		public UnaryContext unar;
		public UnaryContext unar1;
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode AND() { return getToken(ExpressionParser.AND, 0); }
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		return conjunction(0);
	}

	private ConjunctionContext conjunction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, _parentState);
		ConjunctionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_conjunction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(45);
			((ConjunctionContext)_localctx).unar = unary();
			((ConjunctionContext)_localctx).expr =  ((ConjunctionContext)_localctx).unar.expr;
			}
			_ctx.stop = _input.LT(-1);
			setState(55);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConjunctionContext(_parentctx, _parentState);
					_localctx.conj1 = _prevctx;
					_localctx.conj1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_conjunction);
					setState(48);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(49);
					match(AND);
					setState(50);
					((ConjunctionContext)_localctx).unar1 = unary();
					((ConjunctionContext)_localctx).expr =  new And(((ConjunctionContext)_localctx).conj1.expr, ((ConjunctionContext)_localctx).unar1.expr);
					}
					} 
				}
				setState(57);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryContext extends ParserRuleContext {
		public Expression expr;
		public PredicateContext pred;
		public UnaryContext unar;
		public ExpressionContext exp;
		public VariableContext var1;
		public ExpressionContext expr1;
		public VariableContext var2;
		public ExpressionContext expr2;
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode NOT() { return getToken(ExpressionParser.NOT, 0); }
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode OB() { return getToken(ExpressionParser.OB, 0); }
		public TerminalNode CB() { return getToken(ExpressionParser.CB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ALL() { return getToken(ExpressionParser.ALL, 0); }
		public TerminalNode DOT() { return getToken(ExpressionParser.DOT, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode EXIST() { return getToken(ExpressionParser.EXIST, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unary);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				((UnaryContext)_localctx).pred = predicate();
				((UnaryContext)_localctx).expr =  ((UnaryContext)_localctx).pred.expr;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				match(NOT);
				setState(62);
				((UnaryContext)_localctx).unar = unary();
				((UnaryContext)_localctx).expr =  new Not(((UnaryContext)_localctx).unar.expr);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(OB);
				setState(66);
				((UnaryContext)_localctx).exp = expression();
				((UnaryContext)_localctx).expr =  ((UnaryContext)_localctx).exp.expr;
				setState(68);
				match(CB);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				match(ALL);
				setState(71);
				((UnaryContext)_localctx).var1 = variable();
				setState(72);
				match(DOT);
				setState(73);
				((UnaryContext)_localctx).expr1 = expression();
				((UnaryContext)_localctx).expr =  new All(((UnaryContext)_localctx).var1.expr, ((UnaryContext)_localctx).expr1.expr);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				match(EXIST);
				setState(77);
				((UnaryContext)_localctx).var2 = variable();
				setState(78);
				match(DOT);
				setState(79);
				((UnaryContext)_localctx).expr2 = expression();
				((UnaryContext)_localctx).expr =  new Exist(((UnaryContext)_localctx).var2.expr, ((UnaryContext)_localctx).expr2.expr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public Variable expr;
		public Token VAR;
		public TerminalNode VAR() { return getToken(ExpressionParser.VAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			((VariableContext)_localctx).VAR = match(VAR);
			((VariableContext)_localctx).expr =  new Variable((((VariableContext)_localctx).VAR!=null?((VariableContext)_localctx).VAR.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public Expression expr;
		public Token PRED;
		public ManytermContext manyterm1;
		public TermContext term1;
		public TermContext term2;
		public TerminalNode PRED() { return getToken(ExpressionParser.PRED, 0); }
		public TerminalNode OB() { return getToken(ExpressionParser.OB, 0); }
		public TerminalNode CB() { return getToken(ExpressionParser.CB, 0); }
		public ManytermContext manyterm() {
			return getRuleContext(ManytermContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(ExpressionParser.EQUALS, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_predicate);
		try {
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				((PredicateContext)_localctx).PRED = match(PRED);
				setState(88);
				match(OB);
				setState(89);
				((PredicateContext)_localctx).manyterm1 = manyterm();
				((PredicateContext)_localctx).expr =  new Pred((((PredicateContext)_localctx).PRED!=null?((PredicateContext)_localctx).PRED.getText():null), ((PredicateContext)_localctx).manyterm1.list);
				setState(91);
				match(CB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				((PredicateContext)_localctx).PRED = match(PRED);
				((PredicateContext)_localctx).expr =  new Pred((((PredicateContext)_localctx).PRED!=null?((PredicateContext)_localctx).PRED.getText():null), new ArrayList<Expression>());
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				((PredicateContext)_localctx).term1 = term(0);
				setState(96);
				match(EQUALS);
				setState(97);
				((PredicateContext)_localctx).term2 = term(0);
				((PredicateContext)_localctx).expr =  new Equal(((PredicateContext)_localctx).term1.expr,((PredicateContext)_localctx).term2.expr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ManytermContext extends ParserRuleContext {
		public ArrayList<Expression> list;
		public TermContext term1;
		public ManytermContext manyterm1;
		public TermContext term2;
		public TerminalNode COMMA() { return getToken(ExpressionParser.COMMA, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ManytermContext manyterm() {
			return getRuleContext(ManytermContext.class,0);
		}
		public ManytermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_manyterm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterManyterm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitManyterm(this);
		}
	}

	public final ManytermContext manyterm() throws RecognitionException {
		ManytermContext _localctx = new ManytermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_manyterm);
		try {
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				((ManytermContext)_localctx).term1 = term(0);
				setState(103);
				match(COMMA);
				setState(104);
				((ManytermContext)_localctx).manyterm1 = manyterm();
				((ManytermContext)_localctx).list =  new ArrayList<Expression>();_localctx.list.add(((ManytermContext)_localctx).term1.expr); _localctx.list.addAll(((ManytermContext)_localctx).manyterm1.list);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				((ManytermContext)_localctx).term2 = term(0);
				((ManytermContext)_localctx).list =  new ArrayList<Expression>();_localctx.list.add(((ManytermContext)_localctx).term2.expr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Expression expr;
		public TermContext term1;
		public SumContext sum1;
		public SumContext sum2;
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(ExpressionParser.PLUS, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(113);
			((TermContext)_localctx).sum1 = sum(0);
			((TermContext)_localctx).expr =  ((TermContext)_localctx).sum1.expr;
			}
			_ctx.stop = _input.LT(-1);
			setState(123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					_localctx.term1 = _prevctx;
					_localctx.term1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(116);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(117);
					match(PLUS);
					setState(118);
					((TermContext)_localctx).sum2 = sum(0);
					((TermContext)_localctx).expr =  new Sum(((TermContext)_localctx).term1.expr,((TermContext)_localctx).sum2.expr);
					}
					} 
				}
				setState(125);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SumContext extends ParserRuleContext {
		public Expression expr;
		public SumContext sum1;
		public MulContext mul1;
		public MulContext mul2;
		public MulContext mul() {
			return getRuleContext(MulContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ExpressionParser.MULT, 0); }
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public SumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterSum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitSum(this);
		}
	}

	public final SumContext sum() throws RecognitionException {
		return sum(0);
	}

	private SumContext sum(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SumContext _localctx = new SumContext(_ctx, _parentState);
		SumContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_sum, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(127);
			((SumContext)_localctx).mul1 = mul(0);
			((SumContext)_localctx).expr =  ((SumContext)_localctx).mul1.expr;
			}
			_ctx.stop = _input.LT(-1);
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SumContext(_parentctx, _parentState);
					_localctx.sum1 = _prevctx;
					_localctx.sum1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_sum);
					setState(130);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(131);
					match(MULT);
					setState(132);
					((SumContext)_localctx).mul2 = mul(0);
					((SumContext)_localctx).expr =  new Mul(((SumContext)_localctx).sum1.expr,((SumContext)_localctx).mul2.expr);
					}
					} 
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MulContext extends ParserRuleContext {
		public Expression expr;
		public MulContext mul1;
		public Token VAR;
		public ManytermContext manyterm1;
		public VariableContext variable1;
		public TermContext term1;
		public TerminalNode VAR() { return getToken(ExpressionParser.VAR, 0); }
		public TerminalNode OB() { return getToken(ExpressionParser.OB, 0); }
		public TerminalNode CB() { return getToken(ExpressionParser.CB, 0); }
		public ManytermContext manyterm() {
			return getRuleContext(ManytermContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode ZERO() { return getToken(ExpressionParser.ZERO, 0); }
		public TerminalNode NEXT() { return getToken(ExpressionParser.NEXT, 0); }
		public MulContext mul() {
			return getRuleContext(MulContext.class,0);
		}
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMul(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		return mul(0);
	}

	private MulContext mul(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MulContext _localctx = new MulContext(_ctx, _parentState);
		MulContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_mul, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(141);
				((MulContext)_localctx).VAR = match(VAR);
				setState(142);
				match(OB);
				setState(143);
				((MulContext)_localctx).manyterm1 = manyterm();
				((MulContext)_localctx).expr =  new Func((((MulContext)_localctx).VAR!=null?((MulContext)_localctx).VAR.getText():null), ((MulContext)_localctx).manyterm1.list);
				setState(145);
				match(CB);
				}
				break;
			case 2:
				{
				setState(147);
				((MulContext)_localctx).variable1 = variable();
				((MulContext)_localctx).expr =  ((MulContext)_localctx).variable1.expr;
				}
				break;
			case 3:
				{
				setState(150);
				match(OB);
				setState(151);
				((MulContext)_localctx).term1 = term(0);
				((MulContext)_localctx).expr =  ((MulContext)_localctx).term1.expr;
				setState(153);
				match(CB);
				}
				break;
			case 4:
				{
				setState(155);
				match(ZERO);
				((MulContext)_localctx).expr =  new Zero();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(164);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MulContext(_parentctx, _parentState);
					_localctx.mul1 = _prevctx;
					_localctx.mul1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_mul);
					setState(159);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(160);
					match(NEXT);
					((MulContext)_localctx).expr =  new Apostrophe(((MulContext)_localctx).mul1.expr);
					}
					} 
				}
				setState(166);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return disjunction_sempred((DisjunctionContext)_localctx, predIndex);
		case 2:
			return conjunction_sempred((ConjunctionContext)_localctx, predIndex);
		case 7:
			return term_sempred((TermContext)_localctx, predIndex);
		case 8:
			return sum_sempred((SumContext)_localctx, predIndex);
		case 9:
			return mul_sempred((MulContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean disjunction_sempred(DisjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean conjunction_sempred(ConjunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean sum_sempred(SumContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean mul_sempred(MulContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u00aa\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\37\n\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\48\n\4\f\4\16\4;\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5U\n\5\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7g\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bq\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\7\t|\n\t\f\t\16\t\177\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\7\n\u008a\n\n\f\n\16\n\u008d\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a0\n\13\3\13"+
		"\3\13\3\13\7\13\u00a5\n\13\f\13\16\13\u00a8\13\13\3\13\2\7\4\6\20\22\24"+
		"\f\2\4\6\b\n\f\16\20\22\24\2\2\2\u00af\2\36\3\2\2\2\4 \3\2\2\2\6.\3\2"+
		"\2\2\bT\3\2\2\2\nV\3\2\2\2\ff\3\2\2\2\16p\3\2\2\2\20r\3\2\2\2\22\u0080"+
		"\3\2\2\2\24\u009f\3\2\2\2\26\27\5\4\3\2\27\30\b\2\1\2\30\37\3\2\2\2\31"+
		"\32\5\4\3\2\32\33\7\3\2\2\33\34\5\2\2\2\34\35\b\2\1\2\35\37\3\2\2\2\36"+
		"\26\3\2\2\2\36\31\3\2\2\2\37\3\3\2\2\2 !\b\3\1\2!\"\5\6\4\2\"#\b\3\1\2"+
		"#+\3\2\2\2$%\f\3\2\2%&\7\4\2\2&\'\5\6\4\2\'(\b\3\1\2(*\3\2\2\2)$\3\2\2"+
		"\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\5\3\2\2\2-+\3\2\2\2./\b\4\1\2/\60\5"+
		"\b\5\2\60\61\b\4\1\2\619\3\2\2\2\62\63\f\3\2\2\63\64\7\5\2\2\64\65\5\b"+
		"\5\2\65\66\b\4\1\2\668\3\2\2\2\67\62\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3"+
		"\2\2\2:\7\3\2\2\2;9\3\2\2\2<=\5\f\7\2=>\b\5\1\2>U\3\2\2\2?@\7\6\2\2@A"+
		"\5\b\5\2AB\b\5\1\2BU\3\2\2\2CD\7\7\2\2DE\5\2\2\2EF\b\5\1\2FG\7\b\2\2G"+
		"U\3\2\2\2HI\7\13\2\2IJ\5\n\6\2JK\7\r\2\2KL\5\2\2\2LM\b\5\1\2MU\3\2\2\2"+
		"NO\7\f\2\2OP\5\n\6\2PQ\7\r\2\2QR\5\2\2\2RS\b\5\1\2SU\3\2\2\2T<\3\2\2\2"+
		"T?\3\2\2\2TC\3\2\2\2TH\3\2\2\2TN\3\2\2\2U\t\3\2\2\2VW\7\t\2\2WX\b\6\1"+
		"\2X\13\3\2\2\2YZ\7\n\2\2Z[\7\7\2\2[\\\5\16\b\2\\]\b\7\1\2]^\7\b\2\2^g"+
		"\3\2\2\2_`\7\n\2\2`g\b\7\1\2ab\5\20\t\2bc\7\17\2\2cd\5\20\t\2de\b\7\1"+
		"\2eg\3\2\2\2fY\3\2\2\2f_\3\2\2\2fa\3\2\2\2g\r\3\2\2\2hi\5\20\t\2ij\7\16"+
		"\2\2jk\5\16\b\2kl\b\b\1\2lq\3\2\2\2mn\5\20\t\2no\b\b\1\2oq\3\2\2\2ph\3"+
		"\2\2\2pm\3\2\2\2q\17\3\2\2\2rs\b\t\1\2st\5\22\n\2tu\b\t\1\2u}\3\2\2\2"+
		"vw\f\3\2\2wx\7\20\2\2xy\5\22\n\2yz\b\t\1\2z|\3\2\2\2{v\3\2\2\2|\177\3"+
		"\2\2\2}{\3\2\2\2}~\3\2\2\2~\21\3\2\2\2\177}\3\2\2\2\u0080\u0081\b\n\1"+
		"\2\u0081\u0082\5\24\13\2\u0082\u0083\b\n\1\2\u0083\u008b\3\2\2\2\u0084"+
		"\u0085\f\3\2\2\u0085\u0086\7\21\2\2\u0086\u0087\5\24\13\2\u0087\u0088"+
		"\b\n\1\2\u0088\u008a\3\2\2\2\u0089\u0084\3\2\2\2\u008a\u008d\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\23\3\2\2\2\u008d\u008b\3\2\2"+
		"\2\u008e\u008f\b\13\1\2\u008f\u0090\7\t\2\2\u0090\u0091\7\7\2\2\u0091"+
		"\u0092\5\16\b\2\u0092\u0093\b\13\1\2\u0093\u0094\7\b\2\2\u0094\u00a0\3"+
		"\2\2\2\u0095\u0096\5\n\6\2\u0096\u0097\b\13\1\2\u0097\u00a0\3\2\2\2\u0098"+
		"\u0099\7\7\2\2\u0099\u009a\5\20\t\2\u009a\u009b\b\13\1\2\u009b\u009c\7"+
		"\b\2\2\u009c\u00a0\3\2\2\2\u009d\u009e\7\22\2\2\u009e\u00a0\b\13\1\2\u009f"+
		"\u008e\3\2\2\2\u009f\u0095\3\2\2\2\u009f\u0098\3\2\2\2\u009f\u009d\3\2"+
		"\2\2\u00a0\u00a6\3\2\2\2\u00a1\u00a2\f\3\2\2\u00a2\u00a3\7\23\2\2\u00a3"+
		"\u00a5\b\13\1\2\u00a4\u00a1\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3"+
		"\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\25\3\2\2\2\u00a8\u00a6\3\2\2\2\f\36+"+
		"9Tfp}\u008b\u009f\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
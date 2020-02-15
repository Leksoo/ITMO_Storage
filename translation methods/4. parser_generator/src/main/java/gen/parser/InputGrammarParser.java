// Generated from C:/programming/MT/task4/src/main/java/parser\InputGrammar.g4 by ANTLR 4.7.2
package gen.parser;

import grammar.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InputGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HEADER=1, CODEBLOCK=2, INHERIT_ATTR=3, RETURNS=4, RETURN_ATTR=5, START=6, 
		IGNORE=7, EPS=8, WHITESPACE=9, NONTERM=10, TERM=11, STRING=12, COMMA=13, 
		COLON=14, DCOLON=15, SCOLON=16, EQ=17, SPLIT=18, LP=19, RP=20, LB=21, 
		RB=22, LSQ=23, RSQ=24;
	public static final int
		RULE_inputGrammar = 0, RULE_header = 1, RULE_enter_point = 2, RULE_lex = 3, 
		RULE_rules_block = 4, RULE_rule_single = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"inputGrammar", "header", "enter_point", "lex", "rules_block", "rule_single"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'header'", null, null, "'>>'", null, "'start'", "'--skip'", "'EPS'", 
			null, null, null, null, "','", "':'", "'::'", "';'", "'='", "'|'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HEADER", "CODEBLOCK", "INHERIT_ATTR", "RETURNS", "RETURN_ATTR", 
			"START", "IGNORE", "EPS", "WHITESPACE", "NONTERM", "TERM", "STRING", 
			"COMMA", "COLON", "DCOLON", "SCOLON", "EQ", "SPLIT", "LP", "RP", "LB", 
			"RB", "LSQ", "RSQ"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "InputGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InputGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class InputGrammarContext extends ParserRuleContext {
		public Grammar gr;
		public HeaderContext h;
		public LexContext l;
		public Enter_pointContext e;
		public Rules_blockContext r;
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public Enter_pointContext enter_point() {
			return getRuleContext(Enter_pointContext.class,0);
		}
		public List<LexContext> lex() {
			return getRuleContexts(LexContext.class);
		}
		public LexContext lex(int i) {
			return getRuleContext(LexContext.class,i);
		}
		public List<Rules_blockContext> rules_block() {
			return getRuleContexts(Rules_blockContext.class);
		}
		public Rules_blockContext rules_block(int i) {
			return getRuleContext(Rules_blockContext.class,i);
		}
		public InputGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).enterInputGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).exitInputGrammar(this);
		}
	}

	public final InputGrammarContext inputGrammar() throws RecognitionException {
		InputGrammarContext _localctx = new InputGrammarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_inputGrammar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((InputGrammarContext)_localctx).gr =  new Grammar();
			setState(13);
			((InputGrammarContext)_localctx).h = header();
			_localctx.gr.setHeader(((InputGrammarContext)_localctx).h.str);
			setState(18); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(15);
				((InputGrammarContext)_localctx).l = lex();
				_localctx.gr.addTerm(((InputGrammarContext)_localctx).l.term,((InputGrammarContext)_localctx).l.skip);
				}
				}
				setState(20); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TERM );
			setState(22);
			((InputGrammarContext)_localctx).e = enter_point();
			_localctx.gr.setStart(((InputGrammarContext)_localctx).e.str);
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				((InputGrammarContext)_localctx).r = rules_block();
				_localctx.gr.addRulesBlock(((InputGrammarContext)_localctx).r.rules);
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NONTERM );
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

	public static class HeaderContext extends ParserRuleContext {
		public String str;
		public Token CODEBLOCK;
		public TerminalNode HEADER() { return getToken(InputGrammarParser.HEADER, 0); }
		public TerminalNode CODEBLOCK() { return getToken(InputGrammarParser.CODEBLOCK, 0); }
		public TerminalNode SCOLON() { return getToken(InputGrammarParser.SCOLON, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(HEADER);
			setState(32);
			((HeaderContext)_localctx).CODEBLOCK = match(CODEBLOCK);
			setState(33);
			match(SCOLON);
			((HeaderContext)_localctx).str =  (((HeaderContext)_localctx).CODEBLOCK!=null?((HeaderContext)_localctx).CODEBLOCK.getText():null);
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

	public static class Enter_pointContext extends ParserRuleContext {
		public String str;
		public Token NONTERM;
		public TerminalNode START() { return getToken(InputGrammarParser.START, 0); }
		public TerminalNode COLON() { return getToken(InputGrammarParser.COLON, 0); }
		public TerminalNode NONTERM() { return getToken(InputGrammarParser.NONTERM, 0); }
		public TerminalNode SCOLON() { return getToken(InputGrammarParser.SCOLON, 0); }
		public Enter_pointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enter_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).enterEnter_point(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).exitEnter_point(this);
		}
	}

	public final Enter_pointContext enter_point() throws RecognitionException {
		Enter_pointContext _localctx = new Enter_pointContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_enter_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(START);
			setState(37);
			match(COLON);
			setState(38);
			((Enter_pointContext)_localctx).NONTERM = match(NONTERM);
			setState(39);
			match(SCOLON);
			((Enter_pointContext)_localctx).str =  (((Enter_pointContext)_localctx).NONTERM!=null?((Enter_pointContext)_localctx).NONTERM.getText():null);
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

	public static class LexContext extends ParserRuleContext {
		public Term term;
		public boolean skip;
		public Token TERM;
		public Token STRING;
		public TerminalNode TERM() { return getToken(InputGrammarParser.TERM, 0); }
		public TerminalNode COLON() { return getToken(InputGrammarParser.COLON, 0); }
		public TerminalNode STRING() { return getToken(InputGrammarParser.STRING, 0); }
		public TerminalNode SCOLON() { return getToken(InputGrammarParser.SCOLON, 0); }
		public TerminalNode IGNORE() { return getToken(InputGrammarParser.IGNORE, 0); }
		public LexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).enterLex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).exitLex(this);
		}
	}

	public final LexContext lex() throws RecognitionException {
		LexContext _localctx = new LexContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((LexContext)_localctx).skip =  false;
			setState(43);
			((LexContext)_localctx).TERM = match(TERM);
			setState(44);
			match(COLON);
			setState(45);
			((LexContext)_localctx).STRING = match(STRING);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IGNORE) {
				{
				setState(46);
				match(IGNORE);
				((LexContext)_localctx).skip = true;
				}
			}

			setState(50);
			match(SCOLON);
			((LexContext)_localctx).term =  new Term((((LexContext)_localctx).TERM!=null?((LexContext)_localctx).TERM.getText():null), (((LexContext)_localctx).STRING!=null?((LexContext)_localctx).STRING.getText():null));
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

	public static class Rules_blockContext extends ParserRuleContext {
		public RulesBlock rules;
		public Token block_name;
		public Token ia;
		public Token ra;
		public Rule_singleContext r;
		public TerminalNode COLON() { return getToken(InputGrammarParser.COLON, 0); }
		public TerminalNode SCOLON() { return getToken(InputGrammarParser.SCOLON, 0); }
		public TerminalNode NONTERM() { return getToken(InputGrammarParser.NONTERM, 0); }
		public TerminalNode RETURNS() { return getToken(InputGrammarParser.RETURNS, 0); }
		public List<Rule_singleContext> rule_single() {
			return getRuleContexts(Rule_singleContext.class);
		}
		public Rule_singleContext rule_single(int i) {
			return getRuleContext(Rule_singleContext.class,i);
		}
		public TerminalNode INHERIT_ATTR() { return getToken(InputGrammarParser.INHERIT_ATTR, 0); }
		public TerminalNode RETURN_ATTR() { return getToken(InputGrammarParser.RETURN_ATTR, 0); }
		public List<TerminalNode> SPLIT() { return getTokens(InputGrammarParser.SPLIT); }
		public TerminalNode SPLIT(int i) {
			return getToken(InputGrammarParser.SPLIT, i);
		}
		public Rules_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).enterRules_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).exitRules_block(this);
		}
	}

	public final Rules_blockContext rules_block() throws RecognitionException {
		Rules_blockContext _localctx = new Rules_blockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rules_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			((Rules_blockContext)_localctx).block_name = match(NONTERM);
			((Rules_blockContext)_localctx).rules =  new RulesBlock((((Rules_blockContext)_localctx).block_name!=null?((Rules_blockContext)_localctx).block_name.getText():null));
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERIT_ATTR) {
				{
				setState(55);
				((Rules_blockContext)_localctx).ia = match(INHERIT_ATTR);
				_localctx.rules.setInheritedAttrs((((Rules_blockContext)_localctx).ia!=null?((Rules_blockContext)_localctx).ia.getText():null));
				}
			}

			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(59);
				match(RETURNS);
				setState(60);
				((Rules_blockContext)_localctx).ra = match(RETURN_ATTR);
				_localctx.rules.setReturnAttrs((((Rules_blockContext)_localctx).ra!=null?((Rules_blockContext)_localctx).ra.getText():null));
				}
			}

			setState(64);
			match(COLON);
			{
			setState(65);
			((Rules_blockContext)_localctx).r = rule_single();
			_localctx.rules.addRule(((Rules_blockContext)_localctx).r.rule);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPLIT) {
				{
				{
				setState(67);
				match(SPLIT);
				setState(68);
				((Rules_blockContext)_localctx).r = rule_single();
				_localctx.rules.addRule(((Rules_blockContext)_localctx).r.rule);
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(76);
			match(SCOLON);
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

	public static class Rule_singleContext extends ParserRuleContext {
		public Rule rule;
		public Token t;
		public Token c;
		public Token n;
		public Token attr;
		public Token e;
		public TerminalNode EPS() { return getToken(InputGrammarParser.EPS, 0); }
		public List<TerminalNode> CODEBLOCK() { return getTokens(InputGrammarParser.CODEBLOCK); }
		public TerminalNode CODEBLOCK(int i) {
			return getToken(InputGrammarParser.CODEBLOCK, i);
		}
		public List<TerminalNode> TERM() { return getTokens(InputGrammarParser.TERM); }
		public TerminalNode TERM(int i) {
			return getToken(InputGrammarParser.TERM, i);
		}
		public List<TerminalNode> NONTERM() { return getTokens(InputGrammarParser.NONTERM); }
		public TerminalNode NONTERM(int i) {
			return getToken(InputGrammarParser.NONTERM, i);
		}
		public List<TerminalNode> INHERIT_ATTR() { return getTokens(InputGrammarParser.INHERIT_ATTR); }
		public TerminalNode INHERIT_ATTR(int i) {
			return getToken(InputGrammarParser.INHERIT_ATTR, i);
		}
		public Rule_singleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_single; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).enterRule_single(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputGrammarListener ) ((InputGrammarListener)listener).exitRule_single(this);
		}
	}

	public final Rule_singleContext rule_single() throws RecognitionException {
		Rule_singleContext _localctx = new Rule_singleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rule_single);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((Rule_singleContext)_localctx).rule =  new Rule();
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONTERM:
			case TERM:
				{
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(95);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case TERM:
						{
						{
						setState(79);
						((Rule_singleContext)_localctx).t = match(TERM);
						_localctx.rule.addTerm((((Rule_singleContext)_localctx).t!=null?((Rule_singleContext)_localctx).t.getText():null));
						setState(83);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==CODEBLOCK) {
							{
							setState(81);
							((Rule_singleContext)_localctx).c = match(CODEBLOCK);
							_localctx.rule.addCode((((Rule_singleContext)_localctx).c!=null?((Rule_singleContext)_localctx).c.getText():null));
							}
						}

						}
						}
						break;
					case NONTERM:
						{
						{
						setState(85);
						((Rule_singleContext)_localctx).n = match(NONTERM);
						_localctx.rule.addNonTerm((((Rule_singleContext)_localctx).n!=null?((Rule_singleContext)_localctx).n.getText():null));
						setState(89);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==INHERIT_ATTR) {
							{
							setState(87);
							((Rule_singleContext)_localctx).attr = match(INHERIT_ATTR);
							_localctx.rule.addAttr((((Rule_singleContext)_localctx).attr!=null?((Rule_singleContext)_localctx).attr.getText():null));
							}
						}

						setState(93);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==CODEBLOCK) {
							{
							setState(91);
							((Rule_singleContext)_localctx).c = match(CODEBLOCK);
							_localctx.rule.addCode((((Rule_singleContext)_localctx).c!=null?((Rule_singleContext)_localctx).c.getText():null));
							}
						}

						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(97); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NONTERM || _la==TERM );
				}
				break;
			case EPS:
				{
				setState(99);
				((Rule_singleContext)_localctx).e = match(EPS);
				_localctx.rule.addEps();
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CODEBLOCK) {
					{
					setState(101);
					((Rule_singleContext)_localctx).c = match(CODEBLOCK);
					_localctx.rule.addCode((((Rule_singleContext)_localctx).c!=null?((Rule_singleContext)_localctx).c.getText():null));
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32n\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\2\3\2\6\2\25\n\2"+
		"\r\2\16\2\26\3\2\3\2\3\2\3\2\3\2\6\2\36\n\2\r\2\16\2\37\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5\63\n\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\5\6<\n\6\3\6\3\6\3\6\5\6A\n\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\7\6J\n\6\f\6\16\6M\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7V"+
		"\n\7\3\7\3\7\3\7\3\7\5\7\\\n\7\3\7\3\7\5\7`\n\7\6\7b\n\7\r\7\16\7c\3\7"+
		"\3\7\3\7\3\7\5\7j\n\7\5\7l\n\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2t\2\16\3\2\2"+
		"\2\4!\3\2\2\2\6&\3\2\2\2\b,\3\2\2\2\n\67\3\2\2\2\fP\3\2\2\2\16\17\b\2"+
		"\1\2\17\20\5\4\3\2\20\24\b\2\1\2\21\22\5\b\5\2\22\23\b\2\1\2\23\25\3\2"+
		"\2\2\24\21\3\2\2\2\25\26\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\30\3\2"+
		"\2\2\30\31\5\6\4\2\31\35\b\2\1\2\32\33\5\n\6\2\33\34\b\2\1\2\34\36\3\2"+
		"\2\2\35\32\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \3\3\2\2\2"+
		"!\"\7\3\2\2\"#\7\4\2\2#$\7\22\2\2$%\b\3\1\2%\5\3\2\2\2&\'\7\b\2\2\'(\7"+
		"\20\2\2()\7\f\2\2)*\7\22\2\2*+\b\4\1\2+\7\3\2\2\2,-\b\5\1\2-.\7\r\2\2"+
		"./\7\20\2\2/\62\7\16\2\2\60\61\7\t\2\2\61\63\b\5\1\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\64\3\2\2\2\64\65\7\22\2\2\65\66\b\5\1\2\66\t\3\2\2\2\67"+
		"8\7\f\2\28;\b\6\1\29:\7\5\2\2:<\b\6\1\2;9\3\2\2\2;<\3\2\2\2<@\3\2\2\2"+
		"=>\7\6\2\2>?\7\7\2\2?A\b\6\1\2@=\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7\20\2"+
		"\2CD\5\f\7\2DK\b\6\1\2EF\7\24\2\2FG\5\f\7\2GH\b\6\1\2HJ\3\2\2\2IE\3\2"+
		"\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7\22\2\2O\13"+
		"\3\2\2\2Pk\b\7\1\2QR\7\r\2\2RU\b\7\1\2ST\7\4\2\2TV\b\7\1\2US\3\2\2\2U"+
		"V\3\2\2\2Vb\3\2\2\2WX\7\f\2\2X[\b\7\1\2YZ\7\5\2\2Z\\\b\7\1\2[Y\3\2\2\2"+
		"[\\\3\2\2\2\\_\3\2\2\2]^\7\4\2\2^`\b\7\1\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2"+
		"\2aQ\3\2\2\2aW\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2dl\3\2\2\2ef\7\n\2"+
		"\2fi\b\7\1\2gh\7\4\2\2hj\b\7\1\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ka\3\2\2"+
		"\2ke\3\2\2\2l\r\3\2\2\2\17\26\37\62;@KU[_acik";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
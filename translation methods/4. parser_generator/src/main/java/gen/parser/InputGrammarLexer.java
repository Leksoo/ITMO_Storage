// Generated from C:/programming/MT/task4/src/main/java/parser\InputGrammar.g4 by ANTLR 4.7.2
package gen.parser;

import grammar.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InputGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HEADER=1, CODEBLOCK=2, INHERIT_ATTR=3, RETURNS=4, RETURN_ATTR=5, START=6, 
		IGNORE=7, EPS=8, WHITESPACE=9, NONTERM=10, TERM=11, STRING=12, COMMA=13, 
		COLON=14, DCOLON=15, SCOLON=16, EQ=17, SPLIT=18, LP=19, RP=20, LB=21, 
		RB=22, LSQ=23, RSQ=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"HEADER", "CODEBLOCK", "INHERIT_ATTR", "RETURNS", "RETURN_ATTR", "START", 
			"IGNORE", "EPS", "WHITESPACE", "NONTERM", "TERM", "STRING", "COMMA", 
			"COLON", "DCOLON", "SCOLON", "EQ", "SPLIT", "LP", "RP", "LB", "RB", "LSQ", 
			"RSQ"
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


	public InputGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "InputGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00a0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\3\3"+
		"\3\3\3\4\3\4\7\4F\n\4\f\4\16\4I\13\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\7\6R"+
		"\n\6\f\6\16\6U\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\6\nk\n\n\r\n\16\nl\3\n\3\n\3\13\3\13\7"+
		"\13s\n\13\f\13\16\13v\13\13\3\f\3\f\7\fz\n\f\f\f\16\f}\13\f\3\r\3\r\7"+
		"\r\u0081\n\r\f\r\16\r\u0084\13\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\6>GS\u0082\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\3\2\7\5\2\13\f\17\17\"\"\3\2c|\4\2aac|\3\2C\\\4\2C\\"+
		"aa\2\u00a6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5:\3\2\2\2\7C\3\2\2\2\tL\3\2\2\2"+
		"\13O\3\2\2\2\rX\3\2\2\2\17^\3\2\2\2\21e\3\2\2\2\23j\3\2\2\2\25p\3\2\2"+
		"\2\27w\3\2\2\2\31~\3\2\2\2\33\u0087\3\2\2\2\35\u0089\3\2\2\2\37\u008b"+
		"\3\2\2\2!\u008e\3\2\2\2#\u0090\3\2\2\2%\u0092\3\2\2\2\'\u0094\3\2\2\2"+
		")\u0096\3\2\2\2+\u0098\3\2\2\2-\u009a\3\2\2\2/\u009c\3\2\2\2\61\u009e"+
		"\3\2\2\2\63\64\7j\2\2\64\65\7g\2\2\65\66\7c\2\2\66\67\7f\2\2\678\7g\2"+
		"\289\7t\2\29\4\3\2\2\2:>\5+\26\2;=\13\2\2\2<;\3\2\2\2=@\3\2\2\2>?\3\2"+
		"\2\2><\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\5-\27\2B\6\3\2\2\2CG\5\'\24\2DF\13"+
		"\2\2\2ED\3\2\2\2FI\3\2\2\2GH\3\2\2\2GE\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\5"+
		")\25\2K\b\3\2\2\2LM\7@\2\2MN\7@\2\2N\n\3\2\2\2OS\5/\30\2PR\13\2\2\2QP"+
		"\3\2\2\2RU\3\2\2\2ST\3\2\2\2SQ\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\5\61\31\2"+
		"W\f\3\2\2\2XY\7u\2\2YZ\7v\2\2Z[\7c\2\2[\\\7t\2\2\\]\7v\2\2]\16\3\2\2\2"+
		"^_\7/\2\2_`\7/\2\2`a\7u\2\2ab\7m\2\2bc\7k\2\2cd\7r\2\2d\20\3\2\2\2ef\7"+
		"G\2\2fg\7R\2\2gh\7U\2\2h\22\3\2\2\2ik\t\2\2\2ji\3\2\2\2kl\3\2\2\2lj\3"+
		"\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\n\2\2o\24\3\2\2\2pt\t\3\2\2qs\t\4\2\2r"+
		"q\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\26\3\2\2\2vt\3\2\2\2w{\t\5\2"+
		"\2xz\t\6\2\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\30\3\2\2\2}{\3\2"+
		"\2\2~\u0082\7)\2\2\177\u0081\13\2\2\2\u0080\177\3\2\2\2\u0081\u0084\3"+
		"\2\2\2\u0082\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0085\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u0086\7)\2\2\u0086\32\3\2\2\2\u0087\u0088\7.\2\2"+
		"\u0088\34\3\2\2\2\u0089\u008a\7<\2\2\u008a\36\3\2\2\2\u008b\u008c\7<\2"+
		"\2\u008c\u008d\7<\2\2\u008d \3\2\2\2\u008e\u008f\7=\2\2\u008f\"\3\2\2"+
		"\2\u0090\u0091\7?\2\2\u0091$\3\2\2\2\u0092\u0093\7~\2\2\u0093&\3\2\2\2"+
		"\u0094\u0095\7*\2\2\u0095(\3\2\2\2\u0096\u0097\7+\2\2\u0097*\3\2\2\2\u0098"+
		"\u0099\7}\2\2\u0099,\3\2\2\2\u009a\u009b\7\177\2\2\u009b.\3\2\2\2\u009c"+
		"\u009d\7]\2\2\u009d\60\3\2\2\2\u009e\u009f\7_\2\2\u009f\62\3\2\2\2\n\2"+
		">GSlt{\u0082\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
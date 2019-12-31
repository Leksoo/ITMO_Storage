// Generated from Expression.g4 by ANTLR 4.7.1

package parser;
import expression.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPLIES=1, OR=2, AND=3, NOT=4, OB=5, CB=6, VAR=7, PRED=8, ALL=9, EXIST=10, 
		DOT=11, COMMA=12, EQUALS=13, PLUS=14, MULT=15, ZERO=16, NEXT=17, WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"IMPLIES", "OR", "AND", "NOT", "OB", "CB", "VAR", "PRED", "ALL", "EXIST", 
		"DOT", "COMMA", "EQUALS", "PLUS", "MULT", "ZERO", "NEXT", "WS"
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


	public ExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24[\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\7\b\67\n\b\f\b\16\b:\13\b\3\t\3\t\7\t>\n\t\f\t\16\tA\13\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\6\23V\n\23\r\23\16\23W\3\23\3\23\2\2\24\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\3\2\6\3"+
		"\2c|\3\2\62;\3\2C\\\5\2\13\f\17\17\"\"\2]\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2"+
		"\5*\3\2\2\2\7,\3\2\2\2\t.\3\2\2\2\13\60\3\2\2\2\r\62\3\2\2\2\17\64\3\2"+
		"\2\2\21;\3\2\2\2\23B\3\2\2\2\25D\3\2\2\2\27F\3\2\2\2\31H\3\2\2\2\33J\3"+
		"\2\2\2\35L\3\2\2\2\37N\3\2\2\2!P\3\2\2\2#R\3\2\2\2%U\3\2\2\2\'(\7/\2\2"+
		"()\7@\2\2)\4\3\2\2\2*+\7~\2\2+\6\3\2\2\2,-\7(\2\2-\b\3\2\2\2./\7#\2\2"+
		"/\n\3\2\2\2\60\61\7*\2\2\61\f\3\2\2\2\62\63\7+\2\2\63\16\3\2\2\2\648\t"+
		"\2\2\2\65\67\t\3\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29"+
		"\20\3\2\2\2:8\3\2\2\2;?\t\4\2\2<>\t\3\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2"+
		"\2?@\3\2\2\2@\22\3\2\2\2A?\3\2\2\2BC\7B\2\2C\24\3\2\2\2DE\7A\2\2E\26\3"+
		"\2\2\2FG\7\60\2\2G\30\3\2\2\2HI\7.\2\2I\32\3\2\2\2JK\7?\2\2K\34\3\2\2"+
		"\2LM\7-\2\2M\36\3\2\2\2NO\7,\2\2O \3\2\2\2PQ\7\62\2\2Q\"\3\2\2\2RS\7)"+
		"\2\2S$\3\2\2\2TV\t\5\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2"+
		"\2\2YZ\b\23\2\2Z&\3\2\2\2\6\28?W\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%cup
%line
%column

%xstate COMMENT

%{

	public Symbol new_symbol(int type){
		return new Symbol(type, yyline + 1, yycolumn + 1);
	}
	
	public Symbol new_symbol(int type, Object value){
		return new Symbol(type, yyline + 1, yycolumn + 1, value);
	}
	
%}

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%


" "		{ }
"\t"	{ }
"\b"	{ }
"\f"	{ }
"\r\n"	{ }


"program"			{ return new_symbol(sym.PROG); }
"return"			{ return new_symbol(sym.RETURN); }
"enum"				{ return new_symbol(sym.ENUM); }
"class"				{ return new_symbol(sym.CLASS); }
"extends"			{ return new_symbol(sym.EXTENDS); }
"abstract"			{ return new_symbol(sym.ABSTRACT); }
"if"				{ return new_symbol(sym.IF); }
"else"				{ return new_symbol(sym.ELSE); }
"for"				{ return new_symbol(sym.FOR); }
"break"				{ return new_symbol(sym.BREAK); }
"continue"			{ return new_symbol(sym.CONTINUE); }
"switch"			{ return new_symbol(sym.SWITCH); }
"case"				{ return new_symbol(sym.CASE); }
"const"				{ return new_symbol(sym.CONST); }
"void"				{ return new_symbol(sym.VOID); }
"new"				{ return new_symbol(sym.NEW); }
"print"				{ return new_symbol(sym.PRINT); }
"read"				{ return new_symbol(sym.READ); }


"("					{ return new_symbol(sym.LPAREN); }
")"					{ return new_symbol(sym.RPAREN); }
"["					{ return new_symbol(sym.LBRACKET); }
"]"					{ return new_symbol(sym.RBRACKET); }
"{"					{ return new_symbol(sym.LBRACE); }
"}"					{ return new_symbol(sym.RBRACE); }

"+"					{ return new_symbol(sym.PLUS); }
"-"					{ return new_symbol(sym.MINUS); }
"*"					{ return new_symbol(sym.MUL); }
"/"					{ return new_symbol(sym.DIV); }
"%"					{ return new_symbol(sym.MOD); }
"="					{ return new_symbol(sym.ASSIGN); }
"++"				{ return new_symbol(sym.INC); }
"--"				{ return new_symbol(sym.DEC); }

"=="				{ return new_symbol(sym.EQUAL); }
"!="				{ return new_symbol(sym.NOTEQUAL); }
">"					{ return new_symbol(sym.GT); }
">="				{ return new_symbol(sym.GTEQ); }
"<"					{ return new_symbol(sym.LT); }
"<="				{ return new_symbol(sym.LTEQ); }
"&&"				{ return new_symbol(sym.AND); }
"||"				{ return new_symbol(sym.OR); }

";"					{ return new_symbol(sym.SEMI); }
":"					{ return new_symbol(sym.COLON); }
","					{ return new_symbol(sym.COMMA); }
"."					{ return new_symbol(sym.DOT); }
"?"					{ return new_symbol(sym.QMARK); }


"//"				{ yybegin(COMMENT); }
<COMMENT> .			{ yybegin(COMMENT); }
<COMMENT> "\r\n"	{ yybegin(YYINITIAL); }


"true"						{ return new_symbol(sym.BOOLCONST, new Boolean(true)); }
"false"						{ return new_symbol(sym.BOOLCONST, new Boolean(false)); }
[0-9]+						{ return new_symbol(sym.NUMCONST, new Integer(yytext())); }
[a-z|A-Z][a-z|A-Z|0-9|_]*	{ return new_symbol(sym.IDENT, new String(yytext())); }
\'[ -~]\'					{ return new_symbol(sym.CHARCONST, new Character(yytext().charAt(1))); }


. { System.err.println("Lexical error --- (" + yytext() + ") on " + (yyline + 1) + ":" + (yycolumn + 1)); }
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%class Lexer
%byaccj
%line
%column

%{

  public Parser   parser;
  public int      lineno;
  public int      column;
  public String   last_text = "";

  public Lexer(java.io.Reader r, Parser parser) {
    this(r);
    this.parser = parser;
    this.lineno = 1;
    this.column = 1;
  }

  public int get_yyline() {
    return this.yyline + 1;
  }

  public int get_yycolumn() {
    return this.yycolumn + 1;
  }
%}

int         = [0-9]+
float       = [0-9]+("."[0-9]+)?
identifier  = [a-zA-Z][a-zA-Z0-9_]*
newline     = \n
whitespace  = [\r]+
linecomment = "//".*
blockcomment= "/*"[^]*"*/"
define      = "#define" {identifier} ({int} | {float} | {identifier})

%%

"int"                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.INT     ; }
"("                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.LPAREN  ; }
")"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.RPAREN  ; }
"["                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.LBRACKET; }
"]"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.RBRACKET; }
";"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.SEMI    ; }
"+"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"-"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"*"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"/"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"and"                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"or"                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"not"                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.OP      ; }
"<"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.RELOP   ; }
"<="                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.RELOP   ; }
">"                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.RELOP   ; }
">="                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.RELOP   ; }
"<>"                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.RELOP   ; }
"="                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.RELOP   ; }
","                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.COMMA   ; }
"."                                 { parser.yylval = new ParserVal((Object)yytext()); return Parser.DOT     ; }
":="                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.ASSIGN  ; }
"::"                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.TYPEOF  ; }
"print"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.PRINT   ; }
"func"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.FUNC    ; }
"var"                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.VAR     ; }
"void"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.VOID    ; }
"bool"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.BOOL    ; }
"float"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.FLOAT   ; }
"struct"                            { parser.yylval = new ParserVal((Object)yytext()); return Parser.STRUCT  ; }
"size"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.SIZE    ; }
"new"                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.NEW     ; }
"if"                                { parser.yylval = new ParserVal((Object)yytext()); return Parser.IF      ; }
"then"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.THEN    ; }
"else"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.ELSE    ; }
"while"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.WHILE   ; }
"return"                            { parser.yylval = new ParserVal((Object)yytext()); return Parser.RETURN  ; }
"break"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.BREAK   ; }
"continue"                          { parser.yylval = new ParserVal((Object)yytext()); return Parser.CONTINUE; }
"true"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.BOOL_VALUE; }
"false"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.BOOL_VALUE; }
"begin"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.BEGIN   ; }
"end"                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.END     ; }
"addr"                              { parser.yylval = new ParserVal((Object)yytext()); return Parser.ADDR    ; }
"value"                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.VALUE   ; }

{int}                               { parser.yylval = new ParserVal((Object)yytext()); return Parser.INT_VALUE ; }
{float}                             { parser.yylval = new ParserVal((Object)yytext()); return Parser.FLOAT_VALUE ; }
{identifier}                        { parser.yylval = new ParserVal((Object)yytext()); return Parser.IDENT   ; }
{linecomment}                       { System.out.print(yytext()); /* skip */ }
{newline}                           { System.out.println(""); /* skip */ }
" "                                 { System.out.print(" "); /* skip */ }
{whitespace}                        { System.out.print(" "); /* skip */ }
"\t"                                { System.out.print("    "); }
{blockcomment}                      { System.out.print(yytext()); /* skip */ }
{define}                            { parser.yylval = new ParserVal((Object)yytext()); System.out.println("AAA"); return Parser.IDENT; }


\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { this.last_text = yytext(); System.err.println("Lexical error: unexpected character \'" + last_text + "\' at " + (yyline+1) + ":" + (yycolumn+1) + "."); return -1; }

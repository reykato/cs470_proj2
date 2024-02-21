public class Parser
{
    public static final int INT         = 1; // "int"
    public static final int LPAREN      = 2; // "("
    public static final int RPAREN      = 3; // ")"
    public static final int LBRACKET      = 4; // "["
    public static final int RBRACKET      = 5; // "]"
    public static final int SEMI        = 6; // ";"
    public static final int OP          = 7; // "+", "-", "*", "/", "and", "or", "not"
    public static final int RELOP       = 8; // "=", "!=", "<", ">", "<=", ">="
    public static final int INT_VALUE   = 9; // {int}
    public static final int IDENT       = 10; // {identifier}
    public static final int PRINT       = 11;
    public static final int FUNC        = 12;
    public static final int VAR         = 13;
    public static final int VOID        = 14;
    public static final int BOOL        = 15;
    public static final int FLOAT       = 16;
    public static final int STRUCT      = 17;
    public static final int SIZE        = 18;
    public static final int NEW         = 19;
    public static final int IF          = 20;
    public static final int THEN        = 21;
    public static final int ELSE        = 22;
    public static final int WHILE       = 23;
    public static final int RETURN      = 24;
    public static final int BREAK       = 25;
    public static final int CONTINUE    = 26;
    public static final int BEGIN       = 29;
    public static final int END         = 30;
    public static final int ADDR        = 31;
    public static final int VALUE       = 32;
    public static final int COMMA       = 33;
    public static final int DOT      = 34;
    public static final int TYPEOF      = 35;
    public static final int ASSIGN      = 36;
    public static final int FLOAT_VALUE = 37;
    public static final int BOOL_VALUE  = 38;

    public Parser(java.io.Reader r, Compiler compiler) throws Exception
    {
        this.compiler = compiler;
        this.lexer    = new Lexer(r, this);
    }

    Lexer            lexer;
    Compiler         compiler;
    public ParserVal yylval;
    SymbolTable symbol_table = new SymbolTable();

    public int yyparse() throws java.io.IOException
    {
        while ( true )
        {
            int token = lexer.yylex();
            String tokenname = "NULL_TOKEN";
            Object attr = yylval.obj;
            String attrname = (String) attr;

            switch(token) {
                case -1:
                    // lexical error is found
                    System.out.println("\nLexical error: unexpected character \'" + lexer.last_text + "\' at " + lexer.get_yyline() + ":" + lexer.get_yycolumn() + ".");
                    return -1;
                case 0:
                    // EOF is reached
                    System.out.println("Success!");
                    return 0;
                case IDENT:
                int id_num = 0;
                    tokenname = "ID";
                    boolean new_id = symbol_table.add_symbol(attrname, token, attrname);
                    if (new_id) {
                        System.out.print(String.format(
                            "<<new symbol table entity [%d, \"%s\"]>>",
                            symbol_table.get_index(attrname), attrname
                            ));
                    }
                    id_num = symbol_table.get_index(attrname);
                    attrname = "sym-id:" + id_num;
                    break;
                case OP:
                    tokenname = "OP";
                    break;
                case RELOP:
                    tokenname = "RELOP";
                    break;
                case LPAREN:
                    tokenname = "LPAREN";
                    break;
                case RPAREN:
                    tokenname = "RPAREN";
                    break;
                case LBRACKET:
                    tokenname = "LBRACKET";
                    break;
                case RBRACKET:
                    tokenname = "RBRACKET";
                    break;
                case SEMI:
                    tokenname = "SEMI";
                    break;
                case COMMA:
                    tokenname = "COMMA";
                    break;
                case INT:
                    tokenname = "INT";
                    break;
                case PRINT:
                    tokenname = "PRINT";
                    break;
                case ASSIGN:
                    tokenname = "ASSIGN";
                    break;
                case TYPEOF:
                    tokenname = "TYPEOF";
                    break;
                case VAR:
                    tokenname = "VAR";
                    break;
                case FUNC:
                    tokenname = "FUNC";
                    break;
                case IF:
                    tokenname = "IF";
                    break;
                case THEN:
                    tokenname = "THEN";
                    break;
                case ELSE:
                    tokenname = "ELSE";
                    break;
                case WHILE:
                    tokenname = "WHILE";
                    break;
                case CONTINUE:
                    tokenname = "CONTINUE";
                    break;
                case ADDR:
                    tokenname = "ADDR";
                    break;
                case VALUE:
                    tokenname = "VALUE";
                    break;
                case BOOL:
                    tokenname = "BOOL";
                    break;
                case STRUCT:
                    tokenname = "STRUCT";
                    break;
                case SIZE:
                    tokenname = "SIZE";
                    break;
                case NEW:
                    tokenname = "NEW";
                    break;
                case DOT:
                    tokenname = "DOT";
                    break;
                case RETURN:
                    tokenname = "RETURN";
                    break;
                case BREAK:
                    tokenname = "BREAK";
                    break;
                case VOID:
                    tokenname = "VOID";
                    break;
                case BEGIN:
                    tokenname = "BEGIN";
                    break;
                case END:
                    tokenname = "END";
                    break;
                case INT_VALUE:
                    tokenname = "INT_VALUE";
                    break;
                case FLOAT:
                    tokenname = "FLOAT";
                    break;
                case FLOAT_VALUE:
                    tokenname = "FLOAT_VALUE";
                    break;
                case BOOL_VALUE:
                    tokenname = "BOOL_VALUE";
                    break;
            }
            if (tokenname == "ID") System.out.print("<" + tokenname + ", attr:" + attrname + ", " + lexer.get_yyline() + ":" + lexer.get_yycolumn() + ">");
            else if (tokenname == "RELOP" || tokenname == "OP") System.out.print("<" + tokenname + ", attr:\"" + attrname + "\" , " + lexer.get_yyline() + ":" + lexer.get_yycolumn() + ">");
            else if (tokenname == "INT_VALUE" || tokenname == "FLOAT_VALUE" || tokenname == "BOOL_VALUE") System.out.print("<" + tokenname + ", attr:\"" + attrname + "\", " + lexer.get_yyline() + ":" + lexer.get_yycolumn() + ">");
            else System.out.print("<" + tokenname + ", " + lexer.get_yyline() + ":" + lexer.get_yycolumn() + ">");
        }
    }
}


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestTokenizer {

    @Test
    public void testComments() {
        String sl_comment = "//Declare an Int";
        ArrayList<Token> list = Tokenizer.tokenizeString(sl_comment, 0);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).token, sl_comment);
        
        assertEquals(list.get(0).tokenName, "SL_COMMENT");
    }

    @Test
    public void testBraces() {
    	String braces = "{ }";
        ArrayList<Token> list = Tokenizer.tokenizeString(braces, 0);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).token, "{");
        assertEquals(list.get(1).token, "}");
        
        assertEquals(list.get(0).tokenName, "L_BRACE");
        assertEquals(list.get(1).tokenName, "R_BRACE");
    }
    
    @Test
    public void testParens() {
    	String parens = "( )";
        ArrayList<Token> list = Tokenizer.tokenizeString(parens, 0);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).token, "(");
        assertEquals(list.get(1).token, ")");
        
        assertEquals(list.get(0).tokenName, "L_PARA");
        assertEquals(list.get(1).tokenName, "R_PARA");
    }
    
    @Test
    public void testBrackets() {
    	String brackets = "[ ]";
        ArrayList<Token> list = Tokenizer.tokenizeString(brackets, 0);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).token, "[");
        assertEquals(list.get(1).token, "]");
        
        assertEquals(list.get(0).tokenName, "L_BRACKET");
        assertEquals(list.get(1).tokenName, "R_BRACKET");
    }
    
    @Test
    public void testKeywords() {
    	String keywords = "int double char boolean if while void";
        ArrayList<Token> list = Tokenizer.tokenizeString(keywords, 0);
        assertEquals(list.size(), 7);
        assertEquals(list.get(0).token, "int");
        assertEquals(list.get(1).token, "double");
        assertEquals(list.get(2).token, "char");
        assertEquals(list.get(3).token, "boolean");
        assertEquals(list.get(4).token, "if");
        assertEquals(list.get(5).token, "while");
        assertEquals(list.get(6).token, "void");
        
        assertEquals(list.get(0).tokenName, "INT");
        assertEquals(list.get(1).tokenName, "DOUBLE");
        assertEquals(list.get(2).tokenName, "CHAR");
        assertEquals(list.get(3).tokenName, "BOOLEAN");
        assertEquals(list.get(4).tokenName, "IF");
        assertEquals(list.get(5).tokenName, "WHILE");
        assertEquals(list.get(6).tokenName, "VOID");
    }
    
    @Test
    public void testSymbols() {
    	String keywords = "; + - * = . && || ! == > < >= <= >> << -> % !=";
        ArrayList<Token> list = Tokenizer.tokenizeString(keywords, 0);
        assertEquals(list.size(), 19);
        assertEquals(list.get(0).token, ";");
        assertEquals(list.get(1).token, "+");
        assertEquals(list.get(2).token, "-");
        assertEquals(list.get(3).token, "*");
        assertEquals(list.get(4).token, "=");
        assertEquals(list.get(5).token, ".");
        assertEquals(list.get(6).token, "&&");
        assertEquals(list.get(7).token, "||");
        assertEquals(list.get(8).token, "!");
        assertEquals(list.get(9).token, "==");
        assertEquals(list.get(10).token, ">");
        assertEquals(list.get(11).token, "<");
        assertEquals(list.get(12).token, ">=");
        assertEquals(list.get(13).token, "<=");
        assertEquals(list.get(14).token, ">>");
        assertEquals(list.get(15).token, "<<");
        assertEquals(list.get(16).token, "->");
        assertEquals(list.get(17).token, "%");
        assertEquals(list.get(18).token, "!=");

        assertEquals(list.get(0).tokenName, "SEMI");
        assertEquals(list.get(1).tokenName, "PLUS");
        assertEquals(list.get(2).tokenName, "MINUS");
        assertEquals(list.get(3).tokenName, "MULT");
        assertEquals(list.get(4).tokenName, "ASGN");
        assertEquals(list.get(5).tokenName, "PERIOD");
        assertEquals(list.get(6).tokenName, "AND");
        assertEquals(list.get(7).tokenName, "OR");
        assertEquals(list.get(8).tokenName, "NOT");
        assertEquals(list.get(9).tokenName, "EQ");
        assertEquals(list.get(10).tokenName, "GT");
        assertEquals(list.get(11).tokenName, "LT");
        assertEquals(list.get(12).tokenName, "GT_EQ");
        assertEquals(list.get(13).tokenName, "LT_EQ");
        assertEquals(list.get(14).tokenName, "RT_SHIFT");
        assertEquals(list.get(15).tokenName, "LFT_SHIFT");
        assertEquals(list.get(16).tokenName, "PRT");
        assertEquals(list.get(17).tokenName, "PERCENT");
        assertEquals(list.get(18).tokenName, "NOT_EQ");

    }

    @Test
    public void testInts() {
    	String brackets = "0 100 40000000 -50";
        ArrayList<Token> list = Tokenizer.tokenizeString(brackets, 0);
        assertEquals(list.size(), 5);
        assertEquals(list.get(0).token, "0");
        assertEquals(list.get(1).token, "100");
        assertEquals(list.get(2).token, "40000000");
        assertEquals(list.get(3).token, "-");
        assertEquals(list.get(4).token, "50");
        
        assertEquals(list.get(0).tokenName, "INT_NUM");
        assertEquals(list.get(1).tokenName, "INT_NUM");
        assertEquals(list.get(2).tokenName, "INT_NUM");
        assertEquals(list.get(3).tokenName, "MINUS");
        assertEquals(list.get(4).tokenName, "INT_NUM");
    }
    
    @Test
    public void testFloats() {
    	String brackets = "0.0 100.56 .5 -50.5";
        ArrayList<Token> list = Tokenizer.tokenizeString(brackets, 0);
        assertEquals(list.size(), 6);
        assertEquals(list.get(0).token, "0.0");
        assertEquals(list.get(1).token, "100.56");
        assertEquals(list.get(2).token, ".");
        assertEquals(list.get(3).token, "5");
        assertEquals(list.get(4).token, "-");
        assertEquals(list.get(5).token, "50.5");
        
        assertEquals(list.get(0).tokenName, "FLOAT_NUM");
        assertEquals(list.get(1).tokenName, "FLOAT_NUM");
        assertEquals(list.get(2).tokenName, "PERIOD");
        assertEquals(list.get(3).tokenName, "INT_NUM");
        assertEquals(list.get(4).tokenName, "MINUS");
        assertEquals(list.get(5).tokenName, "FLOAT_NUM");
    }
    
    @Test
    public void testID() {
    	String sl_comment = "x bestStringNameEver year2016yay";
        ArrayList<Token> list = Tokenizer.tokenizeString(sl_comment, 0);
        assertEquals(list.size(), 3);
        assertEquals(list.get(0).token, "x");
        assertEquals(list.get(1).token, "bestStringNameEver");
        assertEquals(list.get(2).token, "year2016yay");
        
        assertEquals(list.get(0).tokenName, "ID");
        assertEquals(list.get(1).tokenName, "ID");
        assertEquals(list.get(2).tokenName, "ID");
    }
    
    @Test
    public void testJunk() {
    	String sl_comment = "@ # $ ^";
        ArrayList<Token> list = Tokenizer.tokenizeString(sl_comment, 0);
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).token, "@");
        assertEquals(list.get(1).token, "#");
        assertEquals(list.get(2).token, "$");
        assertEquals(list.get(3).token, "^");
        
        assertEquals(list.get(0).tokenName, "JUNK");
        assertEquals(list.get(1).tokenName, "JUNK");
        assertEquals(list.get(2).tokenName, "JUNK");
        assertEquals(list.get(3).tokenName, "JUNK");
    }

    
}

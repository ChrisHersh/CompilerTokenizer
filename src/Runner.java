import java.util.ArrayList;
import java.util.Scanner;


public class Runner {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	processFile();
    }

    
    public static void processFile()
    {
        Scanner s = new Scanner(System.in);
        ArrayList<Token> t = Tokenizer.tokenizeProgram(s.nextLine());
        
        for(Token token : t)
        {
        	System.out.println("("+token.token+", "+token.tokenName+", " + token.lineNumber + ")");
        }
        
    }
}

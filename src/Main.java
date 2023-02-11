import kylin.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        Interpreter interpreter = new Interpreter(text);
        try{
            int result = interpreter.expr();
            System.out.println(result);
        }catch (kylinException kylinException){
            kylinException.printStackTrace();
        }
    }
}
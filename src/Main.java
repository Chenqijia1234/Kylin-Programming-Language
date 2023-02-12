import kylin.Interpreter;
import kylin.kylinException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">>>");
            String text = scanner.nextLine();
            if (text.trim().equals("exit")) {
                System.out.print("Bye");
                break;
            }
            Interpreter interpreter = new Interpreter(text);
            try {
                int result = interpreter.expr();
                System.out.println(result);
            } catch (kylinException kylinException) {
                System.out.println(kylinException.getMessage());
            }
        }
    }
}
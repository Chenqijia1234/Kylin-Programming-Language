package kylin;

import kylin.tokens.Token;

import static kylin.Constants.*;

public class Interpreter {
    protected String textReader;
    protected int position = 0;
    protected Token currentToken;

    public Interpreter(String textReader) {
        this.textReader = textReader;
    }

    protected void throwError() throws kylinException {
        throw new kylinException("Wrong input");
    }

    public Token getNextToken() throws kylinException {
        if (position >= textReader.length()) {
            return new Token(Constants.EOF, null);
        }

        char currentChar = textReader.charAt(position);
        if (Character.isDigit(currentChar)) {
            Token token = new Token(INTEGER, Integer.valueOf(String.valueOf(currentChar)));
            position++;
            return token;
        }
        if (currentChar == '+') {
            Token token = new Token(PLUS, currentChar);
            position++;
            return token;
        }
        throwError();
        return null;
    }

    public int expr() throws kylinException {
        currentToken = getNextToken();

        Token left = currentToken;
        if(currentToken.getValueType().equals(INTEGER)){
            currentToken = getNextToken();
        }else{
            throwError();
        }

        Token operator = currentToken;
        if(currentToken.getValueType().equals(PLUS)){
            currentToken = getNextToken();
        }else{
            throwError();
        }
        Token right = currentToken;
        if(currentToken.getValueType().equals(INTEGER)){
            currentToken = getNextToken();
        }else{
            throwError();
        }

        return (int)left.getValue() + (int) right.getValue();
    }
}

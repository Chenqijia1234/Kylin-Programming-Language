/**
 * @auther Chenqijia1234
 * @Date 2023/02/12
 */

package kylin;

import kylin.tokens.Token;

import static kylin.Constants.*;

public class Interpreter {

    protected String textReader;
    protected int position = 0;
    protected Token currentToken;
    protected Character currentChar;

    public Interpreter(String textReader) {
        this.textReader = textReader;
        this.currentChar = this.textReader.charAt(position);
    }

    protected void throwError() throws kylinException {
        throw new kylinException("错误的输入内容");
    }

    public Token getNextToken() throws kylinException {
        while (currentChar != null) {
            if (Character.isWhitespace(currentChar)) {
                skipWhiteSpace();
                continue;
            }
            if (Character.isDigit(currentChar)) {
                return new Token(INTEGER, getLongInteger());
            }
            if (currentChar.equals('+')) {
                advancePosition();
                return new Token(PLUS, currentChar);
            }
            if (currentChar.equals('-')) {
                advancePosition();
                return new Token(MINUS, currentChar);
            }
            if (currentChar.equals('*')) {
                advancePosition();
                return new Token(MUL, currentChar);
            }
            if (currentChar.equals('/')) {
                advancePosition();
                return new Token(DIV, currentChar);
            }
            throwError();
        }
        return new Token(EOF, null);
    }

    public void advancePosition() {
        position++;
        if (position >= textReader.length()) {
            currentChar = null;
        } else {
            currentChar = textReader.charAt(position);
        }
    }

    public void skipWhiteSpace() {
        while (currentChar != null && Character.isWhitespace(currentChar)) {
            advancePosition();
        }
    }

    public int getLongInteger() {
        StringBuilder result = new StringBuilder();
        while (currentChar != null && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advancePosition();
        }
        return Integer.parseInt(result.toString());
    }

    public void eat(Constants tokenType) throws kylinException {
        if (currentToken.getValueType() == tokenType) {
            currentToken = getNextToken();
        } else {
            throwError();
        }
    }

    public int expr() throws kylinException {
        currentToken = getNextToken();

        Token left = currentToken;
        eat(INTEGER);

        Token operator = currentToken;
        if (operator.getValueType() == PLUS) {
            eat(PLUS);
        } else if(operator.getValueType() == MINUS){
            eat(MINUS);
        } else if (operator.getValueType() == MUL){
            eat(MUL);
        } else{
            eat(DIV);
        }

        Token right = currentToken;
        eat(INTEGER);

        if (operator.getValueType() == PLUS) {
            return (int) left.getValue() + (int) right.getValue();
        } else if (operator.getValueType() == MINUS) {
            return (int) left.getValue() - (int) right.getValue();
        } else if (operator.getValueType() == MUL) {
            return (int) left.getValue() * (int) right.getValue();
        } else{
            return (int) left.getValue() / (int) right.getValue();
        }

    }
}

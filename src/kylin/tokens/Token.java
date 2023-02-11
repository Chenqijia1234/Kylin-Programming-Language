package kylin.tokens;

import kylin.Constants;

import java.util.Objects;

public class Token {
    protected Object value;
    protected Constants valueType;
    public Token(Constants valueType, Object value){
        this.value = value;
        this.valueType = valueType;
    }
    public Object getValue(){
        return value;
    }

    public boolean isStringToken(){
        return false;
    }
    public boolean isNumberToken(){
        return false;
    }
    public boolean isKeyWordToken(){
        return false;
    }
    public boolean isEOFToken(){
        return valueType == Constants.EOF;
    }

    public Constants getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return "<Token(%s)>".formatted(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token token)) return false;
        return getValue().equals(token.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}

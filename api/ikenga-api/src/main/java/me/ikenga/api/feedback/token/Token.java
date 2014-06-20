package me.ikenga.api.feedback.token;

/**
 * Created by schiller on 09.06.2014.
 */
public class Token {

    String tokenname;
    String owner;
    String value;
    String description;

    public Token(String tokenname, String owner, Object value, String description) {
        this.tokenname = tokenname;
        this.value = value.toString();
        this.owner = owner;
        this.description = description;
    }

    public String getTokenname() {
        return tokenname;
    }

    public void setTokenname(String tokenname) {
        this.tokenname = tokenname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

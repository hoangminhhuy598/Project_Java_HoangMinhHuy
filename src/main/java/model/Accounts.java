package model;

public class Accounts {
    private long user_id;
    private String user_name;
    private String pass_word;

    public Accounts() {
    }

    public Accounts(long user_id, String user_name, String pass_word) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.pass_word = pass_word;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getPass_word() {
        return pass_word;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", pass_word='" + pass_word + '\'' +
                '}';
    }
}

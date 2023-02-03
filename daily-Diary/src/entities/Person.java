package entities;


public class Person{
    private String name;
    private String nickName;
    private String password;

    public Person(String name) {
        this.name = name;
    }
    public Person(String name, String password, String nickName) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
    }
    public String getName() {
        return name;
    }
    public String getNickName() {
        return nickName;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(password);
        sb.append("\n"+nickName);
        sb.append("\n"+name);
        return sb.toString();

    }

    
    
}


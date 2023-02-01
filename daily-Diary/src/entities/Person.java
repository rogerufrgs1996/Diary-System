package entities;




public class Person {
    private String name;
    private String password;

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("NAME");
        sb.append("\n"+name);
        sb.append("\nPASSWORD\n");
        sb.append(password);
        sb.append("\n");
        return sb.toString();

    }

    
    
}


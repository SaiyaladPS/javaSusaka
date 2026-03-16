package week2;

public class Student {
    String name;
    int age;
    
    public void setData() {
        name = "Java";
        age = 20;
    }
    
    public void getData() {
        System.out.println("Name: "+ name);
        System.out.println("Age: "+ age);
    }
}

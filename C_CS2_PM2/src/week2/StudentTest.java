package week2;

public class StudentTest {
    public static void main(String[] args) {
        //ສ້າງອ໊ອບເຈັກຊື່ stu1 ຈາກຄລາດ Student
        Student stu1 = new Student();
        stu1.setData();
        stu1.getData();
        
        //===================
        //ສ້າງອ໊ອບເຈັກຊື່ stu1 ຈາກຄລາດ Student
        Student stu2 = new Student();
        stu2.name = "ສຸກສະຫວັນ";
        stu2.age = 22;
        stu2.getData();
    }
}

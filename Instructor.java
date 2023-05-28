public class Instructor {
    private int id;
    private String fName;
    private String lName;
    private int dOB;
    private int age;
    private String gender;
    private String dept;
    private String Salary;
    private String Position;

    public Instructor(String fName,String lName, int dOB, int age, String gender, String dept, String Salary, String Position) {
        this.fName = fName;
        this.lName = lName;
        this.dOB = dOB;
        this.gender = gender;
        this.age = age;
        this.dept = dept;
        this.Salary = Salary;
        this.Position = Position;
    }
    

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for fName
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    
    // Getter and Setter for lName
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    // Getter and Setter for date of birth
    public int getdOB() {
        return dOB;
    }

    public void setdOB(int dOB) {
        this.dOB = dOB;
    }

    // Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter and Setter for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter for dept
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
}

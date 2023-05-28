public class Dept {
    private int id;
    private String name;
    private String head;
    private String location;
    private String phone;

    public Dept(String name,String head, String location, String phone) {
        this.name = name;
        this.head = head;
        this.location = location;
        this.phone = phone;
    }
    

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for age
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

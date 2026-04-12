package demo.testthymeleaf.model;

public class Course {
    private String id;
    private String name;
    private String description;
    private String instructor;
    private double price;
    private int duration;
    private boolean isOnline;
    private int maxStudents;

    public Course() {
    }


    public Course(String id, String name, String description, String instructor, double price, int duration, boolean isOnline, int maxStudents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.duration = duration;
        this.isOnline = isOnline;
        this.maxStudents = maxStudents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
}

package com.instituteadvisor.servlet;

public class College {
    private int id;
    private String name;
    private String location;
    private String district;
    private String course;
    private float cutoffPercentage;
    private int rating;
    private int fees;

    // Constructor
    public College(int id, String name, String location, String district, String course, float cutoffPercentage, int rating, int fees) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.district = district;
        this.course = course;
        this.cutoffPercentage = cutoffPercentage;
        this.rating = rating;
        this.fees = fees;
    }

    public College() {
    }
    
	// Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDistrict() { return district; }
    public String getCourse() { return course; }
    public float getCutoffPercentage() { return cutoffPercentage; }
    public int getRating() { return rating; }
    public int getFees() { return fees; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDistrict(String district) { this.district = district; }
    public void setCourse(String course) { this.course = course; }
    public void setCutoffPercentage(float cutoffPercentage) { this.cutoffPercentage = cutoffPercentage; }
    public void setRating(int rating) { this.rating = rating; }
    public void setFees(int fees) { this.fees = fees; }
}
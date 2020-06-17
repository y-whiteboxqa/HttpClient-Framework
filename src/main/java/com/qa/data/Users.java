package com.qa.data;

public class Users {

    // initialize variables based on the post request payload
    // this particular post request takes 2 variables in the request body. name and
    // job
    String name;
    String job;
    
    //since the response of this request is also giving 2 more variables, declare them
    String id;
    String createdAt;

    // create constructor
    public Users() {

    }

    public Users(String name, String job) {
        this.name = name;
        this.job = job;

    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    

}

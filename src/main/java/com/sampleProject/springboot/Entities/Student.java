package com.sampleProject.springboot.Entities;

public class Student {

    public Student() {
    }

    public Student(int id, String name, String nickName, String description) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.description = description;
    }

    public int id;
    public String name;
    public String nickName;
    public String description;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", nickName=" + nickName + ", description=" + description + "]";
    }

}

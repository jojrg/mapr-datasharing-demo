package com.mapr.datasharingdemo.model;


import org.apache.commons.lang.builder.HashCodeBuilder;

public class User implements DataBean {


    private String name = null;
    private Integer age = null;
    private Long id = null;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(){
        id=new Long(0);
    }

    public User(Long userId, String username, Integer age){
        this.id = userId;
        this.name = username;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(name).
                append(age).
                append(id.toString()).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;


        if (this.name.equals(other.getName()) && (this.age == other.getAge()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "User [id=" + id.toString() + ", name=" + name + ", age=" + age.toString();
    }



}


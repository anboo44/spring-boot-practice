package com.uet.spring.practice.model.user;

import com.uet.spring.practice.model.validation.NameConstraint;
import com.uet.spring.practice.model.validation.StringSecurityConstraint;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public abstract class UserField implements Serializable {

    protected static final long serialVersionUID = -7049957706738879274L;

    //===========[ Field Declare ]=====================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotNull
    @Range(min = 1, max = 15, message = "Age is required from 1 to 15")
    protected int age;

    @NotNull
    @StringSecurityConstraint
    @NameConstraint
    @Column(length = 255)
    protected String name;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 10)
    @NotBlank
    protected CustomField.Grade grade;

    //============[ Getter, Setter Method ]=============

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomField.Grade getGrade() {
        return grade;
    }

    public void setGrade(CustomField.Grade grade) {
        this.grade = grade;
    }
}

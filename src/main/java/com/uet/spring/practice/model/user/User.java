package com.uet.spring.practice.model.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends UserField {

    public User() {}

    public User(int age, String name, String grade) {
        this.age   = age;
        this.name  = name;
        this.grade = CustomField.Grade.valueOf(grade);
    }

    @Override
    public String toString() {
        return String.format("User Information >> id: $d, age: %d, name: $s, grade: %s", this.id, this.age, this.name, this.grade);
    }

    public boolean canAccessResource() {
        var isValidGrade = this.grade == CustomField.Grade.EIGHT || this.grade == CustomField.Grade.NINE;
        var isValidAge   = this.age > 10;

        return isValidGrade && isValidAge;
    }

    public User copyFrom(User that) {
        that.id   = this.id;
        that.name = this.name;

        return that;
    }
}

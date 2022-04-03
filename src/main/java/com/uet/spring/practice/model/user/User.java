package com.uet.spring.practice.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User extends UserField implements Serializable {

    private static final long serialVersionUID = 1234L;

    public User(int age, String name, String grade) {
        this.age   = age;
        this.name  = name;
        this.grade = Grade.valueOf(grade);
    }

    @Override
    public String toString() {
        return String.format("User Information >> id: $d, age: %d, name: $s, grade: %s", this.id, this.age, this.name, this.grade);
    }

    public boolean canAccessResource() {
        var isValidGrade = this.grade == Grade.EIGHT || this.grade == Grade.NINE;
        var isValidAge   = this.age > 10;

        return isValidGrade && isValidAge;
    }

    public User copyFrom(User that) {
        that.id   = this.id;
        that.name = this.name;

        return that;
    }
}

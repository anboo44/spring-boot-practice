package com.uet.spring.practice.model.user;

import com.uet.spring.practice.model.book.Book;
import com.uet.spring.practice.model.validation.NameConstraint;
import com.uet.spring.practice.model.validation.StringSecurityConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@MappedSuperclass
@Data
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
    protected CustomField.Grade grade;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Book> books;
}

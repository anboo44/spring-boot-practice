package com.uet.spring.practice.model.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uet.spring.practice.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = 1934L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = DescriptionConverter.class)
    @Builder.Default
    @Column(nullable = false)
    private Description description;

    @JsonIgnoreProperties("tags")
    @ManyToMany(mappedBy = "tags")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users;

    public Tag() {}
    public Tag(Description description) {
        this.description = description;
    }
}

package com.blog.services.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;
    @Column(name = "title" , length = 100, nullable = false)
    @NotEmpty
    private String categoryTitle;
    @Column(name = "description")
    @NotEmpty
    private String categoryDescription;

    // mapping posts
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts= new ArrayList<>();

}

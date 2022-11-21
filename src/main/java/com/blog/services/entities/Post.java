package com.blog.services.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer post_Id;

    @NotEmpty
    private String post_Title;
    @NotEmpty
    @Column(length = 1000)
    private String post_Content;
    private String post_Image_Name;
    @NotEmpty
    private Date post_Date;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    private  User user;

}

package org.example.springdatajpapractice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "название в таблице")
    private String name;
    //рефлексия использует Hibernate
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product>products;
    @OneToMany(mappedBy = "category",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore
    private List<Option> options=new ArrayList<>();
}

package org.example.springdatajpapractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    //ORM

    //eager - немедленная (жадная) загрузка
    //lazy - отложенная (ленивая) загрузка (нужно удалять то стринг)
    @ManyToOne(fetch =FetchType.LAZY )
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product",cascade = CascadeType.PERSIST)
    private List<Value>values=new ArrayList<>();
}

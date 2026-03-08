package org.example.springdatajpapractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@Entity
//@Table(name = "categories")
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
////    @Column(name = "название в таблице")
//    private String name;
//    //рефлексия использует Hibernate
//    @OneToMany(mappedBy = "category")
//    @JsonIgnore
//    private List<Product>products;
//    @OneToMany(mappedBy = "category",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
//    @JsonIgnore
//    private List<Option> options=new ArrayList<>();
//}

    @Getter
    @Setter
    @NoArgsConstructor

    @Entity
    @Table(name = "categories")
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
        private List<Product> products = new ArrayList<>();

        @OneToMany(mappedBy = "category")
        @BatchSize(size=10)
        private List<Option> options = new ArrayList<>();

        public Category(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }


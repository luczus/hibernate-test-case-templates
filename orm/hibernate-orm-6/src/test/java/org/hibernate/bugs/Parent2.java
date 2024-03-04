package org.hibernate.bugs;

import jakarta.persistence.*;

@Entity
public class Parent2 {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn( name = "parent2_child" )
    private Child child;

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "idGenerator"
    )
    @TableGenerator(
            name = "idGenerator",
            table = "ID_GENERATOR",
            pkColumnName = "ID_GENERATOR_ID",
            valueColumnName = "ID_GENERATOR_VALUE"
    )
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child dimensionMembers) {
        this.child = dimensionMembers;
    }
}


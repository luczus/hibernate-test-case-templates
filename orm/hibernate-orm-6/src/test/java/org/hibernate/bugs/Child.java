package org.hibernate.bugs;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Child {
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
    @Column
    private String someData = UUID.randomUUID().toString();

    public void setId(Long id) {
        this.id = id;
    }

    public String getSomeData() {
        return someData;
    }

    public void setSomeData(String someData) {
        this.someData = someData;
    }

    public Long getId() {
        return id;
    }


}

package org.hibernate.bugs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "TE_DM")
public class DimensionMember {
    @Id
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

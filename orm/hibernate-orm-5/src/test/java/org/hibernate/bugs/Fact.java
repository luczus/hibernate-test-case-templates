package org.hibernate.bugs;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "TE_FACT")
@Entity
public class Fact {

    @ManyToMany
    @JoinTable(name = "TE_FACT_DM", joinColumns = @JoinColumn(name = "FACT_DM_FACT_ID"), inverseJoinColumns = @JoinColumn(name = "FACT_DM_DM_ID"))
    private Set<DimensionMember> dimensionMembers = new HashSet<>();
    @Id
    private Long id;

    public long getBranch() {
        return branch;
    }

    public void setBranch(long branch) {
        this.branch = branch;
    }

    @Column
    private long branch;

    public Set<DimensionMember> getDimensionMembers() {
        return dimensionMembers;
    }

    public void setDimensionMembers(Set<DimensionMember> dimensionMembers) {
        this.dimensionMembers = dimensionMembers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
//
//    public AttributeValueContainer getAttributeContainer() {
//        return attributeContainer;
//    }
//
//    public void setAttributeContainer(AttributeValueContainer attributeContainer) {
//        this.attributeContainer = attributeContainer;
//    }
}


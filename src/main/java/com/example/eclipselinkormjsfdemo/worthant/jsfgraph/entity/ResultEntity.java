package com.example.eclipselinkormjsfdemo.worthant.jsfgraph.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lab3_x_test_table", schema = "s367837")
public class ResultEntity {
    private long id;
    private double x;

    @Id
    @Column
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator"
    )
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "lab3_x_test_table_id_seq",
            allocationSize = 1
    )
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultEntity that)) return false;
        return getId() == that.getId() && Double.compare(getX(), that.getX()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getX());
    }

    @Override
    public String toString() {
        return "XBeanEntity{" +
                "id=" + id +
                ", x=" + x +
                '}';
    }
}

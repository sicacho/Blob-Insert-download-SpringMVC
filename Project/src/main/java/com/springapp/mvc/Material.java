package com.springapp.mvc;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by khangtnse60992 on 5/31/2015.
 */
@Entity
public class Material {
    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "material", nullable = true, insertable = true, updatable = true)
    private byte[] material;
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Integer id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public byte[] getMaterial() {
        return material;
    }

    public void setMaterial(byte[] material) {
        this.material = material;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material1 = (Material) o;

        if (id != null ? !id.equals(material1.id) : material1.id != null) return false;
        if (!Arrays.equals(material, material1.material)) return false;
        if (name != null ? !name.equals(material1.name) : material1.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (material != null ? Arrays.hashCode(material) : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}

package com.warehouse.app.inventory;

import com.fasterxml.jackson.annotation.*;
import com.warehouse.core.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(callSuper = true, exclude = {"children"})
public class Space extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Space parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval = true)
    private List<Space> children = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "space", orphanRemoval = true)
    private List<Stock> stocks = new ArrayList<>();
}

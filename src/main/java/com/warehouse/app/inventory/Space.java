package com.warehouse.app.inventory;

import com.warehouse.core.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Space extends BaseEntity {

    private String name;

    private int capacity;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Space parentId;
}

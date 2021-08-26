package com.inventory.management.core.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @SequenceGenerator(name = "inventory_sequence",sequenceName = "inventory_sequence")
    @GeneratedValue(generator = "inventory_sequence")
    protected Long id;

    @CreationTimestamp
    protected Date created;

    @UpdateTimestamp
    protected Date updated;
}

package com.inventory.management.app.accounting;

import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine,Long> {
}

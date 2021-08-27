package com.warehouse.app.accounting;

import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine,Long> {
}

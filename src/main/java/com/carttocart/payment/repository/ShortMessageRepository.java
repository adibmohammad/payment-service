package com.carttocart.payment.repository;

import com.carttocart.payment.model.entity.ShortMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortMessageRepository extends JpaRepository<ShortMessage, Long> {

}

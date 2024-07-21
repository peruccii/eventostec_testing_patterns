package com.eventostec.eventostec.repositories;

import com.eventostec.eventostec.domain.address.builder.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByEventId(UUID eventId);
}

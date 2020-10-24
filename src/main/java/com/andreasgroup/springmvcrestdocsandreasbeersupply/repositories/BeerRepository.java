package com.andreasgroup.springmvcrestdocsandreasbeersupply.repositories;

import com.andreasgroup.springmvcrestdocsandreasbeersupply.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}

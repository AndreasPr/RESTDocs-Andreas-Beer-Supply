package com.andreasgroup.springmvcrestdocsandreasbeersupply.web.mappers;

import com.andreasgroup.springmvcrestdocsandreasbeersupply.domain.Beer;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto beerDto);
}

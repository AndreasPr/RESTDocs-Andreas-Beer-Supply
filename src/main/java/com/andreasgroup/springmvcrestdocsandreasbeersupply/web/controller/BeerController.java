package com.andreasgroup.springmvcrestdocsandreasbeersupply.web.controller;

import com.andreasgroup.springmvcrestdocsandreasbeersupply.repositories.BeerRepository;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.web.mappers.BeerMapper;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){

        beerRepository.save(beerMapper.BeerDtoToBeer(beerDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){

        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

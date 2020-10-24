package com.andreasgroup.springmvcrestdocsandreasbeersupply.bootstrap;

import com.andreasgroup.springmvcrestdocsandreasbeersupply.domain.Beer;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
@Component
public class LoaderOfBeer implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public LoaderOfBeer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count() == 0){

            beerRepository.save(Beer
                    .builder()
                    .beerName("Alpha Beer")
                    .beerStyle("GOSE")
                    .quantityToBeerProvider(200)
                    .upc(456011111111L)
                    .price(new BigDecimal("20.32"))
                    .minOnHand(15)
                    .build());

            beerRepository.save(Beer
                    .builder()
                    .beerName("Heineken")
                    .beerStyle("SAISON")
                    .quantityToBeerProvider(300)
                    .upc(479311111111L)
                    .price(new BigDecimal("30.32"))
                    .minOnHand(11)
                    .build());
        }
    }
}

package com.andreasgroup.springmvcrestdocsandreasbeersupply.web.controller;

import com.andreasgroup.springmvcrestdocsandreasbeersupply.domain.Beer;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.repositories.BeerRepository;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.web.model.BeerDto;
import com.andreasgroup.springmvcrestdocsandreasbeersupply.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.andreasgroup.springmvcrestdocsandreasbeersupply.web.mappers")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @Test
    void getBeerById() throws Exception{
        //given
        given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(
                get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                        .param("stateOfBeer", "Nice")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer",
                        pathParameters(
                                parameterWithName("beerId").description("UUID of the beer")
                ), requestParameters(
                        parameterWithName("stateOfBeer").description("Is the Beer nice (Query parameter)")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Beer ID"),
                                fieldWithPath("beerName").description("Name of the Beer"),
                                fieldWithPath("beerStyle").description("Style of the Beer"),
                                fieldWithPath("upc").description("UPC of the Beer"),
                                fieldWithPath("price").description("Price of the Beer"),
                                fieldWithPath("version").description("Version of the Beer"),
                                fieldWithPath("createdDate").description("Created Date"),
                                fieldWithPath("lastModifiedDate").description("Last Modified Date"),
                                fieldWithPath("quantityOnHand").description("Quantity On the Hand")
                        )));
    }

    @Test
    void saveNewBeer() throws Exception{
        BeerDto beerDto = getBeerDtoValidSample();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(
                post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer",
                        requestFields(
                                fieldWithPath("beerName").description("Name of the Beer"),
                                fieldWithPath("beerStyle").description("Style of the Beer"),
                                fieldWithPath("upc").description("UPC of the Beer").attributes(),
                                fieldWithPath("price").description("Price of the Beer"),
                                fieldWithPath("id").ignored(),
                                fieldWithPath("version").ignored(),
                                fieldWithPath("createdDate").ignored(),
                                fieldWithPath("lastModifiedDate").ignored(),
                                fieldWithPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updateBeerById() throws Exception{
        BeerDto beerDto = getBeerDtoValidSample();
        String beetDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(
                put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beetDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getBeerDtoValidSample(){
        return BeerDto.builder()
                .beerName("Beer Test")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("12.67"))
                .upc(1246L)
                .build();
    }
}

package com.andreasgroup.springmvcrestdocsandreasbeersupply.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
public class BeerPagedList extends PageImpl<BeerDto> {


    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }
}

package com.andreasgroup.springmvcrestdocsandreasbeersupply.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created on 24/Oct/2020 to spring-mvc-restdocs-andreas-beer-supply
 */
@Component
public class DateMapper {

    // TYPE CONVERSION FROM Timestamp to OffsetDateTime and vice versa
    public OffsetDateTime asOffsetDateTime(Timestamp ts){
        if(ts != null){
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
        }
        else {
            return null;
        }
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime){
        if(offsetDateTime != null){
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }
        else{
            return null;
        }
    }


}

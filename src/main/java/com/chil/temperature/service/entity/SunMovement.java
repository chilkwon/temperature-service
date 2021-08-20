package com.chil.temperature.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SunMovement {

    private String sunRise;
    private String sunSet;
    private String solarNoon;
    private String dayLength;
    private String civilTwilightBegin;
    private String civilTwilightEnd;
    private String nauticalTwilightBegin;
    private String nauticalTwilightEnd;
    private String astronomicalTwilightBegin;
    private String astronomicalTwilightEnd;

}

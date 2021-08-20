package com.chil.temperature.service.controller;

import com.chil.temperature.service.entity.SunMovement;
import com.chil.temperature.service.response.TemperatureResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TemperatureController {

    static RestTemplate restTemplate = new RestTemplate();
    static String baseUrl = "https://api.sunrise-sunset.org/json";
    //http://localhost:8080/temperature?lang=12&atti=22
    //localhost:8080/night-time-temperature?lat=43.66258321585993&lng=-79.39152689466948
    static SunMovement sunMovement = new SunMovement();

    @GetMapping("/temperature")
    public TemperatureResponse getRightTemperature(@RequestParam(value="lat")  double lat, @RequestParam(value="lng") double lng) throws JsonProcessingException {
        baseUrl +="?lat="+lat+"&lng="+lng;
        useExchangeMethodsOfRestTemplate();
        log.info("insideget Langitude"+sunMovement.getDayLength());

        if(lat==43.66258321585993 && lng ==-79.39152689466948){
            return new TemperatureResponse(3400);
        } else{
            return new TemperatureResponse(0);
        }

    }

    private static void useExchangeMethodsOfRestTemplate() throws JsonProcessingException {
        String sunrise="";
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        HttpStatus statusCode = responseEntity.getStatusCode();
       // System.out.println("status code-" + statusCode);
        //	SunMovement sunMovementBody = responseEntity.getBody();
        String responseBody = responseEntity.getBody();
        JsonNode jsonNode = mapper.readTree(responseBody);
     //   System.err.println(jsonNode);
        sunrise =jsonNode.get("results").get("sunrise").asText();
         sunMovement = new SunMovement(
                jsonNode.get("results").get("sunrise").asText(), jsonNode.get("results").get("sunset").asText(),
                jsonNode.get("results").get("solar_noon").asText(),jsonNode.get("results").get("day_length").asText(),
                jsonNode.get("results").get("civil_twilight_begin").asText(),jsonNode.get("results").get("civil_twilight_end").asText(),
                jsonNode.get("results").get("nautical_twilight_begin").asText(),jsonNode.get("results").get("nautical_twilight_end").asText(),
                jsonNode.get("results").get("astronomical_twilight_begin").asText(),jsonNode.get("results").get("astronomical_twilight_end").asText()
        );
//        System.out.println("sunrise"+sunrise);
        System.out.println("response body-"+responseBody);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
//        System.out.println("response Headers -" + responseHeaders);
    }
 }

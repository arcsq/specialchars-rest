package com.arcsq.specialchars.rest.specialcharsrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EntryPointController {

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String destinationMethod(@RequestBody String body) {
        System.out.println("After http call: " + body);
        return "Ok";
    }


    @GetMapping(value = "/invoke")
    public String invoke() {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String data = "'tÂ est'";
        System.out.println("Prior to http call: " + data);
        HttpEntity<String> entity = new HttpEntity<>(data, headers);

        template.exchange("http://localhost:8080/post", HttpMethod.POST, entity, String.class);
        return "Invoked";
    }

}

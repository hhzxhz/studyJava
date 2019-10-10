package com.wb.controller;

import com.wb.utils.HttpClientHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;



@RestController
public class TestController {

    private final Logger LOGGER= LoggerFactory.getLogger(getClass());

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    private String providerName = "WB-PROVIDER";


    @GetMapping(value="/servers")
    public String getServers() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(providerName);

        String res = "";
        for (ServiceInstance serviceInstance : serviceInstances) {
            res += serviceInstance.getUri() + "\n";
        }

        return res;
    }


    @GetMapping(value = "/call")
    public String test() throws IOException {

        String res = restTemplate.getForObject("http://" + providerName + "/provider/test", String.class);

        return "this is wb-consumer call provider res:" + res ;
    }
}
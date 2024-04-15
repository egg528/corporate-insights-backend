package org.example.clients.client;

import org.example.clients.config.OpenDartFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "openDartClient", url = "${api.open-dart.url}", configuration = OpenDartFeignConfig.class)
public interface OpenDartClient {
    @RequestMapping(method = RequestMethod.GET, value = "/corpCode.xml")
    byte[] findCorporatesZipFile();
}

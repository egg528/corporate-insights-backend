package org.example.clients.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OpenDartInterceptor implements RequestInterceptor {
    private final String openDartKey;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("crtfc_key", openDartKey);
    }
}

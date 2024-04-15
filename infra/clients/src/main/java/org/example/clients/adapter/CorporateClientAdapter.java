package org.example.clients.adapter;

import lombok.RequiredArgsConstructor;
import org.example.clients.client.OpenDartClient;
import org.example.domain.Corporate;
import org.example.domain.LoadCorporateOutputPort;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CorporateClientAdapter implements LoadCorporateOutputPort {

    private final OpenDartClient openDartClient;

    @Override
    public Stream<Corporate> loadAllCorporates() {
        byte[] res = openDartClient.findCorporatesZipFile();
        return null;
    }
}

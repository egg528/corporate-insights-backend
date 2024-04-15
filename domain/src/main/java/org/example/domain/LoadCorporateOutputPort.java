package org.example.domain;

import java.util.stream.Stream;

public interface LoadCorporateOutputPort {
    Stream<Corporate> loadAllCorporates();
}

package org.example.domain.corporate;

import java.util.Map;

public interface CorporateQueryPort {
    Map<String, Corporate> findAll();
}

package org.example.domain;


import java.util.Map;

public interface CorporateQueryPort {
    Map<String, Corporate> findAll();
}

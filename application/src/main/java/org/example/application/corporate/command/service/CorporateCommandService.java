package org.example.application.corporate.command.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.application.corporate.command.RenewCorporateUseCase;
import org.example.domain.corporate.Corporate;
import org.example.domain.corporate.CorporateImportPort;
import org.example.domain.corporate.CorporateQueryPort;
import org.example.domain.corporate.CorporateCommandPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class CorporateCommandService implements RenewCorporateUseCase {

    private final CorporateImportPort corporateImportPort;
    private final CorporateCommandPort updateCorporatePort;
    private final CorporateQueryPort readCorporatePort;

    @Override
    public void renewAll() {
        List<Corporate> loadedCorporates;
        try {
            loadedCorporates  = corporateImportPort.importAllCorporates();
        } catch (Exception e){
            log.error("Failed to load corporates from the data source.",e);
            return;
        }

        Map<String, Corporate> corporateMap = readCorporatePort.findAll();

        loadedCorporates.forEach(corporate -> {
            Corporate existingCorporate = corporateMap.get(corporate.getCode());
            upsertCorporate(existingCorporate, corporate);
        });
    }

    private void upsertCorporate(Corporate existingCorporate, Corporate loadedCorporate) {
        if (existingCorporate == null) {
            updateCorporatePort.save(loadedCorporate);  // 신규 기업 insert
        } else if (!loadedCorporate.getLastModified().equals(existingCorporate.getLastModified())) {
            updateCorporatePort.save(loadedCorporate);  // 기존 기업 update
        }
    }
}

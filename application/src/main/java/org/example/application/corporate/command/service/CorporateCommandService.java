package org.example.application.corporate.command.service;

import lombok.RequiredArgsConstructor;
import org.example.application.corporate.command.RenewCorporatesUseCase;
import org.example.domain.Corporate;
import org.example.domain.LoadCorporatePort;
import org.example.domain.ReadCorporatePort;
import org.example.domain.UpdateCorporatePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class CorporateCommandService implements RenewCorporatesUseCase {

    private final LoadCorporatePort loadCorporatePort;
    private final UpdateCorporatePort updateCorporatePort;
    private final ReadCorporatePort readCorporatePort;

    @Override
    public void renew() throws XMLStreamException, IOException {
        List<Corporate> loadedCorporates = loadCorporatePort.loadAllCorporates();

        Map<String, Corporate> corporateMap = readCorporatePort.findAll();

        // 신규 기업 insert
        loadedCorporates.stream()
                .filter(corporate -> !corporateMap.containsKey(corporate.getCode()))
                .forEach(updateCorporatePort::save);

        // 수정 기업 update
        loadedCorporates.stream()
                .filter(corporate -> corporateMap.containsKey(corporate.getCode()))
                .filter(corporate -> !corporate.getLastModified().equals(corporateMap.get(corporate.getCode())))
                .forEach(updateCorporatePort::save);
    }
}

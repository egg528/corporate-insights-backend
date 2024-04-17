package org.example.api.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.application.corporate.command.RenewCorporatesUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CorporateRenewScheduler {
    private final RenewCorporatesUseCase renewCorporatesUseCase;

    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Seoul")
    public void renewCorporates() throws XMLStreamException, IOException {
        renewCorporatesUseCase.renew();
    }
}

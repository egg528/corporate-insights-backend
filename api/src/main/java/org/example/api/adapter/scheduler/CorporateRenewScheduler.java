package org.example.api.adapter.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.application.corporate.command.RenewCorporatesUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorporateRenewScheduler {
    private final RenewCorporatesUseCase renewCorporatesUseCase;

    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Seoul")
    public void renewCorporates() {
        renewCorporatesUseCase.renew();
    }
}

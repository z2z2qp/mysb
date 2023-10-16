package me.will.sb.service;

import me.will.sb.entity.Log;
import me.will.sb.mapper.LogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author 庄佳彬
 * @since 2023/10/14 16:22
 */
@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Async
    public void save(final Log log) {
        CompletableFuture.runAsync(() -> logRepository.save(log));
    }
}

package com.lichenxing.routingdatasource.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * DelayStopService
 *
 * @author Chenxing Li
 * @date 13/09/2017 19:13
 */
@Slf4j
@Service
public class DelayStopService implements SmartLifecycle {

    private AtomicBoolean running = new AtomicBoolean(false);

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        log.info("############## DelayStopService STOPPPPPPPPPPPPPPPPPP WITH callback");
        stop();
        callback.run();
        running.set(false);
        log.info("############## DelayStopService STOPPPPPPPPPPPPPPPPPP end WITH callback");
    }

    @Override
    public void start() {
        log.info("############## DelayStopService STARTTTTTTTTTTTTTTTT");
        running.set(true);
    }

    @Override
    public void stop() {
        log.info("############## DelayStopService STOPPPPPPPPPPPPPPPPPP");
        running.set(false);
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("############## DelayStopService STOPPPPPPPPPPPPPPPPPP end");
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public int getPhase() {
        return -200;
    }
}

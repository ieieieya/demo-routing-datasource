package com.lichenxing.routingdatasource.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * LifeIsHardService
 *
 * @author Chenxing Li
 * @date 07/09/2017 21:01
 */
@Slf4j
@Service
public class LifeIsHardService implements SmartLifecycle {

    private AtomicBoolean running = new AtomicBoolean(false);

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        log.info("##### #### STOPPPPPPPPPPPPPPPPPP WITH callback");
        callback.run();
        running.set(false);
        log.info("##### #### STOPPPPPPPPPPPPPPPPPP end WITH callback");
    }

    @Override
    public void start() {
        log.info("##### #### STARTTTTTTTTTTTTTTTT");
        running.set(true);
    }

    @Override
    public void stop() {
        log.info("##### #### STOPPPPPPPPPPPPPPPPPP");
        running.set(false);
        log.info("##### #### STOPPPPPPPPPPPPPPPPPP end");
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public int getPhase() {
        return 200;
    }

}

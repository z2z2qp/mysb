package me.will.sb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * 优雅的启动第三方服务
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020 /8/20 16:50
 */
@Component
public class ThirdService implements ApplicationRunner, ApplicationListener<ContextClosedEvent> {

    private ThirdServiceImpl service;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        service = new ThirdServiceImpl();
    }

    /**
     * 具体跑的方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!Objects.isNull(service)){
            new Thread(service).start();
        }

    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (!Objects.isNull(service)){
            service.off();
        }
    }
}

/**
 * 假装的第三方服务
 */
class ThirdServiceImpl implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(ThirdServiceImpl.class);
    private boolean onOff = true;

    @Override
    public void run() {
        while (onOff){
            log.info("aaaaaa");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.debug("sleep error");
            }
        }
        log.info("service is stop");
    }

    /**
     * 关闭
     */
    public void off(){
        onOff = false;
    }
}

package com.github.wang007;

import io.vertx.core.Vertx;
import io.vertx.core.logging.LoggerFactory;
import me.wang007.boot.VertxBoot;

/**
 * created by wang007 on 2019/9/30
 */
public class Main {
    public static void main(String[] args) {

        System.setProperty(LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME,
                "io.vertx.core.logging.SLF4JLogDelegateFactory");

        Vertx vertx = Vertx.vertx();

        VertxBoot.create(vertx)
                .beforeDeployedHook(VertxBootHolder::vertxBoot)
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(vertx::close));
    }
}

package com.github.wang007;

import io.vertx.core.AbstractVerticle;
import me.wang007.annotation.Deploy;
import me.wang007.annotation.Route;

/**
 * created by wang007 on 2019/9/30
 */
@Deploy
public class DemoVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {


        vertx.eventBus().consumer("test3", msg -> {
            System.out.println(msg.body());
            msg.reply("test3 from eventBus");
        });

        //throw new NullPointerException();

    }
}

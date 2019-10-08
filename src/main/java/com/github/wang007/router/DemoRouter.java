package com.github.wang007.router;

import com.github.wang007.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.redis.RedisClient;
import me.wang007.annotation.Route;
import me.wang007.router.AbstractLoadRouter;
import me.wang007.verticle.HttpServerVerticle;

/**
 * created by wang007 on 2019/9/30
 */
@Route("/demo")
public class DemoRouter extends AbstractLoadRouter<HttpServer> {

    private RedisClient redisClient;

    @Override
    protected void init(HttpServer server) {
        redisClient = server.getRedisClient();
    }

    @Override
    public void start() {
        router.route("/d1").handler(rc -> {
            System.out.println("route-path: /demo/d1");
            rc.response().end("ok from d1");

            redisClient.setex("test.xx", 30, "hello", ar -> {
                ar.result();
                System.out.println("set redis value result -> " + ar.result());
            });
        });


        router.route("/d2").handler(rc -> {
            System.out.println("rout-path: /demo/d2");
            rc.response().end("ok from d2");
            redisClient.get("test.xx", ar -> {
                System.out.println("get redis value result -> " + ar.result());
            });
        });

        router.route("/d3").handler(rc -> {
            vertx.eventBus().<String>send("test3", "d333", ar -> {
                rc.response().end(ar.result().body());
            });

        });
    }
}

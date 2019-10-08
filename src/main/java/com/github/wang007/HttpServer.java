package com.github.wang007;

import io.vertx.core.Future;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import me.wang007.annotation.Deploy;
import me.wang007.boot.VertxBoot;
import me.wang007.verticle.HttpServerVerticle;

/**
 * created by wang007 on 2019/9/30
 */
@Deploy(instances = Integer.MAX_VALUE)
public class HttpServer extends HttpServerVerticle {

    private RedisClient redisClient;

    public RedisClient getRedisClient() {
        return redisClient;
    }

    @Override
    protected void init(Future<Void> initFuture) {
        VertxBoot boot = VertxBootHolder.vertxBoot();
        RedisOpt opt = boot.loadFor(RedisOpt.class);
        this.redisClient = RedisClient.create(vertx, opt);
        //必须是redisClient初始化完成时，才调用future#complete
        //如果是异步的client，必须是异步完成时，才调用future#complete
        initFuture.complete();
    }
}

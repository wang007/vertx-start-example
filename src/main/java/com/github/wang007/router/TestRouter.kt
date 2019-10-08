package com.github.wang007.router

import com.github.wang007.HttpServer
import io.vertx.kotlin.redis.getAwait
import io.vertx.kotlin.redis.setexAwait
import io.vertx.redis.RedisClient
import me.wang007.annotation.Route
import me.wang007.router.CoroutineRouter

/**
 *  created by wang007 on 2019/10/2
 */
@Route
class TestRouter : CoroutineRouter<HttpServer>() {

    private lateinit var redis: RedisClient

    override fun init(http: HttpServer) {
        redis = http.redisClient
    }

    override suspend fun start() {

        router.route("/test") {rc ->

            rc.response().end("test from coroutineRouter")
            val result = redis.setexAwait("test.key", 30, "coroutineRouter")
            println("redis result -> $result")
        }


        router.route("/test2") {rc ->

            rc.response().end("test2 from coroutineRouter")
            val result = redis.getAwait("test.key")
            println("redis result -> $result")
        }

    }
}
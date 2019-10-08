package com.github.wang007;

import me.wang007.boot.VertxBoot;

/**
 * created by wang007 on 2019/9/30
 */
public class VertxBootHolder {

    private volatile static VertxBoot vertxBoot;

    synchronized static void vertxBoot(VertxBoot boot) {
        VertxBootHolder.vertxBoot = boot;
    }

    public synchronized static VertxBoot vertxBoot() {
        return vertxBoot;
    }
}

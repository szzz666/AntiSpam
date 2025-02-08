package top.szzz666.AntiSpam.config;


import static top.szzz666.AntiSpam.AntiSpamMain.ec;
import static top.szzz666.AntiSpam.AntiSpamMain.plugin;

public class MyConfig {
    public static void initConfig() {
        ec = new EasyConfig("config.yml", plugin);
        ec.add("reloadCommand", "asreload");
        ec.add("cooldownTicks", 60);
        ec.add("maxAttempts", 3);
        ec.add("maxLength", 100);
        ec.add("messageTooLong", "消息过长，请限制在 %maxLength% 字以内");
        ec.add("messageTooSpam", "请不要刷屏！");
        ec.load();
    }

}

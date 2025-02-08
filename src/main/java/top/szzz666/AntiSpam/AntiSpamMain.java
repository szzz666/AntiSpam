package top.szzz666.AntiSpam;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import top.szzz666.AntiSpam.command.MyCommand;
import top.szzz666.AntiSpam.config.EasyConfig;
import top.szzz666.AntiSpam.event.Listeners;

import static top.szzz666.AntiSpam.config.MyConfig.initConfig;
import static top.szzz666.AntiSpam.tools.pluginUtil.nkConsole;
import static top.szzz666.AntiSpam.tools.pluginUtil.pluginNameLineConsole;


public class AntiSpamMain extends PluginBase {
    public static Plugin plugin;
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    public static EasyConfig ec;

    //插件读取
    @Override
    public void onLoad() {
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        initConfig();
        nkConsole("&b" + plugin.getName() + "插件读取...");
    }

    //插件开启
    @Override
    public void onEnable() {
        //注册监听器
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
        //注册命令
        this.getServer().getCommandMap().register(this.getName(), new MyCommand());
        pluginNameLineConsole();
        nkConsole("&b" + plugin.getName() + "插件开启");
        nkConsole("&c" + plugin.getName() + "如果遇到任何bug，请加入Q群进行反馈：894279534", 1);
    }

    //插件关闭
    @Override
    public void onDisable() {
        nkConsole("&b" + plugin.getName() + "插件关闭");
    }

}

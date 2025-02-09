package top.szzz666.AntiSpam.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

import static top.szzz666.AntiSpam.AntiSpamMain.ec;
import static top.szzz666.AntiSpam.AntiSpamMain.nkServer;


public class Listeners implements Listener {
    private Map<String, Integer> lastMessageTicks = new HashMap<>(); // 记录玩家最后一次发送消息的 tick

    private Map<String, Integer> messageCounts = new HashMap<>(); // 记录玩家在冷却时间内发送的消息数量

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        lastMessageTicks.remove(playerName);
        messageCounts.remove(playerName);
    }


    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage();
        if (message.length() > ec.getInt("maxLength")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ec.getString("messageTooLong")
                    .replace("%maxLength%", ec.getInt("maxLength") + ""));
            return;
        }
        Player player = event.getPlayer();
        String playerName = player.getName();
        int currentTick = nkServer.getTick();
        if (lastMessageTicks.containsKey(playerName)) {
            int lastMessageTick = lastMessageTicks.get(playerName);
            int messageCount = messageCounts.getOrDefault(playerName, 0);
            if (currentTick - lastMessageTick < ec.getInt("cooldownTicks")) {
                messageCount++;
                messageCounts.put(playerName, messageCount);
                if (messageCount > ec.getInt("maxAttempts")) {
                    event.setCancelled(true);
                    player.sendMessage(ec.getString("messageTooSpam"));
                    lastMessageTicks.put(playerName, currentTick);
                    return;
                }
            } else {
                messageCounts.put(playerName, 1);
            }
        } else {
            messageCounts.put(playerName, 1);
        }
        lastMessageTicks.put(playerName, currentTick);
    }
}

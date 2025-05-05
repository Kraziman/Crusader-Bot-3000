package com.CrusaderBot3000;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.time.Duration;
import java.time.Instant;

public class CommandListener extends ListenerAdapter {

    private final Instant startTime = Instant.now();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String msg = event.getMessage().getContentRaw();
        if (!msg.startsWith("!")) return;

        String[] args = msg.substring(1).split(" ");
        String command = args[0].toLowerCase();

        switch (command) {
            case "ping":
                event.getChannel().sendMessage("🏓 Pong!").queue();
                break;

            case "members":
                int total = event.getGuild().getMemberCount();
                event.getChannel().sendMessage("👥 Members: " + total).queue();
                break;

            case "uptime":
                Duration uptime = Duration.between(startTime, Instant.now());
                long minutes = uptime.toMinutes();
                long seconds = uptime.toSecondsPart();
                event.getChannel().sendMessage("⏱ Uptime: " + minutes + "m " + seconds + "s").queue();
                break;

            case "help":
                event.getChannel().sendMessage("""
                        🤖 **CrusaderBot3000 Commands**
                        `!ping` – Check if bot is online
                        `!members` – Show total server members
                        `!uptime` – Show bot uptime
                        `!help` – Show this message
                        """).queue();
                break;

            case "invite":
                event.getChannel().sendMessage("https://discord.gg/SdQS9YZ").queue();
                break;

            default:
                event.getChannel().sendMessage("❓ Unknown command. Try `!help`.").queue();
        }
    }
}


package com.CrusaderBot3000;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.time.Duration;
import java.time.Instant;

public class SlashCommandListener extends ListenerAdapter {
    private final Instant startTime = Instant.now();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping":
                event.reply("🏓 Pong!").queue();
                break;
            case "members":
                int total = event.getGuild().getMemberCount();
                event.reply("👥 Members: " + total).queue();
                break;
            case "uptime":
                Duration uptime = Duration.between(startTime, Instant.now());
                long minutes = uptime.toMinutes();
                long seconds = uptime.toSecondsPart();
                event.reply("⏱ Uptime: " + minutes + "m " + seconds + "s").queue();
                break;
            case "help":
                event.reply("""
                    🤖 **CrusaderBot3000 Commands**
                    `/ping` – Check if bot is online
                    `/members` – Show total server members
                    `/uptime` – Show bot uptime
                    `/invite' - Show the server's invite link
                    `/help` – Show this message
                    """).queue();
                break;
            case "invite":
                event.reply("https://discord.gg/SdQS9YZ").queue();
                break;
            default:
                event.reply("❓ Unknown command.").setEphemeral(true).queue();
        }
    }
}
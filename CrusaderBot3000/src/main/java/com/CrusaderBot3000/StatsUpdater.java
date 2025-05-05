package com.CrusaderBot3000;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

import java.util.EnumSet;

public class StatsUpdater implements Runnable {
    private final JDA jda;

    public StatsUpdater(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void run() {
        for (Guild guild : jda.getGuilds()) {
            updateStats(guild);
        }
    }

    private void updateStats(Guild guild) {
        Category category = guild.getCategoriesByName("📊 Server Stats", true)
                .stream().findFirst().orElse(null);

        if (category == null) {
            category = guild.createCategory("📊 Server Stats").complete();
            category.getManager()
                    .putPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .queue();
        }

        int total = guild.getMemberCount();
        long online = guild.getMembers().stream()
                .filter(m -> !m.getUser().isBot())
                .filter(m -> m.getOnlineStatus() != OnlineStatus.OFFLINE)
                .count();
        long bots = guild.getMembers().stream()
                .filter(m -> m.getUser().isBot())
                .count();

        updateOrCreateChannel(guild, category, "👥 Total: " + total, "👥");
        updateOrCreateChannel(guild, category, "🟢 Online: " + online, "🟢");
        updateOrCreateChannel(guild, category, "🤖 Bots: " + bots, "🤖");
    }

    private void updateOrCreateChannel(Guild guild, Category category, String newName, String emojiPrefix) {
        String channelName = newName.toLowerCase().replace(" ", "-");

        VoiceChannel existing = category.getVoiceChannels().stream()
                .filter(c -> c.getName().startsWith(emojiPrefix.toLowerCase()))
                .findFirst().orElse(null);

        if (existing == null) {
            guild.createVoiceChannel(channelName, category)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .queue();
        } else if (!existing.getName().equals(channelName)) {
            existing.getManager().setName(channelName).queue();
        }
    }
}
package com.CrusaderBot3000;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        String token = "DISCORD_TOKEN"; // Replace with your bot token

        JDA jda = JDABuilder.createDefault(token)
                .setActivity(Activity.watching("server stats"))
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_PRESENCES
                )
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableCache(CacheFlag.ONLINE_STATUS)
                .addEventListeners(new SlashCommandListener())
                .build();

        jda.awaitReady(); // Wait for JDA to fully load
        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Replies with pong"),
                        Commands.slash("members", "Shows total members in the server"),
                        Commands.slash("uptime", "Shows how long the bot has been running"),
                        Commands.slash("invite", "Shows invite link for the server"),
                        Commands.slash("help", "List all commands")
                ).queue();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        StatsUpdater updater = new StatsUpdater(jda);
        scheduler.scheduleAtFixedRate(updater, 0, 5, TimeUnit.SECONDS);
    }
}
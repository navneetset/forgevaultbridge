package tech.sethi.pebbles.forgevaultbridge;

import com.mojang.logging.LogUtils;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.slf4j.Logger;

import java.util.UUID;

@Mod(Forgevaultbridge.MODID)
public class Forgevaultbridge {
    public static final String MODID = "forgevaultbridge";

    private static Economy economy;

    private static Permission permission;

    private static Chat chat;

    public Forgevaultbridge() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public void onServerStarting(ServerStartedEvent event) {
        LOGGER.info("Server starting");
        LOGGER.info("Initializing Pebble's Forge Vault Bridge");
        setupEconomy();
    }


    private static void setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            LOGGER.info("Cannot find Vault!");
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            LOGGER.info("Registered Service Provider for Economy.class not found");
            return;
        }
        economy = rsp.getProvider();
        LOGGER.info("Economy successfully hooked up");
        LOGGER.info("Economy: " + economy.getName());
    }

    public static Economy getEconomy() {
        return economy;
    }


    public static String getPlayerName(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

}

package com.earth2me.essentials.chat;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.EssentialsLogger;
import com.earth2me.essentials.chat.processing.LegacyChatHandler;
import com.earth2me.essentials.chat.processing.SignedChatHandler;
import net.ess3.api.IEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;
import static com.earth2me.essentials.I18n.tl;

public class EssentialsChat extends JavaPlugin {
    private transient IEssentials ess;

    @Override
    public void onEnable() {
        EssentialsLogger.updatePluginLogger(this);
        final PluginManager pluginManager = getServer().getPluginManager();
        ess = (IEssentials) pluginManager.getPlugin("Essentials");
        if (!this.getDescription().getVersion().equals(ess.getDescription().getVersion())) {
            getLogger().log(Level.WARNING, tl("versionMismatchAll"));
        }
        if (!ess.isEnabled()) {
            this.setEnabled(false);
            return;
        }

        final SignedChatHandler signedHandler = new SignedChatHandler((Essentials) ess, this);
        if (signedHandler.tryRegisterListeners()) {
            getLogger().info("Secure signed chat and previews are enabled.");
        } else {
            final LegacyChatHandler legacyHandler = new LegacyChatHandler((Essentials) ess, this);
            legacyHandler.registerListeners();
        }
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {
        return ess.onCommandEssentials(sender, command, commandLabel, args, EssentialsChat.class.getClassLoader(), "com.earth2me.essentials.chat.Command", "essentials.", null);
    }
}

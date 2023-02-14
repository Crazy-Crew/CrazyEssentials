package com.earth2me.essentials.commands;

import com.earth2me.essentials.Mob;
import com.earth2me.essentials.User;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import us.crazycrew.crazyessentials.ServerVersion;

import static com.earth2me.essentials.I18n.tl;

public class Commandbeezooka extends EssentialsCommand {

    public Commandbeezooka() {
        super("beezooka");
    }

    @Override
    protected void run(final Server server, final User user, final String commandLabel, final String[] args) throws Exception {
        if (ServerVersion.isLessThan(ServerVersion.v1_15)) {
            user.sendMessage(tl("unsupportedFeature"));
            return;
        }

        final Entity bee = Mob.BEE.spawn(user.getWorld(), server, user.getBase().getEyeLocation());
        bee.setVelocity(user.getBase().getEyeLocation().getDirection().multiply(2));

        ess.scheduleSyncDelayedTask(() -> {
            final Location loc = bee.getLocation();
            bee.remove();
            loc.getWorld().createExplosion(loc, 0F);
        }, 20);
    }

}

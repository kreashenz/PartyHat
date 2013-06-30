package me.kreashenz.partyhat;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class PartyHat extends JavaPlugin {

	@Override
	public void onEnable(){
		getLogger().info("has been enabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player player = (Player)sender;
		final PlayerInventory pi = player.getInventory();
		final World w = player.getWorld();
		BukkitTask task = null;
		String success = "§aEnjoy your new hat!";
		String noperm = "§cYou do not have permission to use this..";
		String p = "§cYou must be a player to use this..";
		if (cmd.getName().equalsIgnoreCase("partyhat")){
			if(sender instanceof Player){
				if(sender.hasPermission("partyhat.help")) {
					sender.sendMessage("§a[§bPartyHat§a] §2/ph §d: §aShow the help page (this)");
					sender.sendMessage("§a[§bPartyHat§a] §2/builder §d: §aWear the building costume.");
					sender.sendMessage("§a[§bPartyHat§a] §2/hats§d :§a List the avaliable hats.");
					sender.sendMessage("§a[§bPartyHat§a] §2/cancel§d : §aDisables the hat switching.");
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}

		if (cmd.getName().equalsIgnoreCase("cancel")){
			if(sender instanceof Player){
				if(sender.hasPermission("partyhat.cancel")) {
					getServer().getScheduler().cancelTasks(this);
					pi.setArmorContents(null);
					pi.clear();
					for(PotionEffect pot : player.getActivePotionEffects())
						player.removePotionEffect(pot.getType());
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}

		if (cmd.getName().equalsIgnoreCase("builder")){
			if(sender instanceof Player) {
				getServer().getScheduler().cancelTask(task.getTaskId());
				if (sender.hasPermission("partyhat.builder")) {
					pi.setArmorContents(null);
					ItemStack helmet = new ItemStack(Material.GOLD_HELMET);
					ItemStack boots = new ItemStack(Material.GOLD_BOOTS);
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 18000, 1));
					pi.setHelmet(helmet);
					pi.setBoots(boots);
					sender.sendMessage("§a[§bPartyHat§a] §eYou are now using §6§nBuidling§r§e costume.");
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}

		if (cmd.getName().equalsIgnoreCase("lamp")) {
			getServer().getScheduler().cancelTasks(this);
			if (sender instanceof Player) {
				if (sender.hasPermission("partyhat.lamp")) {
					sender.sendMessage(success);
					pi.setArmorContents(null);
					task = getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;
						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.REDSTONE_LAMP_OFF, 1);
							}
							else {
								helm = new ItemStack(Material.REDSTONE_LAMP_ON, 1);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("rbhat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.rbhat")) {
					sender.sendMessage(success);
					pi.setArmorContents(null);
					sender.sendMessage("§a[§bPartyHat§a] §dEnjoy your new hat.");
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (state) {
								helm = new ItemStack(Material.WOOL, 1, (short)14);
							}
							else {
								helm = new ItemStack(Material.WOOL, 1, (short)11);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("grhat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.grhat")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.WOOL, 1, (short)14);
							}
							else {
								helm = new ItemStack(Material.WOOL, 1, (short)5);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("ores")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.ores")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						int currentHelm = 0;
						Material[] helmMaterials = { Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.COAL_ORE, 
								Material.EMERALD_ORE, Material.LAPIS_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.GLOWING_REDSTONE_ORE };

						public void run()
						{
							ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
							pi.setHelmet(helm);
							this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("gphat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.gphat")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.WOOL, 1, (short)10);
							}
							else {
								helm = new ItemStack(Material.WOOL, 1, (short)5);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("rohat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.rohat")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.WOOL, 1, (short)1);
							}
							else {
								helm = new ItemStack(Material.WOOL, 1, (short)14);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("ybhat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.ybhat")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;
						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.WOOL, 1, (short)15);
							}
							else {
								helm = new ItemStack(Material.GOLD_BLOCK, 1);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("bghat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.bghat")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.WOOL, 1, (short)11);
							}
							else {
								helm = new ItemStack(Material.WOOL, 1, (short)5);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("trippy")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.trippy")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						int currentHelm = 0;
						Material[] helmMaterials = { Material.EMERALD_BLOCK, Material.OBSIDIAN, Material.GLOWSTONE, 
								Material.WORKBENCH, Material.LAPIS_BLOCK };

						public void run()
						{
							ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
							pi.setHelmet(helm);
							this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("gbhat")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.gbhat")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.WOOL, 1, (short)15);
							}
							else {
								helm = new ItemStack(Material.WOOL, 1, (short)5);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("tnt")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.tnt")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						boolean state = false;

						public void run() {
							ItemStack helm;
							if (this.state) {
								helm = new ItemStack(Material.TNT, 1);
							}
							else {
								helm = new ItemStack(Material.MOB_SPAWNER, 1);
							}
							pi.setHelmet(helm);
							this.state = (!this.state);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("mega")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.mega")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						int currentHelm = 0;
						Material[] helmMaterials = { Material.STONE, Material.GRASS, Material.DIRT, Material.COBBLESTONE, Material.WOOD_DOUBLE_STEP, Material.WOOD_DOUBLE_STEP, Material.BEDROCK, Material.SAND, Material.GRAVEL, Material.GOLD_ORE, 
								Material.IRON_ORE, Material.COAL_ORE, Material.WOOD, Material.LEAVES, Material.SPONGE, Material.GLASS, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.DISPENSER, Material.SANDSTONE, Material.NOTE_BLOCK, 
								Material.PISTON_STICKY_BASE, Material.PISTON_BASE, Material.WOOL, Material.GOLD_BLOCK, Material.IRON_BLOCK, Material.DOUBLE_STEP, Material.BRICK, Material.TNT, Material.BOOKSHELF, 
								Material.MOSSY_COBBLESTONE, Material.OBSIDIAN, Material.MOB_SPAWNER, Material.CHEST, Material.DIAMOND_ORE, Material.WORKBENCH, Material.SOIL, Material.FURNACE, Material.BURNING_FURNACE, Material.REDSTONE_ORE, 
								Material.GLOWING_REDSTONE_ORE, Material.SNOW_BLOCK, Material.ICE, Material.SNOW_BLOCK, Material.CACTUS, Material.CLAY, Material.JUKEBOX, Material.FENCE, Material.PUMPKIN, Material.NETHERRACK, Material.SOUL_SAND, 
								Material.GLOWSTONE, Material.PORTAL, Material.JACK_O_LANTERN, Material.CAKE_BLOCK, Material.LOCKED_CHEST, Material.SMOOTH_BRICK, Material.MELON_BLOCK, 
								Material.MYCEL, Material.NETHER_BRICK, Material.NETHER_FENCE, Material.ENCHANTMENT_TABLE, Material.ENDER_PORTAL_FRAME, Material.DRAGON_EGG, Material.REDSTONE_LAMP_OFF, 
								Material.REDSTONE_LAMP_ON, Material.EMERALD_ORE, Material.ENDER_CHEST, Material.EMERALD_BLOCK, Material.COMMAND, Material.BEACON, Material.COBBLE_WALL };

						public void run()
						{
							ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
							pi.setHelmet(helm);
							this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
						}
					}
					, 5L, 10L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("ender")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.ender")) {
					sender.sendMessage(success);
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						int currentHelm = 0;
						Material[] helmMaterials = { Material.ENDER_PORTAL_FRAME, Material.ENDER_STONE, Material.ENDER_CHEST };

						public void run() {
							ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
							pi.setHelmet(helm);
							this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
							w.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 4);
							w.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 4);
							w.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 4);
							w.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 4);
							w.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 4);
							w.playEffect(player.getLocation(), Effect.SMOKE, 4);
							w.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
						}
					}
					, 10L, 20L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("skeleton")) {
			getServer().getScheduler().cancelTasks(this);
			if ((sender instanceof Player)) {
				if (sender.hasPermission("partyhat.skeleton")) {
					sender.sendMessage(success);
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 12000, 0));
					getServer().getScheduler().runTaskTimer(this, new Runnable() {
						public void run() {
							ItemStack helm = new ItemStack(Material.SKULL_ITEM, 1, (short)5);
							pi.setHelmet(helm);
							w.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
							w.playEffect(player.getLocation(), Effect.STEP_SOUND, 1);
							w.playSound(player.getLocation(), Sound.SKELETON_WALK, 4.0F, 1.0F);
							w.playSound(player.getLocation(), Sound.SKELETON_DEATH, 4.0F, 1.0F);
							w.playSound(player.getLocation(), Sound.SKELETON_IDLE, 4.0F, 1.0F);
							w.playSound(player.getLocation(), Sound.SKELETON_HURT, 4.0F, 1.0F);
							w.playEffect(player.getLocation(), Effect.SMOKE, 100);
						}
					}
					, 15L, 25L);
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		if (cmd.getName().equalsIgnoreCase("hats")){
			if((sender instanceof Player)){
				if(sender.hasPermission("partyhat.hats")) {
					sender.sendMessage("§e=====================§a[§bPartyHat§a]§e=====================");
					sender.sendMessage("§dAvailable hats§d:§a /lamp§d, §a/bghat§d," +
							" §a/ybhat§d, §a/gphat§d,§a/ores§d, §a/grhat§d, §a/rbhat§d," +
							" §a/trippy§d, §a/gbhat§d, §a/rohat§d, §a/tnt§d, §a/mega§d,§a" +
							" /ender§d, §a/skeleton");
				} else sender.sendMessage(noperm);
			} else sender.sendMessage(p);
		}
		return true;
	}
}
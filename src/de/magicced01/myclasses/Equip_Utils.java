package de.magicced01.myclasses;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Equip_Utils {

	@SuppressWarnings("deprecation")
	public static void respawnPlayer(Player p) {
		p.getInventory().clear();
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20);
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		p.setFireTicks(0);
		p.teleport(GameManager.getSpawn(GameManager.getTeam(p)));
		

	}

	@SuppressWarnings("deprecation")
	public static void scout(Player p) {
		respawnPlayer(p);
		p.setMaxHealth((14));
		int effectidscout = 48;
		int enchlevelarcher1 = 2;

		Enchantment ench1 = new EnchantmentWrapper(effectidscout);
		ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack leggins = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemStack arrows = new ItemStack(Material.ARROW, 128);
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta meta = item.getItemMeta();
		ItemStack sword = new ItemStack(Material.WOOD_SWORD);
		ItemStack stick = new ItemStack(Material.STICK);

		meta.setDisplayName("§4Bone Crusher");
		item.setItemMeta(meta);
		item.addEnchantment(ench1, enchlevelarcher1);
		item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
		p.getInventory().addItem(new ItemStack[] { sword });
		p.getInventory().addItem(new ItemStack[] { item });
		p.getInventory().addItem(new ItemStack[] { arrows });
		p.getInventory().addItem(new ItemStack[] { stick });

		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggins));
		p.getInventory().setBoots(new ItemStack(boots));
	}

	@SuppressWarnings("deprecation")
	public static void tank(Player p) {
		respawnPlayer(p);
		int unbreakingid = 34;
		int unbreakinglevel = 3;

		Enchantment unbreaking = new EnchantmentWrapper(unbreakingid);

		p.setMaxHealth(40);
		p.setHealth(40);
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta metahelmet = helmet.getItemMeta();
		metahelmet.setDisplayName("§6§lHelm der Stärke");
		helmet.setItemMeta(metahelmet);

		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta metachestplate = chestplate.getItemMeta();
		metachestplate.setDisplayName("§6§lStabiler Dornenharnisch");
		chestplate.setItemMeta(metachestplate);

		ItemStack leggins = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta metaleggins = leggins.getItemMeta();
		metaleggins.setDisplayName("§6§lRobuste Hose");
		leggins.setItemMeta(metaleggins);

		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta metaboots = boots.getItemMeta();
		metaboots.setDisplayName("§6§l Massive Stiefel");
		boots.setItemMeta(metaboots);

		ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta metaaxe = axe.getItemMeta();
		metaaxe.setDisplayName("§6§lUrziel der Brecher");
		axe.setItemMeta(metaaxe);

		ItemStack shield = new ItemStack(Material.SHIELD);
		ItemMeta metashield = shield.getItemMeta();
		shield.setDurability((short) 10);
		metashield.setDisplayName("Mithrilschild");

		ItemStack goldpick = new ItemStack(Material.GOLD_PICKAXE);
		ItemMeta metapick = goldpick.getItemMeta();
		metapick.setDisplayName("§6§lCHARGE!");
		goldpick.setItemMeta(metapick);

		ItemStack angel = new ItemStack(Material.FISHING_ROD);
		ItemMeta metaangel = angel.getItemMeta();
		metaangel.setDisplayName("§6§lHiergeblieben!");

		axe.addEnchantment(unbreaking, unbreakinglevel);

		p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setChestplate(new ItemStack(chestplate));
		p.getInventory().setLeggings(new ItemStack(leggins));
		p.getInventory().setBoots(new ItemStack(boots));
		p.getInventory().addItem(new ItemStack[] { axe });
		p.getInventory().setItemInOffHand(shield);
		p.getInventory().addItem(new ItemStack[] { goldpick });

	}

	public void pyro(Player p) {
		respawnPlayer(p);
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta metabow = bow.getItemMeta();
		metabow.setDisplayName("§6§lUrziel der Brecher");
		bow.setItemMeta(metabow);

	}

}

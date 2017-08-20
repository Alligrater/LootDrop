package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

public class LootDrop extends JavaPlugin {
	
	static List<ItemStack> pool = new ArrayList<ItemStack>();
	static List<ItemStack> ironpool = new ArrayList<ItemStack>();
	static List<ItemStack> goldpool = new ArrayList<ItemStack>();
	
	@Override
	public void onEnable() {
		
		pool.add(new ItemStack(Material.LAPIS_BLOCK, 1));
		pool.add(new ItemStack(Material.IRON_INGOT, 2));
		pool.add(new ItemStack(Material.GOLD_INGOT, 2));
		pool.add(new ItemStack(Material.GOLD_NUGGET, 15));
		pool.add(new ItemStack(Material.REDSTONE_BLOCK, 2));
		pool.add(new ItemStack(Material.GOLDEN_APPLE, 1));
		pool.add(new ItemStack(Material.BLAZE_ROD, 4));
		pool.add(new ItemStack(Material.GOLDEN_CARROT, 3));
		pool.add(new ItemStack(Material.COAL_BLOCK, 1));
		pool.add(new ItemStack(Material.EXP_BOTTLE, 5));
		pool.add(new ItemStack(Material.TORCH, 16));
		
		ironpool.add(new ItemStack(Material.LAPIS_BLOCK, 2));
		ironpool.add(new ItemStack(Material.IRON_INGOT, 7));
		ironpool.add(new ItemStack(Material.GOLD_BLOCK, 1));
		ironpool.add(new ItemStack(Material.DIAMOND_ORE, 1));
		ironpool.add(new ItemStack(Material.EMERALD_ORE, 1));
		ironpool.add(new ItemStack(Material.CAKE_BLOCK, 1));
		ironpool.add(new ItemStack(Material.EXP_BOTTLE, 10));
		ironpool.add(new ItemStack(Material.GHAST_TEAR, 1));
		ironpool.add(new ItemStack(Material.OBSIDIAN, 8));
		ironpool.add(new ItemStack(Material.GOLDEN_APPLE, 4));
		ironpool.add(new ItemStack(Material.FERMENTED_SPIDER_EYE, 6));
		ironpool.add(new ItemStack(Material.SEA_LANTERN, 3));
		ironpool.add(new ItemStack(Material.GLOWSTONE, 5));
		ironpool.add(new ItemStack(Material.SPECKLED_MELON, 4));
		
		ItemStack pfish = new ItemStack(Material.RAW_FISH, 3);
		pfish.setDurability((short) 2);
		ironpool.add(pfish);
		
		goldpool.addAll(ironpool);
		goldpool.addAll(pool);
		goldpool.add(new ItemStack(Material.DIAMOND_ORE, 4));
		goldpool.add(new ItemStack(Material.EMERALD_BLOCK, 2));
		goldpool.add(new ItemStack(Material.DRAGONS_BREATH, 4));
		goldpool.add(new ItemStack(Material.END_ROD, 10));
		goldpool.add(new ItemStack(Material.RABBIT_FOOT, 8));
		goldpool.add(new ItemStack(Material.SPECTRAL_ARROW, 16));
		goldpool.add(new ItemStack(Material.SLIME_BLOCK, 2));
		goldpool.add(new ItemStack(Material.NETHER_STAR, 2));
		
		ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 1);
		gapple.setDurability((short) 1);
		goldpool.add(gapple);
		
		ItemStack wither = new ItemStack(Material.SKULL_ITEM, 1);
		wither.setDurability((short) 1);
		goldpool.add(wither);
		
		
		goldpool.add(new ItemStack(Material.RAW_FISH, 10));
		
		ItemStack gkl = new ItemStack(Material.GOLDEN_CARROT, 2);
		ItemMeta gklmeta = gkl.getItemMeta();
		gklmeta.setDisplayName("§e金 §a坷垃");
		List<String> gkllores = new ArrayList<String>();
		gkllores.add("Make Farming Great Again");
		gklmeta.setLore(gkllores);
		gklmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		gkl.setItemMeta(gklmeta);
		gkl.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10);
		
		ItemStack fbread = new ItemStack(Material.BREAD, 2);
		ItemMeta fbmeta = fbread.getItemMeta();
		fbmeta.setDisplayName("§6法棍");
		fbmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fbread.setItemMeta(fbmeta);
		fbread.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		
		ItemStack elderbook = new ItemStack(Material.BOOK, 4);
		ItemMeta eldermeta = elderbook.getItemMeta();
		eldermeta.setDisplayName("§6《他改变了中国》");
		eldermeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		List<String> lores = new ArrayList<String>();
		lores.add("苟...？");
		eldermeta.setLore(lores);
		elderbook.setItemMeta(eldermeta);
		elderbook.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
		
		
		pool.add(gkl);
		pool.add(fbread);
		pool.add(elderbook);
		
		
		getServer().getPluginManager().registerEvents(new BeaconSend(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}

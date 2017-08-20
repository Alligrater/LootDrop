package io.github.Alligrater;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class BeaconSend implements Listener{
	private final Set<Material> containerTypes = EnumSet.of(
		    Material.CHEST,
		    Material.BEACON,
		    Material.DROPPER,
		    Material.HOPPER,
		    Material.DISPENSER,
		    Material.TRAPPED_CHEST,
		    Material.BREWING_STAND,
		    Material.FURNACE,
		    Material.WORKBENCH,
		    Material.JUKEBOX,
		    Material.NOTE_BLOCK,
		    Material.BURNING_FURNACE,
		    Material.STONE_BUTTON,
		    Material.STONE_BUTTON,
		    Material.ANVIL,
		    Material.BED,
		    Material.BED_BLOCK,
		    Material.ENCHANTMENT_TABLE,
		    Material.DAYLIGHT_DETECTOR,
		    Material.DAYLIGHT_DETECTOR_INVERTED,
		    Material.FENCE_GATE,
		    Material.ACACIA_FENCE_GATE,
		    Material.DARK_OAK_FENCE_GATE,
		    Material.BIRCH_FENCE_GATE,
		    Material.JUNGLE_FENCE_GATE,
		    Material.SPRUCE_FENCE_GATE,
		    Material.WOOD_DOOR,
		    Material.ACACIA_DOOR,
		    Material.BIRCH_DOOR,
		    Material.DARK_OAK_DOOR,
		    Material.JUNGLE_DOOR,
		    Material.SPRUCE_DOOR,
		    Material.WOODEN_DOOR,
		    Material.IRON_DOOR,
		    Material.IRON_DOOR_BLOCK,
		    Material.TRAP_DOOR,
		    Material.IRON_TRAPDOOR
		);
	
	
	
	@EventHandler
	public void onBecaonSend(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() != null && ((containerTypes.contains(event.getClickedBlock().getType()) && event.getPlayer().isSneaking()) || !(containerTypes.contains(event.getClickedBlock().getType())))) {
			if(event.getItem().getType().equals(Material.FIREWORK) && event.getItem().getEnchantmentLevel(Enchantment.LUCK) == 10)  {
				Location loca = event.getClickedBlock().getLocation();
				
				event.getPlayer().sendMessage("§c已成功发射信号！请尽快远离降落地点！");
				
				World world = event.getClickedBlock().getWorld();

					double rng = Math.random();
					int index = (int)((rng*100 + 1) - 1);
					Location high = world.getHighestBlockAt(loca).getLocation();
					high = high.add(1, 1, 0);

					
					if(index < 95) {
						Entity fb = world.spawnFallingBlock(loca.add(0, 120, 0), Material.LOG, (byte) 0);
						fb.setCustomName("LootBox");
						fb.setCustomNameVisible(true);
					}
					else if(index >= 95 && index < 99){
						Entity fb = world.spawnFallingBlock(loca.add(0, 120, 0), Material.IRON_BLOCK, (byte) 0);
						fb.setCustomName("UpgradeBox");
						fb.setCustomNameVisible(true);
					}
					else {
						Entity fb = world.spawnFallingBlock(loca.add(0, 120, 0), Material.GOLD_BLOCK, (byte) 0);
						fb.setCustomName("GoldenBox");
						fb.setCustomNameVisible(true);
					}

					//attempt on particles
					
				
				
			}
			else if(event.getItem().getType().equals(Material.FIREWORK) && event.getItem().getEnchantmentLevel(Enchantment.LUCK) == 20)  {
				Location loca = event.getClickedBlock().getLocation();
				World world = event.getClickedBlock().getWorld();
				
				event.getPlayer().sendMessage("§c已成功发射信号！请尽快远离降落地点！");

					double rng = Math.random();
					int index = (int)((rng*100 + 1) - 1);
					Location high = world.getHighestBlockAt(loca).getLocation();
					high = high.add(1, 1, 0);

					
					if(index < 97) {
						Entity fb = world.spawnFallingBlock(loca.add(0, 120, 0), Material.IRON_BLOCK, (byte) 0);
						fb.setCustomName("UpgradeBox");
						fb.setCustomNameVisible(true);
					}
					else {
						Entity fb = world.spawnFallingBlock(loca.add(0, 120, 0), Material.GOLD_BLOCK, (byte) 0);
						fb.setCustomName("GoldenBox");
						fb.setCustomNameVisible(true);
					}
					
					
			}
		}
	}
	
	@EventHandler
	public void onBecaonArrive(EntityChangeBlockEvent event) {
		if(event.getEntity().getCustomName() != null) {
			if(event.getEntity().getCustomName().equals("LootBox")) {
				Location loca = event.getEntity().getLocation();

				World world = event.getEntity().getWorld();
				
				
				world.getBlockAt(loca).setType(Material.CHEST);
				if(world.getBlockAt(loca).getState() instanceof Chest) {
					Chest chest = (Chest) world.getBlockAt(loca).getState();
					
					double rng = Math.random();
					int index = (int)(rng*3 + 1);
					
					List<ItemStack> items = getRandomDrop(index, LootDrop.pool);
					for(ItemStack item : items) {
						chest.getBlockInventory().addItem(item);
					}
				}
				event.setCancelled(true);
				world.getBlockAt(loca).setType(Material.AIR);
				
				for(LivingEntity e: world.getLivingEntities()) {
					if(e.getLocation().distance(loca) <= 1) {
						e.damage(20);
						e.setLastDamageCause(new EntityDamageEvent(e, DamageCause.FALLING_BLOCK, 20));
						
					}
					else if(e.getLocation().distance(loca) <= 3 && e.getLocation().distance(loca) > 1){
						e.damage(10);
						e.setLastDamageCause(new EntityDamageEvent(e, DamageCause.FALLING_BLOCK, 10));
					}
					
				}
				
				world.spawnParticle(Particle.BLOCK_CRACK, loca.add(0, 1, 0), 100, 0.2,0.3,0.2,2,new MaterialData(Material.LOG));
				world.spawnParticle(Particle.BLOCK_CRACK, loca, 100, 0.1,0.3,0.1,1,new MaterialData(Material.LOG));
				world.playSound(loca, Sound.BLOCK_GRASS_BREAK, (float)1.0, (float)1.0);
				world.playSound(loca, Sound.BLOCK_WOOD_BREAK, (float)1.0, (float)1.0);
				
			}
			else if(event.getEntity().getCustomName().equals("UpgradeBox")) {
				Location loca = event.getEntity().getLocation();

				World world = event.getEntity().getWorld();
				
				
				world.getBlockAt(loca).setType(Material.CHEST);
				if(world.getBlockAt(loca).getState() instanceof Chest) {
					Chest chest = (Chest) world.getBlockAt(loca).getState();
					
					double rng = Math.random();
					int index = (int)(rng*4 + 2);
					
					List<ItemStack> items = getRandomDrop(index, LootDrop.ironpool);
					for(ItemStack item : items) {
						chest.getBlockInventory().addItem(item);
					}
				}
				event.setCancelled(true);
				world.getBlockAt(loca).setType(Material.AIR);
				
				for(LivingEntity e: world.getLivingEntities()) {
					if(e.getLocation().distance(loca) <= 1) {
						e.damage(20);
						e.setLastDamageCause(new EntityDamageEvent(e, DamageCause.FALLING_BLOCK, 20));
						
					}
					else if(e.getLocation().distance(loca) <= 3 && e.getLocation().distance(loca) > 1){
						e.damage(10);
						e.setLastDamageCause(new EntityDamageEvent(e, DamageCause.FALLING_BLOCK, 10));
					}
					
				}
				
				world.spawnParticle(Particle.BLOCK_CRACK, loca.add(0, 1, 0), 100, 0.2,0.3,0.2,2,new MaterialData(Material.IRON_BLOCK));
				world.spawnParticle(Particle.BLOCK_CRACK, loca, 100, 0.1,0.3,0.1,1,new MaterialData(Material.IRON_BLOCK));
				world.playSound(loca, Sound.BLOCK_GRASS_BREAK, (float)1.0, (float)1.0);
				world.playSound(loca, Sound.BLOCK_ANVIL_LAND, (float)1.0, (float)1.0);
			}
			else if(event.getEntity().getCustomName().equals("GoldenBox")){
				Location loca = event.getEntity().getLocation();

				World world = event.getEntity().getWorld();
				
				
				world.getBlockAt(loca).setType(Material.CHEST);
				if(world.getBlockAt(loca).getState() instanceof Chest) {
					Chest chest = (Chest) world.getBlockAt(loca).getState();
					double rng = Math.random();
					int index = (int)(rng*4 + 4);
					
					List<ItemStack> items = getRandomDrop(index, LootDrop.goldpool);
					for(ItemStack item : items) {
						chest.getBlockInventory().addItem(item);
					}
				}
				event.setCancelled(true);
				world.getBlockAt(loca).setType(Material.AIR);
				
				
				for(LivingEntity e: world.getLivingEntities()) {
					if(e.getLocation().distance(loca) <= 1) {
						e.damage(20);
						e.setLastDamageCause(new EntityDamageEvent(e, DamageCause.FALLING_BLOCK, 20));
						
					}
					else if(e.getLocation().distance(loca) <= 3 && e.getLocation().distance(loca) > 1){
						e.damage(10);
						e.setLastDamageCause(new EntityDamageEvent(e, DamageCause.FALLING_BLOCK, 10));
					}
					
				}
				
				world.spawnParticle(Particle.BLOCK_CRACK, loca.add(0, 1, 0), 100, 0.2,0.3,0.2,2,new MaterialData(Material.IRON_BLOCK));
				world.spawnParticle(Particle.BLOCK_CRACK, loca, 100, 0.1,0.3,0.1,1,new MaterialData(Material.IRON_BLOCK));
				world.playSound(loca, Sound.BLOCK_GRASS_BREAK, (float)1.0, (float)1.0);
				world.playSound(loca, Sound.ENTITY_PLAYER_LEVELUP, (float)1.0, (float)1.0);
			}
		}
	}
	
	public List<ItemStack> getRandomDrop(int max, List<ItemStack> stack){
		List<ItemStack> items = new ArrayList<ItemStack>();
		for(int i = 0; i < max; i++) {
			double rng = Math.random();
			int index = (int)((rng*stack.size() + 1) - 1);
			items.add(stack.get(index));
		}
		
		return items;
	}
}

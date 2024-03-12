package com.github.jenbroek.fullnight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.glfw.GLFW;

public class ClientInitializer implements ClientModInitializer {

	public static boolean ON = false;

	@Override
	public void onInitializeClient() {
		KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
			new KeyBinding("key.fullnight.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "category.fullnight")
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (keyBinding.wasPressed() && client.player != null) {
				if (ON) {
					client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
					ON = false;
				} else {
					client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false));
					ON = true;
				}
			}
		});
	}

}

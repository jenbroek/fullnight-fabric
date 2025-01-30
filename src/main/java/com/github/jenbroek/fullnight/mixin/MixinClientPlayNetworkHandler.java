package com.github.jenbroek.fullnight.mixin;

import com.github.jenbroek.fullnight.ClientFullnight;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
abstract class MixinClientPlayNetworkHandler extends ClientCommonNetworkHandler {

	protected MixinClientPlayNetworkHandler(MinecraftClient minecraftClient, ClientConnection clientConnection, ClientConnectionState clientConnectionState) {
		super(minecraftClient, clientConnection, clientConnectionState);
	}

	@Inject(method = "onGameJoin", at = @At("TAIL"))
	public void onGameJoin(CallbackInfo ci) {
		if (ClientFullnight.ON) {
			super.client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false));
		}
	}

	@Inject(method = "onPlayerRespawn", at = @At("TAIL"))
	public void onPlayerRespawn(CallbackInfo ci) {
		if (ClientFullnight.ON) {
			super.client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false));
		}
	}

}

package info.saebyn.minecraft.fabric.firstmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.MutableText
import net.minecraft.text.TextContent
import org.slf4j.LoggerFactory

object SaebynsFirstMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("saebyns-first-mod")

	const val MOD_ID = "saebyns_first_mod"


	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")

		ServerPlayConnectionEvents.JOIN.register(ServerPlayConnectionEvents.Join { handler, _, _ ->
			val player: ServerPlayerEntity = handler.player
			val text = MutableText.of(TextContent.EMPTY)
			text.append("Hello ").append(player.name).append(" from Saebyn's first mod!")
			player.sendMessage(text, false)
		});
	}
}
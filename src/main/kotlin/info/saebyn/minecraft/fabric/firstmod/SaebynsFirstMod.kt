package info.saebyn.minecraft.fabric.firstmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.item.ItemGroups
import net.minecraft.particle.DefaultParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.server.command.CommandManager
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.MutableText
import net.minecraft.text.TextContent
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object SaebynsFirstMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("saebyns-first-mod")

    const val MOD_ID = "saebyns_first_mod"

    val SPARKLE: DefaultParticleType = FabricParticleTypes.simple(true)

    val SPARKLEWAND_ITEM = SparkleWandItem(FabricItemSettings().maxCount(1))

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")

        Registry.register(Registries.ITEM, Identifier(MOD_ID, "sparklewand"), SPARKLEWAND_ITEM)

        Registry.register(Registries.PARTICLE_TYPE, Identifier(MOD_ID, "sparkles"), SPARKLE)

        ServerPlayConnectionEvents.JOIN.register(ServerPlayConnectionEvents.Join { handler, _, _ ->
            val player: ServerPlayerEntity = handler.player
            val text = MutableText.of(TextContent.EMPTY)
            text.append("Hello ").append(player.name).append(" from Saebyn's first mod!")
            player.sendMessage(text, false)
        })

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
            dispatcher.register(
                CommandManager.literal("sparkles")
                    .executes { context ->
                        val player = context.source.player ?: return@executes 0

                        spawnParticlesForPlayer(player)

                        1
                    }
            )
        })

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
            .register(ModifyEntries { content: FabricItemGroupEntries ->
                content.add(
                    SPARKLEWAND_ITEM
                )
            })
    }

    private fun spawnParticlesForPlayer(player: ServerPlayerEntity) {
        val text = MutableText.of(TextContent.EMPTY)

        text.append("Have some ✨✨✨ ").append(player.name).append("!")
        player.sendMessage(text, false)
    }
}

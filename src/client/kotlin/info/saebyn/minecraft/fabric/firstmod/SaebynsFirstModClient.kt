package info.saebyn.minecraft.fabric.firstmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry


class SaebynsFirstModClient : ClientModInitializer {

    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        // Register the sparkle particle texture
        /*
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register { atlasTexture, registry ->
            registry.register(
                Identifier("modid", "particle/sparkles")
            )
        }*/


        // Register the sparkle particle factory
        ParticleFactoryRegistry.getInstance().register(
            SaebynsFirstMod.SPARKLE,
            SparkleParticle::Factory
        )

    }
}
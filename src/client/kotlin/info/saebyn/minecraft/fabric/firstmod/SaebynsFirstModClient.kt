package info.saebyn.minecraft.fabric.firstmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry


class SaebynsFirstModClient : ClientModInitializer {

    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        // Register the sparkle particle factory
        ParticleFactoryRegistry.getInstance().register(
            SaebynsFirstMod.SPARKLE,
            SparkleParticle::Factory
        )

        // register ClientChunkEvents.CHUNK_LOAD
        ClientChunkEvents.CHUNK_LOAD.register { _, worldChunk ->
            // Spawn sparkle particle at 0,0,0
            worldChunk.world.addParticle(
                SaebynsFirstMod.SPARKLE,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0
            )
        }

    }


}

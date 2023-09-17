package info.saebyn.minecraft.fabric.firstmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry


class SaebynsFirstModClient : ClientModInitializer {

    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        // Register the sparkle particle factory
        ParticleFactoryRegistry.getInstance().register(
            SaebynsFirstMod.SPARKLE,
            SparkleParticle::Factory
        )


    }


}

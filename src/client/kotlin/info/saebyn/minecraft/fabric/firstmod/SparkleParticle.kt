package info.saebyn.minecraft.fabric.firstmod

import net.minecraft.client.particle.FlameParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType

class SparkleParticle {
    companion object
    class Factory(spriteProvider: SpriteProvider) : FlameParticle.Factory(spriteProvider) {
        override fun createParticle(
            parameters: DefaultParticleType,
            world: ClientWorld?,
            x: Double,
            y: Double,
            z: Double,
            velocityX: Double,
            velocityY: Double,
            velocityZ: Double
        ): Particle? {
            println("SparkleParticleFactory.createParticle")
            return super.createParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ)
        }
    }
}

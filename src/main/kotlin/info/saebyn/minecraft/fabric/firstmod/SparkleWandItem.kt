package info.saebyn.minecraft.fabric.firstmod

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.slf4j.LoggerFactory

class SparkleWandItem(settings: Settings?) : Item(settings) {
    private val logger = LoggerFactory.getLogger("saebyns_first_mod")

    private val particleVelocity = 0.5
    private val baseNumberOfParticles = 5
    private val maxNumberOfParticles = 13
    private val spreadRandomnessFactor = 0.02
    private val strechRandomnessFactor = 0.2
    private val wandVerticalOffset = 1.0

    // handle item use on the client
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) {
            // spawn particle in the direction where the player is looking
            val velocity = user.rotationVector.multiply(particleVelocity)

            // spawn the particle at the location of the tip of the wand
            // get the location of the players hand
            val handPos = user.pos.add(user.getHandPosOffset(this))

            // spawn a random number of particles
            val numParticles = (Math.random() * (maxNumberOfParticles - baseNumberOfParticles + 1)).toInt() + 3

            for (i in 0 until numParticles) {
                val randomVelocity = velocity.add(
                    Math.random() * spreadRandomnessFactor - (spreadRandomnessFactor / 2.0),
                    Math.random() * spreadRandomnessFactor - (spreadRandomnessFactor / 2.0),
                    Math.random() * spreadRandomnessFactor - (spreadRandomnessFactor / 2.0)
                )
                // spread out the particles a bit in the direction of user.rotationVector
                randomVelocity.add(user.rotationVector.multiply(Math.random() * strechRandomnessFactor))

                world.addParticle(
                    SaebynsFirstMod.SPARKLE,
                    handPos.x,
                    handPos.y + wandVerticalOffset,
                    handPos.z,
                    randomVelocity.x,
                    randomVelocity.y,
                    randomVelocity.z
                )
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand))
    }
}
package info.saebyn.minecraft.fabric.firstmod

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.hit.HitResult
import net.minecraft.world.World
import org.slf4j.LoggerFactory

class SparkleWandItem(settings: Settings?) : Item(settings) {
    private val logger = LoggerFactory.getLogger("saebyns_first_mod")

    private val range = 60.0
    private val particleVelocity = 1.5
    private val baseNumberOfParticles = 5
    private val maxNumberOfParticles = 13
    private val spreadRandomnessFactor = 0.02
    private val spreadRandomnessFactorMiss = 0.2
    private val strechRandomnessFactor = 0.2
    private val wandVerticalOffset = 1.0

    // handle item use on the client
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) {
            // get the location of the players hand
            val handPos = user.pos.add(user.getHandPosOffset(this)).add(0.0, wandVerticalOffset, 0.0)

            // find the ray from the player's eye to the block they are looking at
            val ray = user.raycast(range, 0.0f, true)
            val direction = if (ray.type == HitResult.Type.MISS) {
                user.rotationVector.subtract(handPos.normalize().multiply(0.5)).normalize()

                user.pos.add(user.rotationVector.multiply(range)).subtract(handPos).normalize()
            } else {
                ray.pos.subtract(handPos).normalize()
            }

            val spread = if (ray.type == HitResult.Type.MISS) {
                spreadRandomnessFactorMiss
            } else {
                spreadRandomnessFactor
            }

            // spawn particle in the direction where the player is looking
            val velocity = direction.multiply(particleVelocity).add(user.velocity)

            // spawn the particle at the location of the tip of the wand

            // spawn a random number of particles
            val numParticles = (Math.random() * (maxNumberOfParticles - baseNumberOfParticles + 1)).toInt() + 3

            for (i in 0 until numParticles) {
                val randomVelocity = velocity.add(
                    Math.random() * spread - (spread / 2.0),
                    Math.random() * spread - (spread / 2.0),
                    Math.random() * spread - (spread / 2.0)
                )
                // spread out the particles a bit in the direction of user.rotationVector
                randomVelocity.add(direction.multiply(Math.random() * strechRandomnessFactor)).subtract(handPos)

                world.addParticle(
                    SaebynsFirstMod.SPARKLE,
                    handPos.x,
                    handPos.y,
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
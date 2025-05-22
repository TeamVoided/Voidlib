package org.teamvoided.voidcore

import com.mojang.authlib.GameProfile
import net.minecraft.util.Identifier
import java.net.URI
import java.util.*

object CapeManager {
    val mainURl = "https://teamvoided-endpoints.fly.dev/v1"

    private val capeOwners = mutableMapOf<UUID, Identifier>()
    private val noCape = mutableListOf<UUID>()

    fun GameProfile.getCustomCape(): Identifier? {
        val id = this.id
        val cape = capeOwners[id]
        if (cape != null) return cape

        if (noCape.contains(id)) return null

        return fetchCape(id)
    }

    private fun fetchCape(id: UUID): Identifier? {
        val isMember = isMember(id)
        if (isMember) {

//            val getCape = getCapeIdentifier(id)


        } else noCape.add(id)
        return null
    }

//    private fun getCapeIdentifier(id: UUID): Identifier {
//
//
//    }


    fun isMember(uuid: UUID): Boolean {
        val text = URI.create("$mainURl/is_member/$uuid").toURL().readText()
//        println(text)
        return text == "true"
    }
}
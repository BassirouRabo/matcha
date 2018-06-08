package repository

import data.Bloque
import data.Bloques
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.joda.time.DateTime

object BloqueRepository {
    fun getAll(): List<Bloque> {
        return Bloque.all().toList()
    }

    fun getAll(op: Op<Boolean>): List<Bloque> {
        return Bloque.find { op }.toList()
    }

    fun getBloques(username1: String): List<Bloque> {
        return getAll(Bloques.username1.eq(username1))
    }

    fun getBloqueeds(username1: String): List<Bloque> {
        return getAll(Bloques.username2.eq(username1))
    }

    fun get(op: Op<Boolean>): Bloque? {
        val find = Bloque.find { op }
        if (find.empty()) return null
        return find.single()
    }

    fun get(id: Int): Bloque? {
        return Bloque.findById(id)
    }

    fun get(username1: String, username2: String): Bloque? {
        return get(Bloques.username1.eq(username1) and Bloques.username2.eq(username2))
    }

    fun bloque(username1: String, username2: String): Bloque? {
        val bloque: Bloque? = get(username1, username2)
        if (bloque == null) {
            val id = Bloques.insertAndGetId {
                it[Bloques.username1] = username1
                it[Bloques.username2] = username2
                it[Bloques.date] = DateTime.now()
            }.value
            return (get(id))
        }
        bloque.date = DateTime.now()
        return bloque
    }

    fun unbloque(username1: String, username2: String) {
        BloqueRepository.get(username1, username2)?.delete()
    }
}
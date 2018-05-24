package repository

import data.Visit
import data.Visits
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.joda.time.DateTime

object VisitRepository {
    fun getAll() : List<Visit> {
        return Visit.all().toList()
    }

    fun getAll(op: Op<Boolean>) : List<Visit> {
        return Visit.find { op }.toList()
    }

    fun getVisits(username1: String) : List<Visit> {
        return getAll(Visits.username1.eq(username1))
    }

    fun getVisiteds(username1: String) : List<Visit> {
        return getAll(Visits.username2.eq(username1))
    }

    fun get(op: Op<Boolean>) : Visit? {
        val find = Visit.find { op }
        if (find.empty()) return null
        return find.single()
    }

    fun get(id: Int) : Visit? {
        return Visit.findById(id)
    }

    fun get(username1: String, username2: String): Visit? {
        return get(Visits.username1.eq(username1) and Visits.username2.eq(username2))
    }

    fun add(username1: String, username2: String): Visit? {
        val visit: Visit? = get(username1, username2)
        if (visit == null) {
            val id = Visits.insertAndGetId {
                it[Visits.username1] = username1
                it[Visits.username2] = username2
                it[Visits.date] = DateTime.now()
            }.value
            return (get(id))
        }
        visit.date = DateTime.now()
        return visit
    }


}
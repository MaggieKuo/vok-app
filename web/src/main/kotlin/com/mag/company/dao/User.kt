package com.mag.company.dao

import com.github.vokorm.KEntity
import com.github.vokorm.findOneBy
import com.gitlab.mvysny.jdbiorm.Dao
import com.gitlab.mvysny.jdbiorm.Table
import eu.vaadinonkotlin.security.simple.HasPassword

@Table("users")
data class User(override var id: Long? = null,
                var username: String = "",
                override var hashedPassword: String = "",
                var roles: String = "",
                var emp_id: String? = null
) : KEntity<Long>, HasPassword {
    val employee: Employee?
        get() = emp_id?.let {
            Employee.findFirstBy(
                "emp_id = :emp_id",
                { it.bind("emp_id", emp_id) }
            )
        }

    companion object : Dao<User, Long>(User::class.java) {
        fun findByUsername(username: String): User? = findOneBy { User::username eq username }
    }
}


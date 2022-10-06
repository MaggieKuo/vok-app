package com.mag.company.dao

import com.github.vokorm.KEntity
import com.gitlab.mvysny.jdbiorm.Dao
import java.io.Serializable
import javax.validation.constraints.NotNull

data class Department(
    override var id: Long? = null,

    @field:NotNull
    var dep_name: String? = null,

    @field:NotNull
    var leader_id: String? = null
): KEntity<Long>, Serializable{
    companion object: Dao<Department, Long>(Department::class.java)
}

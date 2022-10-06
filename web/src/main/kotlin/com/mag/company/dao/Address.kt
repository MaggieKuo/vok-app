package com.mag.company.dao

import com.github.vokorm.KEntity
import com.gitlab.mvysny.jdbiorm.Dao
import com.google.gson.annotations.SerializedName
import org.jdbi.v3.core.statement.UnableToExecuteStatementException

data class Address(
    override var id: Long? = null,
    @SerializedName(value = "City") var city: String? = null,
    @SerializedName(value = "Zip5") var zip: String? = null,
    @SerializedName(value = "Area") var area: String? = null,
    @SerializedName(value = "Road") var road: String? = null,
): KEntity<Long>{
    override fun save(validate: Boolean) {
        try {
            zip = zip?.substring(0, 3)
            super.save(validate)
        } catch (e: UnableToExecuteStatementException) {
//            TODO("Not yet implemented")
        }
    }

    companion object: Dao<Address, Long>(Address::class.java)
}

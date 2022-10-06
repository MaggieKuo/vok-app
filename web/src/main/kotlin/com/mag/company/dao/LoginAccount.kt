package com.mag.company.dao

import com.github.vokorm.KEntity
import com.gitlab.mvysny.jdbiorm.Dao

data class LoginAccount(
    override var id: String? = null,
    var password: String? = null

): KEntity<String>{
    companion object: Dao<LoginAccount, String>(LoginAccount::class.java)
}

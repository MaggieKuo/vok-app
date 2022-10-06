package com.mag.company.services

import com.github.vokorm.KEntity
import com.gitlab.mvysny.jdbiorm.Dao
import com.mag.company.dao.Employee
import com.vaadin.flow.component.UI
import eu.vaadinonkotlin.security.simple.HasPassword
import eu.vaadinonkotlin.vaadin.Session
import java.io.Serializable

class LoginServices : Serializable {
    var currentUser: User2? = null
        private set
    val isLoggedIn get() = currentUser != null

    fun login(username: String, password: String): Boolean {
        //verify username and password
        val user = User2.findByUsername(username) ?: return false
        if (!user.passwordMatches(password)) return false
        currentUser = user
        UI.getCurrent().page.reload()
        return true
    }

    fun logout() {
        Session.current.close()
        UI.getCurrent().navigate("")
        UI.getCurrent().page.reload()
    }

    fun getCurrentUserRoles(): Set<String>{
        val roles: String = currentUser?.roles ?: return setOf()
        return roles.split(",").toSet()
    }

    fun isUserInRole(role: String): Boolean = getCurrentUserRoles().contains(role)
    fun isAdmin(): Boolean = isUserInRole("administrator")
}

val Session.loginService: LoginServices
    get() = getOrPut { LoginServices() }

data class User2(
    override var id: Long? = null,
    var username: String = "",
    override var hashedPassword: String = "",
    var roles: String = "",
    var emp_id: String? = null

) : KEntity<Long>, HasPassword {
    val employee: Employee?
        get() = emp_id?.let {
            Employee.findFirstBy (
                "emp_id = :emp_id",
                { it.bind("emp_id", emp_id) }
        ) }

    companion object : Dao<User2, Long>(User2::class.java) {
        fun findByUsername(username: String): User2? = findOneBy("username = :username") { query ->
            query.bind("username", username)
        }
    }
}
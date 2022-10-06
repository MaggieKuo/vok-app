package com.mag.company.services

import com.mag.WelcomeView
import com.mag.company.dao.User
import com.vaadin.flow.component.UI
import com.vaadin.flow.server.VaadinRequest
import com.vaadin.flow.server.VaadinService
import eu.vaadinonkotlin.security.BasicUserPrincipal
import eu.vaadinonkotlin.vaadin.Session
import java.io.Serializable
import java.security.Principal
import javax.security.auth.login.FailedLoginException

class LoginManager: Serializable {
    var user: User? = null
        private set

    val isLoggedIn: Boolean get() = user != null

    fun getPrincipal(): Principal? {
        val user = user
        return if (user == null) null else BasicUserPrincipal(user.username)
    }

    fun login(username: String, password: String) {
        val user: User = User.findByUsername(username) ?: throw FailedLoginException("Invalid username or password")
        if (!user.passwordMatches(password)) {
            throw FailedLoginException("Invalid username or password")
        }
        login(user)
    }

    private fun login(user: User) {
        this.user = user

        VaadinService.reinitializeSession(VaadinRequest.getCurrent())
        UI.getCurrent().navigate(WelcomeView::class.java)
    }

    fun logout() {
        Session.current.close()
        UI.getCurrent().page.reload()
    }

    fun getCurrentUserRoles(): Set<String> {
        val roles: String = user?.roles ?: return setOf()
        return roles.split(",").toSet()
    }

    fun isUserInRole(role: String): Boolean = getCurrentUserRoles().contains(role)

    fun isAdmin(): Boolean = isUserInRole("admin")
}

val Session.loginManager: LoginManager get() = getOrPut { LoginManager() }
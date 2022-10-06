package com.mag.company.view

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.setErrorMessage
import com.mag.company.services.loginManager
import com.vaadin.flow.component.login.LoginForm
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.router.Route
import eu.vaadinonkotlin.vaadin.Session
import org.slf4j.LoggerFactory
import javax.security.auth.login.LoginException

@Route("login")
class LoginView() : KComposite() {
    private lateinit var loginForm: LoginForm
    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = false; content { center() }

            val loginI18n: LoginI18n = loginI18n {
                with(form) {
                    title = "登入"
                    username = "帳號"
                    password = "密碼"
                    submit = "登入"
                    forgotPassword = "忘記密碼"
                }
                header.title = "登入"
                additionalInformation = "Log in as guest/guest or admin/admin"
            }
            loginForm = loginForm(loginI18n)
        }
    }

    init {
        loginForm.addLoginListener { e ->
            try {
                Session.loginManager.login(e.username, e.password)
            } catch (e: LoginException) {
                log.warn("Login failed", e)
                loginForm.setErrorMessage("Login failed", e.message)
            } catch (e: Exception) {
                log.error("Internal error", e)
                loginForm.setErrorMessage("Internal error", e.message)
            }
        }
    }

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(LoginView::class.java)
    }

}
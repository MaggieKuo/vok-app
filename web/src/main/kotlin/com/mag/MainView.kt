package com.mag

import com.github.javafaker.Faker
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.h1
import com.github.mvysny.karibudsl.v10.h2
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.mag.company.dao.Department
import com.mag.company.dao.Employee
import com.mag.company.dao.User
import com.mag.school.dao.Student
import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.data.converter.LocalDateToDateConverter
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route
import java.util.*

@Route("", layout = MainLayout::class)
class MainView : KComposite(), BeforeEnterObserver {
    private val root = ui {
        verticalLayout {
            h1("JCConf 2002")
            h2("使用 Kotlin 建構企業網路應用程式")

        }
    }

    companion object {
        var isConvertFileFinished = false
    }

    override fun onAttach(attachEvent: AttachEvent?) {
        if (!isConvertFileFinished) {
            insertStudent()
            insertDepartment()
//            insertAddress()
            insertEmployee()
            insertUser()
            isConvertFileFinished = true
        }
        super.onAttach(attachEvent)
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {

    }
}

fun insertStudent() {
    val faker = Faker.instance(Locale.TAIWAN)
    (1..40).forEach {
        Student(name = faker.name().fullName(),
                birthday = LocalDateToDateConverter().convertToPresentation(faker.date().birthday(20, 21), null
                ),
                gender = Gender.values().random(),
                height = faker.number().randomDouble(1, 155, 190),
                weight = faker.number().randomDouble(1, 45, 80)

        ).save()
    }
}

fun insertDepartment() {
    // department
    val dep = arrayOf("資訊部", "行政部", "財務部", "研發部", "人事部")
    try {
        dep.forEach {
            Department(dep_name = it, leader_id = ""
            ).save()
        }
    } catch (e: Exception) {
        TODO("Not yet implemented")
    }
}

/*
fun insertAddress() {
    try {
        File("/Users/maggiekuo/Downloads/address.json").readText().also {
            Gson().fromJson(it, Array<Address>::class.java).toList().forEach {
                it.save()
            }
        }
    } catch (e: Exception) {
        TODO("Not yet implemented")
    }
}
*/

fun insertEmployee() {
    val faker = Faker.instance(Locale.TAIWAN)
    try {
        (1..30).forEach {
            Employee(emp_name = faker.name().fullName(),
                     birthday = LocalDateToDateConverter().convertToPresentation(faker.date().birthday(25, 60), null
                     ),
                     private_email = faker.internet().safeEmailAddress(),
                     gender = Gender.values().random(),
                     dep_id = (1..5).random().toLong()
            ).save()
        }
    } catch (e: Exception) {
        TODO("Not yet implemented")
    }
}

fun insertUser() {
    val roles = listOf("admin", "user", "guest")
    try {
        roles.forEach { u ->
            User(username = u, roles = u).also {
                it.setPassword(u)
                it.emp_id = Employee.getById((1..30).random().toLong()).emp_id
            }.save(false)
        }
    } catch (e: Exception) {

    }
}

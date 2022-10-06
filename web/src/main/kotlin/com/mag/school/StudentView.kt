package com.mag.school

import com.github.mvysny.karibudsl.v10.*
import com.mag.MainLayout
import com.mag.school.dao.Student
import com.vaadin.flow.component.Text
import com.vaadin.flow.router.BeforeEvent
import com.vaadin.flow.router.HasUrlParameter
import com.vaadin.flow.router.Route

@Route("student", layout = MainLayout::class)
class StudentView: KComposite(), HasUrlParameter<Long> {
    private lateinit var id: Text
    private lateinit var name: Text
    private lateinit var gender: Text
    private lateinit var birthday: Text
    private lateinit var fstudentId: Text
    private val root = ui {
        verticalLayout {
            div {
                strong("序號 : "); this@StudentView.id = text("")
            }
            div {
                strong("姓名 : "); this@StudentView.name = text("")
            }
            div {
                strong("性別 : "); this@StudentView.gender = text("")
            }
            div {
                strong("生日 : "); this@StudentView.birthday = text("")
            }
            div {
                strong("學號 : "); this@StudentView.fstudentId = text("")
            }
        }
    }

    override fun setParameter(event: BeforeEvent?, studentId: Long?) {
        val student = Student.getById(studentId!!)
        id.text = student.id.toString()
        name.text = student.name
        gender.text = student.gender.toString()
        birthday.text = student.birthday.toString()
        fstudentId.text = student.student_id
    }
}
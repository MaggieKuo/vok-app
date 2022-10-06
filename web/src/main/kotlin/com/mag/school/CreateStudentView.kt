package com.mag.school

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.mag.Gender
import com.mag.MainLayout
import com.mag.school.dao.Student
import com.vaadin.flow.router.Route
import java.time.LocalDate

@Route("create-student", layout = MainLayout::class)
class CreateStudentView : KComposite() {
    private val binder = beanValidationBinder<Student>()
    private val root = ui {
        verticalLayout {
            h1("新增學生資料")
            textField("姓名") {
                focus()
                bind(binder).bind(Student::name)
            }
            datePicker("生日") {
                bind(binder).bind(Student::birthday)
            }
            comboBox<Gender>("性別") {
                setItems(*Gender.values())
                bind(binder).bind(Student::gender)
            }
            numberField("身高") {
                bind(binder).bind(Student::height)
                placeholder = "公分"
            }
            numberField("體重") {
                bind(binder).bind(Student::weight)
                placeholder = "公斤"
            }
            button("Save") {
                onLeftClick {
                    val student = Student()
                    if (binder.validate().isOk && binder.writeBeanIfValid(student)) {
                        with(student) {
                            save()
                            student_id = generateStudentId(id)
                            save()
                        }
                        navigateTo(StudentView::class, student.id)
                    }
                }
            }
        }
    }
    companion object{
        fun generateStudentId(id: Long?): String =
            String.format("%s%04d%04d", "JCConf", LocalDate.now().year, id)

    }
}


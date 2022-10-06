package com.mag.school

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.mag.MainLayout
import com.mag.school.dao.Student
import com.vaadin.flow.router.Route
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.impl.GridCrud


@Route("crudui-Student", layout = MainLayout::class)
class CRUDUIStudentView: KComposite() {
    private val root = ui{
        verticalLayout {
            setSizeFull()
            val crud = GridCrud(Student::class.java)
            with(crud){
                setSizeFull()
                with(grid){
                    setColumns("student_id", "name", "birthday", "gender", "email" ,"height", "weight")
                    isColumnReorderingAllowed = true
                }
                with(crudFormFactory){
                    setUseBeanValidation(true)
                    setVisibleProperties(
                        "student_id", "name", "birthday", "gender", "email" ,"height", "weight"
                    )
                    setVisibleProperties(
                        CrudOperation.ADD,
                        "name", "birthday", "gender", "email" ,"height", "weight"
                    )
                }
                setOperations(
                    { Student.findAll()},
                    {
                        it.save(true)
                        null
                    },
                    {
                        it.save(true)
                        null
                    },
                    { it.delete() }
                )
                setFindAllOperationVisible(false)
                grid.columns.forEach {
                    it.setAutoWidth(true)
                }
            }
            add(crud)

        }
    }

}
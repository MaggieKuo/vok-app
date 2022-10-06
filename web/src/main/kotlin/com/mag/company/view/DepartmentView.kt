package com.mag.company.view

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.h1
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.mag.MainLayout
import com.mag.company.dao.Department
import com.mag.company.dao.Employee
import com.vaadin.flow.router.Route
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.impl.GridCrud
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout

@Route("department", layout = MainLayout::class)
class DepartmentView: KComposite() {
    private val root = ui{
        verticalLayout {
            setSizeFull()
            h1("Department")
            val crud = GridCrud(Department::class.java, HorizontalSplitCrudLayout())
            with(crud){
                setSizeFull()
                grid.isColumnReorderingAllowed = true
                grid.setColumns("dep_name", "leader_id")
                with(crudFormFactory){
                    setUseBeanValidation(true)
                    setVisibleProperties("dep_name", "leader_id")
                    setVisibleProperties(CrudOperation.ADD, "dep_name", "leader_id")
                    setVisibleProperties(CrudOperation.UPDATE, "dep_name", "leader_id")
                    setFieldProvider("leader_id", ComboBoxProvider(Employee.getAllEmpId()))
                }
                setOperations(
                    { Department.findAll()},
                    { it.save(true)
                        null },
                    { it.save(true)
                        null },
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


package com.mag.company.view

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.mag.MainLayout
import com.mag.company.dao.Department
import com.mag.company.dao.Employee
import com.vaadin.flow.data.renderer.TextRenderer
import com.vaadin.flow.router.Route
import org.vaadin.crudui.crud.CrudOperation
import org.vaadin.crudui.crud.impl.GridCrud
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider

@Route("employee", layout = MainLayout::class)
class EmployeeView : KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull()
            val crud = GridCrud(Employee::class.java)
            with(crud) {
                with(grid) {
                    setColumns("emp_id", "emp_name", "gender", "birthday", "created", "department.dep_name")
                    isColumnReorderingAllowed = true
                }
                with(crudFormFactory) {
                    setUseBeanValidation(true)
                    setFieldProvider("department",
                                     ComboBoxProvider("DEPT",
                                                      Department.findAll(),
                                                      TextRenderer(Department::dep_name),
                                                      Department::dep_name
                                     )
                    )

                    setVisibleProperties(CrudOperation.READ,
                                         "id",
                                         "emp_id",
                                         "emp_name",
                                         "gender",
                                         "birthday",
                                         "private_email",
                                         "company_email",
                                         "address_id",
                                         "address",
                                         "address2",
                                         "department"
                    )
                    setVisibleProperties(CrudOperation.ADD, "emp_name", "gender", "birthday", "private_email", "dep_id")
                    setVisibleProperties(CrudOperation.UPDATE,
                                         "emp_id",
                                         "emp_name",
                                         "gender",
                                         "birthday",
                                         "private_email",
                                         "company_email",
                                         "department",
                                         "address"
                    )
                    setVisibleProperties(CrudOperation.DELETE, "emp_id", "emp_name")
                }
                setOperations(
                    { Employee.findAll() },
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
                grid.columns.forEach { it.setAutoWidth(true) }
            }
            add(crud)
        }
    }

}



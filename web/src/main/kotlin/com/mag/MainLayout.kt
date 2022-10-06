package com.mag

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.appLayout
import com.mag.company.view.AddressView
import com.mag.company.view.DepartmentView
import com.mag.company.view.EmployeeView
import com.mag.school.AllStudentsView
import com.mag.school.CRUDStudentView
import com.mag.school.CRUDUIStudentView
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.router.RouterLayout
import com.vaadin.flow.router.RouterLink


class MainLayout : KComposite(), RouterLayout {
    private val root = ui {
        appLayout {
            val img = Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo")
            img.height = "44px"
            addToNavbar(DrawerToggle(), img)
            val tabs = Tabs(
                Tab(VaadinIcon.HOME.create(), RouterLink("Home", MainView::class.java)),
                Tab("Student"),
                Tab(VaadinIcon.USERS.create(), RouterLink("Students", AllStudentsView::class.java)),
                Tab(VaadinIcon.USERS.create(), RouterLink("Students by CRUD", CRUDStudentView::class.java)),
                Tab(VaadinIcon.USERS.create(), RouterLink("Students by CRUDUI", CRUDUIStudentView::class.java)),
                Tab("Company"),
                Tab(VaadinIcon.LOCATION_ARROW.create(),RouterLink("Address", AddressView::class.java)),
                Tab(VaadinIcon.USERS.create(), RouterLink("Employee", EmployeeView::class.java)),
                Tab(VaadinIcon.GROUP.create(), RouterLink("Department", DepartmentView::class.java))
            )
            tabs.orientation = Tabs.Orientation.VERTICAL
            addToDrawer(tabs)
        }
    }
}


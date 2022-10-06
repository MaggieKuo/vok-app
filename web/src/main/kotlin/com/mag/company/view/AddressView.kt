package com.mag.company.view

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.addColumnFor
import com.github.mvysny.kaributools.refresh
import com.github.vokorm.dataloader.dataLoader
import com.mag.MainLayout
import com.mag.company.dao.Address
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.router.AfterNavigationEvent
import com.vaadin.flow.router.AfterNavigationObserver
import com.vaadin.flow.router.Route
import eu.vaadinonkotlin.vaadin.vokdb.setDataLoader

@Route("address", layout = MainLayout::class)
class AddressView: KComposite(), AfterNavigationObserver {
    private lateinit var grid: Grid<Address>
    private val root = ui {
        verticalLayout {
            setSizeFull()
            h1("Address")
            grid = grid(Address::class) {
                isExpand = true
                setDataLoader(Address.dataLoader)
                addColumnFor(Address::zip)
                addColumnFor(Address::city)
                addColumnFor(Address::area)
                addColumnFor(Address::road)
            }
        }
    }

    override fun afterNavigation(event: AfterNavigationEvent?) {
        grid.refresh()
    }
}

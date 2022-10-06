package com.mag.company.component

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.beanValidationBinder
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.mag.company.dao.Address
import com.vaadin.flow.router.BeforeEvent
import com.vaadin.flow.router.HasUrlParameter

class AddressComponent : KComposite(), HasUrlParameter<Long> {
    private val binder = beanValidationBinder<Address>()
    var address: Address? = null
        set(value) {
            field = value
            value?.let { binder.readBean(value) }
        }
    private val root = ui {
        horizontalLayout {
            isMargin = false

        }
    }

    override fun setParameter(event: BeforeEvent?, address_id: Long?) {
        address = address_id?.let { Address.findById(address_id) } ?: Address()
    }


}
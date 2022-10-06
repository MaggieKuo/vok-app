package com.mag.company.dao

import com.github.vokorm.KEntity
import com.github.vokorm.db
import com.gitlab.mvysny.jdbiorm.Dao
import com.mag.Gender
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past

data class Employee(
    override var id: Long? = null,
    var emp_id: String? = null,
    @field:Length(min = 2, max = 10, message = "姓名不分中英文字，最少2個字，最多10個字")
    var emp_name: String? = null,
    @field:NotNull
    @field:Past var birthday: LocalDate? = null,
    var created: LocalDate? = null,
    @field:Email var private_email: String? = null,
    @field:Email var company_email: String? = null,
    @field:NotNull var gender: Gender? = null,
    var address_id: Long? = null,
    var address2: String? = null,
    var dep_id: Long? = null,

) : KEntity<Long>{


    override fun save(validate: Boolean) {
        val isNew = (id == null)
        address_id = address?.id
        super.save(validate)
        if (isNew) {
            created = LocalDate.now()
            emp_id = generateId(id)
            company_email = emp_id + "@jcconf.com.tw"
            save(false)
        }
    }
    val address: Address?
        get() = address_id?.let { Address.findById(address_id!!) } ?: null

    val department: Department?
        get() = dep_id?.let { Department.findById(dep_id!!) }?:null

    /*    fun assignAddress() {
            if (address == null && address_id != null)
                address = Address.findById(address_id!!)
        }*/

    companion object : Dao<Employee, Long>(Employee::class.java) {
        fun getAllEmpId(): List<String> = db {
            with(handle) {
                createQuery("SELECT concat(emp_id,'|',emp_name) FROM EMPLOYEE")
                    .mapTo(String::class.java)
                    .list()
            }
        }

        fun getAddressById(id: Long): String =
            Address.findById(id).let {
                "${it?.zip} ${it?.city}${it?.area}${it?.road}"
            }

        fun generateId(id: Long?): String =
            String.format("%s%04d%03d", "JCC", LocalDate.now().year, id)

    }
}



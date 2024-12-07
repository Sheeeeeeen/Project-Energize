package ph.developer.projectenergize.model

import kotlin.jvm.JvmInline

@JvmInline
value class WorkMail(private val email: String) {
    init {
        require(value = email.isNotBlank())
        require(value = email.isNotEmpty())
        //add email match pattern
    }
}

@JvmInline
value class EmployeeId(private val employeeId: String) {
    init {
        require(value = employeeId.isNotBlank())
        require(value = employeeId.isNotEmpty())
    }
}

data class AddMemberDetails(
    val firstName: String,
    val lastName: String,
    val workMail: WorkMail,
    val employeeId: EmployeeId
)
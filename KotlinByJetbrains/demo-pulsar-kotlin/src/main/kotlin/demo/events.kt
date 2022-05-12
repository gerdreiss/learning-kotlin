package demo

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoanApplied(val loan: Loan) {
    companion object {
        fun fromJson(json: String): LoanApplied {
            return Json.decodeFromString(serializer(), json)
        }
    }
}

@Serializable
class LoanDeclined(val loan: Loan, val reason: String)

@Serializable
class LoanApproved(val loan: Loan)

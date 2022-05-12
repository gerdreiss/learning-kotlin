package demo

import kotlinx.serialization.Serializable

@Serializable
class LoanApplied(val loan: Loan)
@Serializable
class LoanDeclined(val loan: Loan, val reason: String)
@Serializable
class LoanApproved(val loan: Loan)

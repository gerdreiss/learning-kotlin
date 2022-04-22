package com.jscriptive.learningkotlin.declarations

typealias Employees = Set<Employee>

class Employee(var name: String, val id: Int) {

    override fun toString(): String {
        return "Employee(name=$name, id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (name != other.name) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode() + id
    }
}

fun main(args: Array<String>) {
    var number: Int
    number = 10
    number = 20 // can be reassigned, coz var

    val emp1 = Employee("Lynn Jones", number)
    emp1.name = "Lynn Smith"
    //emp1 = Employee("Tim Watson", 100) <-- doesn't compute, coz val

    val emp2 = Employee("John Smith", number)
    val emp3 = Employee("John Smith", number)
    val emp4: Any = emp3
    //if (emp4 is Employee) {
    val emp5 = emp4 as Employee
    //}

    val emps: Employees
    emps = setOf(emp1, emp2)

    println(emp1 == emp2) // false
    println(emp2 == emp3) // true
    println(emp2 === emp3) // false
    println(emp1.equals(emp2)) // false
    println(emp2.equals(emp3)) // true

    println(emp5)
    println(emps)

    val change = 4.22
    println("\$change = $$change")

    val p = "bla"
    val rawstring = """c:\system32\temp"""
    val rawstringmultiplelines = """
                |$p $p $p
                |$p $p bloop
        """.trimMargin()
    println(rawstring)
    println(rawstringmultiplelines)
}

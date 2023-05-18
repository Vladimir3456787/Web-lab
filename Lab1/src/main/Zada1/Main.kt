object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val myPhone = MobilePhone("3456")
        val contact1 = Contact("Настя", "89008004567")
        val contact2 = Contact("Лера", "89008123456")
        myPhone.addNewContact(contact1)
        myPhone.addNewContact(contact2)
        myPhone.updateContact(contact2, Contact("Вова", "871390000"))
        myPhone.printContacts()
        myPhone.removeContact(contact1)
        myPhone.printContacts()
    }
}
package nit.contact.objects

/**
 * Created by NIT Admin on 20/03/2016
 */
class DrawerItemMenu {
    var title: String? = null
    var icon: Int = 0
    var numNotify: Int = 0

    constructor(title: String?, icon: Int, numNotify: Int) {
        this.title = title
        this.icon = icon
        this.numNotify = numNotify

    }
}

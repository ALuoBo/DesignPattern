package strategy

import java.time.Year
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 *  在Kotlin 中，有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们，但是很麻烦，各种样板代码存在，我们知道，Kotlin可是宣称要实现零样板代码的。
 *
 *  为了解决这些问题呢？Kotlin标准为我们提供了 属性委托
 *  语法
 *  val/var <属性名>: <类型> by <表达式>
 */


fun main() {

    val name: String by Delegate()
    val name1: String by Delegate1()
    var name3: String by Delegate2()

    name3 = "dododo"

    println(name)
    println(name1)
    println(name3)


    //var year:String by Delegate2()
}


/**
 * thisRef —— 必须与 属性所有者 类型（对于扩展属性——指被扩展的类型）相同或者是它的超类型；
 * property —— 必须是类型 KProperty<*>或其超类型。
 * value —— 必须与属性同类型或者是它的子类型。
 */
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

/**
 * 使用
 */
class Delegate1 : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return thisRef.toString() + "123" + property.name
    }
}

class Delegate2 : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {

        return thisRef.toString() + "444" + property.name
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {

    }

}


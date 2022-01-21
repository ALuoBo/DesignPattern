package strategy


interface ILogStrategy {
    fun log(message :String)
}
class LogStrategyImp : ILogStrategy{
    override fun log(message: String) {
        print(message)
    }
}

/**
 * ILogStrategy by strategy 是什么操作？
 * LogStrategyProxy 这个类不去实现接口方法难道不会导致编译不通过么？
 * LogStrategyProxy 之所以不用实现 ILogStrategy 的 log 方法，是因为在 ILogStrategy 接口后面加了 by strategy，而 strategy 对象就是 LogStrategyProxy 构造函数中的变量，意思是让这个接口的具体实现由 strategy 对象帮我实现就可以了，我（LogStrategyProxy 类）不需要再实现一遍了，
 * 这样是不是跟 Java 中的静态代理很像？只不过在 Kotlin 类委托特性上面编译器帮我们自动生成接口方法的代码
 */

class LogStrategyProxy(strategy : ILogStrategy):ILogStrategy by strategy


fun main(){
    val mLogStrategyImp = LogStrategyImp()
    val mLogStrategy : LogStrategyProxy = LogStrategyProxy(mLogStrategyImp)

    mLogStrategy.log("这是一条测试")

}
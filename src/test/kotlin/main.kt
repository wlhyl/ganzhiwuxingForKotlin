package pub.teanote.ganzhiwuxing


fun main(args: Array<String>) {
    val  a = GanZhi(TianGan("甲"), DiZhi("子"))
    val b = GanZhi(TianGan("癸"), DiZhi("亥"))
    println(b - a)
}
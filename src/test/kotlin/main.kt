package pub.teanote.ganzhiwuxing


fun main(args: Array<String>) {
//    val z= DiZhi("寅")
    val g = TianGan("甲")
//    println(g)
    for(i in 0..11){
        println("${g + i} = ${(g + i).taiXuan}")
    }
//    for (i in 0..13){
//        println("${i}=${GanZhi(g,z)+i}")
//    }
//    val  a = GanZhi(TianGan("甲"), DiZhi("子"))
//    val b = GanZhi(TianGan("癸"), DiZhi("亥"))
//    println(a+(-65))
}
package com.lzh.ganzhiwuxing.ganzhiwuxing

fun main(args: Array<String>) {
    val a = DiZhi("子")
    val b = TianGan("甲")
//    println(!b.yang && !a.yang)
    val c = GanZhi(b, a)
    for (i in 0..60) {
        var k = c + i
        println("${i} ${k} ${k.num}")
    }
//    println(a.wuXing)
//    val b = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")
//    for (i in b) println("${DiZhi(i)} ${DiZhi(i).wuXing}")
//    for (i in b) {
//        var a0 = DiZhi(i)
//        for (j in b) {
//            var a1 = DiZhi(j)
//            if (a0.xing(a1)) println("${b}三合${a}")
//        }
//    }

}
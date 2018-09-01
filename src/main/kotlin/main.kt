package com.lzh.ganzhiwuxing.ganzhiwuxing

fun main(args: Array<String>) {
//    val a = DiZhi("寅")
////    val b = TianGan("乙")
//    println(a.wuXing)
    val b = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")
    for (i in b) println("${DiZhi(i)} ${DiZhi(i).wuXing}")
//    for (i in b) {
//        var a0 = DiZhi(i)
//        for (j in b) {
//            var a1 = DiZhi(j)
//            if (a0.xing(a1)) println("${a0}三合${a1}")
//        }
//    }

}
package org.lzh.ganzhiwuxing

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException


class WuXing(val name: String) {
    private val numToName: Array<String> = arrayOf("木", "火", "土", "金", "水")
    val num: Int = numToName.indexOf(name) + 1

    init {
        if (name !in numToName) {
            throw ValueException(name)
        }

    }

    override fun equals(other: Any?): Boolean {
        if (other !is WuXing) return false
        return num == other.num
    }

    override fun toString(): String {
        return name
    }

    fun ke(other: WuXing): Boolean {
        return (num - other.num - 3) % 5 == 0
    }

    fun sheng(other: WuXing): Boolean {
        return (num - other.num - 4) % 5 == 0
    }
}

class TianGan(val name: String) {
    private val numToName: Array<String> = arrayOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")
    private val numToWuXing: Array<String> = arrayOf("木", "火", "土", "金", "水")
    val num: Int = numToName.indexOf(name) + 1
    val wuXing = WuXing(numToWuXing[(num + 1) / 2 - 1])
    val yang = num % 2 != 0
    val taiXuan = if (num <= 5) 10 - num else 15 - num

    init {
        if (name !in numToName) {
            throw ValueException(name)
        }

    }

    override fun equals(other: Any?): Boolean {
        if (other !is TianGan) return false
        return num == other.num
    }

    operator fun plus(other: Int): TianGan {
        var tmp = other
        while (tmp<0)tmp+=10
        tmp = (num + tmp + 10) % 10
        if (tmp == 0) tmp = 10
        return TianGan(numToName[tmp - 1])
    }

    operator fun minus(other: TianGan): Int {
        return num - other.num
    }

    override fun toString(): String {
        return name
    }

    fun ke(other: TianGan): Boolean {
        return wuXing.ke(other.wuXing)
    }

    fun sheng(other: TianGan): Boolean {
        return wuXing.sheng(other.wuXing)
    }

    fun wuHe(other: TianGan): Boolean {
        if (num == other.num) return false
        return (num - other.num) % 5 == 0
    }
}

class DiZhi(val name: String) {
    private val numToName: Array<String> = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")
    private val numToWuXing: Array<String> = arrayOf("木", "火", "土", "金", "水")
    val num: Int = numToName.indexOf(name) + 1
    val wuXing: WuXing = {
        var tmp = (num + 10) % 12
        if (tmp == 0) tmp = 12

        if (tmp % 3 == 0) {
            WuXing("土")
        } else {
            tmp = tmp / 3 + tmp / 7 + 1
            WuXing(numToWuXing[tmp - 1])
        }
    }()
    val yang = num % 2 != 0
    val taiXuan = if (num <= 6) 10 - num else 16 - num

    init {
        if (name !in numToName) {
            throw ValueException(name)
        }


    }

    override fun equals(other: Any?): Boolean {
        if (other !is DiZhi) return false
        return num == other.num
    }

    operator fun plus(other: Int): DiZhi {
        var tmp = other
        while (tmp<0)tmp+=12
        tmp = (num + other + 12) % 12
        if (tmp == 0) tmp = 12
        return DiZhi(numToName[tmp - 1])
    }

    operator fun minus(other: DiZhi): Int {
        return num - other.num
    }

    override fun toString(): String {
        return name
    }

    fun ke(other: DiZhi): Boolean {
        return wuXing.ke(other.wuXing)
    }

    fun sheng(other: DiZhi): Boolean {
        return wuXing.sheng(other.wuXing)
    }

    fun sanHe(other: DiZhi): Boolean {
        if (num == other.num) return false
        return (num - other.num) % 4 == 0
    }

    fun liuHe(other: DiZhi): Boolean {
        if (num == other.num) return false
        return (num + other.num - 15) % 12 == 0
    }


    fun liuChong(other: DiZhi): Boolean {
        if (num == other.num) return false
        return (num - other.num) % 6 == 0
    }

    fun xing(other: DiZhi): Boolean {
        when {
            name == "子" && other.name == "卯" -> return true
            name == "丑" && other.name == "戌" -> return true
            name == "寅" && other.name == "巳" -> return true
            name == "卯" && other.name == "子" -> return true
            name == "辰" && other.name == "辰" -> return true
            name == "巳" && other.name == "申" -> return true
            name == "午" && other.name == "午" -> return true
            name == "未" && other.name == "丑" -> return true
            name == "申" && other.name == "寅" -> return true
            name == "酉" && other.name == "酉" -> return true
            name == "戌" && other.name == "未" -> return true
            name == "亥" && other.name == "亥" -> return true
        }
        return false
    }
}

class GanZhi(val gan: TianGan, val zhi: DiZhi) {
    val num: Int = getNumber()

    init {
        if (gan.yang != zhi.yang) {
            throw ValueException("干支阴阳不相同")
        }
    }

    private fun getNumber(): Int {
        val g = TianGan("甲")
        val z = DiZhi("子")
        var n: Int = 0
        for (i in 0..60) {
            if (g + i == gan && z + i == zhi) {
                n = i
                break
            }
        }
        return n + 1
    }

    override fun toString(): String {
        return "${gan}${zhi}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is GanZhi) return false
        return gan == other.gan && zhi == other.zhi
    }

    operator fun plus(other: Int): GanZhi {
        val g = gan + other
        val z = zhi + other
        return GanZhi(g, z)
    }

    operator fun minus(other: GanZhi): Int {
        return num - other.num
    }
}
package com.lzh.ganzhiwuxing.ganzhiwuxing

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException


//abstract class Base(num: Int){
//    abstract val numToName: Array<Char>
//    val name = numToName[num - 1].toString()
//    override fun toString(): String {
//        return name
//    }
//}

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
        var tmp = (num + other + 10) % 10
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
    val wuXing: WuXing ={
        var tmp = (num + 10) % 12
        if (tmp == 0) tmp = 12

        if (tmp % 3 == 0) {
            WuXing("土")
        } else {
            tmp = tmp / 3 + tmp / 7 + 1
            WuXing(numToWuXing[tmp - 1])
        }}()
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
        var tmp = (num + other + 12) % 12
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
//class 干支():
//        '''
//a = 干支(干(2), 支(2))
//a['干'] == 干(2)
//a['支'] == 支(2)
//'''
//
//def __init__(self, a, b):
//if not isinstance(a, 干):
//raise ValueError('干支由干、支组，提供的不是干')
//if not isinstance(b, 支):
//raise ValueError('干支由干、支组，提供的不是支')
//
//if not 阴阳相同(a, b):
//raise ValueError('{0} {1}不匹配'.format(a, b))
//
//self.__干 = a
//self.__支 = b
//
//def __str__(self):
//return '{0}{1}'.format(self.__干, self.__支)
//
//@property
//def 干(self):
//return self.__干
//
//@property
//def 支(self):
//return self.__支
//
//@property
//def num(self):
//甲 = 干("甲")
//子 = 支("子")
//
//for i in range(0, 60):
//if self.干 == (甲 + i) and self.支 == (子 + i):
//return i + 1
//
//def __eq__(self, other):
//if not isinstance(other, 干支):
//raise ValueError('只能用于支与干支间')
//return self.干 == other.干 and self.支 == other.支
//
//def __ne__(self, other):
//if not isinstance(other, 干支):
//raise ValueError('只能用于支与干支间')
//return not self.__eq__()
//
//def __add__(self, other):
//if not isinstance(other, int):
//raise ValueError('%s 必须是整数' % other)
//return 干支(self.干 + other, self.支 + other)
//
//def __sub__(self, other):
//if not isinstance(other, 干支):
//raise ValueError('%s 必须是干支' % other)
//return self.num - other.num
//
//# @checkValue(valueType=支)
//# def 获取三合(a):
//#     allSanhe = []
//#     for i in range(1, 13):
//#         for j in range(1, 13):
//#             for k in range(1, 13):
//#                 if 三合(支(i), 支(j), 支(k)):
//#                     allSanhe.append((支(i), 支(j), 支(k)))
//#     tmpSanhe = []
//#     for i in allSanhe:
//#         if i[0] in [支(1), 支(4), 支(7), 支(10)] and \
//#            i[1] in [支(2), 支(5), 支(8), 支(11)]:
//#             tmpSanhe.append(i)
//#     allSanhe = tmpSanhe
//#     for i in allSanhe:
//#         if a in i:
//#             return i
//
//
//# @checkValue(valueType=支)
//# def 获取六冲(a):
//#     for i in range(1, 13):
//#         if 六冲(a, 支(i)):
//#             return 支(i)
//
//
//# @checkValue(valueType=支)
//# def 刑(a, b):
//#     xin_group = []
//#     # 寅 巳 申
//#     xin_group.append((支(1), 支(4)))
//#     xin_group.append((支(4), 支(7)))
//#     xin_group.append((支(7), 支(1)))
//#     # 未 丑 戌
//#     xin_group.append((支(6), 支(12)))
//#     xin_group.append((支(12), 支(9)))
//#     xin_group.append((支(9), 支(6)))
//#
//#     # 子 卯
//#     xin_group.append((支(2), 支(11)))
//#     xin_group.append((支(11), 支(2)))
//#
//#     # 辰 午 酉 亥
//#     xin_group.append((支(3), 支(3)))
//#     xin_group.append((支(5), 支(5)))
//#     xin_group.append((支(8), 支(8)))
//#     xin_group.append((支(10), 支(10)))
//#
//#     return (a, b) in xin_group
//
//
//def 阴阳相同(a, b):
//'''
//可用于与干、支与支、干与支
//'''
//return (a.num - b.num) % 2 == 0
//lzh@lzh:~$

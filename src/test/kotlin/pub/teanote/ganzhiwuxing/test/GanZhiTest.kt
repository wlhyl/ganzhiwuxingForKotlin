package pub.teanote.ganzhiwuxing.pub.teanote.ganzhiwuxing.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pub.teanote.ganzhiwuxing.DiZhi
import pub.teanote.ganzhiwuxing.GanZhi
import pub.teanote.ganzhiwuxing.TianGan
import pub.teanote.ganzhiwuxing.WuXing

@DisplayName("测试60甲子")
class GanZhiTest {

    private val naYing = listOf(
        NaYingData("甲子", WuXing("金")),
        NaYingData("乙丑", WuXing("金")),
        NaYingData("丙寅", WuXing("火")),
        NaYingData("丁卯", WuXing("火")),
        NaYingData("戊辰", WuXing("木")),
        NaYingData("己巳", WuXing("木")),
        NaYingData("庚午", WuXing("土")),
        NaYingData("辛未", WuXing("土")),
        NaYingData("壬申", WuXing("金")),
        NaYingData("癸酉", WuXing("金")),
        NaYingData("甲戌", WuXing("火")),
        NaYingData("乙亥", WuXing("火")),
        NaYingData("丙子", WuXing("水")),
        NaYingData("丁丑", WuXing("水")),
        NaYingData("戊寅", WuXing("土")),
        NaYingData("己卯", WuXing("土")),
        NaYingData("庚辰", WuXing("金")),
        NaYingData("辛巳", WuXing("金")),
        NaYingData("壬午", WuXing("木")),
        NaYingData("癸未", WuXing("木")),
        NaYingData("壬午", WuXing("木")),
        NaYingData("癸未", WuXing("木")),
        NaYingData("甲申", WuXing("水")),
        NaYingData("乙酉", WuXing("水")),
        NaYingData("丙戌", WuXing("土")),
        NaYingData("丁亥", WuXing("土")),
        NaYingData("戊子", WuXing("火")),
        NaYingData("己丑", WuXing("火")),
        NaYingData("庚寅", WuXing("木")),
        NaYingData("辛卯", WuXing("木")),
        NaYingData("壬辰", WuXing("水")),
        NaYingData("癸巳", WuXing("水")),
        NaYingData("甲午", WuXing("金")),
        NaYingData("乙未", WuXing("金")),
        NaYingData("丙申", WuXing("火")),
        NaYingData("丁酉", WuXing("火")),
        NaYingData("戊戌", WuXing("木")),
        NaYingData("己亥", WuXing("木")),
        NaYingData("庚子", WuXing("土")),
        NaYingData("辛丑", WuXing("土")),
        NaYingData("壬寅", WuXing("金")),
        NaYingData("癸卯", WuXing("金")),
        NaYingData("甲辰", WuXing("火")),
        NaYingData("乙巳", WuXing("火")),
        NaYingData("丙午", WuXing("水")),
        NaYingData("丁未", WuXing("水")),
        NaYingData("戊申", WuXing("土")),
        NaYingData("己酉", WuXing("土")),
        NaYingData("庚戌", WuXing("金")),
        NaYingData("辛亥", WuXing("金")),
        NaYingData("壬子", WuXing("木")),
        NaYingData("癸丑", WuXing("木")),
        NaYingData("甲寅", WuXing("水")),
        NaYingData("乙卯", WuXing("水")),
        NaYingData("丙辰", WuXing("土")),
        NaYingData("丁巳", WuXing("土")),
        NaYingData("戊午", WuXing("火")),
        NaYingData("己未", WuXing("火")),
        NaYingData("庚申", WuXing("木")),
        NaYingData("辛酉", WuXing("木")),
        NaYingData("壬戌", WuXing("水")),
        NaYingData("癸亥", WuXing("水")),
    )

    @Test
    @DisplayName("纳音")
    fun naYinTest() {
        val gz = GanZhi(TianGan("甲"), DiZhi("子"))
        for (i in 0 until 60){
            val g = gz + i
            println(g)
            val w = this.naYing.first{ it.name == g.toString()}
            Assertions.assertEquals(w.wuXing, g.naYin, "$g")
        }
    }
}

private data class NaYingData(val name :String, val wuXing :WuXing)

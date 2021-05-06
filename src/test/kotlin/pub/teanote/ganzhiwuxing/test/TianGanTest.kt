package pub.teanote.ganzhiwuxing.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pub.teanote.ganzhiwuxing.TianGan
import pub.teanote.ganzhiwuxing.WuXing

@DisplayName("测试天干")
class TianGanTest {
    private val tianGanName: Array<String> = arrayOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")

    @Test
    @DisplayName("天干toString")
    fun tianGanToStringTest() {
        this.tianGanName.forEach {
            Assertions.assertEquals(it, TianGan(it).toString(), it)
        }
    }

    @Test
    @DisplayName("天干的五行")
    fun wuXingTest() {
        Assertions.assertEquals(WuXing("木"), TianGan("甲").wuXing)
        Assertions.assertEquals(WuXing("木"), TianGan("乙").wuXing)
        Assertions.assertEquals(WuXing("火"), TianGan("丙").wuXing)
        Assertions.assertEquals(WuXing("火"), TianGan("丁").wuXing)
        Assertions.assertEquals(WuXing("土"), TianGan("戊").wuXing)
        Assertions.assertEquals(WuXing("土"), TianGan("己").wuXing)
        Assertions.assertEquals(WuXing("金"), TianGan("庚").wuXing)
        Assertions.assertEquals(WuXing("金"), TianGan("辛").wuXing)
        Assertions.assertEquals(WuXing("水"), TianGan("壬").wuXing)
        Assertions.assertEquals(WuXing("水"), TianGan("癸").wuXing)
    }

    @Test
    @DisplayName("天干的阴阳")
    fun masculineTest() {
        for(i in this.tianGanName.indices){
            if(i % 2 == 0)
                Assertions.assertTrue(TianGan(this.tianGanName[i]).masculine)
            else
                Assertions.assertFalse(TianGan(this.tianGanName[i]).masculine)
        }
    }

    @Test
    @DisplayName("天干的太玄数")
    fun taiXuanTest() {
        Assertions.assertEquals(9, TianGan("甲").taiXuan)
        Assertions.assertEquals(8, TianGan("乙").taiXuan)
        Assertions.assertEquals(7, TianGan("丙").taiXuan)
        Assertions.assertEquals(6, TianGan("丁").taiXuan)
        Assertions.assertEquals(5, TianGan("戊").taiXuan)
        Assertions.assertEquals(9, TianGan("己").taiXuan)
        Assertions.assertEquals(8, TianGan("庚").taiXuan)
        Assertions.assertEquals(7, TianGan("辛").taiXuan)
        Assertions.assertEquals(6, TianGan("壬").taiXuan)
        Assertions.assertEquals(5, TianGan("癸").taiXuan)
    }

    @Test
    @DisplayName("天干相等")
    fun equalsTest() {
        this.tianGanName.forEach {
            Assertions.assertTrue( TianGan(it) == TianGan(it), "$it == $it")
        }
    }

    @Test
    @DisplayName("天干不相等")
    fun notEqualTest() {
        for(i in this.tianGanName.indices){
            val g0 = TianGan(this.tianGanName[i])
            for (j in 1 until this.tianGanName.size){
                val n = (i + j ) % this.tianGanName.size
                val g1 = TianGan(this.tianGanName[n])
                Assertions.assertFalse( g0 == g1, "$g0 !=$g1")
            }
        }
    }

    //用数学归纳法
    @Test
    @DisplayName("天干 + 整数")
    fun plusTest() {
        for(i in this.tianGanName.indices){
            Assertions.assertEquals(TianGan(this.tianGanName[(i+1) % this.tianGanName.size]), TianGan(this.tianGanName[i]) + 1)
        }

        for(i in this.tianGanName.indices){
            Assertions.assertEquals(
                TianGan(this.tianGanName[(i - 1 + this.tianGanName.size) % this.tianGanName.size]),
                TianGan(this.tianGanName[i]) + -1)
        }

        this.tianGanName.forEach {
            val g = TianGan(it)
            Assertions.assertEquals(g + 99 + 1, g + 100)
            Assertions.assertEquals(g + -99 + -1, g + -100)
        }
    }

    @Test
    @DisplayName("天干 -天干 = 正整数")
    fun minusTest() {
        for(i in this.tianGanName.indices){
            val g0 = TianGan(this.tianGanName[i])
            for (j in this.tianGanName.indices){
                val g1 = g0 + j
                Assertions.assertEquals( j, g1 - g0,"$g1 - $g0 = $j")
            }
        }
    }

    @Test
    @DisplayName("天干相克")
    fun keTest() {
        for(i in this.tianGanName.indices){
            val g0 = TianGan(this.tianGanName[i])
            for (j in this.tianGanName.indices){
                val g1 = TianGan(this.tianGanName[j])
                Assertions.assertEquals( g0.wuXing.ke(g1.wuXing), g0.ke(g1),"$g0 克 $g1")
            }
        }
    }

    @Test
    @DisplayName("天干相生")
    fun shengTest() {
        for(i in this.tianGanName.indices){
            val g0 = TianGan(this.tianGanName[i])
            for (j in this.tianGanName.indices){
                val g1 = TianGan(this.tianGanName[j])
                Assertions.assertEquals( g0.wuXing.sheng(g1.wuXing), g0.sheng(g1),"$g0 生 $g1")
            }
        }
    }

    @Test
    @DisplayName("天干五合")
    fun wuHeTest() {
        this.tianGanName.forEach {
            val g0 = TianGan(it)
            for(i in 0..10){
                val g1 = g0 + i
                if(i == 5) Assertions.assertTrue(g0.wuHe(g1))
                else Assertions.assertFalse(g0.wuHe(g1))
            }
        }

    }

}
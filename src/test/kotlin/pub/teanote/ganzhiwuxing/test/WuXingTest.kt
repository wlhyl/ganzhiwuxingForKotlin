package pub.teanote.ganzhiwuxing.pub.teanote.ganzhiwuxing.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pub.teanote.ganzhiwuxing.WuXing


@DisplayName("测试五行")
class WuXingTest {
    private val wuXingName: Array<String> = arrayOf("木", "火", "土", "金", "水")

    @Test
    @DisplayName("五行toString")
    fun wuXingToString() {
        this.wuXingName.forEach {
            Assertions.assertEquals(it, WuXing(it).toString(), it)
        }
    }

    @Test
    @DisplayName("五行相等")
    fun wuXingEquals() {
        this.wuXingName.forEach {
            Assertions.assertTrue( WuXing(it) == WuXing(it), "$it == $it")
        }
    }

    @Test
    @DisplayName("五行不相等")
    fun wuXingNotEquals() {
        for(i in this.wuXingName.indices){
            val g0 = WuXing(this.wuXingName[i])
            for (j in 1 until this.wuXingName.size){
                val n = (i + j ) % this.wuXingName.size
                val g1 = WuXing(this.wuXingName[n])
                Assertions.assertFalse( g0 == g1, "$g0 !=$g1")
            }

        }
    }

    @Test
    @DisplayName("五行相生")
    fun wuXingSheng() {
        for(i in this.wuXingName.indices){
            val n = (i + 1) % this.wuXingName.size
            val g0 = WuXing(this.wuXingName[i])
            val g1 = WuXing(this.wuXingName[n])
            Assertions.assertTrue( g0.sheng(g1), "$g0 生 $g1")
        }
    }

    @Test
    @DisplayName("五行相克")
    fun wuXingKe() {
        for(i in this.wuXingName.indices){
            val n = (i + 2) % this.wuXingName.size
            val g0 = WuXing(this.wuXingName[i])
            val g1 = WuXing(this.wuXingName[n])
            Assertions.assertTrue( g0.ke(g1), "$g0 生 $g1")
        }
    }

    @Test
    @DisplayName("五行不相生")
    fun wuXingNotSheng() {
        for(i in this.wuXingName.indices){
            val n0 = (i + 2) % this.wuXingName.size
            val n1 = (i + 3) % this.wuXingName.size
            val n2 = (i + 4) % this.wuXingName.size

            val g0 = WuXing(this.wuXingName[i])

            val g1 = WuXing(this.wuXingName[n0])
            val g2 = WuXing(this.wuXingName[n1])
            val g3 = WuXing(this.wuXingName[n2])

            Assertions.assertFalse( g0.sheng(g1), "$g0 不生 $g1")
            Assertions.assertFalse( g0.sheng(g2), "$g0 不生 $g2")
            Assertions.assertFalse( g0.sheng(g3), "$g0 不生 $g3")

            // 自己不生自己
            Assertions.assertFalse( g0.sheng(g0), "$g0 不生 $g0")

        }

    }

    @Test
    @DisplayName("五行不相克")
    fun wuXingNotKe() {
        for(i in this.wuXingName.indices){
            val n0 = (i + 1) % this.wuXingName.size
            val n1 = (i + 3) % this.wuXingName.size
            val n2 = (i + 4) % this.wuXingName.size

            val g0 = WuXing(this.wuXingName[i])

            val g1 = WuXing(this.wuXingName[n0])
            val g2 = WuXing(this.wuXingName[n1])
            val g3 = WuXing(this.wuXingName[n2])

            Assertions.assertFalse( g0.ke(g1), "$g0 不克 $g1")
            Assertions.assertFalse( g0.ke(g2), "$g0 不克 $g2")
            Assertions.assertFalse( g0.ke(g3), "$g0 不克 $g3")

            // 自己不克自己
            Assertions.assertFalse( g0.ke(g0), "$g0 不克 $g0")

        }
    }
}
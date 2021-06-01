<template>
  <div id="app">
    <!-- <div style="width: 1000px;height:150px;"></div> -->
    <div id="main" style="width: 1300px;height:600px;"></div>
    <div id="foot">
      <el-form label-width="80px">
        <span>2019年总支出：</span>
        <el-input placeholder="¥54672" :disabled="true" style="width:550px"></el-input>
        <span>2020年总支出：</span>
        <el-input placeholder="¥62318" :disabled="true" style="width:550px"></el-input>
      </el-form>
    </div>
  </div>
</template>

<script>
import pay from '@/api/pay/pay'
export default {
  name: "app",
  data () {
    return {
      lastYear: [],
      thisYear: []
    }
  },
  methods: {
    getPay () {
      pay.getPay().then(
        Response => {
          this.lastYear = Response.data.lastYear
          this.thisYear = Response.data.thisYear
        }
      )
    },
    drawChart () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: '支出统计',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['2019年', '2020年']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          name: '¥',
          type: 'value',
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          name: '',
          type: 'category',
          data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
        },
        series: [
          {
            name: '2019年',
            type: 'bar',
            //data: [5000, 3700, 4730, 6145, 7831, 6389, 5703, 4891, 5029, 3988, 4756, 7004]
            data: this.lastYear
          },
          {
            name: '2020年',
            type: 'bar',
            //data: [4567, 5738, 5639, 6598, 5683, 6762, 5693, 4349, 5738, 6874, 5558, 6739]
            data: this.thisYear
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }
  },
  watch: {
    lastYear: {
      handler () {
        this.drawChart()
      }
    }
  },
  mounted () {
    this.getPay()
    this.drawChart()
  }
};
</script>

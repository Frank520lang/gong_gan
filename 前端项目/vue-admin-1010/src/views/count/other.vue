<template>
  <div id="app">
    <div style="width: 1000px;height:100px;"></div>
    <div id="main" style="width: 1200px;height:500px;"></div>
  </div>
</template>

<script>
import other from '@/api/other/other'
export default {
  name: "app",
  data () {
    return {
      awayList: [],
      downList: [],
      temperatureList: []
    }
  },
  methods: {
    getOther () {
      other.getOther().then(
        Response => {
          this.awayList = Response.data.awayList
          this.downList = Response.data.downList
          this.temperatureList = Response.data.temperatureList
        }

      )
    },
    drawChart () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['蒸发量', '降水量', '平均温度']
        },
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '水量',
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
              formatter: '{value} ml'
            }
          },
          {
            type: 'value',
            name: '温度',
            min: 0,
            max: 30,
            interval: 5,
            axisLabel: {
              formatter: '{value} °C'
            }
          }
        ],
        series: [
          {
            name: '蒸发量',
            type: 'bar',
            //data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            data: this.awayList
          },
          {
            name: '降水量',
            type: 'bar',
            //data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            data: this.downList
          },
          {
            name: '平均温度',
            type: 'line',
            yAxisIndex: 1,
            //data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
            data: this.temperatureList
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }
  },
  watch: {
    //监听data的变化重新加载echart
    temperatureList: {
      handler () {
        this.drawChart()
      }
    }
  },
  mounted () {
    this.getOther()
    this.drawChart()
  }
};
</script>

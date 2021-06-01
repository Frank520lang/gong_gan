<template>
  <div id="app">
    <div style="width: 1000px;height:100px;"></div>
    <div id="main" style="width: 1200px;height:500px;"></div>
  </div>
</template>

<script>
import price from '@/api/price/price'
export default {
  name: "app",
  data () {
    return {
      priceList: [],
      dayList: [],
    }
  },
  methods: {
    getlist () {
      console.log("getlist")
      price.getPriceListPage().then(
        Response => {
          this.priceList = Response.data.priceList;
          this.dayList = Response.data.dayList;
        }
      )
    },
    drawChart () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: "近期市场价格"
        },
        tooltip: {},
        legend: {
          data: ["价格"]
        },
        xAxis: {
          name: "",
          //data: ['11/1', '11/15', '11/30', "12/1", "12/15", "12/30", "1/1", "1/15", "1/30", "2/1", "2/15", "2/28"]
          data: this.dayList
        },
        yAxis: {
          name: "¥"
        },
        series: [
          {
            name: "价格",
            type: "bar",
            //data: [2.92, 2.24, 3.01, 3.32, 2.78, 2.92, 2.24, 3.01, 3.45, 4.24, 3.71, 3.65]
            data: this.priceList
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }
  },
  //监听数据的变化重新加载echart
  watch: {
    //监听priceList参数的变化
    priceList: {
      handler () {
        //当监听到数据发生变化时，重新加载一遍echart
        this.drawChart();
      }
    }
  },
  mounted () {
    //页面初始化之前加载后端数据
    this.getlist();
    //页面初始化之前加载echart
    this.drawChart();

  }
}

</script>

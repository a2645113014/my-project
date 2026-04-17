<template>
  <div>
    <el-row :gutter="24">
      <el-col :span="24">
        <el-card style="margin: 10px 0">
          <div style="font-size: 17px;font-weight: 700">大家好，欢迎使用本系统！</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="12">
        <el-card class="box-card">
          <div class="clearfix" style="margin-bottom: 20px">
            <span style="font-size: 20px;font-weight: bold">公告信息</span>
          </div>
          <div class="text item">
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item :title="item.name" :name="index" v-for="(item,index) in notices" :key="index">
                <div>{{item.content}}</div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card class="box-card">
          <div id="main" style="width: 100%;height: 400px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts"

export default {
  name: "Home",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('user') || '{}'),
      activeNames: [0],
      notices: []
    }
  },
  created() {
    this.loadNotice()
  },
  mounted() {
    this.$request.get('/goods/echarts').then(res => {
      this.initPie(res.data)
    })
  },
  methods: {
    handleChange(val) {
      console.log(val);
    },
    loadNotice(){
      this.$request.get('/notice/selectAll').then(res => {
        this.notices = res.data
      })
    },
    initPie(data){
      let chartDom = document.getElementById('main');
      let myChart = echarts.init(chartDom);
      let option;

      option = {
        title: {
          text: '商品销量统计',
          subtext: '饼图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            type: 'pie',
            radius: '60%',
            data: data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      option && myChart.setOption(option);
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}


</style>

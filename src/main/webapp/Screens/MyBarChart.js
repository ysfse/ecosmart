import { BarChart, LineChart } from "react-native-chart-kit";
import React from 'react';
import {View,Dimensions,Keyboard,Image,ImageBackground,ScrollView,TouchableOpacity} from 'react-native';
import {StyleSheet} from 'react-native';
import Input from '../Shared/Form/Input';
import DateTimePickerModal from "react-native-modal-datetime-picker"
import DatePicker from 'react-native-datepicker'
import AsyncStorage from "@react-native-community/async-storage"
import axios from "axios"
import Icon from '@expo/vector-icons/Entypo';
import styled from 'styled-components'

var { width, height } = Dimensions.get("window");
export default class MyBarChart extends React.Component{

       constructor(props) {
        super(props);
        this.state = {
           loadedS : false,
           loadedC : false,
           isLoading: true,
           date : "2021-04-30",
           data: {
            labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
            datasets: [
                {
                    data: []
                }
            ]
        },
           dataB: {
               labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
               datasets: [
                   {
                       data: []
                   }
               ]
           },
           dataA: {
            labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
            datasets: [
                {
                    data: []
                }
            ]
        },    dataGlobalCarpool: {
          labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
          datasets: [
              {
                  data: []
              }
          ]
      },
        dataACarpool: {
          labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
          datasets: [
              {
                  data: []
              }
          ]
      }, dataBCarpool: {
        labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
        datasets: [
            {
                data: []
            }
        ]
    },
        }
    }

    GetData=(date) => {
      console.log("starting..")
      const self = this; 
      let url = 'https://ecosmart-atos.herokuapp.com/commands/statistics?date=' + date;
      AsyncStorage.getItem("jwt")
      .then((res) => {
        axios
         .get(
            url,
          {
            headers: { Authorization: `Bearer ${res}` },
          }
          )
          //.then((response) => response.json())
          .then((responseJson) => {
            // console.log(responseJson.data)
              // clone the data from the state
           var dataClone = {
                labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                datasets: [
                    {
                        data: []
                    }
                ]
            };          
                 self.setState({
                   data : {
                    labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                    datasets: [
                        {
                            data: []
                        }
                    ]
                },
               dataB : {
                  labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                  datasets: [
                      {
                          data: []
                      }
                  ]
              },
              dataA :{
                labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                datasets: [
                    {
                        data: []
                    }
                ]
            },
                 },(() => { 
                   {dataClone = {...self.state.data},
                   dataClone.datasets[0].data = responseJson.data;                
                    }
              })
                 )
             
            //dataClone = {...self.state.data}
            console.log("dataclone " + dataClone)
            //  const values = responseJson.map(value => value[0]);
           // dataClone.datasets[0].data = responseJson.data;

            var i;
            for (i = 0; i < responseJson.data.length-2; i+=2) {
              this.state.dataB.datasets[0].data.push( dataClone.datasets[0].data[i]);
             //this.state.dataB.datasets[0].data.push( responseJson.data[i]);
              this.state.dataA.datasets[0].data.push( dataClone.datasets[0].data[i+1]);                 
            }
              console.log( this.state.dataA.datasets[0].data);
              console.log(this.state.dataB.datasets[0].data);

              self.setState({
                  isLoading: false,
                  data: dataClone,
                  loadedS:true
              })
          //  .then(() => this.setState({loaded : false}))
            })
      .catch((error) =>{
        console.error(error);
      });
    }
      )}
    GetCarpoolsData=(date) => {
        console.log("starting..")
        const self = this; 
        let url = 'https://ecosmart-atos.herokuapp.com/carpools/statistics?date=' + date;
        AsyncStorage.getItem("jwt")
        .then((res) => {
          axios
           .get(
              url,
            {
              headers: { Authorization: `Bearer ${res}` },
            }
            )
            //.then((response) => response.json())
            .then((responseJson) => {
              // console.log(responseJson.data)
                // clone the data from the state
             var dataClone = {
                  labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                  datasets: [
                      {
                          data: []
                      }
                  ]
              };          
                   self.setState({
                     dataGlobalCarpool : {
                      labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                      datasets: [
                          {
                              data: []
                          }
                      ]
                  },
                 dataBCarpool : {
                    labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                    datasets: [
                        {
                            data: []
                        }
                    ]
                },
                dataACarpool :{
                  labels: ["Day1", "Day2", "Day3", "Day4", "Day5", "Day6", "Day7"],
                  datasets: [
                      {
                          data: []
                      }
                  ]
              },
                   },(() => { 
                     {dataClone = {...self.state.dataGlobalCarpool},
                     dataClone.datasets[0].data = responseJson.data;                
                      }
                })
                   )
               
              //dataClone = {...self.state.data}
              console.log("dataclone " + dataClone)
              //  const values = responseJson.map(value => value[0]);
             // dataClone.datasets[0].data = responseJson.data;
  
              var i;
              for (i = 0; i < responseJson.data.length-2; i+=2) {
                this.state.dataBCarpool.datasets[0].data.push( dataClone.datasets[0].data[i]);
               //this.state.dataB.datasets[0].data.push( responseJson.data[i]);
                this.state.dataACarpool.datasets[0].data.push( dataClone.datasets[0].data[i+1]);                 
              }
                console.log( this.state.dataACarpool.datasets[0].data);
                console.log(this.state.dataBCarpool.datasets[0].data);
  
                self.setState({
                    isLoading: false,
                    dataGlobalCarpool: dataClone,
                    loadedC:true
                })
            //  .then(() => this.setState({loaded : false}))
              })
        .catch((error) =>{
          console.error(error);
        });
      }
        )}
      
    

    render() {
      return(
        <ScrollView  
        showsVerticalScrollIndicator={false}
        style={{
            height:"50%",
            backgroundColor:"white",
           
        }}
        
        > 
          <View style={{
                      flexDirection:"row",
                      paddingTop:20,
                      paddingLeft : 10,
                      paddingRight : 10
                  }}>
                      <TouchableOpacity
                        onPress={this.onTabPressed}
                        style={{
                            borderBottomColor:this.state.popularSelected ? "#044244":"#FFF",
                            borderBottomWidth:4,
                            paddingVertical:5,
                          //  backgroundColor: "#344955"
                          
                        }}
                      >
                          <Text style={{
                              fontFamily:"bold",
                              color:"#044244",
                            //  fontSize : 20

                          }}>Select a date and get your contribution during the next 6 days</Text>
                      </TouchableOpacity> 

                      

              </View>
                <View style={{
                  height:260,
                  width:"100%",
                  paddingHorizontal:35
              }}>
                  <View style={{
                      flexDirection:"row",
                      width:"100%",
                      paddingTop:40,
                      alignItems:"center"
                  }}>
                      <View style={{
                          width:"50%"
                      }}>
                       
                      </View>
                      <View style={{
                          width:"50%",
                          alignItems:"flex-end",
                      }}>
                
                      </View>
                  </View>
                  
                </View>
            
    <DatePicker
        style={{width: 300,
          height : 50,
          marginTop : -250,
          marginLeft : 10,
          marginLeft : -35
        
        }
      }
       date={this.state.date}
       // time ={this.state.time}
        mode="date"
        placeholder="select date"
        format="YYYY-MM-DD"
        minDate="2021-04-01"
        maxDate="2022-04-01"
        confirmBtnText="Confirm"
        cancelBtnText="Cancel"
        customStyles={{
          dateIcon: {
            position: 'absolute',
            left: 0,
            top: 4,
            marginLeft: 80,
          },
          dateInput: {
            marginLeft: 130,
            width : 70,
            mrginTop : -200
          }
        }}
        onDateChange = {
          (date) =>
           {
            this.setState({date : date})  ;   
            this.GetData(this.state.date) ;
            this.GetCarpoolsData(this.state.date) ;                            
           }
          }
      />

       
      
{!this.state.loadedS ?  <></>: ( 
     
     <View> 
     
       <TouchableOpacity
                        onPress={this.onTabPressed}
                        style={{
                            borderBottomColor:this.state.popularSelected ? "#044244":"#FFF",
                            borderBottomWidth:10,
                            borderTopWidth:10,
                            borderLeftWidth : 2,
                            paddingVertical:5,
                            borderTopColor:this.state.popularSelected ? "#044244":"#FFF",
                            marginTop : 40,
                            backgroundColor :"#FEDBD0"
                        }}
                        
                      >
                          <Text style={{
                              fontFamily:"Bold",
                              color:"#044244"
                          }}>Here is your contribution during 6 days starting from from: <Text style={{ fontWeight: 'bold' }}>{this.state.date}</Text>     </Text>
        </TouchableOpacity> 
        <Text>{"\n"}</Text>
        <Text style={{ fontWeight: 'bold' }}>     If you were not using ecosmart   </Text>
  <LineChart
  
  data={this.state.dataB}
  width={Dimensions.get('window').width - 16}
  height={220}
  chartConfig={{
    backgroundColor: '#1cc910',
    backgroundGradientFrom: '#eff3ff',
    backgroundGradientTo: '#efefef',
    decimalPlaces: 2,
    color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
    style: {
      borderRadius: 16,
    },
  }}
 
  style={{
    marginVertical: 8,
    borderRadius: 16,
  }}
/>
</View>
)}

        
{!this.state.loadedS ? null : ( 
        <View>
        <Text style={{ fontWeight: 'bold' }}>    While using ecosmart   </Text>

       
        <LineChart
  data={this.state.dataA}
  width={Dimensions.get('window').width - 16}
  height={220}
  chartConfig={{
    backgroundColor: '#1cc910',
    backgroundGradientFrom: '#eff3ff',
    backgroundGradientTo: '#efefef',
    decimalPlaces: 2,
    color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
    style: {
      borderRadius: 16,
    },
  }}
  style={{
    marginVertical: 8,
    borderRadius: 16,
  }}
/>
</View>
)}

{!this.state.loadedC ?  <></>: ( 
     
     <View> 
     
       <TouchableOpacity
                        onPress={this.onTabPressed}
                        style={{
                            borderBottomColor:this.state.popularSelected ? "#044244":"#FFF",
                            borderBottomWidth:10,
                            borderTopWidth:10,
                            borderLeftWidth : 2,
                            paddingVertical:5,
                            borderTopColor:this.state.popularSelected ? "#044244":"#FFF",
                            marginTop : 40,
                            backgroundColor :"#FEDBD0"
                        }}
                        
                      >
                          <Text style={{
                              fontFamily:"Bold",
                              color:"#044244"
                          }}>Here is your contribution during 6 days starting from from: <Text style={{ fontWeight: 'bold' }}>{this.state.date}</Text>     </Text>
        </TouchableOpacity> 
        <Text>{"\n"}</Text>
        <Text style={{ fontWeight: 'bold' }}>     If you were not using ecosmart   </Text>
  <LineChart
  
  data={this.state.dataBCarpool}
  width={Dimensions.get('window').width - 16}
  height={220}
  chartConfig={{
    backgroundColor: '#1cc910',
    backgroundGradientFrom: '#eff3ff',
    backgroundGradientTo: '#efefef',
    decimalPlaces: 2,
    color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
    style: {
      borderRadius: 16,
    },
  }}
 
  style={{
    marginVertical: 8,
    borderRadius: 16,
  }}
/>
</View>
)}

{!this.state.loadedC ? null : ( 
        <View>
        <Text style={{ fontWeight: 'bold' }}>    While using ecosmart   </Text>

       
        <LineChart
  data={this.state.dataACarpool}
  width={Dimensions.get('window').width - 16}
  height={220}
  chartConfig={{
    backgroundColor: '#1cc910',
    backgroundGradientFrom: '#eff3ff',
    backgroundGradientTo: '#efefef',
    decimalPlaces: 2,
    color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
    style: {
      borderRadius: 16,
    },
  }}
  style={{
    marginVertical: 8,
    borderRadius: 16,
  }}
/>
</View>
)}

      </ScrollView>
      
      )
      
    }
    
}

const Text  = styled.Text`
color : #000                             


${({title, large, small}) => {
   switch(true){
      case title: 
         return `font-size :32px`;
      case large: 
         return `font-size :20px`;
      case small: 
         return `font-size :13px`;
   }
}}

${({bold, heavy}) => {
    switch(true){
       case bold: 
          return `font-weight :600`;
       case heavy: 
          return `font-weight :700`;
     
    }
 }}`

const NoDataFetched  = styled.View`
      margin-top : 20px;
      padding:32px;
      background-color : #fff;
      border-top-left-radius : 24px;
      border-top-right-radius : 24px;
`
const styles = StyleSheet.create({
  title: {
    
    marginTop:30,
    textAlign:'center',
    marginBottom:30,
    fontSize:30,
    fontFamily:'sans-serif',
    color:'green',
    fontWeight:'bold'
    
   //padding: 10
    
},
titles: {
    
  marginTop:30,
  textAlign:'center',
  marginBottom:30,
  fontSize:30,
  fontFamily:'sans-serif',
  color:'red',
  fontWeight:'bold'
  
 //padding: 10
  
},
chart : {
  marginTop:50
},

})



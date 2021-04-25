import React, { Component } from 'react'
import DatePicker from 'react-native-datepicker'
import { Text, View, Button,StyleSheet } from 'react-native'
import { connect } from 'react-redux'

export class MyDatePicker extends Component {
  
  constructor(props){
    super(props)
    this.state = {date:"2016-05-15",
                  time : "17:20",
                  selectedHours : 0,
                  selectedMinutes :0
                 }

  }
 
  render(){
    return (
      <View>
      {this.props.text === "Departure time" ?<Text>Departure time : </Text> : <Text>Departure time Flexibility: </Text> }     
      <DatePicker
        style={{width: 200}}
        date={this.state.date}
        time ={this.state.time}
        mode="datetime"
        placeholder="select date"
        format="YYYY-MM-DD HH:MM"
        minDate="2016-05-01"
        maxDate="2016-06-01"
        confirmBtnText="Confirm"
        cancelBtnText="Cancel"
        customStyles={{
          dateIcon: {
            position: 'absolute',
            left: 0,
            top: 4,
            marginLeft: 0
          },
          dateInput: {
            marginLeft: 36
          }
          // ... You can check the source to find the other keys.
        }}
        onDateChange={(date) => {this.setState({date: date}),
                                 this.props.dates = date
      }}
      />


    </View>
  
    )
  }
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
    padding: 20,
  },
});

const mapStateToProps = (state) => {
  const { needed } = state;
  return {
      date : needed,
  }
}

export default connect(mapStateToProps)(MyDatePicker)
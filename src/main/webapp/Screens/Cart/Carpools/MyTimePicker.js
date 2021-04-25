// React Native Time Picker – To Pick the Time using Native Time Picker
// https://aboutreact.com/react-native-timepicker/

// import React in our code
import React, {useState} from 'react';

// import all the components we are going to use
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';

//import TimePicker from the package we installed
import {TimePicker} from 'react-native-simple-time-picker';

const MyTimePicker = () => {
  const [selectedHours, setSelectedHours] = useState(0);
  const [selectedMinutes, setSelectedMinutes] = useState(0);

  return (
  
        <View style={styles.container}>
        <Text>
          Selected Time: {selectedHours}:{selectedMinutes}
        </Text>

        <TimePicker
          selectedHours={selectedHours}
          //initial Hourse value
          selectedMinutes={selectedMinutes}
          //initial Minutes value
          onChange={(hours, minutes) => {
            setSelectedHours(hours);
            setSelectedMinutes(minutes);
          }}
        />
     </View>
   
  );
};

export default MyTimePicker;

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
import React from "react";
import { View } from "react-native";
import { ScrollView } from "react-native-gesture-handler";

import MyBarChart from "./MyBarChart";

export default class MyChart extends React.Component {
  render() {
    const { navigate } = this.props.navigation;
    return (
      <ScrollView>
        <View style={{ backgroundColor: "#FFF", height: "100%" }}>
          <MyBarChart />
        </View>
      </ScrollView>
    );
  }
}

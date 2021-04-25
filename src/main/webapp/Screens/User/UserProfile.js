import React, { useContext, useState, useCallback } from "react";
import {
  View,
  Text,
  ScrollView,
  Button,
  StyleSheet,
  Dimensions,
} from "react-native";
import { Container } from "native-base";
import { useFocusEffect } from "@react-navigation/native";
import AsyncStorage from "@react-native-community/async-storage";
import OrderCard from "../../Shared/OrderCard";
import { Input } from "react-native-elements";

import axios from "axios";
import baseURL from "../../assets/common/baseUrl";

import AuthGlobal from "../../Context/store/AuthGlobal";
import { logoutUser } from "../../Context/actions/Auth.actions";
import { useEffect } from "react/cjs/react.development";

import RNPickerSelect from "react-native-picker-select";

const UserProfile = (props) => {
  const context = useContext(AuthGlobal);
  const [userProfile, setUserProfile] = useState();
  const [orders, setOrders] = useState();
  const [language, setLanguage] = useState("");
    useFocusEffect(
        useCallback(() => {
        if (
            context.stateUser.isAuthenticated === false || 
            context.stateUser.isAuthenticated === null
        ) {
            props.navigation.navigate("Login")
        }

        AsyncStorage.getItem("jwt")
            .then((res) => {
                console.log(res),
                axios
                    .get('https://ecosmart-atos.herokuapp.com/currentUser', {
                        headers: { Authorization: `Bearer ${res}` },
                        
                    })
                    .then((user) => {setUserProfile(user.data),
                       console.log(user.data)
                       console.log("hey")
                    })
            })
            .catch((error) => console.log(error)
            )



        return () => {
            setUserProfile();
           
        }

    }, [context.stateUser.isAuthenticated]))

    return (
        <Container style={styles.container}>
          <View style={styles.signout}>
            <Button
              title={"Sign Out"}
              onPress={() => [
                AsyncStorage.removeItem("jwt"),
                logoutUser(context.dispatch),
              ]}
            />
          </View>
          <View style={styles.stats}>
            <Button
              color="red"
              title={"My Statistics"}
              onPress={() => props.navigation.navigate("MyChart")}
            />
          </View>
         
          <ScrollView contentContainerStyle={styles.subContainer}>
          <Text>Welcome to EcoSmart</Text>
          <Text>Here is your profile</Text>
            <View style={styles.info}>
              <Text style={styles.title}>Username</Text>
              <View style={styles.detailsContainer}>
                <Text style={styles.details}>
                  {userProfile ? userProfile.firstName + userProfile.lastName : " "}
                </Text>
              </View>
              <Text style={styles.title}>Email</Text>
              <View style={styles.detailsContainer}>
                <Text style={styles.details}>
                  {userProfile ? userProfile.email : " "}
                </Text>
              </View>
         
            </View>
    
       
          </ScrollView>
        </Container>
      );
    };

    const styles = StyleSheet.create({
        container: {
          marginTop: -60,
          flex: 1,
          alignItems: "center",
          // backgroundColor: "red",
        },
        subContainer: {
          alignItems: "center",
          marginTop: 60,
          // backgroundColor: "blue",
          width: Dimensions.get("window").width,
        },
        signout: {
          position: "absolute",
          bottom: 0,
          left: Dimensions.get("window").width/8,
          zIndex: 1
        },
        stats: {
          position: "absolute",
          bottom: 0,
          right: Dimensions.get("window").width/8,
          zIndex: 1
        },
        username: {
          fontSize: 20,
        },
        info: {
          marginTop : 100,
          marginLeft: 40,
          display: "flex",
          width: Dimensions.get("window").width,
          justifyContent: "space-around",
          alignItems: "flex-start",
        },
        title: {
          margin: 20,
          fontSize: 15,
          fontWeight: "bold",
          color: "rgb(53, 166, 89)",
        },
        detailsContainer: {
          backgroundColor: "rgba(168, 165, 155, 0.17)",
          width: Dimensions.get("window").width - 100,
          marginLeft: 25,
          borderRadius: 20,
          borderColor: "grey", // if you need
          borderWidth: 1,
          overflow: "hidden",
          shadowColor: "grey",
          shadowRadius: 10,
          shadowOpacity: 1,
        },
        details: {
          margin: 10,
          textAlign: "center",
          color: "rgba(61, 64, 62, 0.7)",
          fontWeight: "bold",
          fontSize: 12
        },
      
        choice: {
          marginTop: 30,
          marginRight: 50,
          marginLeft: 50,
          marginBottom: 20,
          textAlign: "center",
          color: "rgb(53, 110, 196)",
          fontWeight: "bold",
        },
        pickerContainer: {
          borderColor: 'rgba(12, 49, 122, 0.5)',
         borderWidth: 2,
         borderStyle: 'solid',
          padding: 10,
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          marginBottom: 50,
          borderRadius: 20
        },
        wheel: {
          width: Dimensions.get("window").width - 10,
        },
        orders: {
          alignItems: "center",
        },
        noOrder: {
          marginTop: 20,
          marginLeft: Dimensions.get("window").width / 2 - 70,
        },
        noOrderText: {
          color: "grey",
        },
      });

export default UserProfile;
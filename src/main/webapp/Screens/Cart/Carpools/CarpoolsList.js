import React, { useState, useCallback } from 'react'
import {Image, StyleSheet, View,SafeAreaView, StatusBar,ScrollView} from 'react-native'
import styled from 'styled-components'
import {AntDesign} from '@expo/vector-icons'
import { useFocusEffect } from '@react-navigation/native'
import axios from 'axios';
import AsyncStorage from "@react-native-community/async-storage"

const recipes = [
    {
        name : "Marjane",
        info : "Places left : 2",
        date : "today at : 17h30",
        dateFlex : "17h45",
        driver : "Sam Eman",
        image : require("../../../assets/pic1.jpeg")
    },
    {
        name : "Kitea",
        info : "Places left : 1",
        date : "today at : 17h30",
        dateFlex : "17h45",
        driver : "Zineb Oujilali",
        image : require("../../../assets/pic2.jpeg")
    },
    {
        name : "Ikea",
        info : "Places left : 3",
        date : "today at : 17h30",
        dateFlex : "17h45",
        driver : "Khalil Oujilali",
        image : require("../../../assets/pic1.jpeg")
    },
   

   
]
const CarpoolsList = (props) => {

    const [refreshPage, setRefreshPage] = useState("");

    const [carpools, setCarpools] = useState([]);
    const Bold = ({ children }) => <Text style={{ fontWeight: 'bold' }}>{children}</Text>

  const    ComponentWillMount = () => { useFocusEffect ((
    
        useCallback(
          () => {
           // setFocus(false);
           // setActive(-1);
            
            // Carpools
            AsyncStorage.getItem("jwt")
            .then((res) => {
                console.log(res),
                axios
                     .get('https://ecosmart-atos.herokuapp.com/carpools/availableCarpools', {
                        headers: { Authorization: `Bearer ${res}` },
                     })
                     .then((response) =>{
                        // console.log(response),
                         setCarpools(response.data)
                     })
                     .catch( (error) => {
                         console.log(error + "here")
                     })
            })
            .catch((error) => {
                    console.log(error + "jwt")

            })
            return () => {
                setCarpools([]);              
              };
            },
            [],
        )
    ))
        }
    const participate = (carpoolId) =>{
        console.log("starting");
        console.log(carpoolId)
        let url = `https://ecosmart-atos.herokuapp.com/carpools/participate/${carpoolId}`;
        AsyncStorage.getItem("jwt")
        .then((res) => {
            console.log('hhh'),
            axios
                .post(url,{},
                  
                {
                    headers: { Authorization: `Bearer ${res}` },
                    
                })
                .then(() => {console.log("you participated "),window.location.reload();})
              
        })
        .catch((error) => console.log(error))
       
    }    
      
      
    return(
        ComponentWillMount(),
<ScrollView>
   <Container>
     <StatusBar barStyle="light-content"/>
     <RecipeBackground source ={require("../../../assets/carpool.jpeg")}>
<SafeAreaView>
    <MenuBar>
    <Back>
        <AntDesign name = "arrowleft" size ={24} color ="#FFF"/>
        <Text bold style = {{marginLeft : 10}}>Carpools</Text>
    </Back>
    <AntDesign name = "heart" size ={24} color ="#FFF"  />
    </MenuBar>
    <MainRecipe>
        <Text title heavy >Spicy Shrimp</Text>
        <Divider  />
        <Text bold>50cal per g</Text>
        <Text>3g fat | 10 g protein | 8g carbon</Text>
    </MainRecipe>
   
</SafeAreaView>

     </RecipeBackground>
     <RecipesContainer>
         <Text dark heavy large>Available Carpools for today</Text>
         <Text small> Click participate to get a place in the carpool</Text>
         <Recipes>
            {carpools.map((carpool,index)=>{
              //  console.log(carpool);
                return(
                    <Recipe key ={index}>
                        
                        <RecipeInfo>
                        <ButtonS>
                                    <Text bold small>  {carpool.arrivalLocation.name}</Text>
                            </ButtonS>
                            
                            <Text dark small> <Bold>Places left : </Bold>{carpool.totalPlaces}  {carpool.carpool_id}</Text>
                            <Text dark small> <Bold>Today At :</Bold> {carpool.departureTime.substring(11, 16)} | <Bold> Waiting till :</Bold>  {carpool.departureTimeFlexibility.substring(11, 16)}</Text>
                            <Text dark small> <Bold>Pets : </Bold> Yes <Bold> | Smoking : </Bold> No</Text> 
                            
                            <ButtonC >
                                    <Text bold small>Call Driver</Text>
                             </ButtonC >
                             <ButtonP onPress= {() => { participate(carpool.carpool_id)}}>
                                    <Text bold small>Participate</Text>
                             </ButtonP>
                        </RecipeInfo>
                        <AntDesign name = "heart" siwe={18} color ="#fff" />
                    </Recipe>
                )
            })}
         </Recipes>
     </RecipesContainer>
   </Container>
   </ScrollView>  

    );
    
        }
const Container = styled.View`
      flex : 1;
      background-color : #FFF;
    
`
const Text  = styled.Text`
color : #000                    ;         


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
 }}
`

const RecipeBackground = styled.ImageBackground`
      width : 100%;
      height : 150px;
      
      
`
const MenuBar = styled.View`
      flex-direction : row;
      justify-content : space-between;
      padding : 16px;
      
`
const Back = styled.View`
flex-direction : row;
align-items : center;
      
`
const MainRecipe = styled.View`
padding : 0 32px;
margin : 200px 0 32px 0;
      
`
const Divider  = styled.View`
      border-bottom-color : #fff;
      border-bottom-width: 2px;
      width: 150px;
      margin: 8px 0;
`

const ButtonC  = styled.TouchableOpacity`
margin : 20px 0 48px 32px;
background-color : rgba(255, 255, 120, 0.3);
align-self: flex-start;
padding: 6px 18px
border-radius: 100px;
      
`
const ButtonP  = styled.TouchableOpacity`
margin :  -80px 0 48px 140px;
background-color : rgba(255, 255, 120, 0.3);
align-self: flex-start;
padding: 6px 6px
border-radius: 100px;
      
`
const ButtonS  = styled.TouchableOpacity`
margin :  0 0 10px 0;
background-color : rgba(145, 85, 77, 0.3);

padding: 6px 6px
border-radius: 100px;
      
`
const RecipesContainer  = styled.View`
      margin-top : -24px;
      padding:32px;
      background-color : #fff;
      border-top-left-radius : 24px;
      border-top-right-radius : 24px;
`
const Recipes  = styled.View`
 margin-top : 16px;

      
`
const Recipe = styled.View`
      flex-direction : row;
      align-items : center;
      margin-bottom : 16px;
`

const RecipeImage  = styled.Image`

      width : 60px;
      height : 60px;
      border-radius: 10px; 
      margin-bottom : 80px;
`
const RecipeInfo  = styled.View`
      flex: 1;
      margin-left : 12px;
`
const PressableButton = ({ onPress }) => (
    <ButtonP onPress={onPress} bgColor={bgColor}>
     
    </ButtonP>
  );

/*
const  = styled.`
      
`
 /*${(props) => {props.dark? "#000" : "#fff"}};

  */  
 export default CarpoolsList;
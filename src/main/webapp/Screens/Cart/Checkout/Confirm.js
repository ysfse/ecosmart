import React,{useState} from 'react'
import { View, StyleSheet, Dimensions, ScrollView, Button } from 'react-native'
import {
    Text,
    Left,
    Right,
    ListItem,
    Thumbnail,
    Body
} from 'native-base'
import { connect } from 'react-redux'
import * as actions from '../../../Redux/Actions/cartActions'

import Toast from "react-native-toast-message"
import axios from "axios"
import baseURL from "../../../assets/common/baseUrl"
import AsyncStorage from "@react-native-community/async-storage"

var { width, height } = Dimensions.get('window')


const Confirm = (props) => {
    const[token, setToken] = useState();
    const[productQuantity,setProductQuantity] = useState();
    const finalOrder = props.route.params;
    
const getToken = () =>{
    AsyncStorage.getItem("jwt")
        .then((res) => {
            setToken(res)
        })
        .catch((error) => console.log(error))
           
};

    function confirmOrder() {
        AsyncStorage.getItem("jwt")
        .then((res) => ({
            "quantities": finalOrder.order.order.orderItems.map(x => ({
                "key": x.product.product_id, 
                "values": 1
            })),
            "token": res
        }))
        .then(x =>  {
            console.log("quantities: ", x.quantities)
            axios.post('http://192.168.1.2:8092/commands/add', {
                "products" : x.quantities,
                "shippingAddress1": finalOrder.order.order.shippingAddress1,
                "shippingAddress2": finalOrder.order.order.shippingAddress2 ,
                "city": finalOrder.order.order.city ,
                "ZipCode": finalOrder.order.order.zip,            
                "country": finalOrder.order.order.country,
            }, 
            {
                "headers": { Authorization: `Bearer ${x.token}` },
            })
            props.navigation.navigate("User Profile")
        })
        .catch((error) => console.log(error))
  }
  
 /*   const confirmOrder = () => {
    
        const order = finalOrder.order.order;
        console.log(order);
        AsyncStorage.getItem("jwt")
        .then((res) => {
           
            axios
                .post('http://192.168.1.2:8092/commands/add',{
                    shippingAddress1 : finalOrder.order.order.shippingAddress1,
                    shippingAddress2 :finalOrder.order.order.shippingAddress2 ,
                    city :finalOrder.order.order.city ,
                    ZipCode : finalOrder.order.order.zip,
                   // productQuantity : order.map((x) => {x.product.product_id}),                  
                    country:finalOrder.order.order.country,
                  },
                {
                    headers: { Authorization: `Bearer ${res}` },
                    
                })
                .then((res) => {
                    if (res.status == 200 || res.status == 201) {
                        Toast.show({
                            topOffset: 60,
                            type: "success",
                            text1: "Order Completed",
                            text2: "",
                        })
                        setTimeout(() => {
                            props.clearCart();
                            props.navigation.navigate("Cart")
                        }, 500)
                    }
                })
                .catch((error) => {
                    Toast.show({
                        topOffset: 60,
                        type: "error",
                        text1: "Something went wrong",
                        text2: "Please try again",
                    })
                })
              
        })
        .catch((error) => console.log(error))
     
    }
    */    
    
    
    return(
        <ScrollView contentContainerStyle={styles.container}>
            <View style={styles.titleContainer}>
                <Text style={{ fontSize: 20, fontWeight: 'bold'}}>
                    Confirm Order
                </Text>
                {props.route.params ? 
                <View style={{ borderWidth: 1, borderColor: 'orange'}}>
                    <Text style={styles.title}>Shipping to:</Text>
                    <View style={{ padding: 8 }}>
                        <Text>Address: {finalOrder.order.order.shippingAddress1}</Text>
                        <Text>Address2: {finalOrder.order.order.shippingAddress2}</Text>
                        <Text>City: {finalOrder.order.order.city}</Text>
                        <Text>Zip Code: {finalOrder.order.order.zip}</Text>
                        <Text>Country: {finalOrder.order.order.country}</Text>
                    </View>
                    <Text style={styles.title}>Items:</Text>
                    {finalOrder.order.order.orderItems.map((x) => {
                        return (
                            <ListItem
                                style={styles.listItem}
                                key={x.product.libelle_product}
                                avatar
                            >
                                <Left>
                                    <Thumbnail source={{ uri: x.product.imageUrl}}/>
                                </Left>
                                <Body style={styles.body}>
                                    <Left>
                                        <Text>{x.product.libelle_product}</Text>
                                    </Left>
                                    <Right>
                                        <Text>$ {x.product.price_product}</Text>
                                    </Right>
                                </Body>
                            </ListItem>
                        )
                    })}
                </View>    
           : null }
           <View style={{ alignItems: 'center', margin: 20 }}>
                <Button title={'Place order'} onPress={confirmOrder,  Toast.show({
                    topOffset: 60,
                    type: "success",
                    text1: `Offer Carpool added successfully`,
                    text2: "Thanks for your contribution"
                })}/>
           </View>
            </View>
        </ScrollView>
    )
}

const mapDispatchToProps = (dispatch) => {
    return{
        clearCart: () => dispatch(actions.clearCart())
    }
}

const styles = StyleSheet.create({
    container: {
        height: height,
        padding: 8,
        alignContent: 'center',
        backgroundColor: 'white',
    },
    titleContainer: {
        justifyContent: 'center',
        alignItems: 'center',
        margin: 8,
        
    },
    title: {
        alignSelf: 'center', 
        margin: 8, 
        fontSize: 16,
        fontWeight: 'bold' 
    },
    listItem: {
        alignItems: 'center',
        backgroundColor: 'white',
        justifyContent: 'center',
        width: width / 1.2
    },
    body: {
        margin: 10,
        alignItems: 'center',
        flexDirection: 'row'
    }
})

export default connect(null, mapDispatchToProps)(Confirm);
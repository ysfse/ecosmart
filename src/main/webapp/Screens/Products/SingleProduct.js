import React, { useState, useEffect } from 'react'
import { Image, View, StyleSheet, Text, ScrollView, Button, TouchableOpacity} from 'react-native';
import { Left, Right, Container, H1 } from 'native-base';
import { Icon } from 'react-native-elements';
import Toast from 'react-native-toast-message';
import EasyButton from '../../Shared/StyledComponents/EasyButton'
import TrafficLight from '../../Shared/StyledComponents/TrafficLight'
import MaterialIcons from 'react-native-vector-icons/MaterialIcons'

import { connect } from 'react-redux';
import * as actions from '../../Redux/Actions/cartActions';
import {addToCart} from  '../../Redux/Actions/cartActions';

const SingleProduct = (props) => {

    const [item, setItem] = useState(props.route.params.item);
    const [availability, setAvailability] = useState(null);
    const [availabilityText, setAvailabilityText] = useState("")

    useEffect(() => {
        if (props.route.params.item.countInStock == 0) {
            setAvailability(<TrafficLight unavailable></TrafficLight>);
            setAvailabilityText("Unvailable")
        } else if (props.route.params.item.countInStock <= 5) {
            setAvailability(<TrafficLight limited></TrafficLight>);
            setAvailabilityText("Limited Stock")
        } else {
            setAvailability(<TrafficLight available></TrafficLight>);
            setAvailabilityText("Available")
        }

        return () => {
            setAvailability(null);
            setAvailabilityText("");
        }
    }, [])

    return (
        <Container style={styles.container}>
            <ScrollView style={{ marginBottom: 80, padding: 5 }}>
                <View style={styles.imageContainer}>
                    <Image 
                        source={{
                            uri: "" ? item.image_url
                            : 'https://cdn.pixabay.com/photo/2012/04/01/17/29/box-23649_960_720.png'
                        }}
                        resizeMode="contain"
                        style={styles.image}
                    />
                </View>
                <View style={styles.contentContainer}>
                    <H1 style={styles.contentHeader}>{item.libelle_product}</H1>
                    <View style={{display: "flex", flexDirection: "row", marginTop: 10}}>
                        <Text style={styles.contentText}>{item.carbonBalance.co2Emission}</Text>
                        <TrafficLight style={{ marginLeft: 20}} available></TrafficLight>
                    </View>
        <View style={{
            marginTop: 20,
            paddingVertical: 6,
            paddingHorizontal: 10,
            flexDirection: "row",
            justifyContent: "space-between",
            alignItems: "center"
        }}>
                    <Text style={styles.contentText}>Marjane</Text>
                    <MaterialIcons
  raised
  name='shop'
  style={{fontSize: 30, marginLeft: 12}}
  type='font-awesome'
  color='lightblue'
  onPress={() => console.log('hello')} /></View>

                 
                 <TouchableOpacity onPress={() => this.quantityHandler('less', i)} style={{ borderWidth: 1, borderColor: '#cccccc' }}>
												{/* <MaterialIcons name="remove" size={22} color="#cccccc" /> */}
											</TouchableOpacity>
											<Text style={{ borderTopWidth: 1, borderBottomWidth: 1, borderColor: '#cccccc', paddingHorizontal: 7, paddingTop: 3, color: '#bbbbbb', fontSize: 13 }}>{item.qty}</Text>
											<TouchableOpacity onPress={() => this.quantityHandler('more', i)} style={{ borderWidth: 1, borderColor: '#cccccc' }}>
												{/* <MaterialIcons name="add" size={22} color="#cccccc" /> */}
											</TouchableOpacity>
					</View>
									
                <View style={styles.availabilityContainer}>
                    <View style={styles.availability}>
                        <Text style={{ marginRight: 10, fontWeight: "bold"}}>
                            Availability: 
                        </Text>
                        <Text style={{ marginRight: 10 }}>
                           {availabilityText}
                        </Text>
                        {/* {availability} */}
                    </View>
                    {/* <Text>{item.libelle_product}</Text> */}
                </View>
            </ScrollView>

            <View style={styles.bottomContainer}>
                <Left>
                    <Text style={styles.price}>€ {item.price_product}</Text>
                   
                </Left>
                 
                <Right>
                   <EasyButton 
                   secondary
                   medium
                   onPress={() => {props.addToCart(item),
                        Toast.show({
                            topOffset: 60,
                            type: "success",
                            text1: `${item.libelle_product} added to Cart`,
                            text2: "Go to your cart to complete order"
                        })
                }}
                   >
                       <Text style={{ color: 'white'}}>Add to Cart</Text>
                   </EasyButton>
                  
                </Right>
            </View>
        </Container>
    )

}
const addT = cartItem => {
    let cart = this.state.cart;
    if (cart[cartItem.id]) {
      cart[cartItem.id].amount += cartItem.amount;
    } else {
      cart[cartItem.id] = cartItem;
    }
    if (cart[cartItem.id].amount > cart[cartItem.id].product.stock) {
      cart[cartItem.id].amount = cart[cartItem.id].product.stock;
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    this.setState({ cart });
  };

const mapToDispatchToProps = (dispatch) => {
    return {
        addToCart: (product) => 
            dispatch(actions.addToCart({ product}))
    }
}

/*const mapToDispatchToProps = (dispatch) => {
    return {
        addToCart: (product) => dispatch(addToCart(product))
    }
}*/

const styles = StyleSheet.create({
    container: {
        position: 'relative',
        height: '100%'
    },
    imageContainer: {
        backgroundColor: 'white',
        padding: 0,
        margin: 0,
        display: "flex",
        alignItems: "center",
        justifyContent: "center"
        
    },
    image: {
        width: '80%',
        height: 250
    },
    contentContainer: {
        marginTop: 20,
        justifyContent: 'center',
        alignItems: 'center'

    },
    contentHeader: {
        fontWeight: 'bold',
        fontSize: 18,

    },
    contentText: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    bottomContainer: {
        flexDirection: 'row',
        position: 'absolute',
        bottom: 0,
        left: 0,
        backgroundColor: 'rgba(127, 135, 127,.3)'
    },
    price: {
        fontSize: 24,
        margin: 20,
        color: 'rgba(127, 135, 127,1)'
    },
    availabilityContainer: {
        marginBottom: 20,
        alignItems: "center"
    },
    availability: {
        flexDirection: 'row',
        marginBottom: 10,
    },
    Button:{
        marginTop:20,
        marginBottom:20
    }
})

export default connect(null, mapToDispatchToProps)(SingleProduct);
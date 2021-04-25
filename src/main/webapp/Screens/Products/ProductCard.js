import React from 'react'
import {
    StyleSheet,
    View,
    Dimensions,
    Image,
    Text,
    Button
} from 'react-native'
import Toast from 'react-native-toast-message'
import EasyButton from "../../Shared/StyledComponents/EasyButton"
import { connect } from 'react-redux'
import * as actions from '../../Redux/Actions/cartActions';

var { width } = Dimensions.get("window");

const ProductCard = (props) => {
    const { libelle_product, price_product, image_url, qte_stock,carbonBalance } = props;

    return (
        <View style={styles.container}>
            <Image 
            style={styles.image}
            resizeMode="contain"
            source={{uri: "" ? 
                image_url : 'https://cdn.pixabay.com/photo/2012/04/01/17/29/box-23649_960_720.png'}}
            />
            <View style={styles.card}/>
            <Text style={styles.title}>
                {libelle_product.length > 15 ? libelle_product.substring(0, 15 - 3)
                    + '...' : libelle_product
                }
            </Text>
            <Text style={styles.price}>€{price_product}</Text>
            <Text style={styles.price, {color: "rgb(25, 194, 88)"}}>{carbonBalance.co2Emission} Kg of CO2 per kg</Text>
            { qte_stock > 0 ? (
                <View style={{ marginBottom: 60 }}>
                    <EasyButton 
                    style={{marginTop: 20, backgroundColor: "rgb(157, 175, 194)"}}
                    secondary
                    medium
                    onPress={() => {
                        props.addItemToCart(props),
                        Toast.show({
                            topOffset: 60,
                            type: "success",
                            text1: `${libelle_product} added to Cart`,
                            text2: "Go to your cart to complete order"
                        })
                    }}
                    >
                        <Text style={{ fontWeight: "bold", color: "white"}}>Add to Cart</Text>
                    </EasyButton>
                </View>
            ) : <Text style={{ marginTop: 20 }}>Currently Unavailable</Text>}
        </View>
    )
}


const mapDispatchToProps = (dispatch) => {
    return {
        addItemToCart: (product) => 
            dispatch(actions.addToCart({product}))
    }
}

const styles = StyleSheet.create({
    container: {
        width: width/1.2 ,
        height: width / 1.5,
        padding: 10,
        borderRadius: 10,
        marginTop:55,
        marginBottom: 50,
        marginLeft: 10,
        alignItems: 'center',
        elevation: 8,
        backgroundColor: 'rgb(209, 226, 255)'
    },
    image: {
        width: width / 2 - 10 - 10,
        height: width / 2 - 20 - 30,
        backgroundColor: 'transparent',
        position: 'absolute',
        top: -45
    },
    card: {
        marginBottom: 8,
        height: width / 2 - 20 - 90,
        backgroundColor: 'transparent',
        width: width / 2 - 20 - 10
    },
    title: {
        fontWeight: "bold",
        fontSize: 14,
        textAlign: 'center'
    },
    price: {
        fontSize: 16,
        color: 'grey',
        marginTop: 10
    }
})

export default connect(null, mapDispatchToProps)(ProductCard);
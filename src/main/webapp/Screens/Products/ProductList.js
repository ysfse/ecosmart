import React from 'react';
import { TouchableOpacity, View, Dimensions } from 'react-native';

import ProductCard from './ProductCard'

var { width } = Dimensions.get("window");

const ProductList = (props) => {
    const { item } = props;
    return(
        <TouchableOpacity 
        onPress={() => 
            props.navigation.navigate("Product Detail", { item: item})
        }>
            <View>
            <ProductCard style={{display: "flex", width: "100%",flexDirection: "column", alignItems: "center"}} {...item} />
            </View>
        </TouchableOpacity>
    )
}

export default ProductList;
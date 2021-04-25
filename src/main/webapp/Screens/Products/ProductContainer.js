import React, { useState, useCallback } from "react";

import {
  View,
  StyleSheet,
  ActivityIndicator,
  FlatList,
  ScrollView,
  Dimensions,
 
} from "react-native";
import { Container, Header, Icon, Item, Input, Text } from "native-base";
import { useFocusEffect } from '@react-navigation/native'
import axios from 'axios';
import ProductList from "./ProductList";
import SearchedProduct from "./SearchedProducts";
import Banner from "../../Shared/Banner";
import CategoryFilter from "./CategoryFilter";
import baseURL from "../../assets/common/baseUrl";

var { height } = Dimensions.get('window')

const ProductContainer = (props) => {

  const [products, setProducts] = useState([]);
  const [productsFiltered, setProductsFiltered] = useState([]);
  const [focus, setFocus] = useState();
  const [categories, setCategories] = useState([]);
  const [productsCtg, setProductsCtg] = useState([]);
  const [productsS, setProductsS] = useState([]);
  const [active, setActive] = useState();
  const [initialState, setInitialState] = useState([]);
  const [loading, setLoading] = useState(true);
//  const[scrollY, setScrollY] = useState(new Animated.Value(0));
  const [loadMore, setLoadMore] = useState(false);
  const [page, setPage] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [SearchedText, setSearchedText] = useState("");
  const [Ordered, setOrdered] = useState(false)
  //this.scrollY = new Animated.value(0);

const _searchTextInputChanged = (text) => {
  setSearchedText(text)
}


const _loadProducts = () =>{
  //if ( SearchedText.length > 0) {
    setIsLoading( true ),
    //setTextInput(""),
    getProductsFromApiWithSearchedText(SearchedText)
    .then(data => {
       // this.page = data.page
       // this.totalPages = data.total_pages
       console.log(data),
        setProductsS(data),
        setIsLoading(false),
        setSearchedText("")
        //console.log(productsCtg);
    })
  //}
}
const getProductsFromApiWithSearchedText = (text) =>{
  const url = 'https://ecosmart-atos.herokuapp.com/products?keyword=' + text
      return fetch(url)        //recommanded to use for url search requests
 // .then((response) => response.json())   //make the response a json file
  .then((res) =>  res.json() )
  .catch((error) => console.log(error))
}

const ComponentWillMount = () =>{ useFocusEffect((
    
    useCallback(
      () => {
        setFocus(false);
        setActive(-1);
        
        // Products
      axios
          .get('https://ecosmart-atos.herokuapp.com/products')
         // .then((response) => response.json())
          .then((res) => {
            setProducts(res.data);
            setProductsFiltered(res.data);
            setProductsCtg(res.data);
            setInitialState(res.data);
            setLoading(false);
            setIsLoading(false);
            //added
            this.props.page = res.data.currentPage;
            this.props.totalPages = res.data.totalPages;
            props.SearchedText ="hey";
          })
          .catch((error) => {
            console.log('no')
          })
    
        // Categories
      axios
          .get('https://ecosmart-atos.herokuapp.com/category/all')
          .then((res) => {
            setCategories(res.data),
            console.log(this.props.SearchedText),
            console.log(categories)
          })
          .catch((error) => {
            console.log('Api call error')
          })
    
        return () => {
          setProducts([]);
          setProductsFiltered([]);
          setFocus();
          setCategories([]);
          setActive();
          setInitialState();
        };
      },
      [],
    )
  ))
    }
  
    

  // Product Methods
  const searchProduct = (text) => {
    setProductsFiltered(
      products.filter((i) => i.libelle_product.toLowerCase().includes(text.toLowerCase()))
    );
  };

  const openList = () => {
    setFocus(true);
  };

  const onBlur = () => {
    setFocus(false);
  };





  // Categories
  const changeCtg = (ctg) => {
    {
      ctg === "all"
        ? [setProductsCtg(initialState), setActive(true)]
        : [
            setProductsCtg(
              products.filter((i) => i.category.category_id === ctg),
              setActive(true)
            ),
          ];
    }
  };

  return (
    ComponentWillMount(),
    <>
    
    {loading == false ? (
 <Container style={styles.main}>
 <Header searchBar rounded style={{backgroundColor: "rgba(96, 142, 219, 0.3)", borderTopColor: "rgb(96, 209, 141)", borderTopWidth: 5, padding: 10}}>
   <Item style={{backgroundColor: "white", height: 35}}>
     <Icon name="ios-search"  />
     <Input
        clearButtonMode="always"
        placeholder="Find a product..."
        onFocus={openList}
        onChangeText={(text) => _searchTextInputChanged(text)}
        onSubmitEditing={() => {_loadProducts(), setOrdered(true)}}
     // onChangeText={(text) => searchProduct(text)}
     />
  
     {/* {focus == true ? <Icon onPress={onBlur} name="ios-close" /> : null} */}
   </Item>
 </Header>
 {focus == true ? (
   <>
   {/* <SearchedProduct 
   navigation={props.navigation}
   productsFiltered={productsFiltered} 
  
   />        */}
   <View>
     {Ordered == true 
     ? <Text style={{backgroundColor: "white", textAlign: "center", color: "rgba(0,0,0,0.3", padding: 10}}> Prioritize Choosing the first item of the list since it is the less harmful for the environment</Text> 
     : <Text style={{backgroundColor: "white", textAlign: "center", color: "rgba(0,0,0,0.3", padding: 10}}>    Make your search and get an ordered list from        the item with the lowest carbon emission to the     highest </Text> }
    
   <FlatList
   data ={productsS}
   contentContainerStyle={{display: "flex", alignItems: "center", backgroundColor: "rgb(209, 226, 255)"}}
   renderItem = {({item}) => (
    <ProductList
    navigation={props.navigation}
    key={item.name}
    item={item}

    keyExtractor={item.product_id}
    onEndReachedThreshold={0.5}
   
   /* onEndReached={() => {
      if (this.props.page < this.props.totalPages) { // On vérifie qu'on n'a pas atteint la fin de la pagination (totalPages) avant de charger plus d'éléments
         loadData()
      }
  }}*/
  
/>
   )}
 > 
 
 </FlatList>
 </View>
</>
 ) : (
 
     <View>
       
       {productsCtg.length > 0 ?
    <ScrollView> 
      <View>
       <Banner />
       </View>
       <View>
         <CategoryFilter
           categories={categories}
           categoryFilter={changeCtg}
           productsCtg={productsCtg}
           active={active}
           setActive={setActive}
         />
       </View>
       <FlatList 
        contentContainerStyle={{display: "flex", alignItems: "center", backgroundColor: "white"}}
         data ={productsCtg}
         renderItem = {({item}) => (
          <ProductList
          navigation={props.navigation}
          key={item.name}
          item={item}
          keyExtractor={item.product_id}
          onEndReachedThreshold={0.5}
         /* onEndReached={() => {
            if (this.props.page < this.props.totalPages) { // On vérifie qu'on n'a pas atteint la fin de la pagination (totalPages) avant de charger plus d'éléments
               loadData()
            }
        }}*/       
      />
         )}
       > 
       </FlatList>
       </ScrollView>  
       /* (
         <View style={styles.listContainer}>
           {productsCtg.map((item) => {
               return(
                   <ProductList
                       navigation={props.navigation}
                       key={item.name}
                       item={item}
                   />
               )
           })}
       </View>
       )*/ : (
           <View style={[styles.center, { height: height / 2}]}>
               <Text>No products found</Text>
           </View>
       )}
      
     </View>
   
 )}
</Container>
    ) : (
      // Loading
      <Container style={[styles.center, { backgroundColor: "#f2f2f2" }]}>
        <ActivityIndicator size="large" color="red" />
      </Container>
    )}
 
   </>
  );
};

const styles = StyleSheet.create({
  main: {
    paddingBottom: Dimensions.get("screen").height/16,
    backgroundColor: "rgba(189, 184, 172, 0.3)",
    width: Dimensions.get("screen").width
  },
  container: {
    flexWrap: "wrap",
    backgroundColor: "gainsboro",
  },
  listContainer: {
    height: height,
    flex: 1,
    flexDirection: "row",
    alignItems: "flex-start",
    flexWrap: "wrap",
    backgroundColor: "gainsboro",
   // marginBottom: 2000
  },
  center: {
      justifyContent: 'center',
      alignItems: 'center'
  }
});

export default ProductContainer;
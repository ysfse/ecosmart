import {
    ADD_TO_CART,
    REMOVE_FROM_CART,
    CLEAR_CART
} from '../constants';

const initialState = {
    products : [],
  };

const cartItems = (state =[], action) => {
    switch (action.type) {
        case ADD_TO_CART:
            console.log(state)
            //console.log(action.payload)
            return [...state, action.payload] 
            
        case ADD_TO_CART:

            return {
                ...state,
                products : state.map(product =>
                  product.product_id === state.product.product_id
                    ? {...product, quantity: product.quantity + 1}
                    : product,
                ),
             };

        case REMOVE_FROM_CART:
            return state.filter(cartItem => cartItem !== action.payload)
        case CLEAR_CART:
            return state = []
      
    } 
    return state;
}

export default cartItems;
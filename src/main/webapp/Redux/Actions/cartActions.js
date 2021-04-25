import {
    ADD_TO_CART,
    REMOVE_FROM_CART,
    CLEAR_CART
} from '../constants';



export const addToCart = (payload) => {
    return {
        type: ADD_TO_CART,
        payload
    }
}

/*export const subtractQuantity = id => {
    return {
      type: SUB_QUANTITY,
      id,
    };
  };

  export const addQuantity = id => {
    return {
      type: ADD_QUANTITY,
      id,
    };
  };*/

export const removeFromCart = (payload) => {
    return {
        type: REMOVE_FROM_CART,
        payload
    }
}

export const clearCart = () => {
    return {
        type: CLEAR_CART
    }
}
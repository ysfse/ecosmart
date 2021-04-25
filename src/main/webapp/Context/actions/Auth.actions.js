import jwt_decode from "jwt-decode"
import AsyncStorage from "@react-native-community/async-storage"
import Toast from "react-native-toast-message"
import baseURL from "../../assets/common/baseUrl"

export const SET_CURRENT_USER = "SET_CURRENT_USER";

export const loginUser = (user, dispatch) => {
    fetch('https://ecosmart-atos.herokuapp.com/auth/signin', {
        method: "POST",
        body: JSON.stringify(user),
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
    })
    .then((res) => res.json())
    .then((data) => {
        if (data) {
            const accessToken = data.accessToken;
            AsyncStorage.setItem("jwt", accessToken)
            const decoded = jwt_decode(accessToken)
            dispatch(setCurrentUser(decoded, user))
        } else {
           logoutUser(dispatch)
        }
    })
    .catch((err) => {
        console.log(err);
        Toast.show({
            topOffset: 60,
            type: "error",
            text1: "Please provide correct credentials",
            text2: ""
        });
        logoutUser(dispatch)
    });
};

export const getUserProfile = (id) => {
    fetch(`http://a3271c4b3edc.ngrok.io/api/v1/users/${id}`, {
        method: "GET",
        body: JSON.stringify(user),
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        },
    })
    .then((res) => res.json())
    .then((data) => console.log(data));
}

export const logoutUser = (dispatch) => {
    AsyncStorage.removeItem("jwt");
    dispatch(setCurrentUser({}))
}

export const setCurrentUser = (decoded, user) => {
    return {
        type: SET_CURRENT_USER,
        payload: decoded,
        userProfile: user
    }
}
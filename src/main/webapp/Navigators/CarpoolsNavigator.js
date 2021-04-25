import React from 'react'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { createStackNavigator } from "@react-navigation/stack"
// Screens
import Carpools from '../Screens/Cart/Carpools/Carpools'
import Payment from '../Screens/Cart/Carpools/Payment'
import Confirm from '../Screens/Cart/Carpools/Confirm';
import Cart from '../Screens/Cart/Cart';

const Tab = createMaterialTopTabNavigator();
const Stack = createStackNavigator();

function MyTabs() {
    return(
        <>
        <Tab.Navigator>
            <Tab.Screen name="Suggest a Carpool" component={Carpools} />
            <Tab.Screen name="Find a Carpool" component={Payment} />
            {/* <Tab.Screen name="Confirm" component={Confirm} /> */}
        </Tab.Navigator>
        <Stack.Screen 
                name="Cart"
                component={Cart}
                options={{
                    headerShown: false
                }}
            />
        </>
    );
}

export default function CarpoolsNavigator() {
    return <MyTabs />
}
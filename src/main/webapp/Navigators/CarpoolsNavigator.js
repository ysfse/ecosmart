import React from 'react'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'

// Screens
import Carpools from '../Screens/Cart/Carpools/Carpools'
import Payment from '../Screens/Cart/Carpools/Payment'
import Confirm from '../Screens/Cart/Carpools/Confirm';

const Tab = createMaterialTopTabNavigator();

function MyTabs() {
    return(
        <Tab.Navigator>
            <Tab.Screen name="Carpool" component={Carpools} />
            <Tab.Screen name="Carpool by shops" component={Payment} />
            <Tab.Screen name="Confirm" component={Confirm} />
        </Tab.Navigator>
    );
}

export default function CarpoolsNavigator() {
    return <MyTabs />
}
import React, { useState } from 'react'
import { View, Button } from 'react-native'
import {
    Container,
    Header,
    Content,
    ListItem,
    Text,
    Radio,
    Right,
    Left,
    Picker,
    Icon,
    Body,
    Title
} from 'native-base';
import CarpoolsList from './CarpoolsList';
import Carpools from './Carpools';

const methods = [
    { name: 'Cash on Delivery', value: 1 },
    { name: 'Bank Transfer', value: 2 },
    { name: 'Card Payment', value: 3}
]

const paymentCards = [
    { name: 'Wallet', value: 1 },
    { name: 'Visa', value: 2 },
    { name: 'MasterCard', value: 3},
    { name: 'Other', value: 4}
]
     // return the list of carpools (whole a priori)
const Payment = (props) => {

    const order = props.route.params;

    const [selected, setSelected] = useState();
    const [card, setCard] = useState();
    return(

        <CarpoolsList />
       /*<Container>
           <Header>
               <Body>
                   <Title>Marjane</Title>
               </Body>
           </Header>
           <Text>Sajed Soufiane      01/04    23:00      button-call  </Text>
        
        
       </Container>*/
    )
}

export default Payment;
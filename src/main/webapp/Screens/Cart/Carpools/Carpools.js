import React, { useEffect, useState, useContext} from 'react'
import { Text, View, Button,TouchableOpacity, Keyboard } from 'react-native'
import { Item, Picker } from 'native-base'
import Icon from 'react-native-vector-icons/FontAwesome'
import FormContainer from '../../../Shared/Form/FormContainer'
import Input from '../../../Shared/Form/Input'
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view'
import AuthGlobal from "../../../Context/store/AuthGlobal"
import MyDatePicker from './MyDatePicker'
import { connect } from 'react-redux'
import DateTimePicker from '@react-native-community/datetimepicker';
import MyTimePicker from './MyTimePicker'
import AsyncStorage from "@react-native-community/async-storage"
import axios from "axios"
import DatePicker from 'react-native-datepicker'
import DateTimePickerModal from "react-native-modal-datetime-picker"
import { GooglePlacesAutocomplete } from 'react-native-google-places-autocomplete';
import MapView from 'react-native-maps';
import Toast from 'react-native-toast-message';

const countries = require("../../../assets/countries.json");
const fuel =[ 
    {"name": "Gasoline", "code": "G"}, 
    {"name": "Diesel", "code": "E"}, 
];
const affirm =[ 
    {"name": "true", "code": "1"}, 
    {"name": "false", "code": "0"}, 
];

const Carpools = (props) => {
    const context = useContext(AuthGlobal)
    const [ orderItems, setOrderItems ] = useState();
    const [ DepartureTimeHour, setDepartureTimeHour ] = useState();
    const [ DepartureTimeFlexibility, setDepartureTimeFlexibility ] = useState();
    const [ StartLocation, setStartLocation ] = useState();
    const [ Pets, setPets ] = useState(false);
    const [HelperPets, setHelperPets] = useState();
    const [ country, setCountry ] = useState();
    const [  DepartureTimeDate, setDepartureTimeDate ] = useState();
    const [ EndLocation, setEndLocation ] = useState();
    const [ Smoking, setSmoking ] = useState(true);
    const [HelperSmoking, setHelperSmoking] = useState();
    const [ Places, setPlaces ] = useState();
    const [ CarType, setCarType ] = useState();
    const [ FuelType, setFuelType ] = useState("Gasoline");
    const [ FuelConsumptionPerKm, setFuelConsumptionPerKm ] = useState();
    const [ user, setUser ] = useState();
    const [token, setToken] = useState();
    const [date, setDate] = useState("2021-07-14")
    const [isDepartureTimeDateVisible, setDepartureTimeDateVisibility] = useState(false);
    const [isDepartureTimeHourVisible, setDepartureTimeHourVisibility] = useState(false);
    const [isDepartureTimeHourVisibleFlex, setDepartureTimeHourVisibilityFlex] = useState(false);
   
    const [distance, setDistance] = useState();
    const [originId, setOriginId] = useState();
    const [destinationId, setDestinationId] = useState();

    const showDepartureTimeDate = () => {
        setDepartureTimeDateVisibility(true);
        Keyboard.dismiss()
      };
    
    const hideDepartureTimeDate = () => {
        setDepartureTimeDateVisibility(false);
    };

    const showDepartureTimeHour = () => {
        setDepartureTimeHourVisibility(true);
        Keyboard.dismiss()
      };
    
    const hideDepartureTimeHour = () => {
        setDepartureTimeHourVisibility(false);
    };

    const showDepartureTimeHourFlex = () => {
        setDepartureTimeHourVisibilityFlex(true);
        Keyboard.dismiss()
      };
    
    const hideDepartureTimeHourFlex = () => {
        setDepartureTimeHourVisibilityFlex(false);
    };
    
    const handleDepartureTimeDate = (date) => {
        const _date = new Date(date.toString())
        setDepartureTimeDate(_date.toLocaleDateString())
        console.log(_date.toLocaleDateString())
        hideDepartureTimeDate();
      };

    const handletDepartureTimeHour = (date) => {
        const _date = new Date(date.toString())
        setDepartureTimeHour(_date.toLocaleTimeString())
        console.log(_date.toLocaleTimeString())
        hideDepartureTimeHour();
      };

    const handletDepartureTimeHourFlex = (date) => {
        const _date = new Date(date.toString())
        setDepartureTimeFlexibility(_date.toLocaleTimeString())
        console.log(_date.toLocaleDateString())
        hideDepartureTimeHourFlex();
      };
    const data = {
           "carpoolOffer" : {
            departTime: "2020-07-14 12:00",
            departTimeFlexibility : "2020-07-14 12:00",
            totalPlaces:Places,
            pets: Pets,
            smoking: Smoking,
            depart_address :StartLocation,
            arrival_address: EndLocation,
            fuelConsumptionPerKm:FuelConsumptionPerKm,
            fuel :FuelType
              }
        }
      const offer = () => {
            return  fetch(`https://maps.googleapis.com/maps/api/directions/json?origin=place_id:${originId}&destination=place_id:${destinationId}&key=AIzaSyD6f4JLPNt4Ek_x9-vSVzb-2oA8aXoMN6g`)
               .then( (response) =>response.json())
               .then( (responseJson) => { 
                   const d = responseJson.routes[0].legs[0].distance.value;
                  // console.log(responseJson.routes[0].legs[0].distance.value);
                   setDistance(responseJson.routes[0].legs[0].distance.value);
                   return d;
             }) 
             .then((d)=>{  
            console.log(d);
            let departdatetime = "20"+ DepartureTimeDate.substring(6,8)+"-"+DepartureTimeDate.substring(0,2)+"-"+DepartureTimeDate.substring(3,5)+" "+DepartureTimeHour.substring(0,5) ;
            let departdatetimeflex = "20"+ DepartureTimeDate.substring(6,8)+"-"+DepartureTimeDate.substring(0,2)+"-"+DepartureTimeDate.substring(3,5)+" "+DepartureTimeHour.substring(0,5);
            AsyncStorage.getItem("jwt")
            .then((res) => {
                console.log(res),
                axios
                    .post('https://ecosmart-atos.herokuapp.com/carpools/offer',{
                        departTime: departdatetime ,
                        departTimeFlexibility :departdatetimeflex,
                        totalPlaces:Places,
                        pets: Pets,
                        smoking: Smoking,
                        depart_address :StartLocation,
                        arrival_address: EndLocation,
                        routeDistance :d,
                        fuelConsumptionPerKm:FuelConsumptionPerKm,
                        fuel :FuelType
                      },
                    {
                        headers: { Authorization: `Bearer ${res}` },
                        
                    })
                    .then(() => {console.log("data is " + data)
                 
                })
                  
            })
            .catch((error) => console.log(error))
             } )
             .catch((error) =>{
                   console.log(error)
               }
               )
           };
   
    return (
        <KeyboardAwareScrollView
            viewIsInsideTabBar={true}
            extraHeight={200}
            enableOnAndroid={true}
        >
            <FormContainer title={"Offer a Carpool"}>
    
            <Input
                    placeholder={"Departure Time Date (DD-MM) "}
                    name={"dtd"}
                    value={DepartureTimeDate}
              //     keyboardType={"numeric"}
                  //  onChangeText={(text) => setDepartureTimeDate(text)}
                  onFocus={showDepartureTimeDate}
                /> 
                <DateTimePickerModal
                    isVisible={isDepartureTimeDateVisible}
                    mode="date"
                    onConfirm={handleDepartureTimeDate}
                    onCancel={hideDepartureTimeDate}
                />
                
                 <Input
                placeholder={"Departure Time Hour (DD-MM) "}
                name={"dth"}
                value={DepartureTimeHour}
             //   keyboardType={"numeric"}
            //    onChangeText={(text) => setDepartureTimeHour(text)}
                onFocus={showDepartureTimeHour}
            />
             <DateTimePickerModal
                    isVisible={isDepartureTimeHourVisible}
                    mode="time"
                    onConfirm={handletDepartureTimeHour}
                    onCancel={hideDepartureTimeHour}
                />
                  <Input
                    placeholder={"Departure Time Flexibility"}
                    name={"dtf"}
                    value={DepartureTimeFlexibility}
                    onFocus={showDepartureTimeHourFlex}
                />
             <DateTimePickerModal
                    isVisible={isDepartureTimeHourVisibleFlex}
                    mode="time"
                    onConfirm={handletDepartureTimeHourFlex}
                    onCancel={hideDepartureTimeHourFlex}
                />

<GooglePlacesAutocomplete
            placeholder="Origin"
            query={{
              key: 'AIzaSyD6f4JLPNt4Ek_x9-vSVzb-2oA8aXoMN6g',
              language: 'fr', // language of the results,
              components: 'country:fr',
              fields : 'place_id'
            }}
            onPress={(origin, details = null) => {
              
               setOriginId(origin.place_id);
               setStartLocation(origin.description);
               console.log(origin.place_id+ ' '+origin.description);
              
            }}
            onFail={(error) => console.error(error)}
            textInputProps={{
              InputComp: Input,
              leftIcon: { type: 'font-awesome', name: 'chevron-left' },
              errorStyle: { color: 'red' },
            }}
            requestUrl={{
              url:
                'https://maps.googleapis.com/maps/api',
              useOnPlatform: 'web',
            }}      /> 

<GooglePlacesAutocomplete
            placeholder="Destination"
            query={{
              key: 'AIzaSyD6f4JLPNt4Ek_x9-vSVzb-2oA8aXoMN6g',
              language: 'fr', // language of the results,
              components: 'country:fr',
              fields : 'place_id'
            }}
            onPress={(destination, details = null) => {
              //  console.log(destination.place_id+' '+destination.description),
              setDestinationId(destination.place_id);
              setEndLocation(destination.description);
              console.log(destination.place_id+ ' '+ destination.description);
            }}
            onFail={(error) => console.error(error)}
            textInputProps={{
              InputComp: Input,
              rightIcon: { type: 'font-awesome', name: 'chevron-right' },
              errorStyle: { color: 'red' },
            }}
            requestUrl={{
              url:
                'https://maps.googleapis.com/maps/api',
              useOnPlatform: 'web',
            }}  />
      
                     <Item picker>
                         <Text>Do you allow smoking ?         </Text>
                    <Picker
                        mode="dropdown"
                        iosIcon={<Icon name="arrow-down" color={"#007aff"} />}
                        style={{ width: undefined }}
                        selectedValue={Smoking}
                        placeholder={Smoking}
                        placeholderStyle={{ color: '#007aff' }}
                        placeholderIconColor="#007aff"
                        onValueChange={(e) => setSmoking(e)}
                    >
                        {affirm.map((c) => {
                            return <Picker.Item 
                                    key={c.code} 
                                    label={c.name}
                                    value={c.name}
                                    />
                        })}
                    </Picker>
                </Item>
                     <Item picker>
                         <Text>Do you allow pets in ?            </Text>
                <Picker
                        mode="dropdown"
                        iosIcon={<Icon name="arrow-down" color={"#007aff"} />}
                        style={{ width: undefined }}
                        selectedValue={Pets}
                        placeholder="Select your fuel type"
                        placeholderStyle={{ color: '#007aff' }}
                        placeholderIconColor="#007aff"
                        onValueChange={(e) =>setPets(e)}
                    >
                        {affirm.map((c) => {
                            return <Picker.Item 
                                    key={c.code} 
                                    label={c.name}
                                    value={c.name}
                                    />
                        })}
                    </Picker>
                </Item>
                  <Input
                    placeholder={"Places"}
                    name={"pla"}
                    value={Places}
                    keyboardType={"numeric"}
                    onChangeText={(text) => setPlaces(text)}
                />
            
            <Item picker>
                <Text>Chose You fuel type           </Text>
                    <Picker
                        mode="dropdown"
                        iosIcon={<Icon name="arrow-down" color={"#007aff"} />}
                        style={{ width: undefined }}
                        selectedValue={FuelType}
                        placeholder="Select your fuel type"
                        placeholderStyle={{ color: '#007aff' }}
                        placeholderIconColor="#007aff"
                        onValueChange={(e) => setFuelType(e)}
                    >
                        {fuel.map((c) => {
                            return <Picker.Item 
                                    key={c.code} 
                                    label={c.name}
                                    value={c.name}
                                    />
                        })}
                    </Picker>
                </Item>
            
                    <Input
                    placeholder={"Fuel Consumption per Km"}
                    name={"sfcp"}
                    value={FuelConsumptionPerKm}
                    keyboardType={"numeric"}
                    onChangeText={(text) => setFuelConsumptionPerKm(text)
                     }
                />
                      
                <TouchableOpacity onPress={offer(),
                   Toast.show({
                    topOffset: 60,
                    type: "success",
                    text1: `Offer Carpool added successfully`,
                    text2: "Thanks for your contribution"
                })}>

     <Text >Confirm</Text>
   </TouchableOpacity>
            </FormContainer>
        </KeyboardAwareScrollView>
    )
}

const mapStateToProps = (state) => {
    const { cartItems } = state;
    return {
        cartItems: cartItems,
    }
}

export default connect(mapStateToProps)(Carpools)
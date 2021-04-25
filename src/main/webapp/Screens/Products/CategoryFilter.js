import React from 'react';
import { StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import { ListItem, Badge, Text } from 'native-base';

const CategoryFilter = (props) => {

    return(
        <>
        <ScrollView
            bounces={true}
            horizontal={true}
            style={{ backgroundColor: "white" }}
        >
            <ListItem style={{ margin: 0, padding: 0, borderRadius: 0 }}>
                <TouchableOpacity
                    key={1}
                    onPress={() => {
                        props.categoryFilter('all'), props.setActive(-1)
                    }}
                >
                    <Badge
                        style={[styles.center, {margin: 5},
                            props.active == -1 ? styles.active : styles.inactive
                        ]}
                    >
                        <Text style={{ color: 'white' }}>All</Text>
                    </Badge>
                   
                </TouchableOpacity>
          
                {props.categories.sort((a,b) => a.category_id - b.category_id).map((item) => (
                    
                      <TouchableOpacity
                      key={item.category_id}
                      onPress={() => {
                          props.categoryFilter(item.category_id), 
                          props.setActive(props.categories.indexOf(item))
                      }}
                  >
                         <Badge
                          style={[styles.center, 
                            {margin: 5},
                            props.active == props.categories.indexOf(item) ? styles.active : styles.inactive,
                          //  console.log(props.categories),
                            item.category_id == 0 ? styles.second : {}
                          ]}
                      >
                          <Text style={{ color: 'white' }}>{item.libelle_category}</Text>
                      </Badge>
                   
                  </TouchableOpacity>
                ))}
            </ListItem>
        </ScrollView>
        {props.active == 0 ? <Text style={{backgroundColor: "rgb(245, 208, 159)", textAlign: "center", color: "rgba(0,0,0,0.6)", padding: 10}} >This category offers second-hand products and it's the best place to start to begin the fight against carbonization and pollution.</Text> : <></>}
        </>
    )
}

const styles = StyleSheet.create({
    center: {
        justifyContent: 'center',
        alignItems: 'center'
    },
    active: {
        backgroundColor: '#03bafc'
    },
    inactive: {
        backgroundColor: '#a0e1eb'
    },
    second: {
        backgroundColor: 'rgb(95, 232, 102)'
    }
})

export default CategoryFilter;
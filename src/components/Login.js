import React from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';

export default class Login extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <View style={styles.logoContainer}>
          <Image
            style={styles.logo} 
            source={require('../../assets/img/Vautour.png')}
          />
          <Text style={styles.title}>
            Stupides Vautours
          </Text>
        </View>
        <View style={styles.formContainer}>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#2980b9',
    alignItems: 'center',
    justifyContent: 'center',
  },
  logoContainer:{
    alignItems:'center',
    flexGrow:1,
    justifyContent:'center'
  },
  logo:{
    width:130,
    height:150
  },
  title:{
    color:'#FFFF',
    marginTop:10,
    textAlign:'center',
    width:130,
    opacity:0.8
  },
});

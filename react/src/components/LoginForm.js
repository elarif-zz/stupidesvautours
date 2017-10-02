import React from 'react';
import { StyleSheet, Text, View, TextInput } from 'react-native';

export default class LoginForm extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <TextInput
            placeholder='Username or Email'
            placeholderTextColor='rgba(255,255,255,0.7)' 
            style={styles.input}
        />
        <TextInput 
            placeholder='Password' 
            placeholderTextColor='rgba(255,255,255,0.7)'
            style={styles.input}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
    container:{
        padding:20
    },
    input:{
        height:40,
        backgroundColor:'rgba(255, 255, 255, 0.2)',
        marginBottom:20,
        color:'#FFF',
        paddingHorizontal:10
    }
});
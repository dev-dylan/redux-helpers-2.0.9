'use strict'

/**
 * @Author: lq
 * @Date: 2019-05-07
 * @Project:rn_navigation_redux
 */

import React, { Component } from 'react'
import { View, Text, TouchableOpacity } from 'react-native'
import { connect } from 'react-redux'
import NavigationUtil from '../../navigator/NavigationUtil'

const TAG = 'HomePage'

class HomePage extends Component {
  constructor(props) {
    super(props)
  }

  componentDidMount() {
    // console.log(TAG, 'this.props::', this.props)
  }

  render() {
    let { navigation } = this.props
    var testFunction = function () {
      var a = { name: 'zzz' }
      var b = { name: 'vvv' }
      a.child = b
      b.parent = a
    }
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>HomePage</Text>
        <TouchableOpacity
          onPress={() => {
            // navigation.pop('Init')
            navigation.navigate('DetailPage', {
              sensorsdataparams: {
                name: 'detail',
                $screen_name: 'test_screen',
                $title: 'test_title',
              },
              sensorsdataurl: 'test_url',
              fu: testFunction(),
              label:<Text>测试内容</Text>
            })
          }}
          style={{ marginTop: 20 }}
        >
          <Text style={{ color: 'red' }}>Go to DetailPage </Text>
        </TouchableOpacity>
      </View>
    )
  }
}

export default connect(({ nav }) => ({
  nav,
}))(HomePage)

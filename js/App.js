import React, { Component } from 'react'
import { Provider } from 'react-redux'
import AppNavigator from './navigator/AppNavigators'
import store from './store'
import { NativeModules, Text } from 'react-native'
const RNSensorsAnalyticsModule = NativeModules.RNSensorsAnalyticsModule

type Props = {}
export default class App extends Component<Props> {
  render() {
    const payload = {
      platform_type: 'android',
    }
    RNSensorsAnalyticsModule.registerSuperProperties(payload)
    /**
     * 将store传递给App框架
     */
    return (
      <Provider store={store}>
        <AppNavigator />
      </Provider>
    )
  }
}

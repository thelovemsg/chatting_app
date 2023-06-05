import { combineReducers } from '@reduxjs/toolkit';
import { userProfileReducer } from './userProfile';
import { userMultiProfileReducer } from './userMultiProfile';

const userReducer = combineReducers({
  profile: userProfileReducer,
  multiProfile: userMultiProfileReducer,
});

export default userReducer;

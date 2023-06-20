import { combineReducers } from '@reduxjs/toolkit';
import { chattingRoomReducer } from 'reducers/chatting/chattingReducer';
import { userProfileReducer } from './userProfile';
import { userMultiProfileReducer } from './userMultiProfile';
import { userBirthdayFriendsReducer } from './userBirthdayFriends';
import { userBookmarkFriendsReducer } from './userBookmarkFriends';
import { userFriendsReducer } from './userFriends';
import { userUpdateFriendsReducer } from './userUpdatedFriends';
import { userInfoReducer } from './userInfoSetting';

const userReducer = combineReducers({
  profile: userProfileReducer,
  multiProfile: userMultiProfileReducer,
  updateFriend: userUpdateFriendsReducer,
  birthday: userBirthdayFriendsReducer,
  bookmark: userBookmarkFriendsReducer,
  friends: userFriendsReducer,
  chattingRoom: chattingRoomReducer,
  info: userInfoReducer,
});

export default userReducer;

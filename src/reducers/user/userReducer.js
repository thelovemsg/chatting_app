import { combineReducers } from '@reduxjs/toolkit';
import { userProfileReducer } from './userProfile';
import { userMultiProfileReducer } from './userMultiProfile';
import { userBirthdayFriendsReducer } from './userBirthdayFriends';
import { userBookmarkFriendsReducer } from './userBookmarkFriends';
import { userFriendsReducer } from './userFriends';
import { userUpdateFriendsReducer } from './userUpdatedFriends';

const userReducer = combineReducers({
  profile: userProfileReducer,
  multiProfile: userMultiProfileReducer,
  updateFriend: userUpdateFriendsReducer,
  birthday: userBirthdayFriendsReducer,
  bookmark: userBookmarkFriendsReducer,
  friends: userFriendsReducer,
});

export default userReducer;

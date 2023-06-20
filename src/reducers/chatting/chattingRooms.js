import { combineReducers } from '@reduxjs/toolkit';
import { chattingRoomReducer } from './chattingReducer';

const chattingRoom = combineReducers({
  chattingRoom: chattingRoomReducer,
});

export default chattingRoom;

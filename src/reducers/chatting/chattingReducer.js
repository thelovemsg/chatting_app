import { createSlice } from '@reduxjs/toolkit';

export const chattingRoomSlice = createSlice({
  name: 'user/chattingRoom',
  initialState: {
    loading: false,
    error: null,
    success: false,
    length: 0,
    list: [],
  },
  reducers: {
    GET_USER_CHATTING_ROOM_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_CHATTING_ROOM_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list = action.payload;
      state.length = action.payload.length;
    },
    GET_USER_CHATTING_ROOM_FAILURE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

export const {
  GET_USER_CHATTING_ROOM_REQUEST,
  GET_USER_CHATTING_ROOM_SUCCESS,
  GET_USER_CHATTING_ROOM_FAILURE,
} = chattingRoomSlice.actions;

export const chattingRoomReducer = chattingRoomSlice.reducer;

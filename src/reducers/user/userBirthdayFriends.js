import { createSlice } from '@reduxjs/toolkit';

export const userBirthdayFriendsSlice = createSlice({
  name: 'user/birthday',
  initialState: {
    loading: false,
    error: null,
    success: false,
    length: 0,
    list: [],
  },
  reducers: {
    GET_USER_BIRTHDAY_FRIENDS_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_BIRTHDAY_FRIENDS_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list = action.payload;
      state.length = action.payload.length;
    },
    GET_USER_BIRTHDAY_FRIENDS_FAILUIRE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

export const {
  GET_USER_BIRTHDAY_FRIENDS_REQUEST,
  GET_USER_BIRTHDAY_FRIENDS_SUCCESS,
  GET_USER_BIRTHDAY_FRIENDS_FAILUIRE,
} = userBirthdayFriendsSlice.actions;

export const userBirthdayFriendsReducer = userBirthdayFriendsSlice.reducer;

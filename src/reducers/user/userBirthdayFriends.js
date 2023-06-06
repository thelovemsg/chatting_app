import { createSlice } from '@reduxjs/toolkit';

export const userBirthdayFriendsSlice = createSlice({
  name: 'user/birthdayProfile',
  initialState: {
    loading: false,
    error: null,
    success: false,
    list: [],
  },
  reducer: {
    GET_USER_BIRTHDAY_PROFILE_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_BIRTHDAY_PROFILE_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list = action.payload.birthdayFriends;
    },
    GET_USER_BIRTHDAY_PROFILE_RESPONSE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

export const {
  GET_USER_BIRTHDAY_PROFILE_REQUEST,
  GET_USER_BIRTHDAY_PROFILE_SUCCESS,
  GET_USER_BIRTHDAY_PROFILE_RESPONSE,
} = userBirthdayFriendsSlice.actions;

export const userBirthdayFriendsReducer = userBirthdayFriendsSlice.reducer;

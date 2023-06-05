import { createSlice } from '@reduxjs/toolkit';

export const userBirthdayProfileSlice = createSlice({
  name: 'user/birthdayProfile',
  initialState: {
    loading: false,
    error: null,
    success: false,
    list: [],
  },
  reducer: {
    GET_USER_BIRTHDAY_PROFILE_REQUEST: () => {},
    GET_USER_BIRTHDAY_PROFILE_SUCCESS: () => {},
    GET_USER_BIRTHDAY_PROFILE_RESPONSE: () => {},
  },
});

export const {
  GET_USER_BIRTHDAY_PROFILE_REQUEST,
  GET_USER_BIRTHDAY_PROFILE_SUCCESS,
  GET_USER_BIRTHDAY_PROFILE_RESPONSE,
} = userBirthdayProfileSlice.actions;

export const userBirthdayProfileReducer = userBirthdayProfileSlice.reducer;

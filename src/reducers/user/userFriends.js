import { createSlice } from '@reduxjs/toolkit';

export const userFriendsSlice = createSlice({
  name: 'user/friends',
  initialState: {
    loading: false,
    error: null,
    success: false,
    list: [],
  },
  reducers: {
    GET_USER_FRIENDS_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_FRIENDS_SUCCESS: (state, action) => {
      state.loading = false;
      state.list = action.payload?.friends;
    },
    GET_USER_FRIENDS_FAILURE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload?.code,
        message: action.payload?.message,
      };
    },
  },
});

export const {
  GET_USER_FRIENDS_REQUEST,
  GET_USER_FRIENDS_SUCCESS,
  GET_USER_FRIENDS_FAILURE,
} = userFriendsSlice.actions;

export const userFriendsReducer = userFriendsSlice.reducer;

import { createSlice } from '@reduxjs/toolkit';

export const userUpdateFriendsSlice = createSlice({
  name: 'user/updateFriend',
  initialState: {
    loading: false,
    error: null,
    success: false,
    length: 0,
    list: [],
  },
  reducers: {
    GET_USER_UPDATE_FRIENDS_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_UPDATE_FRIENDS_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list = action.payload;
    },
    GET_USER_UPDATE_FRIENDS_FAILURE: (state, action) => {
      state.loading = false;
      state.success = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  GET_USER_UPDATE_FRIENDS_REQUEST,
  GET_USER_UPDATE_FRIENDS_SUCCESS,
  GET_USER_UPDATE_FRIENDS_FAILURE,
} = userUpdateFriendsSlice.actions;

export const userUpdateFriendsReducer = userUpdateFriendsSlice.reducer;

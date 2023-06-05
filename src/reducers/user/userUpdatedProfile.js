import { createSlice } from '@reduxjs/toolkit';

export const userUpdateProfileSlice = createSlice({
  name: 'user/updateProfile',
  initialState: {
    loading: false,
    error: null,
    success: false,
    list: [],
  },
  reducers: {
    GET_USER_UPDATE_PROFILE_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_UPDATE_PROFILE_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list = state.list.filter(
        (profile) => profile.id !== action.payload.id // remove profile from the list
      );
    },
    GET_USER_UPDATE_PROFILE_FAILURE: (state, action) => {
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
  GET_USER_UPDATE_PROFILE_REQUEST,
  GET_USER_UPDATE_PROFILE_SUCCESS,
  GET_USER_UPDATE_PROFILE_FAILURE,
} = userUpdateProfileSlice.actions;

export const userUpdateProfileReducer = userUpdateProfileSlice.reducer;

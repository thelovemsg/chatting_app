import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    userInfo: {
      id: null,
      multiProfile: {
        isLoading: false,
        success: null,
        error: null,
        list: [],
      },
    },
  },
  reducers: {
    MULTI_PROFILE_REQUEST: (state) => {
      state.userInfo.multiProfile.isLoading = true;
    },
    MULTI_PROFILE_SUCCESS: (state) => {
      state.userInfo.multiProfile.isLoading = false;
    },
    MULTI_PROFILE_FAILURE: (state, action) => {
      state.userInfo.multiProfile.isLoading = false;
      state.userInfo.multiProfile.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

export const { MULTI_PROFILE_REQUEST } = userSlice.actions;

export const loginReducer = userSlice.reducer;

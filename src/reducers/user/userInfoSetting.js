import { createSlice } from '@reduxjs/toolkit';

export const userInfoSlice = createSlice({
  name: 'user/info',
  initialState: {
    loading: false,
    error: null,
    success: null,
    notiStatus: false,
  },
  reducers: {
    FLIP_USER_NOTI_STATUS_REQUEST: (state) => {
      state.loading = true;
    },
    FLIP_USER_NOTI_STATUS_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.notiStatus = action.payload;
    },
    FLIP_USER_NOTI_STATUS_FAILURE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

export const {
  FLIP_USER_NOTI_STATUS_REQUEST,
  FLIP_USER_NOTI_STATUS_SUCCESS,
  FLIP_USER_NOTI_STATUS_FAILURE,
} = userInfoSlice.actions;

export const userInfoReducer = userInfoSlice.reducer;

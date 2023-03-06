import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    loginHandling: false,
    loginDone: false,
    loginError: null,
  },
  reducers: {
    LOG_IN_REQUEST: (state) => {
      state.loginHandling = true;
    },
    LOG_IN_SUCCESS: (state) => {
      state.loginHandling = false;
      state.loginDone = true;
    },
    LOG_IN_FAILURE: (state) => {
      state.loginHandling = false;
    },
    LOG_OUT_REQUEST: (state) => {
      state.loginHandling = true;
    },
    LOG_OUT_SUCCESS: (state) => {
      state.loginHandling = false;
      state.loginDone = false;
    },
    LOG_OUT_FAILURE: (state) => {
      state.loginHandling = false;
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  LOG_IN_REQUEST,
  LOG_IN_SUCCESS,
  LOG_IN_FAILURE,
  LOG_OUT_REQUEST,
  LOG_OUT_SUCCESS,
  LOG_OUT_FAILURE,
} = userSlice.actions;

export const userReducer = userSlice.reducer;

import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    loginHandling: false,
    loginDone: false,
    logoutDone: false,
    loginError: null,
  },
  reducers: {
    LOG_IN_REQUEST: (state) => {
      state.loginHandling = true;
    },
    LOG_IN_SUCCESS: (state) => {
      state.loginHandling = false;
      state.loginDone = true;
      state.logoutDone = false;
    },
    LOG_IN_FAILURE: (state, action) => {
      state.loginHandling = false;
      state.loginError = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
    LOG_OUT_REQUEST: (state) => {
      state.loginHandling = true;
    },
    LOG_OUT_SUCCESS: (state) => {
      state.loginHandling = false;
      state.loginDone = false;
      state.logoutDone = true;
    },
    LOG_OUT_FAILURE: (state, action) => {
      // api 통신시 에러와 에러메시지 받음. default 처리
      state.loginHandling = false;
      state.loginError = {
        code: "0109", //action.payload.code,
        message: "testing", //action.payload.message
      };
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

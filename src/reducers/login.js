import { createSlice } from '@reduxjs/toolkit';

export const loginSlice = createSlice({
  name: 'login',
  initialState: {
    loginCheckHandling: false,
    loginCheckSuccess: false,
    loginCheckFailure: null,
    loginChecking: false,
    loginHandling: false,
    loginDone: false,
    logoutHandling: false,
    logoutDone: false,
    loginError: null,
    logoutError: null,
    registerHandling: false,
    registerDone: false,
    registerError: null,
    accessPermission: null,
  },
  reducers: {
    LOG_IN_CHECK_REQUEST: (state) => {
      state.loginCheckHandling = true;
    },
    LOG_IN_CHECK_FAILURE: (state, action) => {
      state.loginCheckFailure = {
        code: action.payload.code,
        message: action.payload.message,
      };
      state.loginCheckHandling = false;
    },
    LOG_IN_CHECK_SUCCESS: (state) => {
      state.loginCheckHandling = false;
      state.loginCheckSuccess = true;
    },
    LOG_IN_CHECK_DONE: (state) => {
      state.loginChecking = false;
    },
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
      state.logoutHandling = true;
    },
    LOG_OUT_SUCCESS: (state) => {
      state.logoutHandling = false;
      state.logoutDone = true;
      state.loginDone = false;
      state.loginCheckSuccess = false;
    },
    LOG_OUT_FAILURE: (state, action) => {
      // api 통신시 에러와 에러메시지 받음. default 처리
      state.logoutHandling = false;
      state.logoutError = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
    REGISTER_REQUEST: (state) => {
      state.registerHandling = true;
    },
    REGISTER_SUCCESS: (state) => {
      state.registerHandling = false;
      state.registerError = null;
    },
    REGISTER_FAILURE: (state, action) => {
      // api 통신시 에러와 에러메시지 받음. default 처리
      state.registerHandling = false;
      state.registerError = action.payload.message;
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  LOG_IN_CHECK_REQUEST,
  LOG_IN_CHECK_FAILURE,
  LOG_IN_CHECK_SUCCESS,
  LOG_IN_REQUEST,
  LOG_IN_SUCCESS,
  LOG_IN_FAILURE,
  LOG_OUT_REQUEST,
  LOG_OUT_SUCCESS,
  LOG_OUT_FAILURE,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
} = loginSlice.actions;

export const loginReducer = loginSlice.reducer;

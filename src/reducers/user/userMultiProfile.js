import { createSlice } from '@reduxjs/toolkit';

export const userMultiProfileSlice = createSlice({
  name: 'user/multiProfile',
  initialState: {
    loading: false,
    error: null,
    success: false,
    list: [],
  },
  reducers: {
    SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST: () => {},
    SET_USER_MULTI_PROFILE_STATUS_TRUE: (state) => {
      state.success = true;
    },
    SET_USER_MULTI_PROFILE_STATUS_FALSE: (state) => {
      state.success = false;
    },
    GET_USER_MULTI_PROFILE_INFO_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_MULTI_PROFILE_INFO_SUCCESS: (state) => {
      state.loading = false;
    },
    GET_USER_MULTI_PROFILE_INFO_FAILURE: (state) => {
      state.loading = false;
    },
    ADD_USER_MULTI_PROFILE_INFO_REQUEST: (state) => {
      state.loading = true;
    },
    ADD_USER_MULTI_PROFILE_INFO_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list.push(action.payload); // push new profile to the list
    },
    ADD_USER_MULTI_PROFILE_INFO_FAILURE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
      state.success = false;
    },
    RESET_ADD_USER_MULTI_PROFILE_INFO_SUCCESS: (state) => {
      state.success = false;
    },
    REMOVE_USER_MULTI_PROFILE_INFO_REQUEST: (state) => {
      state.loading = true;
    },
    REMOVE_USER_MULTI_PROFILE_INFO_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.list = state.list.filter(
        (profile) => profile.userId !== action.payload.userId // remove profile from the list
      );
    },
    REMOVE_USER_MULTI_PROFILE_INFO_FAILURE: (state, action) => {
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
  SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST,
  SET_USER_MULTI_PROFILE_STATUS_TRUE,
  SET_USER_MULTI_PROFILE_STATUS_FALSE,
  GET_USER_MULTI_PROFILE_INFO_REQUEST,
  GET_USER_MULTI_PROFILE_INFO_SUCCESS,
  GET_USER_MULTI_PROFILE_INFO_FAILURE,
  ADD_USER_MULTI_PROFILE_INFO_REQUEST,
  ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
  RESET_ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
  ADD_USER_MULTI_PROFILE_INFO_FAILURE,
  REMOVE_USER_MULTI_PROFILE_INFO_REQUEST,
  REMOVE_USER_MULTI_PROFILE_INFO_SUCCESS,
  REMOVE_USER_MULTI_PROFILE_INFO_FAILURE,
} = userMultiProfileSlice.actions;

export const userMultiProfileReducer = userMultiProfileSlice.reducer;

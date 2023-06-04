import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    id: null,
    profile: {
      loading: false,
      error: null,
      success: null,
      info: null, // might be object
    },
    multiProfile: {
      loading: false,
      error: null,
      success: false,
      list: [],
    },
  },
  reducers: {
    SET_USER_PROFILE_INFO: (state, action) => {
      state.profile.info = {
        id: action.payload.id,
      };
    },
    REMOVE_USER_PROFILE_INFO: (state) => {
      state.profile.info = null;
    },
    SET_USER_MULTI_PROFILE_STATUS: (state) => {
      state.multiProfile.success = false;
    },
    GET_USER_MULTI_PROFILE_INFO_REQUEST: (state) => {
      state.multiProfile.loading = true;
    },
    GET_USER_MULTI_PROFILE_INFO_SUCCESS: (state) => {
      state.multiProfile.loading = false;
    },
    GET_USER_MULTI_PROFILE_INFO_FAILURE: (state) => {
      console.log('ADD_USER_MULTI_PROFILE_INFO_REQUEST failure....');
      state.multiProfile.loading = false;
    },
    ADD_USER_MULTI_PROFILE_INFO_REQUEST: (state) => {
      console.log('ADD_USER_MULTI_PROFILE_INFO_REQUEST reqeust....');
      state.multiProfile.loading = true;
    },
    ADD_USER_MULTI_PROFILE_INFO_SUCCESS: (state, action) => {
      console.log('ADD_USER_MULTI_PROFILE_INFO_REQUEST success....');
      state.multiProfile.loading = false;
      state.multiProfile.success = true;
      state.multiProfile.list.push(action.payload.profile); // push new profile to the list
    },
    RESET_ADD_USER_MULTI_PROFILE_INFO_SUCCESS: (state) => {
      state.multiProfile.success = false;
    },
    ADD_USER_MULTI_PROFILE_INFO_FAILURE: (state, action) => {
      state.multiProfile.loading = false;
      state.multiProfile.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
      state.multiProfile.success = false;
    },
    REMOVE_USER_MULTI_PROFILE_INFO_REQUEST: (state) => {
      state.multiProfile.loading = true;
    },
    REMOVE_USER_MULTI_PROFILE_INFO_SUCCESS: (state, action) => {
      state.multiProfile.loading = false;
      state.multiProfile.success = true;
      state.multiProfile.list = state.multiProfile.list.filter(
        (profile) => profile.id !== action.payload.id // remove profile from the list
      );
    },
    REMOVE_USER_MULTI_PROFILE_INFO_FAILURE: (state, action) => {
      state.multiProfile.loading = false;
      state.multiProfile.success = false;
      state.multiProfile.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

// Action creators are generated for each case reducer function
export const {
  SET_USER_PROFILE_INFO,
  REMOVE_USER_PROFILE_INFO,
  SET_USER_MULTI_PROFILE_STATUS,
  ADD_USER_MULTI_PROFILE_INFO_REQUEST,
  ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
  ADD_USER_MULTI_PROFILE_INFO_FAILURE,
  GET_USER_MULTI_PROFILE_INFO_REQUEST,
  GET_USER_MULTI_PROFILE_INFO_SUCCESS,
  GET_USER_MULTI_PROFILE_INFO_FAILURE,
  RESET_ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
} = userSlice.actions;

export const loginReducer = userSlice.reducer;

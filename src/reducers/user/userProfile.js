import { createSlice } from '@reduxjs/toolkit';

export const userProfileSlice = createSlice({
  name: 'user/profile',
  initialState: {
    loading: false,
    error: null,
    success: null,
    info: {
      id: '아이디 테스트',
      name: '홍길동', // when user login, id and profile will be served by server at first.
      description: '설명란 설명란 설명란 장영란',
      avatar: null,
      avatars: [], // might be object
    },
  },
  reducers: {
    SET_USER_PROFILE_REQUEST: (state) => {
      state.loading = true;
    },
    SET_USER_PROFILE_SUCCESS: (state, action) => {
      state.loading = false;
      state.success = true;
      state.info = {
        id: action.payload.id,
        name: action.payload.name,
        uploadDate: action.payload.uploadDate,
        description: action.payload.description,
        avatar: action.payload.avatar, // action.payload.mainAvatar
        avatars: action.payload.avatars, // action.payload.avatars;
      };
    },
    SET_USER_PROFILE_FAILURE: (state, action) => {
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
  SET_USER_PROFILE_REQUEST,
  SET_USER_PROFILE_SUCCESS,
  SET_USER_PROFILE_FAILURE,
} = userProfileSlice.actions;

export const userProfileReducer = userProfileSlice.reducer;

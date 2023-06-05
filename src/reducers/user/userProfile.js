import { createSlice } from '@reduxjs/toolkit';

export const userProfileSlice = createSlice({
  name: 'user/profile',
  initialState: {
    id: null,
    loading: false,
    error: null,
    success: null,
    info: {
      name: '홍길동', // when user login, id and profile will be served by server at first.
      description: '설명란 설명란 설명란 장영란',
      mainAvatar: null,
      avatars: [],
    }, // might be object
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
  },
});

// Action creators are generated for each case reducer function
export const { SET_USER_PROFILE_INFO, REMOVE_USER_PROFILE_INFO } =
  userProfileSlice.actions;

export const userProfileReducer = userProfileSlice.reducer;

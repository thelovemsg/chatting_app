import { createSlice } from '@reduxjs/toolkit';

export const userBookmarkFriendsSlice = createSlice({
  name: 'user/bookmark',
  initialState: {
    loading: false,
    error: null,
    success: false,
    list: [],
  },
  reducers: {
    GET_USER_BOOKMARK_FRIENDS_REQUEST: (state) => {
      state.loading = true;
    },
    GET_USER_BOOKMARK_FRIENDS_SUCCESS: (state, action) => {
      state.loading = false;
      state.list = action.payload.bookmarks;
    },
    GET_USER_BOOKMARK_FRIENDS_FAILURE: (state, action) => {
      state.loading = false;
      state.error = {
        code: action.payload.code,
        message: action.payload.message,
      };
    },
  },
});

export const {
  GET_USER_BOOKMARK_FRIENDS_REQUEST,
  GET_USER_BOOKMARK_FRIENDS_SUCCESS,
  GET_USER_BOOKMARK_FRIENDS_FAILURE,
} = userBookmarkFriendsSlice.actions;

export const userBookmarkFriendsReducer = userBookmarkFriendsSlice.reducer;

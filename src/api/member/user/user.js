import axios from 'axios';

export const getFriends = async (data) => axios.post('getFriends', data);

export const getUpdatedFriends = async (data) =>
  axios.post('getUpdatedFriends', data);

export const getBirthdayFriends = async (data) =>
  axios.post('getBirthdayFriends', data);

export const getBookmarkFriends = async (data) =>
  axios.post('getBookbmarkFriends', data);

export const addMultipleProfileApi = async (data) =>
  axios.post('addNewMultiProfile', data);

export const getMultiProfile = async (data) =>
  axios.post('getMultipleProfile', data);

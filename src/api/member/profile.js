import axios from 'axios';

export const addMultipleProfileApi = async (data) =>
  axios.post('addNewMultiProfile', data);

export const getMultiProfile = async (data) =>
  axios.post('getMultipleProfile', data);

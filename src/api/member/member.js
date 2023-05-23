import axios from 'axios';

export const memberJoinApi = async (user) => {
  console.log('memberJoinApi :: ', user);
  let response;
  try {
    response = await axios.post('memberSave', user);
  } catch (error) {
    console.log(error);
  }
  return response;
};

export const memberLoginApi = async (data) => {
  let response;
  try {
    response = await axios.post('login', data);
  } catch (error) {
    console.log(error);
  }
  return response;
};

export const memberLogoutApi = async () => {
  let response;
  try {
    response = await axios.get('logout');
  } catch (error) {
    console.log(error);
  }
  return response;
};

export const test = async (love) => {
  console.log(love);
};

export const memberLoginCheckApi = async () => {
  let response;
  try {
    response = await axios.get('loginCheck');
  } catch (error) {
    console.log(error);
  }
  return response;
};

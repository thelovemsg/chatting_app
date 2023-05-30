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

export const memberLoginApi = async (data) => axios.post('login', data);

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

export const memberLoginCheckApi = async () => axios.get('loginCheck');

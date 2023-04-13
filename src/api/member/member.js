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

export const test = async (love) => {
  console.log(love);
};

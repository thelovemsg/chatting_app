// api.js
import axios from 'axios';
import BASE_URL from './EnvUtils';

const findMemberByTarget = async (name, value) => {
  const response = await axios.post(`${BASE_URL}/findMemberByTarget`, {
    name,
    value,
  });
  console.log(response);
  return response?.data;
};

const api = {
  findMemberByTarget,
};

export default api;

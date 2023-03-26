// api.js
import { BASE_URL } from "./EnvUtils";
import axios from "axios";

export const findMemberByTarget = async (name, value) => {
  const response = await axios.post(`${BASE_URL}/findMemberByTarget`, {
    name,
    value,
  });
  return response?.data;
};

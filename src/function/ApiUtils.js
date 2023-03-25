// api.js

import axios from "axios";
const isLocal = process.env.REACT_APP_ENVIRONMENT === "local";
const baseUrl = isLocal ? "http://localhost:9090" : "https://example.com";

export const findMemberByTarget = async (name, value) => {
  const response = await axios.post(`${baseUrl}/findMemberByTarget`, {
    name,
    value,
  });
  return response?.data;
};

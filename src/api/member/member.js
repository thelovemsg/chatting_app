import axios from "axios";

export const memberJoinApi = async (user) => {
  try {
    const response = await axios.post("http://localhost:9090/memberSave", user);
    return response.data;
  } catch (error) {
    throw error;
  }
};

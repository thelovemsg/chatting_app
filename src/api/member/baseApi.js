import axios from 'axios';

const baseURL = process.env.REACT_APP_API_LOCAL_URL;

const instance = axios.create({
  baseURL,
});

instance.interceptors.request.use(
  (config) => {
    const targetConfig = config;
    const token = localStorage.getItem('token'); // or get the token from cookie
    if (token) {
      targetConfig.headers.Authorization = `Bearer ${token}`;
    }
    // You can also add common headers here
    targetConfig.headers['Content-Type'] = 'application/json';
    return config;
  },
  (error) =>
    // Do something with request error
    Promise.reject(error)
);

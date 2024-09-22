import axios from 'axios';

export default axios.create({
  baseURL: import.meta.env.VITE_REST_API_BASE_URL
});
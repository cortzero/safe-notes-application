import axios from "../config/AxiosConfig";

export const getAllCategories = () => axios.get('/api/categories')
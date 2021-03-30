import Axios from "axios";

import { Title } from "../types/Title";

const titles = {
  list: async (): Promise<Title[]> => {
    const response = await Axios.get<Title[]>("/api/titles");
    return response.data;
  },

  create: async (title: Title): Promise<Title> => {
    const response = await Axios.post<Title>("/api/titles", title);
    return response.data;
  },

  retrieve: async (key: string): Promise<Title> => {
    const response = await Axios.get<Title>(`/api/titles/${key}`);
    return response.data;
  },

  update: async (title: Title): Promise<Title> => {
    await Axios.put(`/api/titles/${title.key}`, title);
    return title;
  },

  delete: async (title: Title): Promise<string | undefined> => {
    await Axios.delete(`/api/titles/${title.key}`);
    return title.key;
  },
};

export default titles;

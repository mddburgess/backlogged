import Axios from "axios";
import { Title } from "../types/Title";

const listTitles = async (): Promise<Title[]> => {
  const response = await Axios.get<Title[]>("/api/titles");
  return response.data;
};

const createTitle = async (title: Title): Promise<Title> => {
  const response = await Axios.post<Title>("/api/titles", title);
  return response.data;
};

const retrieveTitle = async (key: string): Promise<Title> => {
  const response = await Axios.get<Title>(`/api/titles/${key}`);
  return response.data;
};

const updateTitle = async (title: Title): Promise<Title> => {
  await Axios.put(`/api/titles/${title.key}`, title);
  return title;
};

const deleteTitle = async (title: Title): Promise<string | undefined> => {
  await Axios.delete(`/api/titles/${title.key}`);
  return title.key;
};

export const titles = {
  list: listTitles,
  create: createTitle,
  retrieve: retrieveTitle,
  update: updateTitle,
  delete: deleteTitle,
};

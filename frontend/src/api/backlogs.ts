import Axios from "axios";
import { Backlog } from "../types/Backlog";
import { Title } from "../types/Title";

const listBacklogs = async (): Promise<Backlog[]> => {
  const response = await Axios.get<Backlog[]>("/api/backlogs");
  return response.data;
};

const createBacklog = async (title: Title): Promise<Backlog> => {
  const request = {
    title: {
      key: title.key
    }
  };
  const response = await Axios.post<Backlog>("/api/backlogs", request);
  return response.data;
};

const deleteBacklog = async (backlog: Backlog): Promise<string | undefined> => {
  await Axios.delete(`/api/backlogs/${backlog.key}`);
  return backlog.key;
};

export const backlogs = {
  list: listBacklogs,
  create: createBacklog,
  delete: deleteBacklog
};

import Axios from "axios";

import { Backlog } from "../types/Backlog";
import { Title } from "../types/Title";

const backlogs = {
  list: async (): Promise<Backlog[]> => {
    const response = await Axios.get<Backlog[]>("/api/backlogs");
    return response.data;
  },

  create: async (title: Title): Promise<Backlog> => {
    const request = {
      title: {
        key: title.key,
      },
    };
    const response = await Axios.post<Backlog>("/api/backlogs", request);
    return response.data;
  },

  delete: async (backlog: Backlog): Promise<string | undefined> => {
    await Axios.delete(`/api/backlogs/${backlog.key}`);
    return backlog.key;
  },
};

export default backlogs;

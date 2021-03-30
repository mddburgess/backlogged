import Axios from "axios";

import { Activity } from "../types/Activity";

const activities = {
  list: async (): Promise<Activity[]> => {
    const response = await Axios.get<Activity[]>("/api/activities");
    return response.data;
  },
};

export default activities;

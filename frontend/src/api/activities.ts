import { Activity } from "../types/Activity";
import Axios from "axios";

const activities = {
  list: async (): Promise<Activity[]> => {
    const response = await Axios.get<Activity[]>("/api/activities");
    return response.data;
  },
};

export default activities;

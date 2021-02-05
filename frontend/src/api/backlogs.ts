import Axios from "axios";
import {Backlog} from "../types/Backlog";

const listBacklogs = async () => {
    const response = await Axios.get<Backlog[]>('/api/backlogs');
    return response.data;
}

export const backlogs = {
    list: listBacklogs
};

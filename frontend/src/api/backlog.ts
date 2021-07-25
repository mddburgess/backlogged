import axios from "axios";
import {BacklogItem} from "types/BacklogItem";

const backlog = {

    list: async (): Promise<BacklogItem[]> => {
        const response = await axios.get<BacklogItem[]>("/api/backlog");
        return response.data;
    },

    create: async (backlogItem: BacklogItem): Promise<BacklogItem> => {
        const response = await axios.post<BacklogItem>("/api/backlog", backlogItem);
        return response.data;
    },

    delete: async (backlogItem: BacklogItem): Promise<number | undefined> => {
        await axios.delete(`/api/backlog/${backlogItem.id}`);
        return backlogItem.id;
    }
}

export default backlog;

import Axios from "axios";
import {Title} from "../types/Title";

const listTitles = async () => {
    const response = await Axios.get<Title[]>('/api/titles');
    return response.data;
}

const createTitle = async (title: Title) => {
    const response = await Axios.post<Title>('/api/titles', title);
    return response.data;
}

const retrieveTitle: (key: string) => Promise<Title> = async (key: string) => {
    const response = await Axios.get<Title>(`/api/titles/${key}`);
    return response.data;
}

const updateTitle = async (title: Title) => {
    await Axios.put(`/api/titles/${title.key}`, title);
    return title;
}

const deleteTitle = async (title: Title) => {
    await Axios.delete(`/api/titles/${title.key}`);
    return title.key;
}

export const titles = {
    list: listTitles,
    create: createTitle,
    retrieve: retrieveTitle,
    update: updateTitle,
    delete: deleteTitle
};

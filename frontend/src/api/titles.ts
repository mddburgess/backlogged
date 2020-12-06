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

const retrieveTitle: (token: string) => Promise<Title> = async (token: string) => {
    const response = await Axios.get<Title>(`/api/titles/${token}`);
    return response.data;
}

const updateTitle = async (title: Title) => {
    await Axios.put(`/api/titles/${title.token}`, title);
    return title;
}

const deleteTitle = async (title: Title) => {
    await Axios.delete(`/api/titles/${title.token}`);
    return title.token;
}

export const titles = {
    list: listTitles,
    create: createTitle,
    retrieve: retrieveTitle,
    update: updateTitle,
    delete: deleteTitle
};

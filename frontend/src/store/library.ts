import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import Axios from 'axios';
import {Title} from "../types/Title";

export const listTitles = createAsyncThunk(
    'library/listTitles',
    async () => {
        const response = await Axios.get('/api/titles');
        return response.data;
    }
);

export const addTitle = createAsyncThunk(
    'library/addTitle',
    async (title: Title) => {
        const response = await Axios.post('/api/titles', title);
        return response.data;
    }
);

export const updateTitle = createAsyncThunk(
    'library/updateTitle',
    async (title: Title) => {
        await Axios.put(`/api/titles/${title.token}`, title);
        return title;
    }
);

export const deleteTitle = createAsyncThunk(
    'library/deleteTitle',
    async (title: Title) => {
        await Axios.delete(`/api/titles/${title.token}`);
        return title.token;
    }
);

interface LibraryState {
    data: Title[];
}

const initialState: LibraryState = {
    data: []
}

const librarySlice = createSlice({
    name: 'library',
    initialState,
    reducers: {},
    extraReducers: builder => {
        builder.addCase(listTitles.fulfilled, (state, action) => {
            state.data = action.payload;
        });
        builder.addCase(addTitle.fulfilled, (state, action) => {
            state.data.push(action.payload);
        });
        builder.addCase(updateTitle.fulfilled, (state, action) => {
            state.data = state.data.map(title => title.token === action.payload.token ? action.payload : title);
        });
        builder.addCase(deleteTitle.fulfilled, (state, action) => {
            state.data = state.data.filter(title => title.token !== action.payload);
        });
    }
});

export default librarySlice.reducer;

import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import Axios from 'axios';

export const listTitles = createAsyncThunk(
    'library/listTitles',
    async () => {
        const response = await Axios.get('/api/titles');
        return response.data;
    }
);

export const addTitle = createAsyncThunk(
    'library/addTitle',
    async (title) => {
        const response = await Axios.post('/api/titles', title);
        return response.data;
    }
);

export const updateTitle = createAsyncThunk(
    'library/updateTitle',
    async (title) => {
        await Axios.put(`/api/titles/${title.token}`, title);
        return title;
    }
);

export const deleteTitle = createAsyncThunk(
    'library/deleteTitle',
    async (title) => {
        await Axios.delete(`/api/titles/${title.token}`);
        return title.token;
    }
);

const librarySlice = createSlice({
    name: 'library',
    initialState: {
        data: []
    },
    reducers: {},
    extraReducers: {
        [listTitles.fulfilled]: (state, action) => {
            state.data = action.payload;
        },
        [addTitle.fulfilled]: (state, action) => {
            state.data.push(action.payload);
        },
        [updateTitle.fulfilled]: (state, action) => {
            state.data = state.data.map(title => title.token === action.payload.token ? action.payload : title);
        },
        [deleteTitle.fulfilled]: (state, action) => {
            state.data = state.data.filter(title => title.token !== action.payload);
        }
    }
});

export default librarySlice.reducer;

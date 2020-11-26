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
        }
    }
});

export default librarySlice.reducer;

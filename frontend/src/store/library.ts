import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {Title} from "../types/Title";
import {api} from "../api";

export const listTitles = createAsyncThunk(
    'library/listTitles',
    async () => await api.titles.list()
);

export const createTitle = createAsyncThunk(
    'library/createTitle',
    async (title: Title) => await api.titles.create(title)
);

export const updateTitle = createAsyncThunk(
    'library/updateTitle',
    async (title: Title) => await api.titles.update(title)
);

export const deleteTitle = createAsyncThunk(
    'library/deleteTitle',
    async (title: Title) => await api.titles.delete(title)
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
        builder.addCase(createTitle.fulfilled, (state, action) => {
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

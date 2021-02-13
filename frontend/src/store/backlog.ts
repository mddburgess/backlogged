import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {Backlog} from "../types/Backlog";
import {api} from "../api";
import {Title} from "../types/Title";

export const listBacklog = createAsyncThunk(
    'backlog/listEntries',
    async () => await api.backlogs.list()
);

export const createBacklog = createAsyncThunk(
    'backlog/createEntry',
    async (title: Title) => await api.backlogs.create(title)
)

interface BacklogState {
    data: Backlog[];
}

const initialState: BacklogState = {
    data: []
}

const backlogSlice = createSlice({
    name: 'backlog',
    initialState,
    reducers: {},
    extraReducers: builder => {
        builder.addCase(listBacklog.fulfilled, (state, action) => {
            state.data = action.payload;
        });
        builder.addCase(createBacklog.fulfilled, (state, action) => {
            state.data.push(action.payload);
        });
    }
});

export default backlogSlice.reducer;

import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {Backlog} from "../types/Backlog";
import {api} from "../api";

export const listBacklog = createAsyncThunk(
    'backlog/listEntries',
    async () => await api.backlogs.list()
);

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
    }
});

export default backlogSlice.reducer;

import backlog from "api/backlog";
import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {BacklogItem} from "types/BacklogItem";

export const actions = {
    list: createAsyncThunk("backlog/list", backlog.list),
    create: createAsyncThunk("backlog/create", backlog.create),
    delete: createAsyncThunk("backlog/delete", backlog.delete)
}

const slice = createSlice({
    name: "backlog",
    initialState: {
        data: [] as BacklogItem[]
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(actions.list.fulfilled, (state, action) => {
            state.data = action.payload
        });
        builder.addCase(actions.create.fulfilled, (state, action) => {
            state.data.push(action.payload);
        });
        builder.addCase(actions.delete.fulfilled, (state, action) => {
            state.data = state.data.filter(backlog => backlog.id !== action.payload);
        });
    }
});

export default slice.reducer;

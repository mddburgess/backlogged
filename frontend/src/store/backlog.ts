import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

import { api } from "../api";
import { Backlog } from "../types/Backlog";

export const actions = {
  list: createAsyncThunk("backlog/list", api.backlogs.list),
  create: createAsyncThunk("backlog/create", api.backlogs.create),
  delete: createAsyncThunk("backlog/delete", api.backlogs.delete),
};

const backlogSlice = createSlice({
  name: "backlog",
  initialState: {
    data: [] as Backlog[],
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(actions.list.fulfilled, (state, action) => {
      state.data = action.payload;
    });
    builder.addCase(actions.create.fulfilled, (state, action) => {
      state.data.push(action.payload);
    });
    builder.addCase(actions.delete.fulfilled, (state, action) => {
      state.data = state.data.filter((backlog) => backlog.key !== action.payload);
    });
  },
});

export default backlogSlice.reducer;

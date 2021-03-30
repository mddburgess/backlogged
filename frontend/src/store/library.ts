import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Title } from "../types/Title";
import { api } from "../api";

export const actions = {
  list: createAsyncThunk("titles/list", api.titles.list),
  create: createAsyncThunk("titles/create", api.titles.create),
  update: createAsyncThunk("titles/update", api.titles.update),
  delete: createAsyncThunk("titles/delete", api.titles.delete),
};

const librarySlice = createSlice({
  name: "library",
  initialState: {
    data: [] as Title[],
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(actions.list.fulfilled, (state, action) => {
      state.data = action.payload;
    });
    builder.addCase(actions.create.fulfilled, (state, action) => {
      state.data.push(action.payload);
    });
    builder.addCase(actions.update.fulfilled, (state, action) => {
      state.data = state.data.map((title) =>
        title.key === action.payload.key ? action.payload : title
      );
    });
    builder.addCase(actions.delete.fulfilled, (state, action) => {
      state.data = state.data.filter((title) => title.key !== action.payload);
    });
  },
});

export default librarySlice.reducer;

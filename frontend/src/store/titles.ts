import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

import { api } from "../api";
import { Title } from "../types/Title";

type State = {
  list: Title[];
  details?: Title;
};

const initialState: State = {
  list: [],
  details: undefined,
};

const thunkActions = {
  list: createAsyncThunk("titles/list", api.titles.list),
  create: createAsyncThunk("titles/create", api.titles.create),
  retrieve: createAsyncThunk("titles/retrieve", api.titles.retrieve),
  update: createAsyncThunk("titles/update", api.titles.update),
  delete: createAsyncThunk("titles/delete", api.titles.delete),
};

const titlesSlice = createSlice({
  name: "titles",
  initialState,
  reducers: {
    clearDetails: (state) => {
      state.details = undefined;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(thunkActions.list.fulfilled, (state, action) => {
      state.list = action.payload;
    });
    builder.addCase(thunkActions.create.fulfilled, (state, action) => {
      state.list.push(action.payload);
    });
    builder.addCase(thunkActions.retrieve.fulfilled, (state, action) => {
      state.details = action.payload;
    });
    builder.addCase(thunkActions.update.fulfilled, (state, action) => {
      state.list = state.list.map((title) =>
        title.key === action.payload.key ? action.payload : title
      );
    });
    builder.addCase(thunkActions.delete.fulfilled, (state, action) => {
      state.list = state.list.filter((title) => title.key !== action.payload);
    });
  },
});

export const actions = {
  ...thunkActions,
  ...titlesSlice.actions,
};

export default titlesSlice.reducer;

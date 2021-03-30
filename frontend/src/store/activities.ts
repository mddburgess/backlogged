import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { api } from "../api";
import { Activity } from "../types/Activity";

export const actions = {
  list: createAsyncThunk("activities/list", api.activities.list),
};

const activitySlice = createSlice({
  name: "activities",
  initialState: {
    data: [] as Activity[],
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(actions.list.fulfilled, (state, action) => {
      state.data = action.payload;
    });
  },
});

export default activitySlice.reducer;

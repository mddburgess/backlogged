import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { activities } from "../api/activities";
import { Activity } from "../types/Activity";

export const listActivities = createAsyncThunk("activities/list", () => activities.list());

interface ActivityState {
  data: Activity[];
}

const initialState: ActivityState = { data: [] };

const activitySlice = createSlice({
  name: "activities",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(listActivities.fulfilled, (state, action) => {
      state.data = action.payload;
    });
  },
});

export default activitySlice.reducer;

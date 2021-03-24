import { configureStore } from "@reduxjs/toolkit";
import { combineReducers } from "redux";
import library from "./library";
import backlog from "./backlog";
import activities from "./activities";

const store = configureStore({
  reducer: combineReducers({
    activities,
    backlog,
    library,
  }),
});

export type StoreState = ReturnType<typeof store.getState>;
export default store;

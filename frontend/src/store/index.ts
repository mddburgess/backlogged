import { configureStore } from "@reduxjs/toolkit";
import { combineReducers } from "redux";
import library from "./library";
import backlog from "./backlog";

const store = configureStore({
  reducer: combineReducers({
    library,
    backlog,
  }),
});

export type StoreState = ReturnType<typeof store.getState>;
export default store;
